/*휴대폰 인증*/
$(document).on('click','#checkNum',function(e){
    $.ajax({
        url : "/user/sendUserSms",
        type: "post",
        dataType: "json",
        data: {"userPhoneNum":$("#userPhoneNum").val()},
        success : function(numStr){
            $('#checkNumStr').click(function(){
                if($.trim(numStr)==$('#numStr').val()){
                    alert("인증 성공. 휴대폰인증이 정상적으로 완료되었습니다.");
                }else{
                    alert("인증 실패. 인증번호를 다시 확인해 주세요.");
                }
            });
        }
    });
})
/*휴대폰인증 끝*/

/*회원가입 공백확인*/

function formSubmit(){
    let check = false;
    let form= document.memberSignUpForm;
    let email = document.getElementById("email");
    let i = document.getElementById('user_email');
    let j = document.getElementById('email_select');
    email.value = i.value+"@"+ j.value;
    console.log(email.value);



    if(form.userId.value==""){
        alert("아이디를 확인해주세요");
        form.userId.focus();
        return;
    }

    if(!form.userPw.value){
        alert("비밀번호를 입력해주세요");
        form.userPw.focus();
        return;
    }else{
        //8자리 이상, 대문자/소문자/숫자/특수문자 모두 포함되어 있는 지 검사
        var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
        var hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

        if(!reg.test(form.userPw.value)){
            alert("비밀번호는 8자리 이상이어야 하며, 대문자/소문자/숫자/특수문자 모두 포함해야 합니다.");
            form.userPw.focus();
            return;
        }

        //비밀번호에 공백을 포함할 수 없다.
        if(form.userPw.value.search(/\s/) != -1){
            alert("비밀번호에 공백 없이 입력해주세요.");
            form.userPw.focus();
            return;
        }
    }

    if(!form.userName.value){
        alert("이름을 확인해주세요");
        form.userName.focus();
        return;
    }
    if(!form.userEmail1.value){
        alert("이메일을 입력해주세요");
        form.userEmail1.focus();
        return;
    }
    if(form.userEmail2.value =="@선택"){
        alert("이메일을 선택해주세요");
        form.userEmail2.focus();
        return;
    }

    if(!form.userPhoneNum.value){
        alert("휴대폰번호를 입력해주세요");
        form.userPhoneNum.focus();
        return;
    }

    /!*약관동의 확인*!/
    check = false;

    $.each($(".terms"), function(index, item){
        if(!$(item).is(":checked")){
            check = true;
        }
    });

    if(check){
        alert("이용약관 동의가 필요합니다.");
        /!*form.term.focus();*!/
        return;
    }
    if(form.userId.value.length < 4 || form.userId.value.length > 16){
        alert("아이디는 4자 이상, 16자 이하로 입력해주세요.");
        form.userId.focus();
        return false;
    }else{
        check = true;
    }

    $("#submit1").on("click", function(){
        let idChkVal = $("#checkId").val();
        if(idChkVal == "N"){
            alert("중복확인 버튼을 눌러주세요.");
            return;
        }
    });
    form.submit();
}



/*아이디 중복검사*/
function fn_idChk(){
    $.ajax({
        url : "/user/checkUserId",
        type : "post",
        dataType : "json",
        data : {"userId":$("#userId").val()},
        success : function(data){
            if(data == 1) {
                alert("중복된 아이디 입니다.");
            }else if(data == 0){
                $("#checkId").attr("value","Y");
                alert("사용가능한 아이디 입니다.");
            }
        }

    });
}
/*아이디 중복검사 끝*/

function openLogin(evt, loginName) {
    // Declare all variables
    let i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(loginName).style.display = "block";
    evt.currentTarget.className += " active";
}
document.getElementById("defaultOpen").click();


function checkSelectAll(){
// 전체 체크박스
    const checkboxes
        = document.querySelectorAll('input[name="term"]');
// 선택된 체크박스
    const checked
        = document.querySelectorAll('input[name="term"]:checked');
// select all 체크박스
    const selectAll
        = document.querySelector('input[name="agreeAll"]');

    if(checkboxes.length === checked.length)  {
        selectAll.checked = true;
    }else {
        selectAll.checked = false;
    }

}

function selectAll(selectAll)  {
    const checkboxes
        = document.getElementsByName('term');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked
    })
}


/*이메일*/

$(document).ready(function(){
    $('#email_select').change(function(){
        var email = $('#email_select').val(); //id선택자로 email select box값 추출하여 저장
        if(email == '_manual'){ //selectbox value가 _manual이면
            $("#email_select").replaceWith("<input type = 'text' id='input_email' placeholder='직접입력' style='margin: auto !important;'>");
            //selectbox 태그를 input 태그로 변경
            $("#inputEmail").append("<button id='reset' type='button' onclick='check()'>✖</button>");
        }
    });
});


function check(){
    var i = document.getElementById('inputEmail');
    var j = document.getElementById('input_email');
    j.value=null;
    i.innerHTML = "<select id='email_select' name='email'style='font-size: 10px !important;'>"+
        "<option selected disabled>선택</option>"+
        "<option value='naver.com'>naver.com</option>"+
        "<option value='daum.net'>daum.net</option>"+
        "<option value='gmail.com'>gmail.com</option>"+
        "<option value='nate.com'>nate.com</option>"+
        "<option value='hotmail.com'>hotmail.com</option>"+
        "<option value='icloud.com'>icloud.com</option>"+
        "<option value='outlook.com'>outlook.com</option>"+
        "<option value='_manual'>직접입력</option></select>";
};

