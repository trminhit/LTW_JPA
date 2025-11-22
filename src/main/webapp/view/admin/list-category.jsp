<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Quản lý Danh mục</title>

<div class="container mt-4">
    <!-- Header: Tiêu đề + Nút quay về + Nút Thêm -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="text-primary fw-bold m-0">Danh sách Danh mục</h3>
        <div>
            <a href="<c:url value='/admin/home'/>" class="btn btn-secondary me-2">
                <i class="fa fa-arrow-left"></i> Về trang chủ
            </a>
            <a href="<c:url value='/admin/category/add'/>" class="btn btn-primary">
                <i class="fa fa-plus"></i> Thêm mới
            </a>
        </div>
    </div>
    
    <!-- Thông báo lỗi/thành công -->
    <c:if test="${not empty message}">
        <div class="alert alert-success alert-dismissible fade show shadow-sm" role="alert">
            <i class="fa fa-check-circle me-2"></i>${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger alert-dismissible fade show shadow-sm" role="alert">
            <i class="fa fa-exclamation-triangle me-2"></i>${error}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <!-- Bảng dữ liệu -->
    <div class="card shadow mb-4">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped mb-0 align-middle">
                    <thead class="table-primary text-center">
                        <tr>
                            <th>ID</th>
                            <th style="width: 100px;">Hình ảnh</th>
                            <th>Mã Code</th>
                            <th>Tên danh mục</th>
                            <th>Trạng thái</th>
                            <th style="width: 150px;">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cateList}" var="cate">
                            <tr>
                                <td class="text-center fw-bold">${cate.categoryId}</td>
                                
                                <td class="text-center p-2">
                                    <c:if test="${cate.images != null}">
                                        <img src="<c:url value='/image?fname=${cate.images}'/>" 
                                             class="img-fluid rounded shadow-sm" 
                                             style="max-height: 60px; object-fit: cover;">
                                    </c:if>
                                    <c:if test="${cate.images == null}">
                                        <span class="badge bg-light text-secondary border">No Image</span>
                                    </c:if>
                                </td>

                                <td>${cate.categorycode}</td>
                                <td class="fw-semibold">${cate.categoryName}</td>
                                
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${cate.status == 1}">
                                            <span class="badge bg-success">Hoạt động</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-secondary">Đã khóa</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td class="text-center">
                                    <a href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>" 
                                       class="btn btn-sm btn-warning text-white me-1" title="Sửa">
                                        <i class="fa fa-edit"></i> Sửa
                                    </a>
                                    <a href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>" 
                                       class="btn btn-sm btn-danger" title="Xóa"
                                       onclick="return confirm('Bạn chắc chắn muốn xóa danh mục này?');">
                                        <i class="fa fa-trash"></i> Xóa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty cateList}">
                            <tr>
                                <td colspan="6" class="text-center text-muted py-4">
                                    <i class="fa fa-folder-open me-1"></i> Chưa có danh mục nào.
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>