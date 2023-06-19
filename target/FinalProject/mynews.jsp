<%@ page import="by.itclass.constants.AppConstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>My news list</title>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-grid.css">
	<link rel="stylesheet" href="css/bootstrap-reboot.css">
	<link rel="stylesheet" href="css/bootstrap-utilities.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<jsp:include page="add/menu.jsp"/>

<div class="container" style="height: 1000px;">
	<div class="row">
		<div class="col-md-12 m_corr">
			<h2>Мои новости</h2>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="main_news">

				<table class="table table-bordered">
					<tr>
						<th>ID</th>
						<th>Заголовок</th>
						<th>Текст</th>
						<th>Img</th>
						<th>Лайки/Дизлайки</th>
						<th>действие</th>
					</tr>

					<c:forEach items="${newsList}" var="news">
					<tr>
						<td>${news.id}</td>
						<td>${news.title}</td>
						<td>${news.text}</td>
						<td><img src="image/${news.image.name}" width="100px"></td>
						<td>${news.likes}/${news.dislikes}</td>
						<td>
							<a href="<c:url value="<%= AppConstant.URL_GET_NEWS_CONT%>"/>?id=${news.id}&nextUrl=<%= AppConstant.NEWS_JSP %>"class="btn btn-success">Просмотреть</a><br><br>
							<a href="<c:url value="<%= AppConstant.URL_GET_NEWS_CONT%>"/>?id=${news.id}&nextUrl=<%= AppConstant.EDIT_NEWS_JSP %>" class="btn btn-warning">Редактировать</a><br><br>
							<a href="<c:url value="<%= AppConstant.URL_REMOVE_NEWS_CONT%>"/>?id=${news.id}" class="btn btn-danger">Удалить</a><br>
						</td>
						</c:forEach>

				</table>

			</div>
		</div>
	</div>

</div>

<jsp:include page="add/footer.jsp"/>


<script src="js/bootstrap.js"></script>
<script src="js/jquery-3.6.0.js"></script>
</body>
</html>