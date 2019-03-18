<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/18 0018
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/img/favicon.ico">

    <title>Fixed top navbar example for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <style type="text/css">
        body {
            min-height: 75rem;
            padding-top: 4.5rem;
        }
    </style>
</head>
<script>
    function allChecked(a) {
        var checkbox1 = a.previousElementSibling;
        checkbox1.checked = ! checkbox1.checked;
        var p = a.parentNode;
        var checkboxes = p.getElementsByTagName("input");
        for(var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = checkbox1.checked;
        }
    }
</script>
<body>
<%@include file="/jsp/nav.jsp" %>
<div class="container">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/index">首页</a></li>
        <li class="breadcrumb-item">系统管理</li>
        <li class="breadcrumb-item active">用户权限</li>
    </ol>
    <div class="row">
        <div class="col-3">
            <ul id="orgTree" class="ztree"></ul>
        </div>
        <div class="col-9">
            <form action="/system/role/modifymodule" method="post">
                <input type="hidden" name="id" value="${id}">
                <c:forEach items="${modules}" var="module">
                    <p>
                    <c:if test="${list.contains(module)}">
                        <input type="checkbox" value="${module.id}" name="moc" checked>
                    </c:if>
                    <c:if test="${not list.contains(module)}">
                        <input type="checkbox" value="${module.id}" name="moc">
                    </c:if>
                        <input class="btn btn-info" type="button" name="mop" id="mop" value="${module.name}" onclick="allChecked(this)">
                        <c:forEach items="${module.children}" var="m">
                            <c:if test="${list.contains(m)}">
                                <input type="checkbox" name="moc" id="moc" value="${m.id}" checked> ${m.name}
                            </c:if>
                            <c:if test="${not list.contains(m)}">
                                <input type="checkbox" name="moc" value="${m.id}"> ${m.name}
                            </c:if>
                        </c:forEach>
                    </p>
                </c:forEach>
                <input class="btn btn-primary" type="submit" value="保存更改">
            </form>
        </div>
    </div>
</div>


<!-- Bootstrap core JavaScript
 ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery-3.2.1.slim.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug
<script src="../../../../assets/js/ie10-viewport-bug-workaround.js"></script>
-->
</body>
</html>
