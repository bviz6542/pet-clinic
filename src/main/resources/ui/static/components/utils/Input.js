export default class Input extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const name = this.getAttribute('name')
    const value = this.getAttribute('value')
    const label = this.getAttribute('label')
    const errorClass = this.getAttribute('class')
    const errorElem = this.innerHTML
    const isRequired = this.hasAttribute('required')
    const type = this.hasAttribute('type') ? this.getAttribute('type') : 'text'

    this.outerHTML = `
    <div class="w-full h-12 relative my-4">
      <input
        id="${id}"
        name="${name}"
        value="${value}"
        type=${type}
        ${isRequired ? 'required' : ''}
        class="ac-input peer px-5 w-full h-12 outline-none rounded-xl border-2 border-black text-black
        ${this.isError(errorClass) ? 'border-red text-red' : ''}"
      />
      <label
        for="${id}"
        class="absolute px-2 top-1/2 -translate-y-1/2 left-5 bg-white peer-focus:left-4 peer-focus:top-0 peer-focus:text-xs duration-150 peer-valid:left-4 peer-valid:top-0 peer-valid:text-xs
        ${this.isError(errorClass) ? 'text-red' : ''}"
      >
        ${label}
      </label>
      ${errorElem}
    </div>
    `
  }

  isError(err) {
    return err && err === "error"
  }
}
