<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
   <script src="<spring:url value="/resources/javascript/errorHandler.js" /> " ></script>
   <link rel="stylesheet" href='<spring:url value="/resources/css/errorHandler.css" /> '>
   
   <style>
    #formBody{
        border: 2px solid black;
        width:  1000px;
        height: 300px;
        margin: 100px auto;
     }
   </style>
   
   <script type="text/javascript">
        $(document).ready(function(){
        	
        	var loadUserInterest = function() {
        		var userInterest = "${userInterestList}";
        		var userInterestArray = userInterest.split(",");
                
                   $("#interestListField option").each(function(index) {
                	  var actualElement = $(this).val();
                	  var checkIfEqual = false;
                	  $(userInterestArray).each(function(index){
                		  if(actualElement == userInterestArray[index]){
                			  checkIfEqual = true;
                			  return false;
                		  }
                	  });
                	  
                	  if(checkIfEqual == true){
                		  $(this).prop("selected", true);
                	  }
                	  checkIfEquals = false;
                });   
        	}
        	
        var loadUserProgrammingSkill = function() {
        	var userProgrammingSkill = "${userProgrammingSkillList}";
        	var userProgrammingSkillArray = userProgrammingSkill.split(",");
        	
        	$(":checkbox").each(function(){
        		var actualElementCheckBox = $(this).val();
        		var checkIfEqualCheckbox = false;
        		$(userProgrammingSkillArray).each(function(index){
        			if(actualElementCheckBox == userProgrammingSkillArray[index]){
        				checkIfEqualCheckbox = true;
        				return false;
        			}
        		});
        		
        		if(checkIfEqualCheckbox === true){
          		  $(this).prop("checked", true);
          	  }
          	  checkIfEquals = false;
        	});
        }
        	loadUserInterest();
        	loadUserProgrammingSkill();
        });
   
   </script>
</head>
<body>

 <jsp:include page="/WEB-INF/views/fragments/mainPage.jsp" />
 <jsp:include page="/WEB-INF/views/fragments/settingsFragment.jsp" />
 <spring:url value="/resources/images/warning-icon.png" var="warnign_icon" />
 
 <security:authorize access="isAuthenticated()">
 
    <security:authentication property="principal.username" var="current_login"/>
 
    <spring:url value='/settings_account_user/${current_login}/userInformation' var="actionuserInformation" />
    
    <div id="formBody">
       <form:form action="${actionuserInformation}" modelAttribute="userInformation" method="POST">
            <table class="settingsTable">
                <tr>
                    <td><form:label path="age">AGE:</form:label></td>
                    <td><form:input path="age" type="number" min="18" max="99"/></td>  <!-- using  html5 -->
                </tr>  
                <tr>
                    <td><form:label path="surname">Surname:</form:label></td>
                    <td><form:input path="surname"/></td>
               </tr>
               <tr>
                   <td><form:label path="interest">Interest</form:label></td>
                   <td>
                      <form:select path="interest" multiple="true" id="interestListField">
                        <form:options items="${interestList}" />
                     </form:select>
                  </td>
               </tr>
               <tr>
                  <td><form:label path="sex">Sex?</form:label>
                  <td>
                     <form:radiobutton path="sex" value="no" checked="checked" />No
                     <form:radiobutton path="sex" value="yes" />Yes
                  </td>
               </tr>
               <tr>
                  <td><form:label path="programmingSkill"  id="programmingSkillField">ProgramingSkill</form:label></td>
                  <td>
                     <form:checkboxes items="${programmingSkillList}" path="programmingSkill"/>
                  </td>
               </tr>
              </table>           
              
              <input type="submit" value="submit" name="submit"/>
         
         </form:form>
    
    </div>  
 </security:authorize>
   
</body>
</html>