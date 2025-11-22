<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Danh sách Video</title>

<div class="container mt-4">
    <!-- Header với nút quay về -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3>Quản lý Video</h3>
        <div>
            <a href="<c:url value='/admin/home'/>" class="btn btn-secondary me-2">
                <i class="fa fa-arrow-left"></i> Về trang chủ
            </a>
            <a href="<c:url value='/admin/video/add'/>" class="btn btn-success">
                <i class="fa fa-plus"></i> Thêm Video
            </a>
        </div>
    </div>

    <!-- Form tìm kiếm -->
    <div class="row mb-3">
        <div class="col-md-6">
            <form action="<c:url value='/admin/video/list'/>" method="get" class="d-flex">
                <input type="text" name="keyword" class="form-control me-2" 
                       placeholder="Tìm theo tiêu đề..." value="${keyword}">
                <button type="submit" class="btn btn-primary">Tìm</button>
            </form>
        </div>
    </div>

    <!-- Bảng dữ liệu -->
    <table class="table table-bordered table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Poster</th>
                <th>Tiêu đề</th>
                <th>Danh mục</th>
                <th>Lượt xem</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${videoList}" var="v">
                <tr>
                    <td>${v.videoId}</td>
                    <td class="text-center">
                        <c:if test="${v.poster != null}">
                            <img src="<c:url value='/image?fname=${v.poster}'/>" width="80" height="50" style="object-fit: cover; border: 1px solid #ddd;">
                        </c:if>
                        <c:if test="${v.poster == null}">
                            <span class="text-muted small">No Image</span>
                        </c:if>
                    </td>
                    <td>${v.title}</td>
                    <td>
                        <span class="badge bg-info text-dark">${v.category.categoryName}</span>
                    </td>
                    <td>${v.views}</td>
                    <td>
                        <c:choose>
                            <c:when test="${v.active == 1}">
                                <span class="badge bg-success">Hiện</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger">Ẩn</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="<c:url value='/admin/video/edit?id=${v.videoId}'/>" class="btn btn-sm btn-warning text-white">Sửa</a>
                        <a href="<c:url value='/admin/video/delete?id=${v.videoId}'/>" 
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('Bạn chắc chắn muốn xóa video này?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty videoList}">
                <tr>
                    <td colspan="7" class="text-center text-muted">Không có dữ liệu video nào.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>