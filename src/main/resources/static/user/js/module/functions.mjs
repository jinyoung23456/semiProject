//<select>, text, value 을 할당하여 <option>을 추가하는 메소드
export function insertOption(select, text, value, isAbleFunction = true) {
    const option = document.createElement('option')
    let optionText = text
    if(!isAbleFunction && isAbleFunction !== undefined){
        option.disabled = 'disabled'
    }
    option.value = value
    option.innerText = optionText
    select.append(option)
}