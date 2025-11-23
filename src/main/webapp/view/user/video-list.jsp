<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Danh sách Phim</title>

<style>
    /* Style riêng cho Card Phim (Tối hơn Card Danh mục để nổi bật Poster) */
    .movie-card {
        background: rgba(30, 41, 59, 0.8); /* Kính tối màu */
        backdrop-filter: blur(15px);
        border: 1px solid rgba(255, 255, 255, 0.1);
        border-radius: 12px;
        overflow: hidden;
        transition: 0.3s;
    }
    .movie-card:hover { transform: translateY(-10px); box-shadow: 0 20px 40px rgba(0,0,0,0.5); border-color: #f5c518; }

    /* Nút Xem ngay */
    .btn-play {
        background: #e50914; /* Màu đỏ Netflix */
        color: white; font-weight: bold; border: none;
        width: 100%; padding: 8px; border-radius: 5px;
        transition: 0.2s;
    }
    .btn-play:hover { background: #f40612; color: white; transform: scale(1.05); }

    .btn-back-user {
        background: rgba(255,255,255,0.1); color: white;
        border: 1px solid rgba(255,255,255,0.2);
        padding: 8px 20px; border-radius: 30px; text-decoration: none;
    }
    .btn-back-user:hover { background: white; color: black; }
</style>

<div class="container">
    
    <div class="d-flex align-items-center mb-4 border-bottom border-secondary pb-3">
        <a href="<c:url value='/user/home'/>" class="btn-back-user me-3">
            <i class="fa-solid fa-arrow-left"></i> Quay lại
        </a>
        <h3 class="m-0 text-white text-uppercase fw-bold">
            <span class="text-white-50">Chủ đề:</span> 
            <span class="text-warning ms-2">${category.categoryName}</span>
        </h3>
    </div>

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
        <c:forEach items="${videoList}" var="v">
            <div class="col">
                <div class="card h-100 movie-card border-0">
                    <div class="position-relative" style="padding-top: 140%; overflow: hidden;">
                        <c:if test="${v.poster != null}">
                            <img src="<c:url value='/image?fname=${v.poster}'/>" 
                                 class="position-absolute top-0 start-0 w-100 h-100" 
                                 style="object-fit: cover; transition: 0.5s;">
                        </c:if>
                        <c:if test="${v.poster == null}">
                            <div class="position-absolute top-0 start-0 w-100 h-100 bg-secondary d-flex align-items-center justify-content-center text-white-50">
                                No Poster
                            </div>
                        </c:if>
                        
                        <div class="position-absolute top-0 end-0 m-2">
                            <span class="badge bg-dark bg-opacity-75 border border-secondary backdrop-blur">
                                <i class="fa-solid fa-eye text-info me-1"></i> ${v.views}
                            </span>
                        </div>
                    </div>

                    <div class="card-body d-flex flex-column bg-transparent">
                        <h6 class="card-title fw-bold text-white text-truncate mb-1" title="${v.title}">${v.title}</h6>
                        <p class="card-text small text-white-50 text-truncate mb-3">
                            ${v.description}
                        </p>
                        
                        <div class="mt-auto">
                            <a href="#" class="btn-play">
                                <i class="fa-solid fa-play me-2"></i>Xem Phim
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        
        <c:if test="${empty videoList}">
            <div class="col-12 text-center py-5">
                <div class="text-white-50 fs-5">
                    <i class="fa-solid fa-film fa-3x mb-3 opacity-50"></i><br>
                    Chưa có phim nào trong chủ đề này.
                </div>
            </div>
        </c:if>
    </div>
</div>