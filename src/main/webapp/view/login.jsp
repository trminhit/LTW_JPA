<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Nhập</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
body {
	/* Dùng nền gradient xanh đậm */
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
	/* Nền container màu xám đậm */
	background-color: #1f2833;
	padding: 35px;
	border-radius: 10px;
	/* Đổ bóng nhẹ với màu sáng (teal) để tạo hiệu ứng nổi */
	box-shadow: 0 4px 20px rgba(102, 252, 241, 0.1);
	width: 100%;
	max-width: 400px;
	border: 1px solid #45a29e; /* Viền màu teal */
}

.login-container h2 {
	font-size: 1.7rem;
	font-weight: 500;
	margin-bottom: 30px;
	/* Chữ màu teal sáng */
	color: #66fcf1;
}

.input-group-text {
	/* Icon nền trong suốt, viền màu teal */
	background-color: transparent;
	border: 1px solid #45a29e;
	border-right: none;
	border-radius: 0.25rem 0 0 0.25rem;
	color: #66fcf1; /* Màu của icon */
}

.form-control {
	/* Input nền trong suốt, viền màu teal */
	background-color: transparent;
	border: 1px solid #45a29e;
	border-left: none;
	border-radius: 0 0.25rem 0.25rem 0;
	color: #c5c6c7; /* Màu chữ khi gõ */
}

.form-control::placeholder {
	color: #c5c6c7; /* Màu chữ placeholder */
	opacity: 0.7;
}

.form-control:focus {
	/* Khi focus, đổi màu viền và đổ bóng */
	box-shadow: 0 0 0 0.25rem rgba(102, 252, 241, 0.25);
	border-color: #66fcf1;
	background-color: transparent;
	color: #ffffff;
}

/* Đổi màu chữ của alert */
.alert-danger {
	background-color: #3e2226;
	color: #f8d7da;
	border: 1px solid #842029;
}

.btn-primary {
	/* Nút màu teal */
	background-color: #45a29e;
	border-color: #45a29e;
	padding: 10px 0;
	font-size: 1.1rem;
	font-weight: 600;
	color: #0b0c10; /* Chữ trên nút màu đen */
}

.btn-primary:hover {
	/* Hiệu ứng hover sáng hơn */
	background-color: #66fcf1;
	border-color: #66fcf1;
	color: #0b0c10;
}

.form-links {
	text-align: right;
	margin-top: -10px;
	margin-bottom: 20px;
}

.form-links a {
	font-size: 0.9em;
	/* Link màu teal nhạt */
	color: #66fcf1;
	text-decoration: none;
}

.form-links a:hover {
	text-decoration: underline;
}

.register-text {
	text-align: center;
	margin-top: 30px;
	font-size: 0.95em;
	/* Chữ thường màu xám nhạt */
	color: #c5c6c7;
}

.register-text a {
	/* Link đăng ký màu teal sáng, đậm */
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
		<form action="login" method="post">
			<h2 class="text-center" style="color: #ffffff;">Đăng nhập</h2>
			<c:if test="${param.alert == 'reset_success'}">
				<div class="alert alert-success text-center" role="alert">Đổi
					mật khẩu thành công! Vui lòng đăng nhập.</div>
			</c:if>

			<c:if test="${not empty alert}">
				<div class="alert alert-danger text-center" role="alert">
					${alert}</div>
			</c:if>

			<div class="mb-3">
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-user"></i></span> <input
						type="text" placeholder="Tài khoản" name="username"
						id="usernameInput" class="form-control" value="${param.username}">
				</div>
			</div>

			<div class="mb-3">
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-lock"></i></span> <input
						type="password" placeholder="Mật khẩu" name="password"
						id="passwordInput" class="form-control">
				</div>
			</div>

			<div class="form-links">
				<a href="<c:url value='/forgot-password' />">Quên mật khẩu?</a>
			</div>

			<button type="submit" name="submit" class="btn btn-primary w-100">Đăng
				nhập</button>
		</form>

		<p class="register-text">
			Nếu bạn chưa có tài khoản trên hệ thống, thì hãy <a href="register">Đăng
				ký</a>
		</p>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>