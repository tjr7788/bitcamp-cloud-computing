"use strict"

$(eJoinBtn).click(() => {
   $.post(serverApiAddr + '/json/member/add', {
       email: $(eEmail).val(),
       name: $(eName).val(),
       password: $(ePassword).val()
   }, function(data) {
       location.href="login.html";
   }, 'json');
});