export default class Menu extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const content = this.innerHTML

    this.outerHTML = `
    <div id="${id}" class="ac-menu px-7 grid grid-rows-12 border-r-2 border-black h-full">
      ${content}
    </div>
    `
  }

}