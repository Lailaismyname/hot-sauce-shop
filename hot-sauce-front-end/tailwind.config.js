/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    colors: {
      pepper: "#B22222",
      jalapeno: "#3F612D",
      base: "#FFFDED",
    },
    extend: {
      fontFamily: {
        bebas: ["'Bebas Neue'", "sans-serif"],
        fjalla: ["'Fjalla One'", "sans-serif"],
        "permanent-marker": ["'Permanent Marker'", "cursive"],
        "major-mono": ["Major Mono Display", "monospace"],
      },
      boxShadow: {
        "custom-shadow":
          "0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05)",
      },
    },
  },
  plugins: [],
};
