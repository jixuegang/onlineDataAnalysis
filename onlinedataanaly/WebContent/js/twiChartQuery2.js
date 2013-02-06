$(document).ready( function() {
	var posturl = 'ajax/twi!analysis.action';
	$("#analysis").click( function() {
	    $("#progress_div").css('display','block');
 	    $("#chart_div").css('display','none');
 	    $("#help_div").css('display','none');
        $.post(
        	posturl,
           {twiMid :$("#twiMid").val()},
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
    			               var topRepostData = res.topRepostData;
    			               var topFollowersData = res.topFollowersData;
    			               var verifiedUsers = res.verifiedUsers;
    			               
    			               var dataarray = new Array(repostRatioData, userGenderData, userTypeData, locationData);
    			               var titlearray = new Array("转发/评论比率","用户性别分析", "用户类别分析", "用户区域分析");
    			               var renderarray = new Array("repostRatio", "repostSex", "repostUserType", "repostLocation");
    			               var widtharray = new Array(330, 330, 330, 330);
    			               var heightarray = new Array(400, 400, 400, 400);
    			               var distancearray = new Array(-30, -30, -30, 3);
    			               
    			               for(var i=0; i<4; i++) {
    			            	  buildPie(renderarray[i], widtharray[i], heightarray[i], titlearray[i], dataarray[i], distancearray[i]);
    			               }
    			               
    			               var topRepostArray = new Array();
    			               for(var i=0; i<topRepostData.length; i++) {
    			            	   topRepostArray.push(topRepostData[i].name);
    			               }
    			               var topFollowersArray = new Array();
    			               for(var i=0; i<topFollowersData.length; i++) {
    			            	   topFollowersArray.push(topFollowersData[i].name);
    			               }
    			               buildColumn("二次转发用户排行", "topRepostData", topRepostData, topRepostArray, "被转发数");
    			               buildColumn("用户粉丝排行", "topFollowersData", topFollowersData, topFollowersArray, "粉丝数");
    			               
    			               var vuserlist = $("#vuserlist");
   			    			var cssarray = new Array("", "label-success", "label-warning", "label-important", "label-info", "label-inverse");
   			    			for(var i=0; i<verifiedUsers.length;i++) {
   			    				var newspan = $("<span/>").addClass("label").html(verifiedUsers[i]);
   			    				newspan.addClass(cssarray[i%6]);
   			    				newspan.css("padding","10px, 10px");
   			    				newspan.appendTo(vuserlist);
   			    			}
    			               
    		               }
    		           },
   		            'json'
   		        );
        	}
        
        function buildPie(crender, cwidth, cheight, ctitle, cdata, cdistance) {
        	chart = new Highcharts.Chart({
                chart: {
                    renderTo: crender,
                    backgroundColor:'#F5F5F5',
                   
                },
                title: {
                    text: ctitle
                },
                tooltip: {
            	    pointFormat: '<b>{point.percentage}%</b>',
                	percentageDecimals: 0
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000',
                            showInLegend: false,
                            distance: cdistance,
                            formatter: function() {
                                return ''+ this.point.name +':'+ this.y;
                            }
                        }
                    }
                },
                legend: {
             	   enabled: false
             	   },
                series: [{
                    type: 'pie',
                    name: '用户类别',
                    data: cdata,
                }]
            });
        }
        
        function buildColumn(ctitle, crender, cdata, columns, cYname) {
        	chart = new Highcharts.Chart({
                chart: {
                    renderTo: crender,
                    type: 'column',
                    backgroundColor:'#F5F5F5',
                },
                title: {
                    text: ctitle
                },
                xAxis: {                   
                    categories: columns,
                    labels: {
                        rotation: -45,
                        align: 'right',
                        style: {
                            fontSize: '13px',
                        }
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: cYname
                    }
                },
                legend: {
                    enabled: false
                },
               
                series: [{
                    name: 'Population',
                    data: cdata,
                    dataLabels: {
                        enabled: true,
                        rotation: 0,
                        align: 'right',
                        x: 4,
                        y: -10,
                        style: {
                            fontSize: '13px',
                            fontFamily: 'Verdana, sans-serif'
                        }
                    }
                }]
            });
        }
        
        
        
        });

	if(typeof($("#twiMid").val()) != "undefined" && $("#twiMid").val() != "") {
		posturl = 'ajax/twi!chart.action';
		$("#analysis").click();
	}
});