export default class MenuCategory extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const title = this.getAttribute('title')
    const content = this.innerHTML

    this.outerHTML = `
    <div id="${id}" class="ac-menu-category mx-auto w-full flex flex-col">
      <div class="my-5">
        <div class="text-sm text-zinc-500">
          <span class="ml-2">${title}</span>
        </div>
        <ul class="mt-2">${content}</ul>
      </div>
    </div>
    `
  }
}