<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="#">ViduJPA System</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="<c:url value='/home'/>">Trang chủ</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Sản phẩm</a></li>
      </ul>
      
      <ul class="navbar-nav">
        <c:if test="${sessionScope.account == null}">
            <li class="nav-item"><a class="nav-link" href="<c:url value='/login'/>">Đăng nhập</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/register'/>">Đăng ký</a></li>
        </c:if>
        <c:if test="${sessionScope.account != null}">
            <li class="nav-item"><span class="nav-link text-warning">Xin chào, ${sessionScope.account.fullname}</span></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/profile'/>">Profile</a></li>
            <li class="nav-item"><a class="nav-link btn btn-danger btn-sm text-white ms-2" href="<c:url value='/logout'/>">Thoát</a></li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>