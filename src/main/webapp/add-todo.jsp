<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp" />
<div class="container col-md-5" style="height: calc(100vh - 128px);">
    <div class="card mt-2">
        <div class="card-header">
            Add ToDo
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/todos/add" method="post">
                <div>
                    <label class="col-form-label" for="title">Title</label>
                    <div>
                        <input class="form-control" type="text" id="title" name="title" value="${requestScope.todo.getTitle()}" required>
                    </div>
                    <c:if test="${errors != null && errors.containsKey('title')}">
                        <div style="color: red">${errors.get('title')}</div>
                    </c:if>
                </div>
                <div>
                    <label class="col-form-label" for="description">Description</label>
                    <div>
                        <input class="form-control" type="text" id="description" name="description" value="${requestScope.todo.getDescription()}" required>
                    </div>
                    <c:if test="${errors != null && errors.containsKey('description')}">
                        <div style="color: red">${errors.get('description')}</div>
                    </c:if>
                </div>
                <div>
                    <label class="col-form-label" for="date">Date</label>
                    <div>
                        <input class="form-control" type="date" id="date" name="date" value="${todo.getDate() != null ? todo.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}" required>
                    </div>
                    <c:if test="${errors != null && errors.containsKey('date')}">
                        <div style="color: red">${errors.get('date')}</div>
                    </c:if>
                </div>
                <div>
                    <label class="col-form-label" for="status">Status</label>
                    <div>
                        <select class="form-select" name="status" id="status" required>
                            <option value="0" ${todo.getIsDone() == false ? "selected" : ""}>In Progress</option>
                            <option value="1" ${todo.getIsDone() == true ? "selected" : ""}>Completed</option>
                        </select>
                    </div>
                </div>
                <div class="d-flex align-items-center justify-content-center mt-2">
                    <input class="btn btn-primary" type="submit" value="${(todo.getId() != null) && (todo.getId() > 0) ? "Update" : "Save"}">
                </div>
                <input type="hidden" name="id" value="${todo.getId() != null ? todo.getId() : ""}">
                <input type="hidden" name="id_user" value="${user.getId() != null ? user.getId() : ""}">
            </form>
        </div>
    </div>
</div>
<jsp:include page="layout/footer.jsp" />
