<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
  <title>Initialise Screen</title>

  <script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.0/jquery.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/javascript/index.js"/>"></script>
  
  <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.1.3/css/bootstrap.min.css"/>"/>  
  <link href="<c:url value="/webjars/font-awesome/6.0.0/css/all.css"/>" rel="stylesheet">
	
</head>
<body onload="initialisePage('INITIALISE')">
<form:form name="initialise_form" autocomplete="off" action="fileextraction" method="POST" enctype="multipart/form-data">
<div class="content py-5" style="background-color: #EAE8FF; color: #2E008B">
  <div class="container">
	<div class="row">
	 <div class="col-md-8 offset-md-2">
       <span class="anchor"></span>
         <div class="card card-outline-secondary">
           <div class="card-header">
             <h3 class="mb-0">Initialise</h3>
           </div>
          <div class="card-body">
			 <div class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;">
			    <label for="selectedBroadcaster" class="col-sm-4 col-form-label text-left">Select Broadcaster </label>
			   <div class="col-sm-6 col-md-6">
			 <select id="selectedBroadcaster" name="selectedBroadcaster" class="browser-default custom-select custom-select-sm" 
			      		onchange="processUserSelection(this)">
			      		<option value="ISL">ISL</option>
			      		<option value="SUPER_CUP">SUPER CUP</option>
			      	  <option value="VIZ_TRI_NATION">TRI NATION</option>
			      </select>
			    </div>
			  </div>
			  <div id="matchNumber_div" class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;"> 
			    <label for="matchNumber" class="col-sm-4 col-form-label text-left">Match Number 
			    	<i class="fas fa-asterisk fa-sm text-danger" style="font-size: 7px;"></i></label>
			    <div class="col-sm-6 col-md-6">
		             <input type="text" id="matchNumber" name="matchNumber" value="${session_Configurations.matchNumber}"
		             	class="form-control form-control-sm floatlabel"></input>
			    </div>
			 </div> 
			 <div id="updateDuration_div" class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;"> 
			    <label for="updateDuration" class="col-sm-4 col-form-label text-left">Duration 
			    	<i class="fas fa-asterisk fa-sm text-danger" style="font-size: 7px;"></i></label>
			    <div class="col-sm-6 col-md-6">
		             <input type="text" id="updateDuration" name="updateDuration" value="${session_Configurations.updateDuration}"
		             	class="form-control form-control-sm floatlabel"></input>
			    </div>
			 </div> 
			  <div id="ftpPortNumber_div" class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;">
			    <label for="ftpPortNumber" class="col-sm-4 col-form-label text-left">FTP Port Number 
			    	<i class="fas fa-asterisk fa-sm text-danger" style="font-size: 7px;"></i></label>
			    <div class="col-sm-6 col-md-6">
		             <input type="text" id="ftpPortNumber" name="ftpPortNumber" value="${session_Configurations.portNumber}"
		             	class="form-control form-control-sm floatlabel"></input>
			    </div>
			  </div>
			  <div id="ftpUsername_div" class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;">
			    <label for="ftpUsername" class="col-sm-4 col-form-label text-left">USERNAME 
			    	<i class="fas fa-asterisk fa-sm text-danger" style="font-size: 7px;"></i></label>
			    <div class="col-sm-6 col-md-6"> 
		             <input type="text" id="ftpUsername" name="ftpUsername" value="${session_Configurations.username}"
		             		class="form-control form-control-sm floatlabel"></input>
			    </div>
			  </div>
			  <div id="ftpPassword_div" class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;">
			    <label for="ftpPassword" class="col-sm-4 col-form-label text-left">PASSWORD 
			    	<i class="fas fa-asterisk fa-sm text-danger" style="font-size: 7px;"></i></label>
			    <div class="col-sm-6 col-md-6"> 
		             <input type="text" id="ftpPassword" name="ftpPassword" value="${session_Configurations.password}"
		             		class="form-control form-control-sm floatlabel"></input>
			    </div>
			  </div>
		    <button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="finish_btn" id="finish_btn" onclick="processUserSelection(this)">
		  		<i class="fas fa-film"></i> SUBMIT </button>
	       </div>
	    </div>
       </div>
    </div>
   </div>
</div>
</form:form>
</body>
</html>