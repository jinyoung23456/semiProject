/*
    div사이즈 동적으로 구하기
*/
const outer = document.querySelector('.outer');
const innerList = document.querySelector('.inner-list');
const inners = document.querySelectorAll('.inner');

const maxIndex = inners.length;  // 최대 인덱스
let pageIndex = 0; // 현재 슬라이드 화면 인덱스


const maxProgressIndex = inners.length - 2;
let progressBarIndex = 0;
const progressBar = document.querySelector('.progress_bar')
const progressArea = document.querySelector('.progress_area')
const oneStepLength = progressArea.clientWidth / (maxProgressIndex);

inners.forEach((inner) => {
    inner.style.width = `${outer.clientWidth}px`; // inner의 width를 모두 outer의 width로 만들기
})

innerList.style.width = `${outer.clientWidth * inners.length}px`; // innerList의 width를 inner의 width * inner의 개수로 만들기

/*
    버튼에 이벤트 등록하기
*/

const buttonLeft = document.querySelector('.button-previous');
const radioButtons = document.querySelectorAll('input')
const startButton = document.querySelector('.button-start')

startButton.addEventListener('click', ()=>{
    setTimeout(()=>{progressArea.style.opacity=1}, 200)
    pageIndex++;
    innerList.style.marginLeft = `-${outer.clientWidth * pageIndex}px`; // index만큼 margin을 주어 옆으로 밀기
})
for(const radioButton of radioButtons){
    radioButton.addEventListener('click', () => {
        pageIndex++;
        progressBarIndex++;
        pageIndex = pageIndex >= maxIndex ? maxIndex : pageIndex; // index값이 inner의 총 개수보다 많아질 경우 마지막 인덱스값으로 변경
        innerList.style.marginLeft = `-${outer.clientWidth * pageIndex}px`; // index만큼 margin을 주어 옆으로 밀기
        progressBar.style.width = `${oneStepLength * progressBarIndex}px`
        console.log(`now ${pageIndex}`)
        console.log(`max ${maxIndex}`)
        if(progressBarIndex==maxProgressIndex){
            setTimeout(()=>{progressArea.style.opacity=0}, 400)
        }
    })
}
