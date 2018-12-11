

<div class="modal fade" id="engineer-complete" tabindex="-1" role="dialog" aria-labelledby="createReqModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Complete requirement</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form role='form' id='complete-req' name='complete-req' method="post" action="completeHandler.jsp">
            <div class="modal-body">
                <input type='hidden' class='form-control' required name='requirement-id'/>
                <input type='hidden' class='form-control' required name='engineer-id'/>

                <div class="form-group">
                    <label class="col-form-label">Comment:</label>
                    <textarea class="form-control" name="complete-comment"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" id="complete-submit">Complete Requirement</button>
            </div>
            </form>
        </div>
    </div>
</div>
