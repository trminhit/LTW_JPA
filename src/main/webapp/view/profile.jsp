<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>
</head>
<body> <div class="container mt-5">
        <div class="card w-50 mx-auto shadow">
            <div class="card-header bg-info text-white text-center">
                <h3 class="m-0">Cập nhật hồ sơ</h3>
            </div>
            <div class="card-body">
                <c:if test="${not empty message}">
                     <div class="alert alert-success">${message}</div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <form action="<c:url value='/profile'/>" method="post" enctype="multipart/form-data">
                    <div class="text-center mb-4">
                        <c:if test="${sessionScope.account.avatar != null}">
                            <img src="<c:url value='/image?fname=${sessionScope.account.avatar}'/>" 
                                 class="rounded-circle border shadow-sm" 
                                 width="150" height="150" style="object-fit: cover;">
                        </c:if>
                        <c:if test="${sessionScope.account.avatar == null}">
                            <img src="https://via.placeholder.com/150" class="rounded-circle" width="150">
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <label class="fw-bold">Tài khoản:</label>
                        <input type="text" class="form-control bg-light" value="${sessionScope.account.username}" readonly>
                    </div>

                    <div class="mb-3">
                        <label class="fw-bold">Họ và Tên:</label>
                        <input type="text" name="fullname" class="form-control" value="${sessionScope.account.fullname}" required>
                    </div>

                    <div class="mb-3">
                         <label class="fw-bold">Số điện thoại:</label>
                        <input type="text" name="phone" class="form-control" value="${sessionScope.account.phone}">
                    </div>

                    <div class="mb-3">
                        <label class="fw-bold">Đổi ảnh đại diện:</label>
                        <input type="file" name="avatar" class="form-control" accept="image/*">
                    </div>

                    <button type="submit" class="btn btn-primary w-100 fw-bold">Lưu thay đổi</button>
                </form>
                
                <div class="mt-3 text-center">
                    <a href="<c:url value='/waiting'/>" class="text-decoration-none">Quay lại trang chủ</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>