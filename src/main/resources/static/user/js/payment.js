function orderCancel() {
    if (confirm("주문을 취소하시겠습니까?")) {
        window.history.back();
    }
}

function updateProductSum() {
    let productSum = 0;

    const $totalPrice = document.querySelectorAll('.js_totalPrice')
    $totalPrice.forEach(currentPrice=>{
        const priceInt = parseInt(currentPrice.textContent.replace(/원 \/$/, ''), 10)
        productSum = productSum + priceInt
    })
    $finalTotalPrice = document.querySelector('.js_productSum')
    $finalTotalPrice.innerText=productSum + ' 원'

    let charge = 0;

    if(productSum < 30000){
        charge = 3000
    }

    const $charge = document.querySelector('.js_charge')

    $charge.textContent = charge + ' 원'

    document.querySelector('.js_amount').value = productSum + charge;
}

updateProductSum();

const IMP = window.IMP
IMP.init("imp87822476")

const today = new Date();
const hours = today.getHours();
const minutes = today.getMinutes();
const second = today.getSeconds();
const milliseconds = today.getMilliseconds();
const makeMerchantUid = `${hours}` + `${minutes}` + `${second}` + `${milliseconds}`;


const $postCode = document.querySelector('.buyerAddr1');

function payment() {
    const $kakaoPayMethod = document.querySelector('.kakaoPayCheck');
    if ($kakaoPayMethod.checked) {
        kakaoPayment();
        return true;
    }
    return false;
}
const $totalAmount = document.querySelector('.js_amount').value;

const $buyName = document.querySelector('.pay_buyerName').value

const $buyTel = document.querySelector('.pay_buyerTel').value

const $addr1 = document.querySelector('#address1').value
const $addr2 = document.querySelector('#address2').value
const $addr3 = document.querySelector('#address3').value

const productCodeElements = document.querySelectorAll('.getProductCode');
let productArr = [];
productCodeElements.forEach(element => {
    const productCode = element.dataset.productCode;
    console.log('Product Code:', productCode);

    productArr += productCode;
    console.log(productArr)
});

function kakaoPayment() {

    const addr = $addr2 + $addr3


    IMP.request_pay({
        pg : 'kakaopay',
        merchant_uid : "IMP" + makeMerchantUid,
        name : 'Dosirak',
        amount : $totalAmount,
        buyer_name : $buyName,
        buyer_tel : `${$buyTel}`,
        buyer_addr : `${addr}`,
        buyer_postcode : `${$addr1}`,
    }, (rsp) => {
        if (rsp.success) {
            alert("결제 성공");
            document.forms['finish'].submit();
        } else {
            alert("결제 실패");
        }
    })
}

const postCodeButton = document.querySelector('#postCode')
postCodeButton.addEventListener('click', () => {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById("address1").value = data.zonecode;
            document.getElementById("address2").value = data.address;
            document.getElementById("address3").focus();
        }
    }).open();
})