<%@page contentType="text/html" pageEncoding="UTF-8" %>

<jsp:include page="layout/header.jsp" />
<div class="d-flex align-items-center justify-content-center" style="height: calc(100vh - 128px);">
    <form class="d-flex flex-column align-items-center" action="${pageContext.request.contextPath}/login" method="post">
        <div class="row my-2">
            <label class="form-label" for="username">Username</label>
            <div>
                <input class="form-control" type="text" id="username" name="username">
            </div>
        </div>
        <div class="row my-2">
            <label class="form-label" for="password">Password</label>
            <div>
                <input class="form-control" type="password" id="password" name="password">
            </div>
        </div>
        <div class="row my-2 d-flex justify-content-center">
            <input class="btn btn-primary" type="submit" value="Login" style="width: 150px">
        </div>
    </form>
</div>
<jsp:include page="layout/footer.jsp" />