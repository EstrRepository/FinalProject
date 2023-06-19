<%@ page import="by.itclass.model.utils.DateUtil" %>
<%@ page import="by.itclass.constants.AppConstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>News site</title>
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
		<div class="col-lg-8 m_corr">
			<div class="main_news">
				<img src="image/${newsList[0].image.name}" class="img-fluid">
				<h2>${newsList[0].title}</h2>
				<p>${newsList[0].text}
					<a href="<c:url value="<%= AppConstant.URL_GET_NEWS_CONT%>"/>?id=${newsList[0].id}&nextUrl=<%= AppConstant.NEWS_JSP %>">читать далее...</a>
				</p>
				<p>
					<span class="right_date">${DateUtil.format(newsList[0].date, DateUtil.OUTPUT_PATTERN)}</span>
				</p>
			</div>
		</div>
		<div class="col-lg-4 m_corr">
			<iframe frameborder="0" height="131" marginheight="0" marginwidth="0" scrolling="no" src="https://admin.myfin.by/outer/informer/brest/full" width="100%"></iframe>

			<hr>

			<h3>Топ новости за неделю:</h3>
			<!-- сделать вывод 3 лучших новостей за последнюю неделю - JAVA -->
			<c:forEach items="${topNewsList}" var="news" end="2">
				<div class="top_news">
					<h4> &#128077; ${news.likes} - ${news.title}</h4>
					<p>${news.text} <a href="<c:url value="<%= AppConstant.URL_GET_NEWS_CONT%>"/>?id=${news.id}&nextUrl=<%= AppConstant.NEWS_JSP%>">читать далее...</a>
					</p>
					<!-- В этой новости в превью выводится до 150 символов - JAVA -->
				</div>
			</c:forEach>

		</div>
	</div>

	${message}
	<c:forEach items="${newsList}" var="news" begin="1">
		<div class="row">
			<div class="col-md-12 m_corr">
				<div class="regular_news">
					<div class="row">
						<div class="col-4">
							<img src="image/${news.image.name}" class="img-fluid">
						</div>
						<div class="col-8">
							<h4>${news.title}</h4>
							<p>${news.text}
								<a href="<c:url value="<%= AppConstant.URL_GET_NEWS_CONT%>"/>?id=${news.id}&nextUrl=<%= AppConstant.NEWS_JSP %>">читать далее...</a></p>
							<p> &nbsp;
								<span class="right_date">${DateUtil.format(news.date, DateUtil.OUTPUT_PATTERN)}</span>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item">
				<a class="page-link" href="#" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item">
				<a class="page-link" href="#" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</ul>
	</nav>
</div>

<jsp:include page="add/footer.jsp"/>

<script src="js/bootstrap.js"></script>
<script src="js/jquery-3.6.0.js"></script>
</body>
</html>