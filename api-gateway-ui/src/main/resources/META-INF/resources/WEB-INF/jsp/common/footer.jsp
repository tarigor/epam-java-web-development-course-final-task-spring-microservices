<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 04/11/2021
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local" var="local"/>
<footer class="u-align-center u-clearfix u-footer u-grey-60 u-footer" id="sec-ac13">
	<div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
		<p class="u-align-center u-custom-font u-font-georgia u-small-text u-text u-text-variant u-text-1">
			<f:message key="footer.creator" bundle="${local}"/>
			<br>Igor Taren
		</p>
	</div>
</footer>
