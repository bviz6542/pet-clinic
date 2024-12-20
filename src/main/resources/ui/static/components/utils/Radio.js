export default class Radio extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const name = this.getAttribute('name')
    const value = this.getAttribute('value')
    const label = this.getAttribute('label')

    this.outerHTML = `
            <div class="inline-flex items-center">
              <label class="relative flex items-center cursor-pointer" for="${id}">
                <input
                    id="${id}"
                    name="${name}"
                    value="${value}"
                    type="radio"
                    class="peer h-5 w-5 cursor-pointer appearance-none rounded-full border-2 transition-all border-secondary checked:border-primary"
                >
                <span
                    class="absolute bg-primary w-3 h-3 rounded-full opacity-0 peer-checked:opacity-100 transition-opacity duration-150 top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2"
                >
                </span>
              </label>
              <label
                  class="ml-2 text-black cursor-pointer text-sm"
                  for="${id}"
              >
                ${label}
              </label>
            </div>
    `
  }
}
