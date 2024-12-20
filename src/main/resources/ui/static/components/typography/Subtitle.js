export default class Subtitle extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const content = this.innerText

    this.outerHTML = `
    <div class="ac-sub-title h-full justify-center flex flex-col">
      <h2 id="${id}" class="ac-sub-title text-xl font-bold">${content}</h2>
    </div>
    `
  }
}
