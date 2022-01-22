<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local" var="local"/>

<html style="font-size: 16px;">
<head>
	<title><f:message key="client.cabinet.page.name"
	                  bundle="${local}"/></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Client-Cabinet.css" media="screen">
	<meta property="og:title" content="Client Cabinet">
	<c:import url="common/head.jsp"/>
</head>
<body class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="sec-b41e" data-image-width="1280" data-image-height="839">
	<div class="u-align-left u-clearfix u-sheet u-sheet-1">
		<br>
		<h3 class="u-align-center u-custom-font u-font-georgia u-text u-text-default u-text-1" style="color: white">
			<f:message
					key="client.cabinet.page.name" bundle="${local}"/></h3>
		<br>
		<a class="u-align-center u-custom-font u-font-georgia u-text u-text-default u-text-1"
		   style="color: white"><f:message
				key="admin.cabinet.requests" bundle="${local}"/></a>
		<table class="u-align-center  table"
		       style="margin-left: auto;margin-right: auto;text-align: center">
			<thead class="u-grey-80 u-opacity u-opacity-70">
			<tr class="text-center">
				<th scope="col" style="text-align: center"><f:message key="request.id"
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
				<tr class=" text-center u-grey-10 u-opacity-85">
					<th scope="col" style="text-align: center">${clientRequest.getRequest().getRequestId()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getRequest().getPersonsAmount()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getRequest().getRoomClass()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getRequest().getCheckInDate()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getRequest().getCheckOutDate()}</th>
					<th scope="col" style="text-align: center">${clientRequest.getRequest().getRequestSentTime()}</th>
					<th scope="col" style="text-align: center"><f:message
							key="${clientRequest.getRequestStatusDescription()}"
							bundle="${local}"/></th>
					<th style="text-align: center">
						<c:if test="${clientRequest.isProcessed()}">
							<a href="<c:url value="${pageContext.request.contextPath}/command?name=cancel_request&requestId=${clientRequest.getRequest().getRequestId()}"/>"
							   class="btn btn-danger">
								<f:message key="cancel" bundle="${local}"/>
							</a>
						</c:if>
					</th>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<br>
		<a class="u-align-center u-custom-font u-font-georgia u-text u-text-default u-text-1"
		   style="color: white"><f:message key="admin.cabinet.orders" bundle="${local}"/></a>
		<table class="u-align-center  table"
		       style="margin-left: auto;margin-right: auto;text-align: center">
			<thead class="u-grey-80 u-opacity u-opacity-70">
			<tr class="text-center">
				<th scope="col" style="text-align: center"><f:message key="order.id" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="request.id" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="room.id" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="room.class" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="check.in.date" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="check.out.date" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="invoice.receiving.time" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="status" bundle="${local}"/></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${clientOrders}" var="clientOrder">
				<tr class=" text-center u-grey-10 u-opacity-85">
					<th scope="col" style="text-align: center">${clientOrder.getOrder().getOrderId()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getOrder().getRequestId()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getOrder().getRoomId()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getOrder().getRoomClass()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getOrder().getCheckInDate()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getOrder().getCheckOutDate()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getOrder().getInvoiceSentTime()}</th>
					<th scope="col" style="text-align: center"><f:message
							key="${clientOrder.getOrderStatusDescription()}" bundle="${local}"/></th>
					<th style="text-align: center">
						<a>
							<c:if test="${clientOrder.isPaymentRequired()}">
								<a href="<c:url value="${pageContext.request.contextPath}/command?name=invoice
								&orderID=${clientOrder.getOrder().getOrderId()}
								&requestID=${clientOrder.getOrder().getRequestId()}
								&roomID=${clientOrder.getOrder().getRoomId()}
								&roomClass=${clientOrder.getOrder().getRoomClass()}
								&dateFrom=${clientOrder.getOrder().getCheckInDate()}
								&dateTo=${clientOrder.getOrder().getCheckOutDate()}
								"/>" class="btn btn-success">
									<f:message key="client.cabinet.pay" bundle="${local}"/>
								</a>
							</c:if>
						</a>
					</th>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</section>

<c:import url="common/footer.jsp"/>
<%--<c:import url="common/cookies.jsp"/>--%>
</body>
</html>