import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { loginUser } from "../features/user/userThunks";
import Input from "../components/common/Input";

export default function Login() {
  const dispatch = useDispatch();
  const { loading } = useSelector((state) => state.user);

  const [form, setForm] = useState({
    email: "",
    password: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(loginUser(form));
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-[#f7faf7] px-4">
      <form
        onSubmit={handleSubmit}
        className="bg-white p-8 rounded-2xl shadow-md w-full max-w-md border border-gray-200"
      >
        {/* Green tag like landing page */}
        <div className="inline-flex items-center gap-2 bg-[#e8f7ec] text-[#14833b] px-4 py-2 rounded-full text-sm font-medium mx-auto mb-6">
          🔒 Secure Login
        </div>

        <h2 className="text-3xl font-extrabold text-center text-gray-800 mb-6">
          Welcome Back
        </h2>

        <div className="space-y-4">
          <Input
            label="Email"
            value={form.email}
            onChange={(e) => setForm({ ...form, email: e.target.value })}
          />

          <Input
            label="Password"
            type="password"
            value={form.password}
            onChange={(e) => setForm({ ...form, password: e.target.value })}
          />
        </div>

        <button
          disabled={loading}
          className={`w-full mt-6 py-3 rounded-xl text-white font-semibold transition-all
            ${
              loading
                ? "bg-[#1fa74a]/40 cursor-not-allowed"
                : "bg-[#1fa74a] hover:bg-[#188a3c] shadow-sm hover:shadow-md"
            }
          `}
        >
          {loading ? "Logging in..." : "Login"}
        </button>

        <p className="text-center mt-4 text-sm text-gray-500">
          Forgot password?{" "}
          <span className="text-[#1fa74a] hover:underline cursor-pointer">
            Reset
          </span>
        </p>
      </form>
    </div>
  );
}
