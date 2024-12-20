export default class Checkbox extends HTMLElement {
  constructor() {
    super();

    this.variants = {
      primary: "border-secondary checked:bg-primary checked:border-primary",
      black: "border-gray checked:bg-black checked:border-black",
      red: "border-red checked:bg-red checked:border-red",
      green: "border-green checked:bg-green checked:border-green",
    }
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const name = this.getAttribute('name')
    let value = this.getAttribute('value')
    const color = this.hasAttribute('color')
        ? this.getAttribute('color')
        : 'primary'
    const label = this.getAttribute('label')

    if (value == null) {
      value = false
    }

    this.outerHTML = `
          <div class="inline-flex items-center">
            <label class="flex items-center cursor-pointer relative" for="${id}">
              <input
                  id="${id}"
                  name="${name}"
                  value="${value}"
                  type="checkbox"
                  class="peer h-5 w-5 cursor-pointer transition-all appearance-none border-2 rounded-md ${this.variants[color]}"
              />
              <input type="hidden" name="_${name}" value="on" />
              <span
                  class="absolute text-white opacity-0 peer-checked:opacity-100 top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2"
              >
                <svg
                    width="16"
                    height="16"
                    viewBox="0 0 16 16"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                    stroke="currentColor"
                >
                  <path
                      d="M3 8.92308L6.28079 12L13 5"
                      stroke-width="2"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                  />
                </svg>
              </span>
            </label>
            <label class="cursor-pointer ml-2 text-slate-600 text-sm" for="${id}">
              ${label}
            </label>
          </div>
    `
  }
}
