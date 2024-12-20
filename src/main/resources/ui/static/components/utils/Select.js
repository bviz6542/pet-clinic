export default class Select extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const id = this.getAttribute('id')
    const name = this.getAttribute('name')
    const value = this.getAttribute('value')
    const label = this.getAttribute('label')
    const content = this.innerHTML
    const isRequired = this.hasAttribute('required')
    const errorClass = this.getAttribute('class')

    const tempDiv = document.createElement('div')
    tempDiv.innerHTML = content
    const errorMessage = tempDiv.querySelector('.err')
    const optionList = this.getOptionList(tempDiv)

    this.outerHTML = `
      <div
        class="ac-select relative w-full my-4 h-12"
        x-data="{
          open: false,
          ${value ? 'selectedValue:' + `'${value}'` : 'selectedValue:\'\''}
        }"
      >
        <div class="select-button relative w-full">
          <button
            type="button"
            @click="open = !open"
            class="w-full h-12 flex justify-between items-center border-2 border-black rounded-xl bg-white px-5
            ${this.isError(errorClass) ? 'border-red text-red' : ''}"
          >
            <span x-text="(selectedValue == '') ? '--- ${label} ---' : selectedValue"></span>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
              class="size-6"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="m19.5 8.25-7.5 7.5-7.5-7.5"
              />
            </svg>
            <div
              class="absolute top-0 -translate-y-1/2 text-xs bg-white px-2"
              x-show="selectedValue != ''"
            >
              ${label}
            </div>
          </button>
        </div>
        ${errorMessage ? errorMessage.outerHTML : ''}

        <ul
          x-show="open"
          class="options-list absolute z-10 bg-white py-2 rounded-xl border-2 border-black w-full mt-4"
          @click.outside="open = false"
        >
          ${optionList}
        </ul>
        
        <input type="hidden" id="${id}" name="${name}" :value="selectedValue" required=${isRequired} />
      </div>
    `
  }

  getOptionList(tempDiv) {
    const options = tempDiv.querySelectorAll('ac-option')
    const optionsArray = Array.from(options.values()).map(
        node => node.outerHTML)
    return optionsArray.join('')
  }

  isError(err) {
    return err && err === "error"
  }

}