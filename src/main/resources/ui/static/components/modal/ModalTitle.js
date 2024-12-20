export default class ModalTitle extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const title = this.innerText

    this.outerHTML = `
    <div class="absolute top-0 flex w-full max-h-24 min-h-20 items-center justify-center bg-white rounded-t-xl border-b-2 border-gray">
      <h2 class="text-xl font-bold">${title}</h2>
    </div>
    `
  }

}
