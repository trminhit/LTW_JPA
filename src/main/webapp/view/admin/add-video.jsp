<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Thêm Video</title>

<div class="container mt-4" style="max-width: 600px;">
    <h3>Thêm Video Mới</h3>
    
    <form action="<c:url value='/admin/video/add'/>" method="post" enctype="multipart/form-data">
        
        <div class="mb-3">
            <label>Video ID:</label>
            <input type="text" name="videoId" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Tiêu đề:</label>
            <input type="text" name="title" class="form-control" required>
        </div>
        
        <div class="mb-3">
            <label>Mô tả:</label>
            <textarea name="description" class="form-control" rows="3"></textarea>
        </div>

        <div class="mb-3">
            <label>Danh mục:</label>
            <select name="categoryId" class="form-control">
                <c:forEach items="${listCate}" var="c">
                    <option value="${c.categoryId}">${c.categoryName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Poster:</label>
            <input type="file" name="poster" class="form-control">
        </div>

        <div class="mb-3">
            <label>Trạng thái:</label>
            <select name="active" class="form-control">
                <option value="1">Hoạt động</option>
                <option value="0">Khóa</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Lưu lại</button>
        <a href="<c:url value='/admin/video/list'/>" class="btn btn-secondary">Hủy</a>
    </form>
</div>