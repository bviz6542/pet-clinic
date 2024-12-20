export default class SearchBar extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const placeholder = this.getAttribute('placeholder')
    const btnLabel = this.getAttribute('btn-label')
    const getUrl = this.getAttribute('get-url')
    const target = this.getAttribute('target')

    this.outerHTML = `
    <div class="ac-search-bar w-full h-12 flex items-center my-auto">
      <div class="mr-4 w-full h-full flex flex-col">
        <input
          id="${id}"
          name="${id}"
          type="search"
          placeholder="${placeholder}"
          class="outline-none bg-gray border-2 border-black rounded-xl px-5 py-2 w-full h-full"
          required
        />
      </div>
      <div class="h-full flex flex-col min-w-24">
        <button
          hx-include="#${id}"
          hx-get="${getUrl}"
          hx-target="${target}"
          hx-swap="outerHTML"
          class="outline-none border-2 border-black bg-black px-3 py-2 text-white rounded-xl hover:text-black hover:bg-white transition duration-150 h-full"
        >
          ${btnLabel}
        </button>
      </div>
    </div>
    `
  }
}