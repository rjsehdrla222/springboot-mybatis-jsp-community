<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>KDGCommunity</title>

    <jsp:include page="index-head.jsp"/>

</head>

<body>

<!-- Navigation -->
<%@ include file="index-nav.jsp" %>

<!-- Page Header -->
<header class="masthead" style="background-color:#002752; height:70px;">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container px-4 px-lg-5 my-5">
    <hr>
    <div class="btn-group btn-group-toggle btn-sm" data-toggle="buttons" style="float: right">
        <button type="button" class="btn btn-primary" onclick="location.href='create'">글쓰기</button>
    </div>
    <table class="table">
        <thead class="table-light">
        <tr>
            <th scope="col">번호</th>
            <th scope="col" style="width: 50%">제목</th>
            <th scope="col">글쓴이</th>
            <th scope="col">등록일</th>
            <th scope="col">조회</th>
        </tr>
        </thead>
        <h6>${total}개의 글</h6>
        <tbody>
        <c:forEach items="${list}" var="list">
            <tr onclick="location.href='/detail/${list.id}'">
                <td style="text-align: center">${list.id}</td>
                <td><a href="/detail/${list.id}"
                       style="color: black; backgorund-color:transparent; text-decoration:none" ;>${list.title}</a></td>
                <td><a href="/detail/${list.id}"
                       style="color: black; backgorund-color:transparent; text-decoration:none" ;>${list.name}</a></td>
                <td><fmt:formatDate value="${list.create_date}" dateStyle="default"/></td>
                <!--<td>($){list.create_date}</td>-->
                <td>${list.viewcnt}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    <br>
    <div class="row text-center" style="width: 100%">
        <div style="float:none; margin:0 auto">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="page=1" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach var="i" begin="1" end="${page}">
                    <li class="page-item"><a class="page-link" href="page=${i}">${i}</a></li>
                </c:forEach>
                <li class="page-item">
                    <a class="page-link" href="page=${page}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>

</div>

<hr>
<!-- Footer -->
<jsp:include page="index-footer.jsp"/>

<!-- Bootstrap core JavaScript -->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Custom scripts for this template -->
<script src="../js/clean-blog.min.js"></script>

</body>

</html>