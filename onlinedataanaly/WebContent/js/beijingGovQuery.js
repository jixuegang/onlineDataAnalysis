$(document).ready( function() {
	$("#analysis").click( function() {
	    $("#progress_div").css('display','block');
        $.post(
           'ajax/beijinggov!analysis.action',
           {dayOfstat :$("#dayOfstat").val()},
           function(data){
        	   var res = eval(data);
               var actionErrors = res.actionErrors;
               if(actionErrors != "") {
	        	   $("#error_div").css('display','block');
	        	   $("#error_div").text(actionErrors);
	        	   $("#progress_div").css('display','none');
               } else {	        	   
	        	   setTimeout(pollServer,1000);
               }
            },
            'json'
        );
        
        function pollServer(){
    		$.post(
    		           'ajax/beijinggov!progress.action',
    		           {dayOfstat :$("#dayOfstat").val()},
    		           function(data){
    		               var res = eval(data);
    		               var progress = res.progress;
    		               if(progress < 100) {
    		            	   $("#progress_bar").css("width",progress + "%");
    		            	   setTimeout(pollServer,1000);
    		               } else {
    		            	   $("#progress_div").css('display','none');
    		            	   var newFileName = res.newFileName;
    		            	   var newFileCreatedTime = res.newFileCreatedTime;
    			               $("#twiText").text(twiText);
    		            	   $("#table_div").css('display','block');

    		               }
    		            },
    		            'json'
    		        );
    	}
        
        
    });
	
	
	
	
});