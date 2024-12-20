export default class TableHeaderGroup extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const content = this.innerHTML

    this.outerHTML = `
    <div id="${id}" class="ac-thead min-h-16 max-h-24">
      ${content}
    </div>
    `
  }
}
