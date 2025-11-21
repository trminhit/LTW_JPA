<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Thêm Danh mục</title>

<div class="card shadow mb-4" style="max-width: 600px; margin: 0 auto;">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Thêm mới danh mục</h6>
    </div>
    <div class="card-body">
        <form action="<c:url value='/admin/category/add'/>" method="post" enctype="multipart/form-data">
            
            <div class="mb-3">
                <label class="form-label">Mã danh mục (Code):</label>
                <input type="text" class="form-control" name="categoryCode" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Tên danh mục:</label>
                <input type="text" class="form-control" name="categoryName" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Hình ảnh:</label>
                <input type="file" class="form-control" name="images">
            </div>

            <div class="mb-3">
                <label class="form-label">Trạng thái:</label>
                <select class="form-select" name="status">
                    <option value="1">Hoạt động</option>
                    <option value="0">Khóa</option>
                </select>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">Lưu lại</button>
                <a href="<c:url value='/admin/category/list'/>" class="btn btn-secondary">Hủy bỏ</a>
            </div>
        </form>
    </div>
</div>