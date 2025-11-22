<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <sitemesh:write property="head"/>
    <style>
        body { background-color: #f8f9fa; }
        .navbar { box-shadow: 0 2px 4px rgba(0,0,0,.1); }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div class="container">
            <a class="navbar-brand" href="#">WEB QUẢN LÝ</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ms-auto align-items-center">
                    <c:if test="${sessionScope.account != null}">
                        <li class="nav-item text-white me-3">
                            Xin chào, <b>${sessionScope.account.fullname}</b>
                        </li>
                        <li class="nav-item me-3">
                            <c:if test="${sessionScope.account.avatar != null}">
                                <img src="<c:url value='/image?fname=${sessionScope.account.avatar}'/>" 
                                     style="width: 35px; height: 35px; border-radius: 50%; object-fit: cover;">
                            </c:if>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-sm btn-danger" href="<c:url value='/logout'/>">Thoát</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container" style="min-height: 500px;">
        <sitemesh:write property="body"/>
    </div>

    <footer class="bg-light text-center text-lg-start mt-4 border-top py-3">
        <div class="text-center text-muted">© 2025 Copyright: LTWEB Project</div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>