<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/header.jsp" />

<div class="d-flex flex-column align-items-center justify-content-start" style="height: calc(100vh - 128px);">
    <div>
        <h3>Users</h3>
        <a class="btn btn-primary btn-sm my-2" href="${pageContext.request.contextPath}/users/add">New User</a>
    </div>
    <table class="table table-hover table-striped">
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>
            <th>Password</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${requestScope.users}" var="u">
            <tr>
                <td>${u.getId()}</td>
                <td>${u.getFirstName()}</td>
                <td>${u.getLastName()}</td>
                <td>${u.getUsername()}</td>
                <td>${u.getPassword()}</td>
                <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/users/add?id=${u.getId()}">Update</a></td>
                <td><a class="btn btn-sm btn-danger" onclick="return confirm('Are you sure to delete this user?');" href="${pageContext.request.contextPath}/users/delete?id=${u.getId()}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="layout/footer.jsp" />
