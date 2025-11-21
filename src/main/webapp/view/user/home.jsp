<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Trang chủ User</title>

<div class="jumbotron bg-light p-5 rounded shadow-sm">
    <h1 class="display-4">Xin chào, ${sessionScope.account.fullname}!</h1>
    <p class="lead">Chào mừng bạn quay trở lại hệ thống mua sắm.</p>
    <hr class="my-4">
    <p>Bạn đang đăng nhập với quyền: <b>User (Khách hàng)</b></p>
    
    <div class="d-grid gap-2 d-md-block">
        <a class="btn btn-primary btn-lg" href="#">Xem sản phẩm</a>
        <a class="btn btn-success btn-lg" href="#">Giỏ hàng</a>
        
        <a class="btn btn-info btn-lg text-white" href="<c:url value='/profile'/>" role="button">
            Thông tin cá nhân
        </a>
    </div>
</div>