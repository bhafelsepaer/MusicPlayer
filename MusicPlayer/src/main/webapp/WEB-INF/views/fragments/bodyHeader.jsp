<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ page session="false"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<spring:url value="/resources/css/musicplayer.css"/>" />
<link rel="stylesheet"
	href="<spring:url value="/resources/css/tabNavigator.css"/>" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}

	$(document).ready(function() {
		$("#tabs li").click(function() {
			$("#tabs li").removeClass("active");
			$(this).addClass("active");
			$(".tab_content").hide();
			var selected_tab = $(this).find("a").attr("href");
			$(selected_tab).fadeIn();
			return false;
		});
	});
	function load() {
		<spring:url value="/resources/images/music-header.jpg" var="head" />
	}
</script>

</head>
<body>

	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<c:url value="/logout_music_store" var="logoutUrl" />

	<div id="head">
		<div class="image">
			<img alt="error" src="${head}">
		</div>
	</div>
	<div id="navbar">
		<div class="link">
			<security:authorize access="isAuthenticated()">
				<security:authentication property="principal.username"
					var="current_user" />
				<div class="current_user">
					<a
						href='<spring:url  value='/settings_account_user/${current_user}/profile'/>'>${current_user}</a>
				</div>
				<div class="logout">
					<a href="javascript:formSubmit()">Logout</a>
				</div>
			</security:authorize>
			<security:authorize access="isAnonymous()">
				<div class="login">
					<a href='<spring:url value="/login" /> '>Login</a>
				</div>
				<div class="registration">
					<a href='<spring:url value="/registration" /> '>Registration</a>
				</div>
			</security:authorize>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/fragments/sidebar.jsp" />
	<div id="tabBar">
		<ul id="tabs">
			<li class="active"><a href="#overwiewHome">Przeglad</a></li>
			<li><a href="#Genre">Genre</a></li>
			<li><a href="#Album">Album</a></li>
			<li><a href="#Artist">Artist</a></li>
		</ul>
		<div id="tabInner">
			<div id="overwiewHome" class="tab_content active">
			   <div id="GenreBlock">
				  <jsp:include page="/WEB-INF/views/musicplayer/overwiewHomePage.jsp" />
		       </div>
		           <div class="spaceBetweenPage"></div>
				<div id="AlbumBlock">
				    <jsp:include page="/WEB-INF/views/musicplayer/Show Album.jsp" />
				</div>
				   <div class="spaceBetweenPage"></div>
				<div id="ArtistBlock">
				    <jsp:include page="/WEB-INF/views/musicplayer/show_artist.jsp" />
				</div>
			</div>
			<div id="Genre" class="tab_content">
			     <jsp:include page="/WEB-INF/views/musicplayer/overwiewHomePage.jsp" />
			</div>
			<div id="Album" class="tab_content">
			<jsp:include page="/WEB-INF/views/musicplayer/Show Album.jsp" />
			</div>
			<div id="Artist" class="tab_content">
			   <jsp:include page="/WEB-INF/views/musicplayer/show_artist.jsp" />
			</div>
		</div>
	</div>

</body>
</html>