export default class Table extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const content = this.innerHTML

    this.outerHTML = `
    <div id="${id}" class="ac-table flex flex-col border-collapse w-full h-full">
      ${content}
    </div>
    `
  }
}