<%@page contentType="text/html" pageEncoding="UTF-8" %>

<jsp:include page="layout/header.jsp" />
<h3>Login</h3>
<form class="" action="${pageContext.request.contextPath}/login" method="post">
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
    <div class="row my-2">
        <input class="btn btn-primary" type="submit" value="Login">
    </div>
</form>
<jsp:include page="layout/footer.jsp" />