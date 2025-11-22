<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Cập nhật Video</title>

<div class="container mt-4" style="max-width: 700px;">
    <h3>Cập nhật Video</h3>
    <form action="<c:url value='/admin/video/edit'/>" method="post" enctype="multipart/form-data">
        
        <div class="mb-3">
            <label>Video ID:</label>
            <input type="text" name="videoId" class="form-control" value="${video.videoId}" readonly>
        </div>

        <div class="mb-3">
            <label>Tiêu đề:</label>
            <input type="text" name="title" class="form-control" value="${video.title}" required>
        </div>
        
        <div class="mb-3">
            <label>Mô tả:</label>
            <textarea name="description" class="form-control" rows="3">${video.description}</textarea>
        </div>

        <div class="mb-3">
            <label>Danh mục:</label>
            <select name="categoryId" class="form-control">
                <c:forEach items="${listCate}" var="c">
                    <option value="${c.categoryId}" ${c.categoryId == video.category.categoryId ? 'selected' : ''}>
                        ${c.categoryName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Poster hiện tại:</label><br>
            <c:if test="${video.poster != null}">
                 <img src="<c:url value='/image?fname=${video.poster}'/>" width="120" class="img-thumbnail mb-2">
            </c:if>
            <br>
            <label>Chọn Poster mới (nếu muốn thay đổi):</label>
            <input type="file" name="poster" class="form-control">
        </div>

        <div class="mb-3">
            <label>Trạng thái:</label>
            <select name="active" class="form-control">
                <option value="1" ${video.active == 1 ? 'selected' : ''}>Hoạt động</option>
                <option value="0" ${video.active == 0 ? 'selected' : ''}>Khóa</option>
            </select>
        </div>

        <button type="submit" class="btn btn-warning">Cập nhật</button>
        <a href="<c:url value='/admin/video/list'/>" class="btn btn-secondary">Hủy</a>
    </form>
</div>