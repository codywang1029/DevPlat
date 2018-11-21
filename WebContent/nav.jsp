<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="main.jsp">DevPlat</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="main.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Entry 1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Entry 2</a>
            </li>
            <li class="nav-item dropdown" name="dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="Requirement" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="height:100%;">
                    Requirement
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="margin-top:8px;display:none;">
                    <a class="dropdown-item" data-toggle="modal" data-target="#create_req">Create a Requirement</a>
                    <a class="dropdown-item" href="viewReq.jsp">My Requirement</a>
                    <a class="dropdown-item" href="#">Complete Requirement</a>
                    <a class="dropdown-item" href="#">My Review</a>
                </div>
            </li>

            <%@include file="create_req.jsp"%>

        </ul>
    </div>
</nav>