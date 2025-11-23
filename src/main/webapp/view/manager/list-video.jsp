<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Quản lý Video</title>

<div class="container mt-4">
    
    <div class="d-flex justify-content-between align-items-center mb-3">
        <div>
            <h3 class="text-success m-0">
                <i class="fa-solid fa-film"></i>
                <c:if test="${currentCategory != null}">
                    VIDEO THUỘC: <span class="text-danger">${currentCategory.categoryName}</span>
                </c:if>
                <c:if test="${currentCategory == null}">
                    TẤT CẢ VIDEO CỦA TÔI
                </c:if>
            </h3>
        </div>
        <div>
            <c:if test="${currentCategory != null}">
                <a href="<c:url value='/manager/home'/>" class="btn btn-secondary me-2">
                    <i class="fa-solid fa-arrow-left"></i> Quay lại Danh mục
                </a>
            </c:if>
            
            <a href="<c:url value='/manager/video/add'/>" class="btn btn-success fw-bold">
                <i class="fa-solid fa-plus"></i> Thêm Video Mới
            </a>
        </div>
    </div>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="card shadow">
        <div class="card-body p-0">
            <table class="table table-bordered table-hover mb-0 align-middle">
                <thead class="table-light text-center">
                    <tr>
                        <th>ID</th>
                        <th style="width: 100px;">Poster</th>
                        <th>Tiêu đề</th>
                        <th>Danh mục</th>
                        <th>Lượt xem</th>
                        <th>Trạng thái</th>
                        <th style="width: 150px;">Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${videoList}" var="v">
                        <tr>
                            <td class="text-center fw-bold">${v.videoId}</td>
                            <td class="text-center p-2">
                                <c:if test="${v.poster != null}">
                                    <img src="<c:url value='/image?fname=${v.poster}'/>" 
                                         style="width: 80px; height: 50px; object-fit: cover; border-radius: 4px;">
                                </c:if>
                                <c:if test="${v.poster == null}">
                                    <span class="text-muted small">No Image</span>
                                </c:if>
                            </td>
                            <td class="fw-bold text-primary">${v.title}</td>
                            <td>
                                <span class="badge bg-info text-dark">${v.category.categoryName}</span>
                            </td>
                            <td class="text-center">${v.views}</td>
                            <td class="text-center">
                                <span class="badge ${v.active == 1 ? 'bg-success' : 'bg-secondary'}">
                                    ${v.active == 1 ? 'Hoạt động' : 'Khóa'}
                                </span>
                            </td>
                            <td class="text-center">
                                <a href="<c:url value='/manager/video/edit?id=${v.videoId}'/>" 
                                   class="btn btn-sm btn-warning text-white me-1" title="Sửa">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </a>
                                <a href="<c:url value='/manager/video/delete?id=${v.videoId}'/>" 
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
                                <i class="fa-solid fa-video-slash me-2"></i> Chưa có video nào trong danh sách này.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>