<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Ký Tài Khoản</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
body {
	background: linear-gradient(135deg, #1f2833, #0b0c10);
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	margin: 0;
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		"Helvetica Neue", Arial, sans-serif;
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
	font-size: 1.7rem;
	font-weight: 500;
	margin-bottom: 30px;
	color: #66fcf1;
}

.input-group-text {
	background-color: transparent;
	border: 1px solid #45a29e;
	border-right: none;
	border-radius: 0.25rem 0 0 0.25rem;
	color: #66fcf1;
}

.form-control {
	background-color: transparent;
	border: 1px solid #45a29e;
	border-left: none;
	border-radius: 0 0.25rem 0.25rem 0;
	color: #c5c6c7;
}

.form-control::placeholder {
	color: #c5c6c7;
	opacity: 0.7;
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
	padding: 10px 0;
	font-size: 1.1rem;
	font-weight: 600;
	color: #0b0c10;
}

.btn-primary:hover {
	background-color: #66fcf1;
	border-color: #66fcf1;
	color: #0b0c10;
}

.register-text {
	text-align: center;
	margin-top: 30px;
	font-size: 0.95em;
	color: #c5c6c7;
}

.register-text a {
	color: #66fcf1;
	text-decoration: none;
	font-weight: 600;
}

.register-text a:hover {
	text-decoration: underline;
}
</style>

</head>
<body>

	<div class="login-container">
		<form action="register" method="post">
			<h2 class="text-center" style="color: #ffffff;">Tạo tài khoản mới</h2>

			<c:if test="${not empty alert}">
				<div class="alert alert-danger text-center" role="alert">
					${alert}</div>
			</c:if>

			<div class="mb-3">
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-user"></i></span> <input
						type="text" placeholder="Tài khoản" name="username"
						class="form-control" required>
				</div>
			</div>

			<div class="mb-3">
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-id-card"></i></span>
					<input type="text" placeholder="Họ và Tên" name="fullname"
						class="form-control" required>
				</div>
			</div>

			<div class="mb-3">
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-envelope"></i></span>
					<input type="email" placeholder="Nhập Email" name="email"
						class="form-control" required>
				</div>
			</div>

			<div class="mb-3">
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-phone"></i></span> <input
						type="text" placeholder="Số điện thoại" name="phone"
						class="form-control" required>
				</div>
			</div>


			<div class="mb-3">
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-lock"></i></span> <input
						type="password" placeholder="Mật khẩu" name="password"
						class="form-control" required>
				</div>
			</div>

			<div class="mb-3">
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-lock"></i></span> <input
						type="password" placeholder="Nhập lại mật khẩu" name="re_password"
						class="form-control" required>
				</div>
			</div>

			<button type="submit" class="btn btn-primary w-100 mt-3">Tạo
				tài khoản</button>
		</form>

		<p class="register-text">
			Nếu bạn đã có tài khoản? <a href="login">Đăng nhập</a>
		</p>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>