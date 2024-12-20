export default class Textarea extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const name = this.getAttribute('name')
    const value = this.getAttribute('value')
    const label = this.getAttribute('label')
    const rows = this.hasAttribute('rows') ? this.getAttribute('rows') : '5'
    const errorClass = this.getAttribute('class')
    const errorElem = this.innerHTML
    const isRequired = this.hasAttribute('required')

    this.outerHTML = `
    <div class="w-full relative my-4">
      <textarea
        id="${id}"
        name="${name}"
        value="${value}"
        rows=${rows}
        ${isRequired ? 'required' : ''}
        class="ac-textarea peer w-full border-2 border-black rounded-xl px-5 resize-none py-2 text-black
        ${this.isError(errorClass) ? 'border-red text-red' : ''}"
      ></textarea>
      <label
        for="${id}"
        class="absolute absolute px-2 -translate-y-1/2 left-4 bg-white text-xs
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
