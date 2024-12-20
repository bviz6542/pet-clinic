export default class ModalFooter extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const content = this.innerHTML

    this.outerHTML = `
    <div class="absolute bottom-0 flex w-full max-h-24 min-h-20 items-center justify-center bg-white rounded-b-xl border-t-2 border-gray">
      ${content}
    </div>
    
    `
  }

}
