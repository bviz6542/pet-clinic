export default class Container extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const content = this.innerHTML

    this.outerHTML = `
    <div class="h-full pt-3">
      <div id="${id}" class="ac-container grid grid-rows-12 h-full">
        ${content}
      </div>
    </div>
    `
  }
}
