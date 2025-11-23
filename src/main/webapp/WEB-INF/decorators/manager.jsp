<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:write property="title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    
    <sitemesh:write property="head"/>
    
    <style>
        /* GLOBAL CSS */
        html, body {
            margin: 0 !important; padding: 0 !important;
            width: 100%; height: 100%;
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #0f2027, #203a43, #2c5364);
            background-attachment: fixed;
            color: #e0e0e0;
        }
        .app-container { display: flex; flex-direction: column; min-height: 100vh; }
        .content-wrapper { flex: 1; width: 100%; padding-top: 90px; padding-bottom: 40px; }

        /* HEADER & FOOTER STYLES*/
        .navbar-modern {
            background: rgba(255, 255, 255, 0.05);
            backdrop-filter: blur(10px);
            border-bottom: 1px solid rgba(255, 255, 255, 0.1);
            box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
            padding: 15px 0;
        }
        .nav-link { color: rgba(255,255,255,0.7) !important; font-weight: 500; transition: 0.3s; }
        .nav-link:hover { color: #fff !important; transform: translateY(-2px); }
        
        .btn-logout {
            border: 1px solid rgba(255, 107, 107, 0.5); color: #ff6b6b !important;
            border-radius: 30px; padding: 5px 20px; font-weight: 600; text-decoration: none;
            display: inline-flex; align-items: center; gap: 8px; transition: 0.3s; background: rgba(0,0,0,0.2);
        }
        .btn-logout:hover { background: #ff6b6b; color: white !important; box-shadow: 0 0 15px #ff6b6b; }

        footer {
            background: rgba(0, 0, 0, 0.3);
            backdrop-filter: blur(5px);
            border-top: 1px solid rgba(255, 255, 255, 0.05);
        }
    </style>
</head>
<body>
    <div class="app-container">
        
        <jsp:include page="/common/manager/header.jsp" />
    
        <div class="content-wrapper">
            <div class="container">
                <sitemesh:write property="body"/>
            </div>
        </div>
        
        <jsp:include page="/common/manager/footer.jsp" />

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>