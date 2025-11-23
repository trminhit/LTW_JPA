<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Trang chủ Admin</title>

<style>
    .card-img-wrapper {
        height: 160px; overflow: hidden; position: relative;
        border-bottom: 1px solid rgba(0,0,0,0.1);
    }
    .card-img-top { width: 100%; height: 100%; object-fit: cover; transition: 0.5s; }
    .category-card:hover .card-img-top { transform: scale(1.1); }
    
    .status-badge {
        position: absolute; top: 10px; right: 10px;
        backdrop-filter: blur(5px); box-shadow: 0 2px 5px rgba(0,0,0,0.3);
    }

    .btn-grid-action {
        width: 32px; height: 32px;
        display: inline-flex; align-items: center; justify-content: center;
        border-radius: 50%;
        background: rgba(0,0,0,0.05); color: #555;
        transition: 0.2s; text-decoration: none;
    }
    .btn-grid-action:hover { transform: scale(1.1); }
    
    .btn-view:hover { background: #0dcaf0; color: white; }
    .btn-edit:hover { background: #ffc107; color: white; }
    .btn-delete:hover { background: #dc3545; color: white; }
</style>

<div class="card-glass p-4 mb-4 d-flex justify-content-between align-items-center flex-wrap">
    <div>
        <h3 class="fw-bold text-dark m-0">
            <i class="fa-solid fa-crown text-primary me-2"></i>QUẢN TRỊ HỆ THỐNG
        </h3>
        <p class="text-secondary m-0 mt-1">Quản lý toàn bộ danh mục và video trên hệ thống.</p>
    </div>
    <div class="mt-3 mt-md-0">
        <a href="<c:url value='/admin/category/add'/>" class="btn-create">
            <i class="fa-solid fa-plus me-2"></i>Thêm Danh Mục
        </a>
    </div>
</div>

<div class="row g-4">
    <c:forEach items="${cateList}" var="c">
        <div class="col-12 col-md-6 col-lg-4 col-xl-3">
            <div class="card h-100 card-glass border-0 category-card">
                <div class="card-img-wrapper">
                    <c:if test="${c.images != null}">
                        <img src="<c:url value='/image?fname=${c.images}'/>" class="card-img-top">
                    </c:if>
                    <c:if test="${c.images == null}">
                        <div class="d-flex align-items-center justify-content-center h-100 bg-light">
                            <i class="fa-regular fa-image fa-2x text-muted"></i>
                        </div>
                    </c:if>
                    <span class="badge ${c.status == 1 ? 'bg-success' : 'bg-danger'} status-badge rounded-pill bg-opacity-75">
                        ${c.status == 1 ? 'Active' : 'Inactive'}
                    </span>
                </div>

                <div class="card-body p-3">
                    <div class="d-flex justify-content-between mb-2">
                        <span class="badge bg-light text-dark border">${c.categorycode}</span>
                        <small class="text-muted">ID: #${c.categoryId}</small>
                    </div>
                    <h5 class="card-title fw-bold text-dark mb-1 text-truncate" title="${c.categoryName}">
                        ${c.categoryName}
                    </h5>
                    <p class="card-text small text-muted mb-0">
                        <i class="fa-solid fa-user-tag me-1"></i> ${c.user.fullname}
                    </p>
                </div>

                <div class="card-footer bg-transparent border-top p-3 d-flex justify-content-around">
                    <a href="<c:url value='/admin/video/list?categoryId=${c.categoryId}'/>" 
                       class="btn-grid-action btn-view" title="Xem Video" data-bs-toggle="tooltip">
                        <i class="fa-solid fa-film"></i>
                    </a>
                    <a href="<c:url value='/admin/category/edit?id=${c.categoryId}'/>" 
                       class="btn-grid-action btn-edit" title="Sửa" data-bs-toggle="tooltip">
                        <i class="fa-solid fa-pen"></i>
                    </a>
                    <a href="<c:url value='/admin/category/delete?id=${c.categoryId}'/>" 
                       class="btn-grid-action btn-delete" 
                       onclick="return confirm('Bạn chắc chắn muốn xóa?');" title="Xóa">
                        <i class="fa-solid fa-trash"></i>
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>