<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Danh sách Video</title>

<div class="container mt-4">
    
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3><i class="fa-solid fa-video"></i> Quản lý Video</h3>
        <div>
            <a href="<c:url value='/admin/home'/>" class="btn btn-secondary me-2">
                <i class="fa-solid fa-arrow-left"></i> Về trang chủ
            </a>
            <a href="<c:url value='/admin/video/add'/>" class="btn btn-success">
                <i class="fa-solid fa-plus"></i> Thêm Video
            </a>
        </div>
    </div>

    <div class="card mb-4 shadow-sm">
        <div class="card-body py-3">
            <form action="<c:url value='/admin/video/list'/>" method="get" class="row g-3 align-items-center">
                <div class="col-auto">
                    <label class="col-form-label fw-bold">Tìm kiếm:</label>
                </div>
                <div class="col-auto flex-grow-1">
                    <input type="text" name="keyword" class="form-control" 
                           placeholder="Nhập tên video cần tìm..." 
                           value="${keyword}">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa-solid fa-search"></i> Tìm
                    </button>
                    <a href="<c:url value='/admin/video/list'/>" class="btn btn-outline-secondary">
                        <i class="fa-solid fa-rotate"></i> Reset
                    </a>
                </div>
            </form>
        </div>
    </div>

    <div class="card shadow-sm">
        <div class="card-body p-0">
            <table class="table table-striped table-hover mb-0 align-middle">
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
                                    <img src="<c:url value='/image?fname=${v.poster}'/>" width="80" 
                                         height="50" style="object-fit: cover; border-radius: 4px;">
                                </c:if>
                                <c:if test="${v.poster == null}">
                                    <span class="text-muted small">No Image</span>
                                </c:if>
                            </td>
                            <td class="fw-bold text-primary">${v.title}</td>
                            <td>
                                <span class="badge bg-info text-dark">${v.category.categoryName}</span>
                            </td>
                            <td>${v.views}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${v.active == 1}">
                                        <span class="badge bg-success">Hoạt động</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">Khóa</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="<c:url value='/admin/video/edit?id=${v.videoId}'/>" class="btn btn-sm btn-warning text-white" title="Sửa">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </a>
                                <a href="<c:url value='/admin/video/delete?id=${v.videoId}'/>" 
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('Bạn chắc chắn muốn xóa video này?');" title="Xóa">
                                   <i class="fa-solid fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <c:if test="${empty videoList}">
                        <tr>
                            <td colspan="7" class="text-center py-4 text-muted">
                                <i class="fa-solid fa-magnifying-glass"></i> Không tìm thấy video nào phù hợp.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>