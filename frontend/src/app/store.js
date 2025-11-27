import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../features/auth/authSlice";
import kitchenReducer from "../features/kitchen/kitchenSlice";
import inventoryReducer from "../features/inventory/inventorySlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    kitchen: kitchenReducer,
    inventory: inventoryReducer,
  },
});