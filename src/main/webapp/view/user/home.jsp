<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Trang chủ - RoMinh</title>

<style>
    /* Card Kính sáng cho User */
    .card-glass {
        background: rgba(255, 255, 255, 0.95);
        backdrop-filter: blur(15px);
        border: 1px solid rgba(255, 255, 255, 0.5);
        border-radius: 16px;
        box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
        overflow: hidden;
        transition: transform 0.3s ease;
    }
    .card-glass:hover { transform: translateY(-10px); box-shadow: 0 15px 50px rgba(0,0,0,0.4); }

    /* Banner chào mừng */
    .welcome-banner {
        background: rgba(255, 255, 255, 0.1);
        border: 1px solid rgba(255, 255, 255, 0.2);
        backdrop-filter: blur(10px);
        border-radius: 16px;
        padding: 30px;
        margin-bottom: 40px;
    }

    /* Nút xem chủ đề */
    .btn-theme {
        background: linear-gradient(135deg, #11998e, #38ef7d);
        border: none; color: white; font-weight: 600;
        width: 100%; padding: 10px; border-radius: 30px;
        transition: 0.3s;
    }
    .btn-theme:hover { transform: scale(1.02); color: white; box-shadow: 0 5px 15px rgba(56, 239, 125, 0.4); }
</style>

<div class="welcome-banner text-center">
    <h2 class="fw-bold text-white m-0 text-uppercase ls-2">
        <i class="fa-solid fa-clapperboard text-warning me-3"></i>Khám phá thế giới phim
    </h2>
    <p class="text-white-50 mt-2 mb-0">Tuyển tập những bộ phim hay nhất dành cho bạn</p>
</div>

<div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4">
    <c:forEach items="${cateList}" var="c">
        <div class="col">
            <div class="card h-100 card-glass border-0">
                <div style="height: 220px; overflow: hidden; position: relative;">
                    <c:if test="${c.images != null}">
                        <img src="<c:url value='/image?fname=${c.images}'/>" class="w-100 h-100" style="object-fit: cover;">
                    </c:if>
                    <c:if test="${c.images == null}">
                        <div class="d-flex align-items-center justify-content-center h-100 bg-light text-muted">
                            <i class="fa-regular fa-image fa-3x"></i>
                        </div>
                    </c:if>
                    <div class="position-absolute bottom-0 start-0 w-100 p-3" 
                         style="background: linear-gradient(to top, rgba(0,0,0,0.8), transparent);">
                        <span class="badge bg-warning text-dark shadow-sm">${c.categorycode}</span>
                    </div>
                </div>
                
                <div class="card-body text-center p-4">
                    <h5 class="card-title fw-bold text-dark mb-3 text-truncate" title="${c.categoryName}">
                        ${c.categoryName}
                    </h5>
                    <a href="<c:url value='/video?categoryId=${c.categoryId}'/>" class="btn btn-theme">
                        <i class="fa-solid fa-eye me-2"></i>Xem chủ đề
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>