<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Quản lý Danh mục</title>

<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex justify-content-between align-items-center">
        <h6 class="m-0 font-weight-bold text-primary">Danh sách danh mục</h6>
        <a href="<c:url value='/admin/category/add'/>" class="btn btn-primary btn-sm">
            <i class="fa fa-plus"></i> Thêm mới
        </a>
    </div>
    
    <div class="card-body">
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <div class="table-responsive">
            <table class="table table-bordered table-hover" width="100%" cellspacing="0">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Hình ảnh</th>
                        <th>Mã Code</th>
                        <th>Tên danh mục</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cateList}" var="cate">
                        <tr>
                            <td>${cate.categoryId}</td>
                            
                            <td class="text-center">
                                <c:if test="${cate.images != null}">
                                    <img src="<c:url value='/image?fname=${cate.images}'/>" 
                                         width="80" height="80" 
                                         style="object-fit: cover; border-radius: 5px; border: 1px solid #eee;">
                                </c:if>
                                <c:if test="${cate.images == null}">
                                    <span class="text-muted small">Không có ảnh</span>
                                </c:if>
                            </td>

                            <td>${cate.categorycode}</td>
                            <td>${cate.categoryName}</td>
                            
                            <td>
                                <c:choose>
                                    <c:when test="${cate.status == 1}">
                                        <span class="badge bg-success">Hoạt động</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">Khóa</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <a href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>" class="btn btn-sm btn-warning text-white">
                                    Sửa
                                </a>
                                <a href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>" 
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('Bạn chắc chắn muốn xóa danh mục này?');">
                                    Xóa
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>