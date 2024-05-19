document.addEventListener('DOMContentLoaded', function() {

    // 첫 화면 로드시 실행
    updateProductSum();
    updateTotalAmountAndCharge();

    // 수량 변경 할때마다
    $('.decrease, .increase').click(function () {
        updateProductSum();
        updateTotalAmountAndCharge();
    });

    // 총 상품금액 계산 함수
    function updateProductSum() {
        let productSum = 0;
        $('.js_productRow').each(function () {
            let price = parseInt($(this).find('.js_productPrice').val());
            let quantity = parseInt($(this).find('.js_productQuantity').val());
            let sum = price * quantity;

            productSum += parseInt(sum);
        });
        $('.js_productSum').val(productSum);
    }

    // 주문 예상금액 계산 함수
    function updateTotalAmountAndCharge() {
        let totalAmount = 0;
        $('.js_productSum').each(function () {
            totalAmount += parseInt($(this).val());
        });

        let charge = totalAmount > 30000 ? 0 : 3000;

        $('.js_total').val(totalAmount + charge);
        $('.js_charge').val(charge);
    }

    // 전체선택 체크박스 선택해지
    if($("tr").hasClass("js_productRow") === false){
        console.log(123);
        $(".checkAll").prop("checked", false);
    }

    // 선택 삭제
    $('.deleteBtn').click(function () {

        $('.bl_checkBx__input:checked').each(function () {
            const button = $(this).closest('.js_productRow');
            const productCode = button.data('product-code');
            const suitboxCode = button.data('suitbox-code');

            // 행 삭제
            $(button).remove();

            if (productCode != null) {
                console.log('도시락 상품' + productCode)
                axios.post('/user/cart/delete-product', {
                    productCode: productCode
                }, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(res => {
                        console.log('성공:', res.status.message);
                    })
                    .catch(err => {
                        console.log('오류:', err.response.message);
                    });
            } else if (suitboxCode != null) {
                console.log('맞춤 도시락 상품' + suitboxCode)
                axios.post('/user/cart/delete-suitbox', {
                    suitboxCode: suitboxCode
                }, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(res => {
                        console.log('성공:', res.status.message);
                    })
                    .catch(err => {
                        console.log('오류:', err.response.message);
                    });
            }
        });
        updateProductSum();
        updateTotalAmountAndCharge();

        // 전체선택 체크박스 선택해지
        $(".checkAll").prop("checked", false);
    })

    // 페이지가 로드될 때 실행할 코드
    const checkAll = document.querySelector('.checkAll');
    const checkboxes = document.querySelectorAll('.checkOne');
    const $cartElements = document.querySelector('#cartList')

    // 체크박스 전체 선택/해제
    checkAll.addEventListener('click', () => {
        const isChecked = checkAll.checked;

        for (const checkbox of checkboxes) {
            checkbox.checked = isChecked;
        }
    });
});

// 수량 조절 버튼
const buttons = document.querySelectorAll('.decrease, .increase')
Array.from(buttons).forEach(function (button) {
    button.addEventListener('click', ButtonClick);
});

function ButtonClick(e) {
    const button = e.currentTarget;
    const input = button.parentElement.querySelector('.quantity');
    const value = parseInt(input.value, 10);
    let newValue = value;

    if (button.classList.contains('decrease')) {
        if (value > 1) {
            newValue = value - 1;
            input.value = newValue;
        }
    } else if (button.classList.contains('increase')) {
        newValue = value + 1;
        input.value = newValue;
    }

    const productCode = button.closest('tr').getAttribute('data-product-code');
    const suitboxCode = button.closest('tr').getAttribute('data-suitbox-code');
    if (productCode != null) {
        axios.post('/user/cart/update-quantity', {
            productCode: productCode,
            cartitemCount: newValue,
        }, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => {
                console.log('성공:', res.status.message);
            })
            .catch(err => {
                console.log('오류:', err.response.message);
            })
    } else {
        axios.post('/user/cart/update-quantity/suit-box', {
            suitboxCode: suitboxCode,
            cartitemCount: newValue,
        }, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => {
                console.log('성공:', res.status.message);
            })
            .catch(err => {
                console.log('오류:', err.response.message);
            })

    }
}


// 구매하기 블록 스티키
// window.addEventListener('scroll', () => {
//     const element = document.querySelector('.sticky');
//     const offset = window.pageYOffset;
//
//     if (offset >= 200) {
//         element.style.position = 'fixed';
//         element.style.top = '35px';
//         element.style.right = '341px';
//     } else {
//         element.style.position = 'absolute';
//         element.style.top = '0';
//         element.style.right = '0';
//     }
// });

// 이미지 드래그 방지
document.querySelectorAll('.di_btn img').forEach(function (img) {
    img.addEventListener('dragstart', function () {
        event.preventDefault();
    });
});

// 장바구니 상품 여부 표시
document.addEventListener('DOMContentLoaded', () => {
    const cartItems = document.querySelectorAll('.hidden_block');
    const cartEmptyMessage = document.querySelector('.bl_TBList__empty');

    if (cartItems.length === 0) {
        cartEmptyMessage.parentNode.classList.add('hide-empty-row');
        cartEmptyMessage.classList.add('show_empty_message');
    }
});