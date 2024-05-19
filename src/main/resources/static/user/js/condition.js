function noSpace(obj){
    let str_space = /\s/;
    if(str_space.exec(obj.value)){
        alert("공백 입력 불가");
        obj.focus();
        obj.value = obj.value.replace(' ', '');
        return false;
    }
}
function onlyKorFunc(txt){
    var regexp = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
    if(regexp.exec(txt.value)){
        alert("한글만 입력할 수 있습니다.");
        txt.focus();
        txt.value = txt.value.replace(regexp, '');
        return false;
    }
}
function idCondition(){
    let id = $(".el_idInput").val();
    let Num = id.search(/[0-9]/g);
    let Eng = id.search(/[a-z]/ig);
    let Special = id.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if(id.length < 4 || id.length > 15){
        alert("4 ~ 15자 이내로 입력해주세요");
        $(".idHidden").val(false);
        return false;
    }else if(Num<0 || Eng<0){
        alert("영문자와 숫자가 반드시 포함되어야 합니다");
        $(".idHidden").val(false);
        return false;
    }else if(Special>0){
        alert("특수기호는 사용할 수 없습니다");
        $(".idHidden").val(false);
        return false;
    }

    $(".idHidden").val(true);
}
function passwordCondition(){
    let password = $(".el_pwdInput").val();
    let pwNum = password.search(/[0-9]/g);
    let pwEng = password.search(/[a-z]/ig);
    let pwSpe = password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if(password.length < 8){
        alert("8자리 이상 입력해주세요");
        $(".el_pwdInput").focus();
        $(".pwdMessage").text('부적합').css('color', 'red');
        $(".tr_inputPwdConfirm").hide();
        return false;
    }else if(pwNum<0 || pwEng<0 || pwSpe<0){
        alert("영문자, 숫자, 특수기호가 반드시 포함되어야 합니다");
        $(".el_pwdInput").focus();
        $(".pwdMessage").text('부적합').css('color', 'red');
        $(".tr_inputPwdConfirm").hide();
        return false;
    }

    $(".pwdMessage").text('적합').css('color', 'blue');
    $(".tr_inputPwdConfirm").show();
}

function passwordCheck(){
    if($(".el_pwdInput").val() == $(".el_pwdCheckInput").val()){
        $(".pwdCheckMessage").text('일치').css('color', 'blue');
        $("#inputPwdCheck").val("pass");
    }else{
        $(".pwdCheckMessage").text('불일치').css('color', 'red');
        $("#inputPwdCheck").val("");
    }
}

// 이메일인증
function sendCode(){
    let email = document.getElementById("inputEmail").value;
    let hiddenCode = document.getElementById("hiddenCode").value;

    if(!email.includes("@")){
        alert("올바른 이메일 주소를 입력해주세요");
        return;
    }

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/sendVerificationCode", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            alert("인증코드가 이메일로 전송되었습니다.");
        }
    };
    xhr.send(JSON.stringify({"email": email, "hiddenCode": hiddenCode}));
}

function verifyCode(){
    let inputCode = document.getElementById("inputCode").value;
    let hiddenCode = document.getElementById("hiddenCode").value;
    if(inputCode == null || inputCode == ""){
        alert("인증코드를 입력해주세요");
        return;
    }

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/verifyVerificationCode", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                let response = JSON.parse(xhr.responseText);
                if(response.valid){
                    alert("인증되었습니다.");
                    document.getElementById("verifyVerificationCode").value = "pass";
                }else{
                    alert("인증코드가 올바르지 않습니다.");
                    document.getElementById("verifyVerificationCode").value = "";
                }
            }else{
                alert("인증코드 확인 중 오류가 발생했습니다.");
                document.getElementById("verifyCode").value = "";
            }
        }
    }
    xhr.send(JSON.stringify({"inputCode":inputCode, "hiddenCode":hiddenCode}));
}

// 주소
const $searchZipCode = document.getElementById("searchZipCode");
$searchZipCode.onclick = function(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById("address1").value = data.zonecode;
            document.getElementById("address2").value = data.address;
            document.getElementById("address3").focus();
        }
    }).open();
}

// 이메일 중복 확인
window.onload = function() {

    if(document.getElementById("duplicationCheck")) {
        const $duplication = document.getElementById("duplicationCheck");

        $duplication.onclick = function() {
            let email = document.getElementById("inputEmail").value.trim();

            fetch("/user/emailDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({email: email})
            })
                .then(result => result.text())
                .then(result => {
                    alert(result);
                    if(result == "사용가능한 이메일입니다."){
                        document.getElementById("duplCheck").value = "pass";
                        showInputCode();
                    }else{
                        document.getElementById("duplCheck").value = "";
                        hideInputCode();
                    }
                })
                .catch((error) => {
                    alert("에러가 발생했습니다. \n다시 시도해 주세요.");
                    document.getElementById("duplCheck").value = "";
                    hideInputCode();
                })
        }
    }
}
function showInputCode(){
    document.getElementById("duplicationCheck").style.display = "none";
    document.querySelector(".sendCodeBtn").style.display = "block";
    document.querySelector(".tr_inputCode").style.display = "table-row";
}
function hideInputCode(){
    document.getElementById("duplicationCheck").style.display = "block";
    document.querySelector(".sendCodeBtn").style.display = "none";
    document.querySelector(".tr_inputCode").style.display = "none";
}