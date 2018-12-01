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

   $("[name='delete-req']").click(function(){
        if (confirm('Do you want to delete the requirement?')) {
           $.ajax({
               type: "POST",
               url: "DeleteReq",
               data: {'id': $(this).attr('id')},
               dataType: "text",
               success: function (response) {
                   if (response == "success") {
                       $('#'+id+'.req-item').hide('normal');
                   }
                   else{
                       alert("deletion failed.")
                   }
               }
           });
        }
   });

    $("[name='edit-req']").click(function(){

        var engineerId;
        var reviewerId;

        $.ajax({
            type: "POST",
            url: "GetReqById",
            data: {'id': $(this).attr('id')},
            dataType: "json",
            async: false,
            success: function (data) {
                console.log(data);
                $("[name='edit-id']").val(data.id);
                $("[name='edit-name']").val(data.name);
                $("[name='edit-priority']").val(data.priority);
                $("[name='edit-stage']").val(data.stage);
                $("[name='edit-description']").val(data.description);
                var date = data.engineerDeadline;
                var engineerDeadline = formatDate(date);
                $("[name='edit-engineer-deadline']").val(engineerDeadline);
                date = data.reviewerDeadline;
                var reviewerDeadline = formatDate(date);
                $("[name='edit-reviewer-deadline']").val(reviewerDeadline);
                engineerId = data.engineerId;
                reviewerId = data.reviewerId;
            }
        });

        $.ajax({
            type: "POST",
            url: "GetEngineerReviewer",
            data: {'engineerId': engineerId, 'reviewerId':reviewerId},
            dataType: "json",
            async: false,
            success: function (data) {
                $("[name='edit-engineer']").val(data.engineer);
                $("[name='edit-reviewer']").val(data.reviewer);
            }
        });


        $('#edit').modal('show');
    });

    $("[name='engineer-complete']").click(function(){
        $.ajax({
            type: "POST",
            url: "GetReqById",
            data: {'id': $(this).attr('id')},
            dataType: "json",
            async: false,
            success: function (data) {
                console.log(data);
                $("[name='requirement-id']").val(data.id);
                $("[name='engineer-id']").val(data.engineerId);
            }
        });
        $('#engineer-complete').modal('show');
    });

    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth()+1),
            day = '' + (d.getDate()+1),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
    }

});