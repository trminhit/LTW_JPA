<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg navbar-dark navbar-modern fixed-top">
    <div class="container">
        <a class="navbar-brand fw-bold text-uppercase text-white" href="<c:url value='/admin/home'/>">
            <i class="fa-solid fa-user-shield text-primary me-2"></i>RoMinh <span class="fs-6 fw-light opacity-75">| Admin Panel</span>
        </a>
        
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link mx-2" href="<c:url value='/admin/home'/>">
                        <i class="fa-solid fa-house me-1"></i> Trang chủ
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link mx-2" href="<c:url value='/admin/category/list'/>">
                        <i class="fa-solid fa-layer-group me-1"></i> QL Danh mục
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link mx-2" href="<c:url value='/admin/video/list'/>">
                        <i class="fa-solid fa-film me-1"></i> QL Video
                    </a>
                </li>
            </ul>
            
            <ul class="navbar-nav ms-auto align-items-center">
                <c:if test="${sessionScope.account != null}">
                    <li class="nav-item me-3">
                        <a class="nav-link" href="<c:url value='/profile'/>">
                            <i class="fa-regular fa-circle-user me-2"></i>
                            Xin chào, <span class="text-primary fw-bold">${sessionScope.account.fullname}</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="btn-logout" href="<c:url value='/logout'/>">
                            Đăng xuất <i class="fa-solid fa-right-from-bracket"></i>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>