import Menu from "./menu/Menu.js";
import MenuCategory from "./menu/MenuCategory.js";
import MenuItem from "./menu/MenuItem.js";
import TableCell from "./table/TableCell.js";
import TableRow from "./table/TableRow.js";
import PageTitle from "./typography/PageTitle.js";
import SearchBar from "./utils/SearchBar.js";
import TableHeaderCell from "./table/TableHeaderCell.js";
import TableHeaderGroup from "./table/TableHeaderGroup.js";
import TableBodyGroup from "./table/TableBodyGroup.js";
import Table from "./table/Table.js";
import Container from "./layout/Container.js";
import Button from "./utils/Button.js";
import Input from "./utils/Input.js";
import Select from "./utils/Select.js";
import Option from "./utils/Option.js";
import Modal from "./modal/Modal.js";
import ModalTitle from "./modal/ModalTitle.js";
import ModalBody from "./modal/ModalBody.js";
import ModalFooter from "./modal/ModalFooter.js";
import Subtitle from "./typography/Subtitle.js";
import Textarea from "./utils/Textarea.js";
import Checkbox from "./utils/Checkbox.js";
import Radio from "./utils/Radio.js";

// Menu Components
customElements.define("ac-menu", Menu)
customElements.define("ac-menu-category", MenuCategory)
customElements.define("ac-menu-item", MenuItem)

// Table Components
customElements.define("ac-table", Table)
customElements.define("ac-thead", TableHeaderGroup)
customElements.define("ac-tbody", TableBodyGroup)
customElements.define("ac-tr", TableRow)
customElements.define("ac-th", TableHeaderCell)
customElements.define("ac-td", TableCell)

// Typography Components
customElements.define("ac-page-title", PageTitle)
customElements.define("ac-sub-title", Subtitle)

// Modal Components
customElements.define("ac-modal", Modal)
customElements.define("ac-modal-title", ModalTitle)
customElements.define("ac-modal-body", ModalBody)
customElements.define("ac-modal-footer", ModalFooter)

// Utils Components
customElements.define("ac-search-bar", SearchBar)
customElements.define("ac-input", Input)
customElements.define("ac-button", Button)
customElements.define("ac-select", Select)
customElements.define("ac-option", Option)
customElements.define("ac-textarea", Textarea)
customElements.define("ac-checkbox", Checkbox)
customElements.define("ac-radio", Radio)

// Layout Components
customElements.define("ac-container", Container)
