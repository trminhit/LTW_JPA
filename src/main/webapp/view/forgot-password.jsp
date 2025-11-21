<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên Mật Khẩu</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    body {
        background: linear-gradient(135deg, #1f2833, #0b0c10);
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        margin: 0;
    }
    .login-container {
        background-color: #1f2833;
        padding: 35px;
        border-radius: 10px;
        box-shadow: 0 4px 20px rgba(102, 252, 241, 0.1);
        width: 100%;
        max-width: 400px;
        border: 1px solid #45a29e;
    }
    .login-container h2 {
        font-weight: 500;
        margin-bottom: 30px;
        color: #66fcf1;
    }
    .input-group-text {
        background-color: transparent;
        border: 1px solid #45a29e;
        border-right: none;
        color: #66fcf1;
    }
    .form-control {
        background-color: transparent;
        border: 1px solid #45a29e;
        color: #c5c6c7;
    }
    .form-control:focus {
        box-shadow: 0 0 0 0.25rem rgba(102, 252, 241, 0.25);
        border-color: #66fcf1;
        background-color: transparent;
        color: #ffffff;
    }
    .alert-danger {
        background-color: #3e2226;
        color: #f8d7da;
        border: 1px solid #842029;
    }
    .btn-primary {
        background-color: #45a29e;
        border-color: #45a29e;
        color: #0b0c10;
    }
    .btn-primary:hover {
        background-color: #66fcf1;
        border-color: #66fcf1;
    }
</style>
</head>
<body>
	<div class="login-container">
		<form action="forgot-password" method="post">
			<h2 class="text-center" style="color: #ffffff;">Tìm tài khoản</h2>
			
			<c:if test="${not empty alert}">
				<div class="alert alert-danger text-center" role="alert">
					${alert}
				</div>
			</c:if>
			
			<p style="color: #c5c6c7;">Vui lòng nhập email để tìm tài khoản của bạn.</p>
			
			<div class="mb-3">
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-envelope"></i></span>
					<input type="email" placeholder="Email" name="email"
						class="form-control" value="${param.email}" required> </div>
			</div>
			
			<button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
			
			<div class="text-center mt-3">
				<a href="<c:url value='/login' />" style="color: #66fcf1;">Hủy</a>
			</div>
		</form>
	</div>
</body>
</html>