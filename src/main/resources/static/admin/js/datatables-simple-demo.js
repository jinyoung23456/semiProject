window.addEventListener('DOMContentLoaded', event => {
    // 데이터 테이블 초기화
    const datatablesSimple = document.getElementById('datatablesSimple');
    let dataTable;
    if (datatablesSimple) {
        // 검색 기능을 활성화하여 테이블을 초기화합니다.
        dataTable = new simpleDatatables.DataTable(datatablesSimple, {
            searchable: true,
        });
    }

    // 검색 입력 필드, 돋보기 버튼, 카테고리 설정
    const searchInput = document.querySelector('.form-control');
    const searchButton = document.getElementById('btnNavbarSearch');
    const searchCondition = document.getElementById('categorySelect');

    // 검색 입력 필드에서 엔터 키 이벤트를 감지합니다.
    if (searchInput) {
        searchInput.addEventListener('keydown', function(event) {
            // 엔터 키를 누르면
            if (event.key === 'Enter') {
                // 기본 동작(폼 제출)을 막습니다.
                event.preventDefault();

                // 입력된 검색어를 가져와 데이터 테이블에 적용합니다.
                const searchValue = searchInput.value;
                const categoryValue = searchCondition.value;

                if (dataTable && (searchValue || categoryValue === '전체')) {
                    dataTable.search(searchValue);
                } else if (dataTable && categoryValue !== '전체') {
                    dataTable.search(categoryValue);
                }
            }
        });
    }

    // 돋보기 버튼 클릭 이벤트를 추가합니다.
    if (searchButton) {
        searchButton.addEventListener('click', function() {
            // 입력된 검색어와 선택된 카테고리를 가져와 데이터 테이블에 적용합니다.
            const searchValue = searchInput.value;
            const categoryValue = searchCondition.value;

            if (dataTable && (searchValue || categoryValue === '전체')) {
                dataTable.search(searchValue);
            } else if (dataTable && categoryValue !== '전체') {
                dataTable.search(categoryValue);
            }
        });
    }

    // 카테고리 선택 이벤트를 추가합니다.
    if (searchCondition) {
        searchCondition.addEventListener('change', function() {
            // 카테고리가 선택된 후, 검색어가 입력되어 있다면 검색어를 가져와 데이터 테이블에 적용합니다.
            const searchValue = searchInput.value;
            const categoryValue = searchCondition.value;

            if (dataTable && (searchValue || categoryValue === '전체')) {
                dataTable.search(searchValue);
            } else if (dataTable && categoryValue !== '전체') {
                dataTable.search(categoryValue);
            }
        });
    }
});
