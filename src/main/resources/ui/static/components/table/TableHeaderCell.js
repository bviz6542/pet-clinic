export default class TableHeaderCell extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const col = this.getAttribute('col')
    const content = this.innerHTML

    this.outerHTML = `
    <div id="${id}" class="ac-th text-center font-bold py-5 mr-1 border-b-2 border-primary ${col}">
      ${content}
    </div>
    `
  }
}