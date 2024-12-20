export default class Modal extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const openFlag = this.getAttribute('openFlag')
    const content = this.innerHTML

    this.outerHTML = `
    <div x-show="${openFlag}" class="absolute top-0 left-0 bg-zinc-800/20 w-full h-full z-50 flex backdrop-blur-md">
      <div @click.outside="${openFlag} = false" class="relative bg-white h-4/5 w-1/2 max-w-screen-md min-w-[420px] z-50 m-auto flex flex-col rounded-xl border-2 border-black">
        ${content}
      </div>
    </div>
    `
  }
}
