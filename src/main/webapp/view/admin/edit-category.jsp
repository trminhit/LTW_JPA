<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Sửa Danh mục</title>

<div class="card shadow mb-4" style="max-width: 600px; margin: 0 auto;">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Cập nhật danh mục</h6>
    </div>
    <div class="card-body">
        <form action="<c:url value='/admin/category/edit'/>" method="post" enctype="multipart/form-data">
            
            <input type="hidden" name="categoryId" value="${cate.categoryId}">

            <div class="mb-3">
                <label class="form-label">Mã danh mục (Code):</label>
                <input type="text" class="form-control" name="categoryCode" value="${cate.categorycode}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Tên danh mục:</label>
                <input type="text" class="form-control" name="categoryName" value="${cate.categoryName}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Hình ảnh hiện tại:</label><br>
                <c:if test="${cate.images != null}">
                    <img src="<c:url value='/image?fname=${cate.images}'/>" width="100" class="mb-2 rounded border">
                </c:if>
                <input type="file" class="form-control" name="images">
                <small class="text-muted">Chọn ảnh mới nếu muốn thay đổi</small>
            </div>

            <div class="mb-3">
                <label class="form-label">Trạng thái:</label>
                <select class="form-select" name="status">
                    <option value="1" ${cate.status == 1 ? 'selected' : ''}>Hoạt động</option>
                    <option value="0" ${cate.status == 0 ? 'selected' : ''}>Khóa</option>
                </select>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-warning text-white">Cập nhật</button>
                <a href="<c:url value='/admin/category/list'/>" class="btn btn-secondary">Hủy bỏ</a>
            </div>
        </form>
    </div>
</div>