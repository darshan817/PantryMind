import { createSlice } from "@reduxjs/toolkit";
import { loginUser, registerUser } from "./userThunks";
<<<<<<< HEAD
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

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    logout(state) {
      state.user = null;
      state.token = null;
      state.isAuthenticated = false;
      removeToken();
    },
    clearError(state) {
      state.error = null;
    }
=======

const userSlice = createSlice({
  name: "user",
  initialState: {
    user: null,
    loading: false,
    error: null,
  },
  reducers: {
    logout(state) {
      state.user = null;
      localStorage.removeItem("user");
    },
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487
  },
  extraReducers: (builder) => {
    builder

      // REGISTER
      .addCase(registerUser.pending, (state) => {
        state.loading = true;
<<<<<<< HEAD
        state.error = null;
=======
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487
      })
      .addCase(registerUser.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload;
        localStorage.setItem("user", JSON.stringify(action.payload));
      })
      .addCase(registerUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })

      // LOGIN
      .addCase(loginUser.pending, (state) => {
        state.loading = true;
<<<<<<< HEAD
        state.error = null;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.loading = false;
        state.token = action.payload.token;
        state.isAuthenticated = true;
        setToken(action.payload.token);

        const userInfo = { email: action.meta.arg.email }
        state.user = userInfo;
        localStorage.setItem("user", JSON.stringify(userInfo));
=======
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload;
        localStorage.setItem("user", JSON.stringify(action.payload));
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
<<<<<<< HEAD
        state.isAuthenticated = false;
=======
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487
      });
  },
});

<<<<<<< HEAD
export const { logout, clearError } = userSlice.actions;
=======
export const { logout } = userSlice.actions;
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487
export default userSlice.reducer;
