/** @type {import('./tailwindcss').Config} */
module.exports = {
  content: [
    "./static/components/**/*.{html,js}",
    "./templates/response_fragments/**/*.{html,js}",
    "./templates/fragments/**/*.{html,js}",
    "./templates/layouts/**/*.{html,js}",
    "./templates/pages/**/*.{html,js}",
    "./templates/showcase/**/*.{html,js}",
  ],
  theme: {
    extend: {
      colors: {
        'primary': '#00ADFF',
        'secondary': '#C6E9FF',
        'black': '#000000',
        'gray': '#E6E6E6',
        'green': '#02B602',
        'yellow': '#FFDC02',
        'red': '#EE0023',
      }
    },
  },
  plugins: [],
}

