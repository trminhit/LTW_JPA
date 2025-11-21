<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Admin Dashboard</title>

<div class="row justify-content-center">
    <div class="col-md-8">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">Thông tin Quản trị viên</h4>
            </div>
            <div class="card-body text-center">
                
                <div class="mb-3">
                    <c:if test="${sessionScope.account.avatar != null}">
                        <img src="<c:url value='/image?fname=${sessionScope.account.avatar}'/>" 
                             class="rounded-circle border border-3 border-primary p-1" 
                             width="150" height="150" style="object-fit: cover;">
                    </c:if>
                    <c:if test="${sessionScope.account.avatar == null}">
                        <img src="https://via.placeholder.com/150" class="rounded-circle border" width="150">
                    </c:if>
                </div>

                <h3>${sessionScope.account.fullname}</h3>
                <p class="text-muted">${sessionScope.account.email}</p>
                
                <hr>
                
                <div class="d-flex justify-content-center gap-3 mt-4">
                    <a href="<c:url value='/admin/category/list'/>" class="btn btn-outline-primary btn-lg px-4">
                        Quản lý Danh mục
                    </a>
                    <a href="#" class="btn btn-outline-success btn-lg px-4">
                        Quản lý Video
                    </a>
                    <a href="<c:url value='/profile'/>" class="btn btn-outline-info btn-lg px-4">
                        Cập nhật Profile
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>