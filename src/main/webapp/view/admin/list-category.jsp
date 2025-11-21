<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý Danh mục</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container mt-4">

	<div class="d-flex justify-content-between align-items-center mb-3">
		<h2>Quản lý Danh mục sản phẩm</h2>

		<a href="<c:url value='/logout' />" class="btn btn-danger">Đăng
			xuất</a>
	</div>

	<a href="<c:url value='/admin/category/add' />"
		class="btn btn-primary mb-3"> Thêm danh mục mới </a>

	<table class="table table-bordered table-striped">
		<thead class="table-dark">
			<tr>
				<th>ID</th>
				<th>Hình ảnh</th>
				<th>Tên danh mục</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${cateList}" var="cate" varStatus="STT">
				<tr>
					<td>${cate.cateid}</td>

					<td><c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
						<img src="${imgUrl}" alt="Ảnh"
						style="width: 100px; height: auto; border: 1px solid #ddd;">
					</td>

					<td>${cate.catename}</td>

					<td><a
						href="<c:url value='/admin/category/edit?id=${cate.cateid }'/>">Sửa</a>
						| <a
						href="<c:url value='/admin/category/delete?id=${cate.cateid }'/>"
						onclick="return confirm('Bạn có chắc chắn muốn xóa không?')">Xóa</a>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

	<br />

</body>
</html>