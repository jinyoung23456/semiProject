const $bodyRows = document.querySelectorAll('.datatable tbody tr')

$bodyRows.forEach((row, index) => {
    if (row.className == 'view') {      //view row 기준
        row.addEventListener('mouseover', ()=>{     // hover 기능
            row.style.backgroundColor= "#F2F2F2"
        })
        row.addEventListener('mouseout', ()=>{
            row.style.backgroundColor= "white"
        })

        row.setAttribute('status', 'close')     // 닫혀있는 상태로 설정

        row.addEventListener('click', () => {
            // 닫혔을 때 클릭시 다음 view 가 나오기 전까지 set 열기
            if (row.getAttribute("status") === 'close') {
                row.style.fontWeight = "bold"
                row.style.backgroundColor = "#F2F2F2"
                row.setAttribute('status', 'open')
                let currentIndex = index + 1
                while($bodyRows[currentIndex].className == 'set'){
                    $bodyRows[currentIndex].style.display = 'table-row'
                    currentIndex++
                }
            } else if (row.getAttribute("status") === 'open') {
                // 열렸을 때 클릭시 다음 view 나오기 전까지 set 닫기
                row.style.fontWeight = 'normal'
                row.setAttribute('status', 'close')
                let currentIndex = index + 1
                while($bodyRows[currentIndex].className == 'set'){
                    $bodyRows[currentIndex].style.display = 'none'
                    currentIndex++
                }
            }
        })
    }
})