<%@page contentType="text/html" pageEncoding="UTF-8" %>

<jsp:include page="layout/header.jsp" />
<div class="d-flex align-items-center justify-content-center" style="height: calc(100vh - 128px);">
    <h1>${requestScope.title}</h1>
</div>
<jsp:include page="layout/footer.jsp" />