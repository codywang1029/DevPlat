<div class="modal fade" id="create_req" tabindex="-1" role="dialog" aria-labelledby="createReqModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create new requirement</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form role='form' id='login-form' name='login-form' method="post" action="viewReq.jsp">
            <div class="modal-body">
                    <div class='form-group'>
                        <label>Name</label> <input type='text' class='form-control'
                                                       required name='name' placeholder='Enter requirement name'/>
                    </div>
                <div class="form-group">
                    <label>Priority</label>
                    <select class="custom-select" name="priority" required>
                        <option selected>Choose...</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </div>
                    <div class='form-group'>
                        <label>Engineer Assignment</label> <input type='text'
                                                       class='form-control' required name='engineer'
                                                       placeholder='Enter the name of the engineer'>
                        <span id="engineer-not-exist" style="color: red; display: none">No such person.</span>
                    </div>

                <div class="form-group">
                    <label >Deadline for engineer</label>
                    <input type="date" max="3000-12-31"
                        name="engineer-deadline"   min="" class="form-control">
                </div>
                    <div class='form-group'>
                        <label>Reviewer Assignment</label> <input type='text'
                                                              class='form-control' required name='reviewer'
                                                              placeholder='Enter the name of the reviewer'>
                        <span id="reviewer-not-exist" style="color: red; display: none">No such person.</span>
                     </div>
                <div class="form-group">
                    <label >Deadline for reviewer</label>
                    <input type="date" max="3000-12-31"
                           name="reviewer-deadline"   min="" class="form-control">
                </div>
                <div class="form-group">
                    <label class="col-form-label">Description:</label>
                    <textarea class="form-control" name="description"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Save changes</button>
            </div>
            </form>
        </div>
    </div>
</div>

<script  type="text/javascript">

    $(function(){
        var d = new Date();
        var minDate = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
        console.log(minDate);
        $('input[name="engineer-deadline"]').attr("min",minDate);
        $('input[name="reviewer-deadline"]').attr("min",minDate);
        $('input[name="engineer"]').blur(function(){
            $.ajax({
                type: "POST",
                url: "CheckUserExist",
                data:  { 'username':$(this).val()},
                dataType: "text",
                success: function(response){
                    if (response=="success"){
                        $('#engineer-not-exist').hide();
                    }
                    else{
                        $('#engineer-not-exist').show();
                    }
                }
            });
        });

        $('input[name="reviewer"]').blur(function(){
            $.ajax({
                type: "POST",
                url: "CheckUserExist",
                data:  { 'username':$(this).val()},
                dataType: "text",
                success: function(response){
                    if (response=="success"){
                        $('#reviewer-not-exist').hide();
                    }
                    else{
                        $('#reviewer-not-exist').show();
                    }
                }
            });
        });
    });


</script>