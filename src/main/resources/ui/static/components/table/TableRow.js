export default class TableRow extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const border = this.getAttribute('border')
    const content = this.innerHTML

    this.outerHTML = `
    <div id="${id}" class="ac-tr min-h-16 max-h-20 grid grid-cols-12 ${border}">
      ${content}
    </div>
    `
  }
}