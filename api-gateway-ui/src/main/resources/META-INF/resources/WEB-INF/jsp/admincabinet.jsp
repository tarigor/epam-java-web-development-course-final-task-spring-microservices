<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local" var="local"/>
<html style="font-size: 16px;">
<head>
	<title><f:message
			key="admin.cabinet.page.name" bundle="${local}"/></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Admin-Cabinet.css" media="screen">
	<meta property="og:title" content="Admin Cabinet">
	<c:import url="common/head.jsp"/>
</head>
<body class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="sec-b41e" data-image-width="1280" data-image-height="839">
	<%--	<div class="u-align-left u-clearfix u-sheet u-sheet-1">--%>
	<div class="u-clearfix u-sheet u-sheet-1">
		<br>
		<h3 class="u-align-center u-custom-font u-font-georgia u-text u-text-default u-text-1" style="color: white">
			<f:message
					key="admin.cabinet.name" bundle="${local}"/></h3>
		<br>
		<a class="u-align-center u-custom-font u-font-georgia u-text u-text-default u-text-1"
		   style="color: white"><f:message
				key="admin.cabinet.requests" bundle="${local}"/></a>
		<table class="u-align-center table"
		       style="margin-left: auto;margin-right: auto;text-align: center">
			<thead class="u-grey-80 u-opacity u-opacity-70">
			<tr class="text-center" style="font-size: x-small">
				<th scope="col" style="text-align: center"><f:message key="request.id"
				                                                      bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="first.name"
				                                                      bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="last.name"
				                                                      bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="email"
				                                                      bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="persons"
				                                                      bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="room.class"
				                                                      bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="check.in.date"
				                                                      bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="check.out.date"
				                                                      bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="request.sent.time"
				                                                      bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="status.of.request"
				                                                      bundle="${local}"/></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${clientRequests}" var="clientRequest">
				<tr class=" text-center u-grey-10 u-opacity-85" style="font-size: x-small">
					<th scope="col" style="text-align: center">${clientRequest.getRequestID()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getFirstName()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getLastName()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getEmail()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getPersons()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getRoomClass()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getDateFrom()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getDateTo()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getRequestSentTime()}</th>
					<th scope="col" style="text-align: center"><f:message
							key="${clientRequest.getRequestOrderStatus().getDescription()}" bundle="${local}"/></th>
					<th style="text-align: center">
						<a href="<c:url value="${pageContext.request.contextPath}/command?name=request-handling
						&requestID=${clientRequest.getRequestID()}
						&firstName=${clientRequest.getFirstName()}
						&lastName=${clientRequest.getLastName()}
						&email=${clientRequest.getEmail()}
						&persons=${clientRequest.getPersons()}
						&roomClass=${clientRequest.getRoomClass()}
						&dateFrom=${clientRequest.getDateFrom()}
						&dateTo=${clientRequest.getDateTo()}
						"/>">
							<c:if test="${clientRequest.isProcessed()}">
								<button type="button" class="btn btn-success"><f:message
										key="admin.cabinet.room.selection"
										bundle="${local}"/></button>
							</c:if>
						</a>
					</th>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<br>
		<a class="u-align-center u-custom-font u-font-georgia u-text u-text-default u-text-1"
		   style="color: white"><f:message key="admin.cabinet.orders" bundle="${local}"/></a>
		<table class="u-align-center table"
		       style="margin-left: auto;margin-right: auto;text-align: center">
			<thead class="u-grey-80 u-opacity u-opacity-70">
			<tr class="text-center" style="font-size: x-small">
				<th scope="col" style="text-align: center"><f:message key="order.id" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="request.id" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="first.name" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="last.name" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="email" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="room.id" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="room.class" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="check.in.date" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="check.out.date" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="invoice.sent.time" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="payment.receiving.time"
				                                                      bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="status.of.booking" bundle="${local}"/></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${clientOrders}" var="clientOrder">
				<tr class=" text-center u-grey-10 u-opacity-85" style="font-size: x-small">
					<th scope="col" style="text-align: center">${clientOrder.getOrderID()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getRequestID()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getFirstName()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getLastName()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getEmail()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getRoomID()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getRoomClass()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getCheckInDate()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getCheckOutDate()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getInvoiceSentTime()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getPaymentReceivingTime()}</th>
					<th scope="col" style="text-align: center"><f:message
							key="${clientOrder.getRequestOrderStatus().getDescription()}" bundle="${local}"/></th>
					<th style="text-align: center">
						<a>
							<c:if test="${clientOrder.isCanBeCanceled()}">
								<a href="<c:url value="${pageContext.request.contextPath}/command?name=order_handling&type=reject&orderID=${clientOrder.getOrderID()}&roomID=${clientOrder.getRoomID()}"/>">
									<button type="button" class="btn btn-danger"><f:message
											key="reject"
											bundle="${local}"/></button>
								</a>
								<a href="<c:url value="${pageContext.request.contextPath}/command?name=order_handling&type=approve&orderID=${clientOrder.getOrderID()}&roomID=${clientOrder.getRoomID()}"/>">
									<button type="button" class="btn btn-success"><f:message
											key="approve"
											bundle="${local}"/></button>
								</a>
							</c:if>
						</a>
					</th>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<c:import url="common/serviceerror.jsp"/>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<%--<c:import url="common/cookies.jsp"/>--%>
</body>
</html>