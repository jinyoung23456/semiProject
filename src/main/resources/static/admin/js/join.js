$(function(){
    $(".joinBtn").click(function(event){

        let allinputFilled = true;

        // 모든 필수 입력칸 확인
        $("input[required]").each(function(){
            if($(this).val().trim() === ""){
                event.preventDefault();
                alert("입력란을 모두 작성해주세요.");
                $(this).focus();
                allinputFilled = false;
                return false;
            }
        })

        if(allinputFilled){
            if($("#duplCheck").val() == ""){
                alert("이메일 중복확인을 진행해 주세요.");
                $("#duplCheck").focus();
                return false;
            }

            if($("#verifyVerificationCode").val() == ""){
                alert("이메일 인증확인을 진행해 주세요.");
                $("#inputCode").focus();
                return false;
            }

            if($(".tr_inputPwdConfirm").css("display") == "none"){
                alert("조건에 알맞는 비밀번호를 생성해 주세요.");
                $("#inputPwd").focus();
                return false;
            }

            if($("#inputPwdCheck").val() == ""){
                alert("비밀번호 확인을 진행해 주세요.");
                $("#inputPwdConfirm").focus();
                return false;
            }
        }
    })

    // 이메일 값을 변경할때마다
    $("#inputEmail").change(function(){
        $("#duplCheck").val("");
        hideInputCode();
    })
})


window.onload = function() {

    if(document.getElementById("duplicationCheck")) {
        const $duplication = document.getElementById("duplicationCheck");

        $duplication.onclick = function() {
            let email = document.getElementById("inputEmail").value.trim();

            fetch("/admin/member/idDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({email: email})
            })
                .then(result => result.text())
                .then(result => {
                    alert(result);
                    if(result == "사용 가능한 이메일입니다."){
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
    document.querySelector(".tr_inputCode").style.display = "block";
}

function hideInputCode(){
    document.getElementById("duplicationCheck").style.display = "block";
    document.querySelector(".sendCodeBtn").style.display = "none";
    document.querySelector(".tr_inputCode").style.display = "none";
}

function noSpace(obj){
    let str_space = /\s/;
    if(str_space.exec(obj.value)){
        alert("공백 입력 불가");
        obj.focus();
        obj.value = obj.value.replace(' ', '');
        return false;
    }
}
function passwordCondition(){
    let password = $("#inputPwd").val();
    let pwNum = password.search(/[0-9]/g);
    let pwEng = password.search(/[a-z]/ig);
    let pwSpe = password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if(password.length < 8){
        alert("8자리 이상 입력해주세요");
        $("#inputPwd").focus();
        $(".tr_inputPwdConfirm").hide();
        return false;
    }else if(pwNum<0 || pwEng<0 || pwSpe<0){
        alert("영문자, 숫자, 특수기호가 반드시 포함되어야 합니다");
        $("#inputPwd").focus();
        $(".tr_inputPwdConfirm").hide();
        return false;
    }

    $(".tr_inputPwdConfirm").show();
    return false;
}
function passwordCheck(){
    if($("#inputPwd").val() == $("#inputPwdConfirm").val()){
        $("#pwdCheckMsg").text('※ 비밀번호 일치').css('color', 'blue');
        $("#inputPwdCheck").val("pass");
        // $(".joinBtn").removeAttr("disabled");
    }else{
        $("#pwdCheckMsg").text('※ 비밀번호 불일치').css('color', 'red');
        $("#inputPwd").focus();
        $("#inputPwdCheck").val("");
    }
}

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