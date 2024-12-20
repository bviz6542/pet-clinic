export default class ModalBody extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const content = this.innerHTML

    this.outerHTML = `
    <div class="flex flex-col h-full w-full py-24 bg-white rounded-xl">
      <div class="flex flex-col h-full w-full overflow-auto px-5">
        ${content}
      </div>
    </div>
    `
  }

}
