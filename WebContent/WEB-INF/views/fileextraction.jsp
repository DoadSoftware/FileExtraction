<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
  <title>File Extraction</title>
	
  <script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.0/jquery.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/javascript/index.js"/>"></script>
  
  <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.1.3/css/bootstrap.min.css"/>"/>  
  <link href="<c:url value="/webjars/font-awesome/6.0.0/css/all.css"/>" rel="stylesheet">
  
 <!-- <script type="text/javascript">
  setInterval(() => {
	  processFileExtractionProcedures('EXTRACT-DATA');		
	}, 1000);
  </script>-->
  
</head>
<body onload="reloadPage('FILEEXTRACTION')">
<div class="content py-5" style="background-color: #EAE8FF; color: #2E008B">
  <div class="container">
	<div class="row">
	 <div class="col-md-8 offset-md-2">
       <span class="anchor"></span>
         <div class="card card-outline-secondary">
           <div class="card-header">
             <h3 class="mb-0">FILE-EXTRACTION</h3>   
           </div>
           <div class="card-body">
           	<div id="captions_div" class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;">
           		<label class="col-sm-4 col-form-label text-left">Broadcaster: ${session_broadcaster.replace("_"," ")} </label>
           		<label class="col-sm-4 col-form-label text-left">FTP PORT: ${port_number} </label>
           		<label class="col-sm-4 col-form-label text-left">DURATION: ${time_duration} </label>
           		<label class="col-sm-4 col-form-label text-left">USERNAME: ${username} </label>
           		<label class="col-sm-4 col-form-label text-left">PASSWORD: ${password} </label>
           		<label class="col-sm-4 col-form-label text-left">MATCH ${match_number} </label>
           		<label id="download_lbl" class="col-sm-4 col-form-label text-left">Data Download STOP</label>
           		<div align="center">
	  				 <button style="background-color:#f44336;color:#FEFEFE;" class="btn btn-sm" type="button" 
	           			name="start_btn" id="start_btn" onclick="processUserSelection(this)"> Start </button>
				  	<button style="background-color:#f44336;color:#FEFEFE;" class="btn btn-sm" type="button"
				  		name="stop_btn" id="stop_btn" onclick="processUserSelection(this)"> Stop </button>
  				</div>
           	</div>
	       </div>
	    </div>
       </div>
    </div>
  </div>
</div>
<input type="hidden" name="selected_broadcaster" id="selected_broadcaster" value="${session_broadcaster}"/>
<input type="hidden" name="matchNumber" id="matchNumber" value="${match_number}"/>
<input type="hidden" name="ftpUsername" id="ftpUsername" value="${username}"/>
<input type="hidden" name="ftpPassword" id="ftpPassword" value="${password}"/>
<input type="hidden" name="updateDuration" id="updateDuration" value="${time_duration}"/>
<input type="hidden" name="ftpPortNumber" id="ftpPortNumber" value="${port_number}"/>
</body>
</html>