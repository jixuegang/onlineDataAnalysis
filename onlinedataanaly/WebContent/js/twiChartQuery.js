$(document).ready( function() {
	$("#analysis").click( function() {
	    $("#progress_div").css('display','block');
 	    $("#chart_div").css('display','none');
 	    $("#help_div").css('display','none');
        $.post(
           'ajax/twi!analysis.action',
           {twiMid :$("#twiMid").val()},
           function(data){
        	   var res = eval(data);
               var actionErrors = res.actionErrors;
               if(actionErrors != "") {
	        	   $("#error_div").css('display','block');
	        	   $("#error_div").text(actionErrors);
               } else {	        	   
	        	   setTimeout(pollServer,1000);
               }
            },
            'json'
        );
        
        function pollServer(){
    		$.post(
    		           'ajax/twi!progress.action',
    		          
    		           {twiMid :$("#twiMid").val()},
    		           function(data){
    		               var res = eval(data);
    		               var progress = res.progress;
    		               if(progress < 100) {
    		            	   $("#progress_bar").css("width",progress + "%");
    		            	   setTimeout(pollServer,1000);
    		               } else {
    		            	   $("#progress_div").css('display','none');
    		            	   $("#chart_div").css('display','block');
    		            	   var twiText = res.twiText;
    			               $("#twiText").text(twiText);
    			               var userTypeData = res.userTypeData;
    			               var userGenderData = res.userGenderData;
    			               var repostRatioData = res.repostRatioData;
    			               var locationData = res.locationData;
    			               
    			               iChart(function(){
    			            	 var chart1 = new iChart.Pie2D({
       			           			background_color : '#EFEFEF',
       			           			render : 'repostRatio',
       			           			data: repostRatioData,
       			           			title : '转发/评论比率',
       			           			shadow:true,
       			           			shadow_color:'#c7c7c7'
       			           		});
       			           		chart1.draw();
       			           		
    			           		var chart2 = new iChart.Pie2D({
    			           			background_color : '#EFEFEF',
    			           			render : 'repostUserType',
    			           			data: userTypeData,
    			           			title : '用户类别分析',
    			           			shadow:true,
    			           			shadow_color:'#c7c7c7'
    			           		});
    			           		chart2.draw();
    			           		
    			           		var chart3 = new iChart.Pie2D({
    			    				background_color : '#EFEFEF',
    			    				render : 'repostSex',
    			    				data: userGenderData,
    			    				title : '用户性别分析',
    			    				shadow:true,
    			    				shadow_color:'#c7c7c7'
    			    			});
    			    			chart3.draw();
    			    			
    			    			var chart4 = new iChart.Pie2D({
    			    				background_color : '#EFEFEF',
    			    				render : 'repostLocation',
    			    				data: locationData,
    			    				title : '用户区域分析',
    			    				shadow:true,
    			    				shadow_color:'#c7c7c7'
    			    			});
    			    			chart4.draw();
    			           		
    			               });
    		               }
    		            },
    		            'json'
    		        );
    	}
        
        
    });
	
	
	
	
});