

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <style><%@include file="../css/mainPage.css"%></style>


<div class="container">
<c:url var="add_book_url" value="/printRequest/saveDetails"/>

 <form:form  action="${add_book_url}" method="post" modelAttribute="printRequestReq">


        <h1>insert print request</h1>

     <c:if test="${hasError}">
         <div style="background: red" >${message}</div>
     </c:if>

        <div class="form-group">
            <form:input type="text"   path="personalCode"/>
            <label  class="control-label">personal code</label><i class="bar"></i>
        </div>

        <div class="form-group">
            <form:input type="text"   path="branchCode"/>
            <label  class="control-label">branch code</label><i class="bar"></i>
        </div>

        <div class="form-group">
            <form:input type="text"   path="cardPAN"/>
            <label  class="control-label">card pan</label><i class="bar"></i>
        </div>

        <div class="form-group">
            <form:input type="text"   path="ipAddress"/>
            <label  class="control-label">ip address</label><i class="bar"></i>
        </div>

        <div class="form-group">

            <input >
            <form:input type="date"  path="issueDate"/>
            <label  class="control-label">issue date</label><i class="bar"></i>
        </div>

        <div class="form-group-btn">
            <input type="submit" value="submit" class="button" style="background: #2d6050" />
        </div>
 </form:form>
</div>
