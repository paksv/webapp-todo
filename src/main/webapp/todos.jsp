<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/header.jsp" />
<div class="d-flex flex-column align-items-center justify-content-start" style="height: calc(100vh - 128px);">
    <div>
        <h3>Todos</h3>
        <a class="btn btn-primary btn-sm my-2" href="${pageContext.request.contextPath}/todos/add">Add ToDo</a>
    </div>
    <table class="table table-hover table-striped">
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Date</th>
            <th>Status</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${todos}" var="t">
            <tr>
                <td>${t.getTitle()}</td>
                <td>${t.getDescription()}</td>
                <td>${t.getDate()}</td>
                <td>${t.getIsDone() ? "Completed" : "In progress"}</td>
                <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/todos/add?id=${t.getId()}">Update</a></td>
                <td><a class="btn btn-sm btn-danger" onclick="return confirm('Are you sure to delete this todo?');" href="${pageContext.request.contextPath}/todos/delete?id=${t.getId()}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="layout/footer.jsp" />