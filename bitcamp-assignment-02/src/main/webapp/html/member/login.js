"use strict"

$(eLoginBtn).click(() => {
   $.post(serverApiAddr + '/json/member/login', {
       email: $(eEmail).val(),
       password: $(ePassword).val()
   }, function(data) {
       if (data.status == 'success') {
           console.log(data.session);
           location.href = '../card/list.html';
       } else {
           alert('로그인 실패');
       }
   }, 'json');
});