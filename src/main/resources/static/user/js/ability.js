$(function(){
    // 변수 선언
    var imageButton = $('.item-box .item-img a');
    var popup = $('.img-popup');
    var closeButton = popup.find('> .popup-inner > .close-btn');
    var htmlAndBody = $('html, body');
    var focusTarget;
  
    // 팝업 생성
    imageButton.on('click', function(e){
      e.preventDefault();
      var popupImg = popup.find('> .popup-inner > img');
      var image = $(this).find('> img');
      var src = image.attr('src');
      var alt = image.attr('alt');
      focusTarget = $(this);
      popupImg.attr('src', src).attr('alt', alt);
      createPopup();
    });
  
    // 팝업 제거
    popup.on('click', function(){
      removePopup();
    });
    closeButton.on('click', function(e){
      e.preventDefault();
      removePopup();
    });
  
    // 팝업창 검은배경과 닫기 버튼을 제외한 나머지 부분 클릭시 닫히지 않도록 하기
    popup.find('> .popup-inner').on('click', function(e){
      e.stopPropagation();
    })
  
    // 팝업 생성 함수
    function createPopup(){
      popup.addClass('active');
      htmlAndBody.css('overflow-y', 'hidden');
      setTimeout(function(){
        closeButton.focus();
      }, 50);
    }
    // 팝업 제거 함수
    function removePopup(){
      popup.removeClass('active');
      focusTarget.focus();
      htmlAndBody.css('overflow-y', 'visible');
    }
  })