export default class Button extends HTMLElement {
  constructor() {
    super();

    this.variants = {
      primary: "border-primary bg-primary hover:text-primary hover:bg-white",
      black: "border-black bg-black hover:text-black hover:bg-white",
      red: "border-red bg-red hover:text-red hover:bg-white",
      green: "border-green bg-green hover:text-green hover:bg-white",
    }
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const type = this.getAttribute('type')
    const content = this.innerHTML
    const color = this.getAttribute('color')
    const onClick = this.getAttribute("on-click")
    const isHxActionExist = this.hasAttribute('hx-action');
    const isFormExist = this.hasAttribute('form')

    this.outerHTML = `
    <button
      id="${id}"
      type="${type}"
      class="ac-button flex min-w-24 outline-none border-2 h-12 px-3 py-2 items-center justify-center text-white rounded-xl transition duration-150 ${this.variants[color]}"
      @click="${onClick}"
      ${isHxActionExist ? this.getAttribute('hx-action') : ''}
      ${isFormExist ? 'form=' + this.getAttribute('form') : ''}
    >
      ${content}
    </button>
    `
  }
}
