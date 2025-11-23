<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>Trang chủ Manager</title>

<style>
    :root {
        --glass-bg: rgba(255, 255, 255, 0.95); 
        --glass-border: 1px solid rgba(255, 255, 255, 0.5);
    }

    .card-glass {
        background: var(--glass-bg);
        backdrop-filter: blur(15px);
        border: var(--glass-border);
        border-radius: 16px; 
        box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2); 
        overflow: hidden;
        margin-bottom: 20px;
    }

    .card-header-glass {
        padding: 20px 25px;
        border-bottom: 1px solid rgba(0,0,0,0.05); 
        background: transparent;
    }

    .table-modern {
        width: 100%;
        margin-bottom: 0;
        border-collapse: collapse;
        color: #444; 
    }

    .table-modern thead th {
        background-color: rgba(0,0,0,0.02);
        color: #666;
        text-transform: uppercase;
        font-size: 0.75rem;
        font-weight: 700;
        letter-spacing: 1px;
        padding: 18px 15px;
        border-bottom: 1px solid rgba(0,0,0,0.1);
        border-top: none;
    }

    .table-modern tbody td {
        padding: 15px;
        vertical-align: middle;
        border-bottom: 1px solid rgba(0,0,0,0.05); 
        font-size: 0.95rem;
    }

    .table-modern tbody tr:last-child td {
        border-bottom: none;
    }

    .table-modern tbody tr {
        transition: background-color 0.2s ease;
    }
    .table-modern tbody tr:hover {
        background-color: rgba(0,0,0,0.03); 
    }

        .img-thumb { 
        width: 60px; height: 40px; object-fit: cover; 
        border-radius: 6px; 
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }

    .action-btn {
        width: 34px; height: 34px; 
        display: inline-flex; align-items: center; justify-content: center;
        border-radius: 50%; 
        margin: 0 4px; 
        transition: 0.2s; text-decoration: none;
        border: 1px solid transparent;
    }
    .action-btn:hover { transform: translateY(-2px); box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
    
    .btn-view { background: #e0f7fa; color: #00bcd4; }
    .btn-view:hover { background: #00bcd4; color: white; }

    .btn-edit { background: #fff8e1; color: #ffc107; }
    .btn-edit:hover { background: #ffc107; color: white; }

    .btn-delete { background: #ffebee; color: #ef5350; }
    .btn-delete:hover { background: #ef5350; color: white; }

    .btn-create {
        background: linear-gradient(135deg, #11998e, #38ef7d);
        border: none; font-weight: 600; color: white;
        padding: 10px 24px; border-radius: 50px;
        box-shadow: 0 4px 15px rgba(56, 239, 125, 0.3);
        text-decoration: none; display: inline-block; transition: 0.3s;
    }
    .btn-create:hover { transform: translateY(-2px); color: white; box-shadow: 0 6px 20px rgba(56, 239, 125, 0.5); }
    
    .code-badge {
        background: #f1f5f9;
        color: #475569;
        padding: 4px 8px;
        border-radius: 4px;
        font-family: 'Courier New', monospace;
        font-size: 0.85rem;
        font-weight: bold;
    }

</style>

<div class="card-glass p-4 mb-4 d-flex justify-content-between align-items-center flex-wrap">
    <div>
        <h3 class="fw-bold text-dark m-0 mb-1">
            <i class="fa-solid fa-hands-clapping text-warning me-2"></i>Chào mừng trở lại!
        </h3>
        <p class="text-secondary m-0 opacity-75">Quản lý không gian phim ảnh của bạn.</p>
    </div>
    <div class="mt-3 mt-md-0">
        <a href="<c:url value='/manager/category/add'/>" class="btn-create">
            <i class="fa-solid fa-plus me-2"></i>Tạo Danh Mục
        </a>
    </div>
</div>

<c:if test="${param.error != null}">
    <div class="alert alert-danger shadow-sm border-0 mb-4" style="border-radius: 12px;">
        <i class="fa-solid fa-triangle-exclamation me-2"></i>${param.error}
    </div>
</c:if>

<div class="card-glass">
    <div class="card-header-glass">
        <h6 class="m-0 fw-bold text-secondary text-uppercase ls-1">
            <i class="fa-solid fa-list me-2"></i>Danh sách danh mục
        </h6>
    </div>
    
    <div class="card-body p-0">
        <c:if test="${empty cateList}">
            <div class="text-center py-5">
                <i class="fa-solid fa-folder-open fa-3x text-secondary opacity-25 mb-3"></i>
                <p class="text-secondary">Chưa có danh mục nào.</p>
            </div>
        </c:if>

        <c:if test="${not empty cateList}">
            <div class="table-responsive">
                <table class="table-modern">
                    <thead>
                        <tr>
                            <th class="text-center" style="width: 80px;">ID</th>
                            <th class="text-center" style="width: 100px;">Ảnh</th>
                            <th class="text-center">Mã Code</th>
                            <th class="text-start">Tên danh mục</th>
                            <th class="text-center">Trạng thái</th>
                            <th class="text-center" style="width: 180px;">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cateList}" var="c">
                            <tr>
                                <td class="text-center text-muted fw-bold">#${c.categoryId}</td>
                                
                                <td class="text-center">
                                    <c:if test="${c.images != null}">
                                        <img src="<c:url value='/image?fname=${c.images}'/>" class="img-thumb">
                                    </c:if>
                                    <c:if test="${c.images == null}">
                                        <div class="img-thumb d-flex align-items-center justify-content-center bg-light text-muted border">
                                            <i class="fa-regular fa-image"></i>
                                        </div>
                                    </c:if>
                                </td>
                                
                                <td class="text-center">
                                    <span class="code-badge">${c.categorycode}</span>
                                </td>
                                
                                <td>
                                    <span class="fw-bold text-dark" style="font-size: 1rem;">${c.categoryName}</span>
                                </td>
                                
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${c.status == 1}">
                                            <span class="badge rounded-pill bg-success bg-opacity-10 text-success border border-success border-opacity-25 px-3">
                                                Hoạt động
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge rounded-pill bg-secondary bg-opacity-10 text-secondary border border-secondary border-opacity-25 px-3">
                                                Đã khóa
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                
                                <td class="text-center">
                                    <a href="<c:url value='/manager/video/list?categoryId=${c.categoryId}'/>" 
                                       class="action-btn btn-view" title="Xem Video" data-bs-toggle="tooltip">
                                        <i class="fa-solid fa-film"></i>
                                    </a>

                                    <a href="<c:url value='/manager/category/edit?id=${c.categoryId}'/>" 
                                       class="action-btn btn-edit" title="Sửa" data-bs-toggle="tooltip">
                                        <i class="fa-solid fa-pen"></i>
                                    </a>
                                    
                                    <a href="<c:url value='/manager/category/delete?id=${c.categoryId}'/>" 
                                       class="action-btn btn-delete" 
                                       onclick="return confirm('Bạn có chắc chắn muốn xóa?');" title="Xóa">
                                        <i class="fa-solid fa-trash-can"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>