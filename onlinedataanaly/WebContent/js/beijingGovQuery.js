$(document).ready( function() {
	$("#analysis").click( function() {
	    $("#progress_div").css('display','block');
        $.post(
           'ajax/beijinggov!analysis.action',
           {dayOfstat :$("#dayOfstat").val()},
           function(data){
        	   var res = eval(data);
        	   var actionErrors = res.errorMsg;
               if(actionErrors != null && typeof(actionErrors) != "undefined" && actionErrors != "") {
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
    		            	   var tr = $("#row1").clone(true);
    		                   tr.children().eq(1).html("<a href='download?path=resources/beijinggov/"+newFileName+"'><B>"+newFileName+"</B></a>");
    		                   tr.children().eq(2).html(newFileCreatedTime);
    		                   tr.insertAfter("#table_div tr:first");
    		                   
    		                   var count=$("#table_data tr").length;
    		                   for(var i=0;i<count;i++){
    		                	   $("#table_data tr").eq(i).children().eq(0).html((i+2));
    		                   }
    		               }
    		            },
    		            'json'
    		        );
    	}
        
    });
	
	
	
	
});