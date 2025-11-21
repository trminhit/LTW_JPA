<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    
    <div class="card shadow">
        <div class="card-header bg-danger text-white">
            <h3>Khu vực Quản trị viên (Admin)</h3>
        </div>
        <div class="card-body">
            <h5 class="card-title">Xin chào, <b>${sessionScope.account.fullname}</b>!</h5>
            <p class="card-text text-muted">Bạn đang đăng nhập với quyền cao nhất (Role ID: 3).</p>
            
            <hr>
            
            <h5>Chức năng quản lý:</h5>
            <div class="row mt-3">
                <div class="col-md-4 mb-3">
                    <a href="<c:url value='/admin/category/list'/>" class="btn btn-outline-primary w-100 py-3">
                        Quản lý Danh mục
                    </a>
                </div>
                <div class="col-md-4 mb-3">
                    <a href="#" class="btn btn-outline-primary w-100 py-3">
                        Quản lý Sản phẩm
                    </a>
                </div>
                <div class="col-md-4 mb-3">
                    <a href="#" class="btn btn-outline-primary w-100 py-3">
                        Quản lý Người dùng
                    </a>
                </div>
            </div>
            
            <hr>
            <div class="d-flex justify-content-end">
                <a href="<c:url value='/logout'/>" class="btn btn-secondary">Đăng xuất</a>
            </div>
        </div>
    </div>

</body>
</html>