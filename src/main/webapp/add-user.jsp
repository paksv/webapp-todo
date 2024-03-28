<%@page contentType="text/html" pageEncoding="UTF-8" %>

<jsp:include page="layout/header.jsp" />
<div class="container col-md-5" style="height: calc(100vh - 128px);">
    <div class="card mt-2">
        <div class="card-header">
            Add User
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/users/add" method="post">
                <div>
                    <label class="col-form-label" for="firstName">First Name</label>
                    <div>
                        <input class="form-control" type="text" id="firstName" name="firstName" value="${requestScope.user.getFirstName()}" required>
                    </div>
                    <c:if test="${errors != null && errors.containsKey('firstName')}">
                        <div style="color: red">${errors.get('firstName')}</div>
                    </c:if>
                </div>
                <div>
                    <label class="col-form-label" for="lastName">Last Name</label>
                    <div>
                        <input class="form-control" type="text" id="lastName" name="lastName" value="${requestScope.user.getLastName()}" required>
                    </div>
                    <c:if test="${errors != null && errors.containsKey('lastName')}">
                        <div style="color: red">${errors.get('lastName')}</div>
                    </c:if>
                </div>
                <div>
                    <label class="col-form-label" for="username">Username</label>
                    <div>
                        <input class="form-control" type="text" id="username" name="username" value="${requestScope.user.getUsername()}" required>
                    </div>
                    <c:if test="${errors != null && errors.containsKey('username')}">
                        <div style="color: red">${errors.get('username')}</div>
                    </c:if>
                </div>
                <div>
                    <label class="col-form-label" for="password">Password</label>
                    <div>
                        <input class="form-control" type="password" minlength="4" id="password" name="password" value="${requestScope.user.getPassword()}" required>
                    </div>
                    <c:if test="${errors != null && errors.containsKey('password')}">
                        <div style="color: red">${errors.get('password')}</div>
                    </c:if>
                </div>
                <div class="d-flex align-items-center justify-content-center mt-2">
                    <input class="btn btn-primary" type="submit" value="${(user.getId() != null) && (user.getId() > 0) ? "Update" : "Save"}">
                </div>
                <input type="hidden" name="id" value="${user.getId() != null ? user.getId() : ""}">
            </form>
        </div>
    </div>
</div>
<jsp:include page="layout/footer.jsp" />