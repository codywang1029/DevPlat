$(document).ready(function(){
    var id;
   $(".req-item").hover(function () {
       id = $(this).attr('id');
       console.log(id);
       $('#'+id+'.req-toolbar').show('fast');
   }, function() {
       $('#'+id+'.req-toolbar').hide('fast');
       id=0;
   });
});