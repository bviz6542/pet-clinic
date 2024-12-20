export default class TableBodyGroup extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const content = this.innerHTML

    this.outerHTML = `
    <div id="${id}" class="ac-tbody flex flex-col overflow-auto w-full grow">
      ${content}
    </div>
    `
  }
}
