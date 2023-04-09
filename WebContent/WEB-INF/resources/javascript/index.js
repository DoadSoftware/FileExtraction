var display;
function processWaitingButtonSpinner(whatToProcess) 
{
	switch (whatToProcess) {
	case 'START_WAIT_TIMER': 
		$('.spinner-border').show();
		$(':button').prop('disabled', true);
		break;
	case 'END_WAIT_TIMER': 
		$('.spinner-border').hide();
		$(':button').prop('disabled', false);
		break;
	}
}
function reloadPage(whichPage)
{
	switch(whichPage){
	/*case 'INITIALISE':
		//processUserSelection(document.getElementById('select_broadcaster'));
		//processFileExtractionProcedures('LOAD_MATCHES');
		break;*/
	//case 'FILEEXTRACTION':
		///break;
	}
}
function initialisePage(whichPage)
{
	switch(whichPage){
	case 'INITIALISE':
		//processUserSelection($('#select_sports'));
		break;
	
	}
}
function initialiseForm(whatToProcess, dataToProcess)
{
	/*switch (whatToProcess){
	case '':

		break;
	}*/
}
function processMatchTime() {
	processFileExtractionProcedures('START_FILE_EXTRACTION');
}
function processUserSelection(whichInput)
{	
	switch ($(whichInput).attr('id')) {
	case 'finish_btn':
      	document.initialise_form.submit();
		break;
	case 'start_btn':
		processFileExtractionProcedures('START_FILE_EXTRACTION');
		display = setInterval(processMatchTime, 60000);
		break;
	case 'stop_btn':
		clearInterval(display); 
		break;
	}
}	
function uploadFormDataToSessionObjects(whatToProcess)
{
	var formData = new FormData();
	var url_path;

	$('input, select, textarea').each(
		function(index){ 
			formData.append($(this).attr('id'),document.getElementById($(this).attr('id')).value);
		}
	);
	
	switch(whatToProcess.toUpperCase()) {
	case 'SAVE_DATA':
		//url_path = 'save_data';
		//formData.append('file_name',document.getElementById('file_name').value);
		break;
	}
	
	$.ajax({    
		headers: {'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')},
        url : url_path,     
        data : formData,
        cache: false,
        contentType: false,
        processData: false,
        type: 'POST',     
        success : function(response) {

        	switch(whatToProcess.toUpperCase()) {
        	case 'SAVE_DATA':
        	 	window.location.reload();
        		break;
        	}
        	
        },    
        error : function(e) {    
       	 	console.log('Error occured in uploadFormDataToSessionObjects with error description = ' + e);     
        }    
    });		
	
}
function processFileExtractionProcedures(whatToProcess)
{
	var valueToProcess;
	switch(whatToProcess) {
	case 'START_FILE_EXTRACTION':
		valueToProcess = 'START';
		break;
	}
	
	$.ajax({    
        type : 'Get',     
        url : 'processFileExtractionProcedures.html',     
        data : 'whatToProcess=' + whatToProcess + '&valueToProcess=' + valueToProcess, 
        dataType : 'json',
        success : function(data) {
			
			switch(whatToProcess) {
			case 'LOAD_FILE':
				addItemsToList(whatToProcess,data)
				break;
			}
			processWaitingButtonSpinner('END_WAIT_TIMER');
	    },    
	    error : function(e) {    
	  	 	console.log('Error occured in ' + whatToProcess + ' with error description = ' + e);     
	    }    
	});
}
function addItemsToList(whatToProcess, dataToProcess){

	//var option,i,label,select,div,col_num_per_row = 0, col_id = 0;
	
	switch (whatToProcess) {
	
	}
}
function checkEmpty(inputBox,textToShow) {

	var name = $(inputBox).attr('id');
	
	document.getElementById(name + '-validation').innerHTML = '';
	document.getElementById(name + '-validation').style.display = 'none';
	$(inputBox).css('border','');
	if(document.getElementById(name).value.trim() == '') {
		$(inputBox).css('border','#E11E26 2px solid');
		document.getElementById(name + '-validation').innerHTML = textToShow + ' required';
		document.getElementById(name + '-validation').style.display = '';
		document.getElementById(name).focus({preventScroll:false});
		return false;
	}
	return true;	
}