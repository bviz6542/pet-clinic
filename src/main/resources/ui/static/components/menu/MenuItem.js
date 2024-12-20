export default class MenuItem extends HTMLElement {
  constructor() {
    super();

    this.variants = {
      active: "bg-black text-white hover:cursor-pointer",
      inactive: "bg-white text-black hover:cursor-pointer hover:bg-gray transition duration-150"
    }
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const url = this.getAttribute('url')
    const text = this.textContent.trim()
    const isActive = this.getAttribute('is-active')

    this.outerHTML = `
    <a
      id="${id}"
      href="${url}"
      class="ac-menu-item block mb-1 w-full py-2 rounded-xl px-5
      ${isActive ? this.variants['active'] : this.variants['inactive']}"
    >
      ${text}
    </a>
    
    `
  }
}