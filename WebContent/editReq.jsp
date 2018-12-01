

<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="createReqModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Edit requirement</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form role='form' id='create-req' name='create-req' method="post" action="viewReq.jsp">
            <div class="modal-body">
                <input type='hidden' class='form-control' required name='edit-id'/>
                    <div class='form-group'>
                        <label>Name</label> <input type='text' class='form-control'
                                                       required name='edit-name' readonly/>
                    </div>
                <div class="form-group">
                    <label>Priority</label>
                    <select class="custom-select" name="edit-priority"  required>
                        <option selected>Choose...</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Stage</label>
                    <select class="custom-select" name="edit-stage"  required>
                        <option selected>Choose...</option>
                        <option value="0">Created</option>
                        <option value="1">Engineer Completed</option>
                        <option value="2">Reviewer Completed</option>
                    </select>
                </div>
                    <div class='form-group'>
                        <label>Engineer Assignment</label> <input type='text'
                                                       class='form-control' required name='edit-engineer'
                                                       >
                        <span id="edit-engineer-not-exist" style="color: red; display: none">No such person.</span>
                    </div>

                <div class="form-group">
                    <label >Deadline for engineer</label>
                    <input type="date" max="3000-12-31"
                        name="edit-engineer-deadline"   min="" class="form-control">
                </div>
                    <div class='form-group'>
                        <label>Reviewer Assignment</label> <input type='text'
                                                              class='form-control' required name='edit-reviewer'
                                                              >
                        <span id="edit-reviewer-not-exist" style="color: red; display: none">No such person.</span>
                     </div>
                <div class="form-group">
                    <label >Deadline for reviewer</label>
                    <input type="date" max="3000-12-31"
                           name="edit-reviewer-deadline"   min="" class="form-control">
                </div>
                <div class="form-group">
                    <label class="col-form-label">Description:</label>
                    <textarea class="form-control" name="edit-description"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" id="edit-submit">Save changes</button>
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
        $('input[name="edit-engineer-deadline"]').attr("min",minDate);
        $('input[name="edit-reviewer-deadline"]').attr("min",minDate);

        $('input[name="edit-engineer"]').blur(function(){
            $.ajax({
                type: "POST",
                url: "CheckUserExist",
                data:  { 'username':$(this).val()},
                dataType: "text",
                success: function(response){
                    if (response=="success"){
                        $('#edit-engineer-not-exist').hide();
                    }
                    else{
                        $('#edit-engineer-not-exist').show();
                    }
                }
            });
        });

        $('input[name="edit-reviewer"]').blur(function(){
            $.ajax({
                type: "POST",
                url: "CheckUserExist",
                data:  { 'username':$(this).val()},
                dataType: "text",
                success: function(response){
                    if (response=="success"){
                        $('#edit-reviewer-not-exist').hide();
                    }
                    else{
                        $('#edit-reviewer-not-exist').show();
                    }
                }
            });
        });
    });


</script>