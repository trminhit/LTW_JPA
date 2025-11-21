<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <div class="card">
        <div class="card-header bg-warning text-dark">
            <h3>Khu vực Quản lý (Manager)</h3>
        </div>
        <div class="card-body">
            <h5 class="card-title">Xin chào, ${sessionScope.account.fullname} (Manager)</h5>
            <p class="card-text">Đây là trang chức năng dành cho cấp quản lý.</p>
            
            <a href="#" class="btn btn-primary">Quản lý Đơn hàng</a>
            <a href="#" class="btn btn-primary">Xem Báo cáo</a>
            
            <hr>
            <a href="<c:url value='/logout'/>" class="btn btn-danger">Đăng xuất</a>
        </div>
    </div>
</body>
</html>