document.addEventListener('DOMContentLoaded', () => {

    const selectElement = document.querySelector('.datatable-selector');
    if (selectElement) {
        selectElement.addEventListener('change', function handleStatusChange(e){
            const selectedOption = e.target.value
            alert('선택된 옵션 값:'+ selectedOption)
        });
    }

    function handleStatusChange(e) {
        const selectedOption = e.target.value
        if (selectedOption === 'select') {
            return;
        }

        let orderStatus;
        if (selectedOption === 'O') {
            orderStatus = 'O';
        } else if (selectedOption === 'X') {
            orderStatus = 'X'
        } else if (selectedOption === 'C') {
            orderStatus = 'C'
        }

        if (orderStatus) {
            axios.post("/admin/orderStatus/updateStatus", {
                status: orderStatus
            })
                .then(res => {
                    console.log('요청 성공:', res.data)
                })
                .catch(err => {
                    console.log('요청 실패:', err)
                })
        }
    }
})