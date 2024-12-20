export default class TableCell extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const col = this.getAttribute('col')
    const content = this.innerHTML

    this.outerHTML = `
    <div id="${id}" class="ac-td font-normal flex items-center overflow-x-auto text-sm py-5 mr-1 px-2 ${col}">
      ${content}
    </div>
    `
  }
}
