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

    $("[name='close-req']").click(function(){
        if (confirm('Do you want to close the requirement?')) {
            $.ajax({
                type: "POST",
                url: "CloseReq",
                data: {'id': $(this).attr('id')},
                dataType: "text",
                success: function (response) {
                    if (response == "success") {
                        location.reload();
                    }
                    else{
                        alert("Error")
                    }
                }
            });
        }
    });

    $("[name='reviewer-complete']").click(function(){
        $.ajax({
            type: "POST",
            url: "GetReqById",
            data: {'id': $(this).attr('id')},
            dataType: "json",
            async: false,
            success: function (data) {
                console.log(data);
                $("[name='requirement-id']").val(data.id);
                $("[name='reviewer-id']").val(data.engineerId);
            }
        });
        $('#reviewer-complete').modal('show');
    });


    $(".req-icon,.req-name").click(function(){
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
                $("#detail-name").text(data.name);
                $("#detail-priority").text(data.priority);
                if (data.priority==1){
                    $("#detail-priority").css('background-color','#009688');
                }
                else if(data.priority==2){
                    $("#detail-priority").css('background-color','#FFB800');
                }
                else if(data.priority==3){
                    $("#detail-priority").css('background-color','#FF5722');
                }
                if (data.stage>0) {
                    $("#stage1").css('background-color', '#009688');
                    $.ajax({
                        type: "POST",
                        url: "GetCompletedReq",
                        data: {'id': id},
                        dataType: "json",
                        async: false,
                        success: function (req) {
                            $("#completed-time").text(req.completedDate);
                            $("#engineer-comment").text(req.comment);
                            $("#engineer-completed").show();
                        }
                    });

                    console.log("stagehere: "+data.stage);
                    if (data.stage >1) {
                        $("#stage2").css('background-color', '#009688');
                        $.ajax({
                            type: "POST",
                            url: "GetReviewedReq",
                            data: {'id': id},
                            dataType: "json",
                            async: false,
                            success: function (req) {
                                $("#reviewed-time").text(req.reviewedDate);
                                $("#reviewer-comment").text(req.comment);
                                $("#reviewer-completed").show();
                            }
                        });
                    }
                    else{
                        $("#stage2").css('background-color', '#FF5722');
                        $("#reviewer-completed").hide();
                    }
                }
                else{
                    $("#engineer-completed").hide();
                    $("#reviewer-completed").hide();
                    $("#stage2").css('background-color', '#FF5722');
                    $("#stage1").css('background-color', '#FF5722');
                }
                engineerId = data.engineerId;
                reviewerId = data.reviewerId;
                $("#detail-description").text(data.description);
                $("#engineer-deadline").text(data.engineerDeadline);
                $("#reviewer-deadline").text(data.reviewerDeadline);
            }
        });

        $.ajax({
            type: "POST",
            url: "GetEngineerReviewer",
            data: {'engineerId': engineerId, 'reviewerId':reviewerId},
            dataType: "json",
            async: false,
            success: function (data) {
                $("#engineer-name").text(data.engineer);
                $("#reviewer-name").text(data.reviewer);
            }
        });

        $('#detail').modal('show');
    });



    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth()+1),
            day = '' + (d.getDate()+2),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
    }

});