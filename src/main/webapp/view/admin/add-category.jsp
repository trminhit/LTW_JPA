<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Danh mục mới</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container mt-4">

	<h2>Thêm Danh mục mới</h2>
	
	<c:url value="/admin/category/add" var="addURL" />
	
	<form action="${addURL}" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="catename" class="form-label">Tên danh mục:</label>
			
			<input type="text" class="form-control" id="catename"
				name="catename" placeholder="Ví dụ: Quần Áo Trẻ Em" required>
		</div>

		<div class="mb-3">
			<label for="icon" class="form-label">Ảnh đại diện (Icon):</label>
			<input type="file" class="form-control" id="icon" name="icon">
		</div>

		<button type="submit" class="btn btn-primary">Thêm mới</button>
		<a href="<c:url value='/admin/category/list' />" class="btn btn-secondary">Hủy</a>
	</form>

</body>
</html>