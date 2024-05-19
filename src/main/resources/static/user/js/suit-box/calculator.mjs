const $selectTags = document.querySelectorAll('.menu-select')

const $absCarbo = document.querySelector('#absCarbo');
const $absSugar = document.querySelector('#absSugar');
const $absProtein = document.querySelector('#absProtein');
const $absFat = document.querySelector('#absFat');
const $absSaturatedFat = document.querySelector('#absSaturatedFat');
const $absTransFat = document.querySelector('#absTransFat');
const $absCholesterol = document.querySelector('#absCholesterol');
const $absNatrium = document.querySelector('#absNatrium');
const $absCalory = document.querySelector('#absCalory');
const $totalPrice = document.querySelector('#totalPrice')

const $plusButton = document.querySelector('#plusButton')
const $minusButton = document.querySelector('#minusButton')
const $quantity = document.querySelector('#quantity')


// 현재 선택된 메뉴
let rice;
let main;
let side;
let kimchi;
// 모든 영양소가 합쳐진 객체
const combine = {}
// 카테고리별로 저장해두기
function sortCategory(selectMenu){
    switch(selectMenu.menuCategory){
        case('rice'): rice = selectMenu; break;
        case('main'): main = selectMenu; break;
        case('side'): side = selectMenu; break;
        case('kimchi'): kimchi = selectMenu; break;
    }
}
function totalPriceToHtml(totalPrice){
    $totalPrice.textContent = totalPrice.toLocaleString();
}
// 저장된 메뉴의 영양소를 모두 합쳐 combine 객체에 할당
function updateNutrion(selectMenu){
    for(const key in selectMenu){
        if(typeof selectMenu[key] === 'number' && key!='menuCode'){
            combine[key] = (rice?rice[key]:0) + (main?main[key]:0) + (side?side[key]:0) + (kimchi?kimchi[key]:0)
        }
    }
}
// 만들어진 영양소 합계 객체 테이블로 전달
function combineToAbsTable(combine) {
    $absCarbo.textContent = (combine.menuCarbo || 0) + 'g';
    $absSugar.textContent = (combine.menuSugar || 0) + 'g';
    $absProtein.textContent = (combine.menuProtein || 0) + 'g';
    $absFat.textContent = (combine.menuFat || 0) + 'g';
    $absSaturatedFat.textContent = (combine.menuSaturatedFat || 0) + 'g';
    $absTransFat.textContent = (combine.menuTransFat || 0) + 'g';
    $absCholesterol.textContent = (combine.menuCholesterol || 0) + 'mg';
    $absNatrium.textContent = (combine.menuNatrium || 0) + 'mg';
    $absCalory.textContent = (combine.menuCalory || 0) + 'Kcal';
    $totalPrice.textContent = ((7000 + (combine.menuExtracash || 0)) * $quantity.value).toLocaleString();
}

//이벤트핸들러
$selectTags.forEach((select)=>{
    select.addEventListener('change', ()=>{
        const selectMenu = JSON.parse(select.value)
        sortCategory(selectMenu)    //카테코리별로 저장
        updateNutrion(selectMenu)   //합계 최신화
        combineToAbsTable(combine)  //합계된 영양소, 총액 전달]
    })
})
$plusButton.addEventListener('click', ()=>{
    $quantity.value++
    $totalPrice.textContent = ((7000 + (combine.menuExtracash || 0)) * $quantity.value).toLocaleString();
})
$minusButton.addEventListener('click', ()=>{
    if($quantity.value>1){$quantity.value--}
    $totalPrice.textContent = ((7000 + (combine.menuExtracash || 0)) * $quantity.value).toLocaleString();
})
$quantity.addEventListener('change',()=>{
    if($quantity.value<1){$quantity.value = 1}
    $totalPrice.textContent = ((7000 + (combine.menuExtracash || 0)) * $quantity.value).toLocaleString();
})
