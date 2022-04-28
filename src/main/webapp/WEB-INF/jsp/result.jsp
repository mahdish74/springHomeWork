<jsp:useBean id="printRequestRes" scope="request" type="ir.mahdi.startup.startup.dto.out.PrintRequestRes"/>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>Successfully</div>


<style>
    <%@include file="../css/mainPage.css" %>
</style>


<div class="container">
        <h3> personal code:  ${printRequestRes.personalCode}</h3><br>
        <h3> branch code: ${printRequestRes.branchCode}</h3><br>
        <h3> card pan: ${printRequestRes.cardPAN}</h3><br>
        <h3> ip address: ${printRequestRes.ipAddress}</h3><br>
        <h3> issue date: ${printRequestRes.issueDate}</h3><br>
        <h3> code: ${printRequestRes.code}</h3><br>
</div>



