<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Thêm Video (Manager)</title>

<div class="container mt-4" style="max-width: 700px;">
    <div class="card shadow">
        <div class="card-header bg-success text-white">
            <h4 class="m-0 fw-bold"><i class="fa-solid fa-video"></i> Thêm Video Mới</h4>
        </div>
        <div class="card-body">
            <form action="<c:url value='/manager/video/add'/>" method="post" enctype="multipart/form-data">
                
                <div class="mb-3">
                    <label class="form-label fw-bold">Mã Video (ID):</label>
                    <input type="text" name="videoId" class="form-control" placeholder="Nhập mã video..." required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Tiêu đề:</label>
                    <input type="text" name="title" class="form-control" placeholder="Nhập tiêu đề video..." required>
                </div>
                
                <div class="mb-3">
                    <label class="form-label fw-bold">Mô tả:</label>
                    <textarea name="description" class="form-control" rows="3" placeholder="Mô tả nội dung..."></textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Thuộc Danh mục:</label>
                    <select name="categoryId" class="form-select" required>
                        <option value="" disabled selected>-- Chọn danh mục của bạn --</option>
                        <c:forEach items="${listCate}" var="c">
                            <option value="${c.categoryId}">${c.categoryName}</option>
                        </c:forEach>
                    </select>
                    <div class="form-text text-muted">Chỉ hiển thị các danh mục do bạn quản lý.</div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Poster (Ảnh bìa):</label>
                    <input type="file" name="poster" class="form-control" accept="image/*">
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Trạng thái:</label>
                    <select name="active" class="form-select">
                        <option value="1">Hoạt động</option>
                        <option value="0">Khóa</option>
                    </select>
                </div>

                <div class="d-flex justify-content-between mt-4">
                    <a href="<c:url value='/manager/video/list'/>" class="btn btn-secondary">
                        <i class="fa-solid fa-arrow-left"></i> Quay lại
                    </a>
                    <button type="submit" class="btn btn-success fw-bold">
                        <i class="fa-solid fa-save"></i> Lưu Video
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>