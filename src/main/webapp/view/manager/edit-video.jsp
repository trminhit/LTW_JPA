<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Cập nhật Video (Manager)</title>

<div class="container mt-4" style="max-width: 700px;">
    <div class="card shadow">
        <div class="card-header bg-warning text-dark">
            <h4 class="m-0 fw-bold">Cập nhật Video</h4>
        </div>
        <div class="card-body">
            <form action="<c:url value='/manager/video/edit'/>" method="post" enctype="multipart/form-data">
                
                <div class="mb-3">
                    <label class="form-label fw-bold">Mã Video (ID):</label>
                    <input type="text" name="videoId" class="form-control bg-light" value="${video.videoId}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Tiêu đề:</label>
                    <input type="text" name="title" class="form-control" value="${video.title}" required>
                </div>
                
                <div class="mb-3">
                    <label class="form-label fw-bold">Mô tả:</label>
                    <textarea name="description" class="form-control" rows="4">${video.description}</textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Danh mục:</label>
                    <select name="categoryId" class="form-select">
                        <c:forEach items="${listCate}" var="c">
                            <option value="${c.categoryId}" ${c.categoryId == video.category.categoryId ? 'selected' : ''}>
                                ${c.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                    <div class="form-text">Chỉ hiển thị các danh mục do bạn quản lý.</div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Poster hiện tại:</label><br>
                    <c:if test="${video.poster != null}">
                         <img src="<c:url value='/image?fname=${video.poster}'/>" width="150" class="img-thumbnail mb-2 rounded">
                    </c:if>
                    <c:if test="${video.poster == null}">
                        <span class="badge bg-secondary">Chưa có ảnh</span>
                    </c:if>
                    <br>
                    <label class="form-label mt-2">Chọn Poster mới (nếu muốn thay đổi):</label>
                    <input type="file" name="poster" class="form-control" accept="image/*">
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Trạng thái:</label>
                    <select name="active" class="form-select">
                        <option value="1" ${video.active == 1 ? 'selected' : ''}>Hoạt động</option>
                        <option value="0" ${video.active == 0 ? 'selected' : ''}>Khóa</option>
                    </select>
                </div>

                <div class="d-flex justify-content-between mt-4">
                    <a href="<c:url value='/manager/video/list'/>" class="btn btn-secondary">
                        <i class="fa-solid fa-arrow-left"></i> Quay lại
                    </a>
                    <button type="submit" class="btn btn-warning text-dark fw-bold">
                        <i class="fa-solid fa-save"></i> Lưu thay đổi
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>