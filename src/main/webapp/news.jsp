<%@ page import="by.itclass.model.utils.DateUtil" %>
<%@ page import="by.itclass.constants.AppConstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>News</title>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-grid.css">
	<link rel="stylesheet" href="css/bootstrap-reboot.css">
	<link rel="stylesheet" href="css/bootstrap-utilities.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<jsp:include page="add/menu.jsp"/>

<div class="container">
	<div class="row">
		<div class="col-lg-12 m_corr">
			<div class="main_news">

				<center>
					<img src="image/${news.image.name}" class="img-fluid" style="max-height: 400px;">
				</center>
				<br><hr>
				<h2>
					<form method="post" action="<c:url value="<%= AppConstant.URL_LIKE_NEWS_CONT%>"/> ">
						<input type="hidden" name="<%= AppConstant.ID_LABEL%>" value="${news.id}">
						<input type="hidden" name="<%= AppConstant.NEXT_URL_LABEL%>" value="<%= AppConstant.NEWS_JSP%>">
						<button name="<%= AppConstant.ACTION_LABEL%>" value="<%= AppConstant.LIKE_ACTION%>" class="like_button">&#128077;</button>
						${news.likes} | ${news.dislikes}
						<button name="<%= AppConstant.ACTION_LABEL%>" value="<%= AppConstant.DISLIKE_ACTION%>" class="like_button">&#128078;</button>
						${news.title}
					</form>

				</h2>
				<p>${news.text}
					<br><br>
					<%--						<span class="right_date">Author: ${news.author} | ${DateUtil.format(news.date, DateUtil.OUTPUT_PATTERN)}</span>--%>
				</p>
				<p>
					<a href="<c:url value="<%= AppConstant.URL_MY_NEWS_CONT%>"/>">Назад</a>
				</p>
			</div>
		</div>
	</div>
</div>


<jsp:include page="add/footer.jsp"/>


<script src="js/bootstrap.js"></script>
<script src="js/jquery-3.6.0.js"></script>
</body>
</html>