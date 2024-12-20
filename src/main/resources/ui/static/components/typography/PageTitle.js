export default class PageTitle extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const content = this.innerText

    this.outerHTML = `
    <div id="${id}" class="ac-page-title h-full justify-center flex flex-col">
      <h1 class="text-4xl font-bold text-black">${content}</h1>
    </div>
    `
  }
}
