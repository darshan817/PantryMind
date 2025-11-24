export default function Input({ label, ...rest }) {
  return (
    <div className="flex flex-col mb-3">
      <label className="text-sm font-semibold mb-1">{label}</label>
      <input
        {...rest}
        className="border px-3 py-2 rounded-lg outline-none focus:ring-2 focus:ring-indigo-500"
      />
    </div>
  );
}
