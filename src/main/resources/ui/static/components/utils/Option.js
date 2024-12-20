export default class Option extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const optionName = this.innerText
    const value = this.getAttribute('value')
    const onClick = this.getAttribute('@click')

    this.outerHTML = `
    <li
      @click="selectedValue = '${value}'; open = false; ${onClick}"
      class="ac-option cursor-pointer select-none hover:bg-gray px-5 py-2 hover:bg-secondary z-50"
    >
      ${optionName}
    </li>
    `
  }
}