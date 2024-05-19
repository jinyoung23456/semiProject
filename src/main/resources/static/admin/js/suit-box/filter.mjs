
const $menuRows = document.querySelector('#menuRows')   // 메뉴가 담긴 테이블
const $criteria = document.querySelector('#criteria')   // 분류
const $value = document.querySelector('#value')         // 값
import { insertOption } from '/user/js/module/functions.mjs';

function createTableRow(menu) { //innerHtml 을 통해 추가할 tableRow 태그
    return `<tr class="menuTable" id="${menu.menuCode}">
        <td>${menu.menuCode}</td>
        <td>${menu.menuName}</td>
        <td>${menu.menuCategory}</td>
        <td>${menu.menuExtracash}</td>
        <td>${menu.menuStatus}</td>
        <td>${menu.menuCarbo}</td>
        <td>${menu.menuSugar}</td>
        <td>${menu.menuProtein}</td>
        <td>${menu.menuFat}</td>
        <td>${menu.menuSaturatedFat}</td>
        <td>${menu.menuTransFat}</td>
        <td>${menu.menuCholesterol}</td>
        <td>${menu.menuNatrium}</td>
        <td>${menu.menuCalory}</td>
    </tr>
    `
}
// 분류와 값에 따라 로우 리턴하는 함수
async function resultRows() {
    const criteria = $criteria.value
    const value = $value.value
    const selectCondition = { criteria, value }
    const json = JSON.stringify(selectCondition)
    const response = await fetch('/admin/suit-box/menu/fetch-view', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: json
    });
    const responseJson = await response.json();
    responseJson.forEach(menu => {
        const menuTr = createTableRow(menu)
        const nowHtml = $menuRows.innerHTML
        $menuRows.innerHTML = nowHtml + menuTr
    })
    const $menuTable = document.querySelectorAll('.menuTable')

    $menuTable.forEach((row) => {
        row.addEventListener('click', () => {
            location.href = "/admin/suit-box/menu/modify?menuCode=" + row.id;
        })
    })
}

//분류를 선택할 경우 해당 분류에 해당하는 value select option 할당하기
$criteria.addEventListener('change', () => {
    $value.innerHTML = ''
    const option = $criteria.value;
    switch (option) {
        case ('all'):
            insertOption($value, '=== 전체 ===', 'all');
            $menuRows.innerHTML = ''
            resultRows()
            break;
        case ('category'):
            insertOption($value, '=== 전체 ===', 'all');
            insertOption($value, '밥류', 'rice');
            insertOption($value, '메인', 'main');
            insertOption($value, '서브', 'side');
            insertOption($value, '김치', 'kimchi'); break;
        case ('status'):
            insertOption($value, '=== 전체 ===', 'all');
            insertOption($value, '판매중', 'Y');
            insertOption($value, '일시 중단', 'S');
            insertOption($value, '판매 중단', 'N'); break;
    }
})

$value.addEventListener('change', () => {
    $menuRows.innerHTML = ''
    console.log(`${$menuRows.innerHTML}`)
    resultRows()
})