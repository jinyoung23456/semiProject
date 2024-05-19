let riceSelect = document.querySelector('#rice')
let mainSelect = document.querySelector('#main')
let sideSelect = document.querySelector('#side')
let kimchiSelect = document.querySelector('#kimchi')

let targetSelect
import{insertOption} from '/user/js/module/functions.mjs'

function isMenuStatus (menu){
    if(menu.menuStatus == 'S'){
        return false;
    } else {
        return true;
    }
}

for(const menu of menuList){
    switch(menu.menuCategory){
        case('rice'): targetSelect = riceSelect; break;
        case('main'): targetSelect = mainSelect; break;
        case('side'): targetSelect = sideSelect; break;
        case('kimchi'): targetSelect = kimchiSelect; break;
    }
    const text = (menu.menuStatus == 'S'?' (품절)':'') + menu.menuName + (menu.menuExtracash?` (+${menu.menuExtracash})`:'')
    insertOption(targetSelect, text, JSON.stringify(menu), isMenuStatus(menu))
}