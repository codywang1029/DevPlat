<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="createReqModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content" style="padding-bottom: 30px;">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Requirement Detail</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="container-fluid">
                <div class="row">
                    <div class="col">
                        <div class="attr-value">
                            <span class="attr">Name: </span>
                            <span class="value" id="detail-name"></span>
                        </div>

                        <div class="attr-value">
                            <span class="attr">Priority: </span>
                            <div class="bar" style="margin-right: 210px;" id="detail-priority">1</div>
                        </div>

                        <div class="attr-value" style="padding-right: 60px">
                            <span class="attr">Stage: </span>
                            <div class="bar" style="background-color: #FF5722" id="stage2">Reviewer</div>
                            <div class="bar" style="background-color: #FF5722" id="stage1">Engineer</div>
                            <div class="bar">Creator</div>
                        </div>

                        <div class="attr-value">
                            <span class="attr">Engineer: </span>
                            <span class="value" id="engineer-name"></span>
                        </div>
                        <div class="attr-value">
                            <span class="attr">Engineer Deadline: </span>
                            <span class="value" id="engineer-deadline"></span>
                        </div>

                        <div class="attr-value">
                            <span class="attr">Reviewer: </span>
                            <span class="value" id="reviewer-name"></span>
                        </div>
                        <div class="attr-value">
                            <span class="attr">Reviewer Deadline: </span>
                            <span class="value" id="reviewer-deadline"></span>
                        </div>
                    </div>
                    <div class="col">
                        <div class="text-card">
                            <span style="font-family: Sans-serif;font-size: 15px;font-weight: bold;">Description</span>
                            <div class = "text-card-text" id="detail-description">The creator did not add description to this requirement</div>
                        </div>
                    </div>
                </div>
                <div class="row" id = "engineer-completed" style="display:none">
                    <div class="col">
                    <div class="attr-value">
                        <span class="attr">Engineer Completed Time: </span>
                        <span class="value" id="completed-time"></span>
                    </div>
                    <div class="text-card-sm">
                        <span style="font-family: Sans-serif;font-size: 15px;font-weight: bold;">Engineer Comment</span>
                        <div class = "text-card-text" id="engineer-comment">The engineer did not add any comment</div>
                    </div>
                    </div>
                </div>
                <div class="row" id = "reviewer-completed" style="display:none">
                    <div class="col">
                        <div class="attr-value">
                            <span class="attr">Reviewer Completed Time: </span>
                            <span class="value" id="reviewed-time"></span>
                        </div>
                        <div class="text-card-sm">
                            <span style="font-family: Sans-serif;font-size: 15px;font-weight: bold;">Reviewer Comment</span>
                            <div class = "text-card-text" id="reviewer-comment">The reviewer did not add any comment</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
