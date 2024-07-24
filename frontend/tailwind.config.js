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

      backgroundImage: {
        'main-bg': "url('/public/capa-do-site.jpg')",
      },

      luizImage: {
        'masc-roupas': "url('/luiz.png')",
      },

    },
  },

  plugins: [],
}
