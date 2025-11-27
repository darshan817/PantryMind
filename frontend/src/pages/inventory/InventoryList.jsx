import { useState, useEffect, useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { fetchInventoryItems, deleteInventoryItem } from "../../features/inventory/inventoryThunks";
import { SearchInput } from "../../components/ui";

export default function InventoryList() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { items, loading, error } = useSelector((state) => state.inventory);
  const { user } = useSelector((state) => state.auth || {});
  const [filteredItems, setFilteredItems] = useState([]);

  useEffect(() => {
    dispatch(fetchInventoryItems());
  }, [dispatch]);

  useEffect(() => {
    setFilteredItems(items);
  }, [items]);

  const handleSearch = useCallback((searchTerm) => {
    if (!searchTerm.trim()) {
      setFilteredItems(items);
    } else {
      const filtered = items.filter(item =>
        item.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        item.description?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        item.categoryName?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        item.location?.toLowerCase().includes(searchTerm.toLowerCase())
      );
      setFilteredItems(filtered);
    }
  }, [items]);

  const handleDelete = async (itemId) => {
    if (window.confirm("Are you sure you want to delete this item?")) {
      dispatch(deleteInventoryItem(itemId));
    }
  };

  const getExpiryStatus = (expiryDate) => {
    if (!expiryDate) return "no-expiry";
    const today = new Date();
    const expiry = new Date(expiryDate);
    const diffDays = Math.ceil((expiry - today) / (1000 * 60 * 60 * 24));
    
    if (diffDays < 0) return "expired";
    if (diffDays <= 3) return "expiring-soon";
    if (diffDays <= 7) return "expiring-week";
    return "fresh";
  };

  const getExpiryColor = (status) => {
    switch (status) {
      case "expired": return "bg-red-100 text-red-800";
      case "expiring-soon": return "bg-orange-100 text-orange-800";
      case "expiring-week": return "bg-yellow-100 text-yellow-800";
      case "fresh": return "bg-green-100 text-green-800";
      default: return "bg-gray-100 text-gray-800";
    }
  };

  const menuItems = [
    {
      title: "Dashboard",
      description: "Back to dashboard",
      icon: (
        <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M3 7v10a2 2 0 002 2h14a2 2 0 002-2V9a2 2 0 00-2-2H5a2 2 0 00-2-2z" />
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8 5a2 2 0 012-2h4a2 2 0 012 2v6H8V5z" />
        </svg>
      ),
      color: "blue",
      onClick: () => navigate(user?.role === "ADMIN" ? "/admin" : "/member")
    },
    {
      title: "Add Item",
      description: "Add new inventory item",
      icon: (
        <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
        </svg>
      ),
      color: "green",
      onClick: () => navigate("/inventory/add")
    },
    {
      title: "Reports",
      description: "View inventory reports",
      icon: (
        <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
        </svg>
      ),
      color: "purple",
      onClick: () => {}
    }
  ];

  const getColorClasses = (color) => {
    const colors = {
      green: "bg-green-50 hover:bg-green-100 text-green-800 border-green-200",
      blue: "bg-blue-50 hover:bg-blue-100 text-blue-800 border-blue-200",
      purple: "bg-purple-50 hover:bg-purple-100 text-purple-800 border-purple-200",
      gray: "bg-gray-50 hover:bg-gray-100 text-gray-800 border-gray-200"
    };
    return colors[color] || colors.gray;
  };

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex">
        <div className="flex-1 flex items-center justify-center">
          <div className="text-lg">Loading inventory...</div>
        </div>
        <div className="w-80 p-6">
          <div className="bg-white rounded-lg shadow p-6 h-full">
            <h2 className="text-lg font-semibold text-gray-900 mb-6">Menu</h2>
            <div className="space-y-4">
              {menuItems.map((item, index) => (
                <div key={index} className={`p-4 rounded-lg border cursor-pointer transition-all duration-200 ${getColorClasses(item.color)}`}>
                  <div className="flex items-start gap-3">
                    <span className="text-current">{item.icon}</span>
                    <div>
                      <h3 className="font-semibold">{item.title}</h3>
                      <p className="text-sm opacity-75 mt-1">{item.description}</p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 flex">
      <div className="flex-1 p-6">
        <div className="bg-white rounded-lg shadow">
          {/* Header */}
          <div className="px-6 py-4 border-b border-gray-200">
            <div className="flex justify-between items-center">
              <h1 className="text-2xl font-bold text-gray-900">Inventory Items</h1>
              <button
                onClick={() => navigate("/inventory/add")}
                className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-lg font-medium"
              >
                Add Item
              </button>
            </div>
            
            {/* Search */}
            <div className="mt-4">
              <SearchInput
                placeholder="Search items, categories, locations..."
                onSearch={handleSearch}
                className="max-w-md"
              />
            </div>
          </div>

          {/* Error */}
          {error && (
            <div className="px-6 py-4 bg-red-50 border-b border-red-200">
              <p className="text-red-700">{error}</p>
            </div>
          )}

          {/* Items Table */}
          <div className="overflow-x-auto">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Item
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Category
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Quantity
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Expiry Date
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Location
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Actions
                  </th>
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {filteredItems.length === 0 ? (
                  <tr>
                    <td colSpan="6" className="px-6 py-8 text-center text-gray-500">
                      {items.length === 0 ? (
                        <>No inventory items found. <button onClick={() => navigate("/inventory/add")} className="text-green-600 hover:underline">Add your first item</button></>
                      ) : (
                        "No items match your search criteria."
                      )}
                    </td>
                  </tr>
                ) : (
                  filteredItems.map((item) => {
                    const expiryStatus = getExpiryStatus(item.expiryDate);
                    return (
                      <tr key={item.id} className="hover:bg-gray-50">
                        <td className="px-6 py-4 whitespace-nowrap">
                          <div>
                            <div className="text-sm font-medium text-gray-900">{item.name}</div>
                            {item.description && (
                              <div className="text-sm text-gray-500">{item.description}</div>
                            )}
                          </div>
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap">
                          <span className="text-sm text-gray-900">{item.categoryName || "N/A"}</span>
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap">
                          <span className="text-sm text-gray-900">{item.quantity} {item.unitName || ""}</span>
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap">
                          {item.expiryDate ? (
                            <span className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${getExpiryColor(expiryStatus)}`}>
                              {new Date(item.expiryDate).toLocaleDateString()}
                            </span>
                          ) : (
                            <span className="text-sm text-gray-500">No expiry</span>
                          )}
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                          {item.location || "N/A"}
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                          <button
                            onClick={() => navigate(`/inventory/edit/${item.id}`)}
                            className="text-indigo-600 hover:text-indigo-900 mr-4"
                          >
                            Edit
                          </button>
                          <button
                            onClick={() => handleDelete(item.id)}
                            className="text-red-600 hover:text-red-900"
                          >
                            Delete
                          </button>
                        </td>
                      </tr>
                    );
                  })
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>

      {/* Right Sidebar Menu */}
      <div className="w-80 p-6">
        <div className="bg-white rounded-lg shadow p-6 h-full">
          <h2 className="text-lg font-semibold text-gray-900 mb-6">Menu</h2>
          
          <div className="space-y-4">
            {menuItems.map((item, index) => (
              <div
                key={index}
                onClick={item.onClick}
                className={`p-4 rounded-lg border cursor-pointer transition-all duration-200 ${getColorClasses(item.color)}`}
              >
                <div className="flex items-start gap-3">
                  <span className="text-current">{item.icon}</span>
                  <div>
                    <h3 className="font-semibold">{item.title}</h3>
                    <p className="text-sm opacity-75 mt-1">{item.description}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}