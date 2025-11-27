import { createSlice } from "@reduxjs/toolkit";
import { loginUser, registerUser } from "./authThunks";
import { getToken, removeToken, setToken } from "../../utils/auth";

const initialState = {
  user: (() => {
    try {
      return JSON.parse(localStorage.getItem("user")) || null;
    } catch {
      return null;
    }
  })(),
  token: getToken(),
  isAuthenticated: !!getToken(),
  loading: false,
  error: null,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    logout(state) {
      state.user = null;
      state.token = null;
      state.isAuthenticated = false;
      removeToken();
      localStorage.removeItem("user");
    },
    clearError(state) {
      state.error = null;
    },
    //  ADD: Update user role after kitchen operations
    updateUserRole(state, action) {
      if (state.user) {
        state.user.role = action.payload;
        localStorage.setItem("user", JSON.stringify(state.user));
      }
    }
  },
  extraReducers: (builder) => {
    builder
      // REGISTER
      .addCase(registerUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(registerUser.fulfilled, (state, action) => {
        state.loading = false;
        console.log(" Registration response:", action.payload);
        
        // Handle response with both user and token
        if (action.payload.user && action.payload.token) {
          state.user = action.payload.user;
          state.token = action.payload.token;
          state.isAuthenticated = true;
          setToken(action.payload.token);
          localStorage.setItem("user", JSON.stringify(action.payload.user));
          console.log(" User registered with ID:", action.payload.user.id);
        } else {
          // Fallback for old response format
          state.user = action.payload;
          state.isAuthenticated = true;
          localStorage.setItem("user", JSON.stringify(action.payload));
          console.log(" Old response format, user ID:", action.payload.id);
        }
      })
      .addCase(registerUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })

      // LOGIN
      .addCase(loginUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.loading = false;
        state.token = action.payload.token;
        state.isAuthenticated = true;
        setToken(action.payload.token);

        // Handle user data from login response
        if (action.payload.user) {
          state.user = action.payload.user;
          localStorage.setItem("user", JSON.stringify(action.payload.user));
          console.log(" User logged in with ID:", action.payload.user.id);
        } else {
          // Fallback for old format
          const userInfo = { email: action.meta.arg.email };
          state.user = userInfo;
          localStorage.setItem("user", JSON.stringify(userInfo));
          console.log(" Old login format, no user ID");
        }
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        state.isAuthenticated = false;
      });
  },
});

export const { logout, clearError, updateUserRole } = authSlice.actions;
export default authSlice.reducer;
