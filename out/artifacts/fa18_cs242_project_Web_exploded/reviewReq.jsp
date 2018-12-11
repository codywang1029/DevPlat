
<div class="modal fade" id="reviewer-complete" tabindex="-1" role="dialog" aria-labelledby="createReqModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Review requirement</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form role='form' id='review-req' name='review-req' method="post" action="reviewHandler.jsp">
            <div class="modal-body">
                <input type='hidden' class='form-control' required name='requirement-id'/>
                <input type='hidden' class='form-control' required name='reviewer-id'/>

                <div class="form-group">
                    <label class="col-form-label">Comment:</label>
                    <textarea class="form-control" name="review-comment"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" id="review-submit">Finish Review</button>
            </div>
            </form>
        </div>
    </div>
</div>
