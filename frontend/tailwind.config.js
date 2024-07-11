/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        amazon: {
          orange: '#FF9900',
          black: '#000000',
          apricot: '#FEBD69',
          gunmetal: '#232F3E',
          charcoal: '#37475A',
          eerie: '#131A22',
        },
      },
    },

    /*extend: {
      backgroundImage: {
        'main-bg': "url('/main.png')",
      }
    },*/
  },

  plugins: [],
}
