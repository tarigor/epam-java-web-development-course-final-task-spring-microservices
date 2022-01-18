<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local" var="local"/>
<header class="u-clearfix u-grey-60 u-header" id="sec-d985" data-animation-name="" data-animation-duration="0"
        data-animation-delay="0" data-animation-direction="">
	<div class="u-clearfix u-sheet u-sheet-1">
		<p class="u-align-center u-text u-text-1">
			<c:choose>
				<c:when test="${sessionScope.user==null}">
					<a><f:message key="header.hello.guest" bundle="${local}"/>!</a>
				</c:when>
				<c:when test="${sessionScope.user.getUserType().contains('ADMIN')}">
					<a><f:message key="header.hello.admin" bundle="${local}"/>!</a>
				</c:when>
				<c:when test="${sessionScope.user.getUserType().contains('CLIENT')}">
					<a><f:message key="header.hello"
					              bundle="${local}"/> ${sessionScope.user.getUserFirstName()} ${sessionScope.user.getUserLastName()}!</a>
				</c:when>
			</c:choose>
		</p>
		<h3 class="u-custom-font u-font-georgia u-text u-text-default u-text-white u-text-2"><f:message
				key="header.hotel.name" bundle="${local}"/></h3>
		<nav class="u-menu u-menu-dropdown u-offcanvas u-menu-1" data-responsive-from="XL">
			<div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px; font-weight: 700;">
				<a class="u-button-style u-custom-active-border-color u-custom-active-color u-custom-border u-custom-border-color u-custom-borders u-custom-hover-border-color u-custom-hover-color u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-text-active-color u-custom-text-color u-custom-text-hover-color u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
				   href="#"
				   style="font-size: calc(1em + 8px); height: 24px; color: rgb(255, 255, 255) !important; padding: 4px 0px;">
					<svg class="u-svg-link" preserveAspectRatio="xMidYMin slice" viewBox="0 0 50 50" style="">
						<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg-ca5a"></use>
					</svg>
					<svg class="u-svg-content" viewBox="0 0 50 50" x="0px" y="0px" id="svg-ca5a"
					     style="enable-background:new 0 0 50 50;">
						<g>
							<rect y="3" width="50" height="2"></rect>
							<rect y="17" width="50" height="2"></rect>
							<rect y="31" width="50" height="2"></rect>
							<rect y="45" width="50" height="2"></rect>
						</g>
					</svg>
				</a>
			</div>
			
			<div class="u-custom-menu u-nav-container">
				<ul class="u-nav u-spacing-20 u-unstyled u-nav-1">
					<c:forEach items="${sessionScope.menuList}" var="item">
						<li class="u-nav-item">
							<a class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							   href="${pageContext.request.contextPath}${item.getCommand()}&lang=${sessionScope.language}"
							   href="${item.getCommand()}"
							   style="padding: 10px;">
								<f:message key="${item.getMenuItemDescription().getBundle()}"
								           bundle="${local}"/>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			
			<div class="u-custom-menu u-nav-container-collapse">
				<div class="u-container-style u-grey-60 u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
					<div class="u-inner-container-layout u-sidenav-overflow">
						<div class="u-menu-close"></div>
						<ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2">
							<c:forEach items="${sessionScope.menuList}" var="item">
								<li class="u-nav-item">
									<a class="u-button-style u-nav-link"
									   href="${item.getCommand()}&lang=${sessionScope.language}"
									   style="padding: 10px;">
										<f:message key="${item.getMenuItemDescription().getBundle()}"
										           bundle="${local}"/>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="u-grey-25 u-menu-overlay u-opacity u-opacity-70"></div>
			</div>
		</nav>
		<a href="/page?name=${sessionScope.lastpage}&lang=by_BY"
		   class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-btn u-button-style u-none u-text-palette-1-base u-btn-1">WRW</a>
		<a href="/page?name=${sessionScope.lastpage}&lang=en_US"
		   class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-btn u-button-style u-none u-text-palette-1-base u-btn-2">ENG</a>
	</div>
</header>