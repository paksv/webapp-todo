<%@page contentType="text/html" pageEncoding="UTF-8" %>

<jsp:include page="layout/header.jsp" />
<h1>Add User</h1>
<form action="${pageContext.request.contextPath}/users/add" method="post">
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="firstName">First Name</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="firstName" name="firstName" value="${requestScope.user.getFirstName()}">
        </div>
        <c:if test="${errors != null && errors.containsKey('firstName')}">
            <div style="color: red">${errors.get('firstName')}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="lastName">Last Name</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="lastName" name="lastName" value="${requestScope.user.getLastName()}">
        </div>
        <c:if test="${errors != null && errors.containsKey('lastName')}">
            <div style="color: red">${errors.get('lastName')}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="username">Username</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="username" name="username" value="${requestScope.user.getUsername()}">
        </div>
        <c:if test="${errors != null && errors.containsKey('username')}">
            <div style="color: red">${errors.get('username')}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="password">Password</label>
        <div class="col-sm-4">
            <input class="form-control" type="password" minlength="4" id="password" name="password" value="${requestScope.user.getPassword()}">
        </div>
        <c:if test="${errors != null && errors.containsKey('password')}">
            <div style="color: red">${errors.get('password')}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary" type="submit" value="${(user.getId() != null) && (user.getId() > 0) ? "Update" : "Save"}">
        </div>
    </div>
    <input type="hidden" name="id" value="${user.getId() != null ? user.getId() : ""}">
</form>
<jsp:include page="layout/footer.jsp" />