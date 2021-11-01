/*의사 사진 등록*/

$("#doc").on("click", function(){

    let check = false;
    function showImage(fileCallPath){
        if(check) {return;}
        $(".bigPictureWrapper").css("display", "flex").show();
        $(".bigPicture").html("<img src='/upload/display?fileName=" + encodeURIComponent(fileCallPath) + "'>")
            .animate({width:"100%", height:"100%"}, 1000);
        check = true;
    }

    $(".bigPictureWrapper").on("click", function(){
        if(!check){return;}
        $(".bigPicture").animate({width: "0%", height: "0%"}, 1000);
        setTimeout(function(){
            check = false;
            $(".bigPictureWrapper").hide();
        }, 1000)
    })


    let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
    let maxSize = 5242880;// 5MB
    let inputFile = $(".uploadDiv input");
    let uploadResult = $(".uploadResult ul");


    $("input[type='submit']").on("click", function(e){
        e.preventDefault();
        let form = $("form#doctorSignUpForm");
        let str = "";
        $(".uploadResult ul li").each(function(i, obj){
            str += "<input type='hidden' name='attachList[" + i + "].fileName' value='" + $(obj).data('name') + "'>"
            str += "<input type='hidden' name='attachList[" + i + "].uuid' value='" + $(obj).data('uuid') + "'>"
            str += "<input type='hidden' name='attachList[" + i + "].uploadPath' value='" + $(obj).data('path') + "'>"
            str += "<input type='hidden' name='attachList[" + i + "].image' value='" + $(obj).data('type') + "'>"
        });
        form.append(str).submit();
    })

    function showUploadFile(uploadFiles){
        let str = "";
        $(uploadFiles).each(function(i, obj){
            if(!obj.image){
                let fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.fileName);

                str += "<li data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-name='" + obj.fileName.substring(obj.fileName.indexOf("_") + 1) + "' data-type='" + obj.image +"'>";
                str += "<div>";
                str += "<a href='/upload/download?fileName=" + fileCallPath + "'>";
                str += "<img src='/img/attach.png' width='100px'>" + obj.fileName.substring(obj.fileName.indexOf("_") + 1);
                str += "</a>"
                str += "<span data-file='" + fileCallPath + "' data-type='file'>❌</span>";
                str += "</div>";
                str += "</li>";
            }else{
                let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.fileName);
                let originPath = encodeURIComponent(obj.uploadPath + "/" + obj.fileName);

                str += "<li data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-name='" + obj.fileName.substring(obj.fileName.indexOf("_") + 1) + "' data-type='" + obj.image +"'>";
                str += "<div>";
                str += "<a href=\"javascript:showImage(\'" + originPath + "\')\">";
                str += "<img src='/upload/display?fileName=" + fileCallPath + "'>" + obj.fileName.substring(obj.fileName.indexOf("_") + 1);
                str += "</a>";
                str += "<span data-file='" + fileCallPath + "' data-type='image'>❌</span>";
                str += "</div>";
                str += "</li>";
            }
        });
        uploadResult.append(str);
    }

    $(".uploadResult").on("click", "span", function(){
        let targetFile = $(this).data("file");
        let type = $(this).data("type");
        let li = $(this).parents("li");

        $.ajax({
            url: "/upload/deleteFile",
            type: "POST",
            data: {fileName:targetFile, type:type},
            dataType: "text",
            success: function(result){
                alert(result);
                li.remove();
            }
        });

    })

    //확장자 별 업로드 제한, 특정 크기 이상의 파일은 업로드 제한
    function checkExtension(fileName, fileSize){
        if(regex.test(fileName)){
            alert("업로드 할 수 없는 파일의 형식입니다.");
            return false;
        }
        if(fileSize >= maxSize){
            alert("파일 사이즈 초과");
            return false;
        }
        return true;
    }

    $("#doc").change(function(){
        let formData = new FormData();
        let inputFile = $("input[name='uploadFiles']");
        let files = inputFile[0].files;

        for(let i=0; i<files.length; i++){
            if(!checkExtension(files[i].name, files[i].size)){ return; }
            formData.append("uploadFiles", files[i]);
        }

        $.ajax({
            url: "/upload/uploadAjaxAction",
            type: "post",
            data: formData,
            contentType: false,
            processData: false,
            success: function(fileList){
                showUploadFile(fileList);
                inputFile.val("");
            }
        });
    });
})
/*의사 사진 등록 끝*/

/*병원 사진 등록*/
$("#hos").on("click", function(){
    let check = false;
    function showImage1(fileCallPath){
        if(check) {return;}
        $(".bigPictureWrapper1").css("display", "flex").show();
        $(".bigPicture1").html("<img src='/hosupload/hosdisplay?hosFileName=" + encodeURIComponent(fileCallPath) + "'>")
            .animate({width:"100%", height:"100%"}, 1000);
        check = true;
    }

    $(".bigPictureWrapper1").on("click", function(){
        if(!check){return;}
        $(".bigPicture1").animate({width: "0%", height: "0%"}, 1000);
        setTimeout(function(){
            check = false;
            $(".bigPictureWrapper1").hide();
        }, 1000)
    })


    let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
    let maxSize = 5242880;// 5MB
    let inputFile = $(".uploadDiv input");
    let uploadResult = $(".uploadResult1 ul");

    $("input[type='submit']").on("click", function(e){
        e.preventDefault();
        let form = $("form#doctorSignUpForm");
        let str = "";
        $(".uploadResult1 ul li").each(function(i, obj){
            str += "<input type='hidden' name='hosattachList[" + i + "].hosFileName' value='" + $(obj).data('name') + "'>"
            str += "<input type='hidden' name='hosattachList[" + i + "].hosUuid' value='" + $(obj).data('uuid') + "'>"
            str += "<input type='hidden' name='hosattachList[" + i + "].hosUploadPath' value='" + $(obj).data('path') + "'>"
            str += "<input type='hidden' name='hosattachList[" + i + "].hosImage' value='" + $(obj).data('type') + "'>"
        });
        form.append(str).submit();
    })

    function showUploadFile1(hosUploadFiles){
        let str = "";
        $(hosUploadFiles).each(function(i, obj){
            if(!obj.hosImage){
                let fileCallPath = encodeURIComponent(obj.hosUploadPath + "/" + obj.hosFileName);

                str += "<li data-path='" + obj.hosUploadPath + "' data-uuid='" + obj.hosUuid + "' data-name='" + obj.hosFileName.substring(obj.hosFileName.indexOf("_") + 1) + "' data-type='" + obj.hosImage +"'>";
                str += "<div>";
                str += "<a href='/hosupload/download?hosFileName=" + fileCallPath + "'>";
                str += "<img src='/img/attach.png' width='100px'>" + obj.hosFileName.substring(obj.hosFileName.indexOf("_") + 1);
                str += "</a>"
                str += "<span data-file='" + fileCallPath + "' data-type='file'>❌</span>";
                str += "</div>";
                str += "</li>";
            }else{
                let fileCallPath = encodeURIComponent(obj.hosUploadPath + "/s_" + obj.hosFileName);
                let originPath = encodeURIComponent(obj.hosUploadPath + "/" + obj.hosFileName);

                str += "<li data-path='" + obj.hosUploadPath + "' data-uuid='" + obj.hosUuid + "' data-name='" + obj.hosFileName.substring(obj.hosFileName.indexOf("_") + 1) + "' data-type='" + obj.hosImage +"'>";
                str += "<div>";
                str += "<a href=\"javascript:showImage1(\'" + originPath + "\')\">";
                str += "<img src='/hosupload/hosdisplay?hosFileName=" + fileCallPath + "'>" + obj.hosFileName.substring(obj.hosFileName.indexOf("_") + 1);
                str += "</a>";
                str += "<span data-file='" + fileCallPath + "' data-type='image'>❌</span>";
                str += "</div>";
                str += "</li>";
            }
        });
        uploadResult.append(str);
    }

    $(".uploadResult1").on("click", "span", function(){
        let targetFile = $(this).data("file");
        let type = $(this).data("type");
        let li = $(this).parents("li");

        $.ajax({
            url: "/hosupload/hosdeleteFile",
            type: "POST",
            data: {hosFileName:targetFile, type:type},
            dataType: "text",
            success: function(result){
                alert(result);
                li.remove();
            }
        });

    })

    //확장자 별 업로드 제한, 특정 크기 이상의 파일은 업로드 제한
    function checkExtension(hosFileName, fileSize){
        if(regex.test(hosFileName)){
            alert("업로드 할 수 없는 파일의 형식입니다.");
            return false;
        }
        if(fileSize >= maxSize){
            alert("파일 사이즈 초과");
            return false;
        }
        return true;
    }

    $("#hos").change(function(){
        let formData = new FormData();
        let inputFile = $("input[name='hosUploadFiles']");
        let files = inputFile[0].files;

        for(let i=0; i<files.length; i++){
            if(!checkExtension(files[i].name, files[i].size)){ return; }
            formData.append("hosUploadFiles", files[i]);
        }

        $.ajax({
            url: "/hosupload/hosuploadAjaxAction",
            type: "post",
            data: formData,
            contentType: false,
            processData: false,
            success: function (FileList) {
                showUploadFile1(FileList);
                inputFile.val("");
            }
        });
    });
})
/*병원 사진 등록 끝*/

function checkSelectAll() {
// 전체 체크박스
    const checkboxes
        = document.querySelectorAll('input[name="term"]');
// 선택된 체크박스
    const checked
        = document.querySelectorAll('input[name="term"]:checked');
// select all 체크박스
    const selectAll
        = document.querySelector('input[name="agreeAll"]');

    if (checkboxes.length === checked.length) {
        selectAll.checked = true;
    } else {
        selectAll.checked = false;
    }

}

function selectAll(selectAll) {
    const checkboxes
        = document.getElementsByName('term');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked
    })
}

/*이메일*/
$(document).ready(function () {
    $('#email_select').change(function () {
        let email = $('#email_select').val(); //id선택자로 email select box값 추출하여 저장
        console.log(email);
        if (email == '_manual') { //selectbox value가 _manual이면
            $("#email_select").replaceWith("<input type = 'text' id='input_email' placeholder='직접입력' style='margin: auto !important;'>");
            //selectbox 태그를 input 태그로 변경
            $("#inputEmail").append("<button id='reset' type='button' onclick='check()' style='position: absolute;left: 62%;top: 186%;box-shadow: none;'>✖</button>");
        }
    });
});


function check() {
    let i = document.getElementById('inputEmail');
    let j = document.getElementById('input_email');
    j.value = null;
    i.innerHTML = "<select id='email_select' name='email'style='font-size: 19px !important;border-radius: 0;width: 147%;'>" +
        "<option selected disabled>선택</option>" +
        "<option value='naver.com'>naver.com</option>" +
        "<option value='daum.net'>daum.net</option>" +
        "<option value='gmail.com'>gmail.com</option>" +
        "<option value='nate.com'>nate.com</option>" +
        "<option value='hotmail.com'>hotmail.com</option>" +
        "<option value='icloud.com'>icloud.com</option>" +
        "<option value='outlook.com'>outlook.com</option>" +
        "<option value='_manual'>직접입력</option></select>";
};


function formSubmit() {
    let form = document.doctorSignUpForm;
    let email = document.getElementById("email");
    let i = document.getElementById('user_email');
    let j = document.getElementById('email_select');
    email.value = i.value + "@" + j.value;
    console.log(email.value);
    let major = document.getElementById("major");
    let f = document.getElementById('major_select');
    major.value = f.value;

    form.submit();

}

$(document).ready(function () {
    $("#popup_open_btn").on("click", function () {
        let check = false;
        let form = document.doctorSignUpForm;
        if (form.docId.value == "") {
            alert("아이디를 확인해주세요");
            form.docId.focus();
            return;
        }
        if (!form.docPw.value) {
            alert("비밀번호를 입력해주세요");
            form.docPw.focus();
            return;
        } else {
            //8자리 이상, 대문자/소문자/숫자/특수문자 모두 포함되어 있는 지 검사
            let reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
            let hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

            if (!reg.test(form.docPw.value)) {
                alert("비밀번호는 8자리 이상이어야 하며, 대문자/소문자/숫자/특수문자 모두 포함해야 합니다.");
                form.docPw.focus();
                return;
            }

            //비밀번호에 공백을 포함할 수 없다.
            if (form.docPw.value.search(/\s/) != -1) {
                alert("비밀번호에 공백 없이 입력해주세요.");
                form.docPw.focus();
                return;
            }
        }

        if (!form.docMajor1.value) {
            alert("전공을 선책해주세요");
            form.docName.focus();
            return;
        }
        if (!form.docHospitalName.value) {
            alert("병원이름을 확인해주세요");
            form.docHospitalName.focus();
            return;
        }
        if (!form.docHosPhone.value) {
            alert("병원전회번호를 확인해주세요");
            form.docHosPhone.focus();
            return;
        }
        if (form.docLat.value == 37.555946 && form.docLng.value == 126.972317) {
            alert("지도에서 위치를 선택해주세요");
           /* /!* form.searchBar.focus();*!/*/
            return;
        }
        if (!form.docAddress.value) {
            alert("상세주소를 확인해주세요");
            form.docAddress.focus();
            return;
        }
        if (!form.docName.value) {
            alert("이름을 확인해주세요");
            form.docName.focus();
            return;
        }
        if (!form.docEmail1.value) {
            alert("이메일을 입력해주세요");
            form.docEmail1.focus();
            return;
        }
        if (form.docEmail2.value == "@선택") {
            alert("이메일을 선택해주세요");
            form.docEmail2.focus();
            return;
        }

        if (!form.docPhoneNum.value) {
            alert("휴대폰번호를 입력해주세요");
            form.docPhoneNum.focus();
            return;
        }
        if (!form.numStr.value) {
            alert("인증번호를 입력해주세요");
            form.numStr.focus();
            return;
        }
        /*/!*약관동의 확인*!/*/
        check = false;

        $.each($(".terms"), function (index, item) {
            if (!$(item).is(":checked")) {
                check = true;
            }
        });

        if (check) {
            alert("이용약관 동의가 필요합니다.");
           /* /!*form.term.focus();*!/*/
            return;
        }
        if (form.docId.value.length < 4 || form.docId.value.length > 16) {
            alert("아이디는 4자 이상, 16자 이하로 입력해주세요.");
            form.docId.focus();
            return false;
        } else {
            check = true;
        }

        $("#submit1").on("click", function () {
            let idChkVal = $("#checkId").val();
            if (idChkVal == "N") {
                alert("중복확인 버튼을 눌러주세요.");
                return;
            }

        });
        setTimeout(() => {
                $("#popup_open_btn").on("click", function () {
                    $(".box_layer").css("display", "block");
                });
            }
        );
    });
});


/*전공선택*/
$(document).ready(function () {
    $('#major_select').change(function () {
        let major = $('#major_select').val(); //id선택자로 email select box값 추출하여 저장
        /*if(email == '_manual'){ //selectbox value가 _manual이면
            $("#email_select").replaceWith("<input type = 'text' id='input_email' placeholder='직접입력' style='margin: auto !important;'>");
            //selectbox 태그를 input 태그로 변경
            $("#inputEmail").append("<button id='reset' type='button' onclick='check()'>✖</button>");
        }*/
        console.log(major);
    });
});


/* 지도 */
let map;
let marker;
let geocoder;
let responseDiv;
let response;

function f1() {
    const arr = map.location.split(",");
    document.writeln(arr[0]);
    document.writeln(arr[1]);

}

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 13,
        center: {lat: 37.555946, lng: 126.972317},
        mapTypeControl: false,
    });
    geocoder = new google.maps.Geocoder();

    const inputText = document.createElement("input");


    inputText.type = "text";
    inputText.class = "searchBar";
    inputText.placeholder = "주소를 검색해 주세요.";

    const submitButton = document.createElement("input");

    submitButton.type = "button";
    submitButton.value = "검색";
    submitButton.classList.add("button1", "button-1", "search");

    const clearButton = document.createElement("input");

    clearButton.type = "button";
    clearButton.value = "취소";
    clearButton.classList.add("button1", "button-secondary1", "clear");
    response = document.createElement("pre");
    response.id = "response";
    response.innerText = "";
    responseDiv = document.createElement("div");
    responseDiv.id = "response-container";
    responseDiv.appendChild(response);

    const instructionsElement = document.createElement("p");

    instructionsElement.id = "instructions";
    // instructionsElement.innerHTML =
    //     "<strong>Instructions</strong>: Enter an address in the textbox to geocode or click on the map to reverse geocode.";
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(inputText);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(submitButton);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(clearButton);
    map.controls[google.maps.ControlPosition.LEFT_TOP].push(instructionsElement);
    map.controls[google.maps.ControlPosition.LEFT_TOP].push(responseDiv);
    marker = new google.maps.Marker({
        map,
    });
    map.addListener("click", (e) => {
        geocode({location: e.latLng});
    });
    submitButton.addEventListener("click", () =>
        geocode({address: inputText.value})
    );
    clearButton.addEventListener("click", () => {
        clear();
    });
    clear();
}

function clear() {
    marker.setMap(null);
    responseDiv.style.display = "none";
}

function geocode(request) {
    clear();
    geocoder
        .geocode(request)
        .then((result) => {
            const {results} = result;

            map.setCenter(results[0].geometry.location);
            marker.setPosition(results[0].geometry.location);
            marker.setMap(map);
            responseDiv.style.display = "none";
            response.innerText = JSON.stringify(result, null, 2);

            var location = results[0].geometry.location;
            alert(location);
            /*const arr = map.location.split(",");*/
            console.log(location);
            var arr = location.toString().split(",");
            console.log(arr[0].substring(1).trim());
            console.log(arr[1].substring(0, 11).trim());


            let form = document.doctorSignUpForm;
            form.docLat.value = arr[0].substring(1).trim();
            form.docLng.value = arr[1].substring(0, 11).trim();

            return results;


        })
        .catch((e) => {
            alert("Geocode was not successful for the following reason: " + e);
        });
}


/*$(document).ready(function () {*/

$(".btn_close").on("click", function () {
    $(".box_layer").css("display", "none");
})

/*})*/






