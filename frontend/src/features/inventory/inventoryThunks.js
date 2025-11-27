import { setLoading, setItems, addItem, updateItem, removeItem, setError } from "./inventorySlice";

const API_BASE = "http://localhost:8080";

export const fetchInventoryItems = () => async (dispatch) => {
  dispatch(setLoading(true));
  try {
    console.log("GET request to fetch inventory items");
    const response = await fetch(`${API_BASE}/inventory-items`, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });
    console.log("GET response status:", response.status);
    if (response.ok) {
      const data = await response.json();
      console.log("GET response data:", data);
      dispatch(setItems(data));
    } else {
      console.log("GET request failed with status:", response.status);
      dispatch(setError("Failed to fetch inventory items"));
    }
  } catch (error) {
    console.log("GET request error:", error);
    dispatch(setError(error.message));
  } finally {
    dispatch(setLoading(false));
  }
};

export const createInventoryItem = (itemData) => async (dispatch) => {
  try {
    console.log("POST request to create inventory item with data:", itemData);
    const response = await fetch(`${API_BASE}/inventory-items`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify(itemData)
    });
    console.log("POST response status:", response.status);
    if (response.ok) {
      const data = await response.json();
      console.log("POST response data:", data);
      dispatch(addItem(data));
      return data;
    } else {
      console.log("POST request failed with status:", response.status);
      const errorText = await response.text();
      console.log("POST error response:", errorText);
      throw new Error("Failed to create item");
    }
  } catch (error) {
    console.log("POST request error:", error);
    dispatch(setError(error.message));
    throw error;
  }
};

export const deleteInventoryItem = (itemId) => async (dispatch) => {
  try {
    const response = await fetch(`${API_BASE}/inventory-items/${itemId}`, {
      method: 'DELETE',
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });
    if (response.ok) {
      dispatch(removeItem(itemId));
    } else {
      throw new Error("Failed to delete item");
    }
  } catch (error) {
    dispatch(setError(error.message));
  }
};