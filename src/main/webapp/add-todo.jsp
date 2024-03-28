<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp" />
<h3>Add ToDo</h3>
<form action="${pageContext.request.contextPath}/todos/add" method="post">
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="title">Title</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="title" name="title" value="${requestScope.todo.getTitle()}">
        </div>
        <c:if test="${errors != null && errors.containsKey('title')}">
            <div style="color: red">${errors.get('title')}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="description">Description</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="description" name="description" value="${requestScope.todo.getDescription()}">
        </div>
        <c:if test="${errors != null && errors.containsKey('description')}">
            <div style="color: red">${errors.get('description')}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="date">Date</label>
        <div class="col-sm-4">
            <input class="form-control" type="date" id="date" name="date" value="${todo.getDate() != null ? todo.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
        </div>
        <c:if test="${errors != null && errors.containsKey('date')}">
            <div style="color: red">${errors.get('date')}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="status">Status</label>
        <div class="col-sm-4">
            <select class="form-select" name="status" id="status">
                <option value="0" ${todo.getIsDone() == false ? "selected" : ""}>In Progress</option>
                <option value="1" ${todo.getIsDone() == true ? "selected" : ""}>Completed</option>
            </select>
        </div>
    </div>
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary" type="submit" value="${(todo.getId() != null) && (todo.getId() > 0) ? "Update" : "Save"}">
        </div>
    </div>
    <input type="hidden" name="id" value="${todo.getId() != null ? todo.getId() : ""}">
    <input type="hidden" name="id_user" value="${user.getId() != null ? user.getId() : ""}">
</form>
<jsp:include page="layout/footer.jsp" />
