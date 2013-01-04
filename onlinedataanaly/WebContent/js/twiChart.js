var data = [
				{name : '媒体',value : 7,color:'#a5c2d5'},
			   	{name : '+V',value : 5,color:'#cbab4f'},
			   	{name : '普通',value : 12,color:'#76a871'},
		 ];
var data2 = [
				{name : '男',value : 7,color:'#a5c2d5'},
				{name : '女',value : 5,color:'#cbab4f'},
		    ];

var data3 = [
	             {name : '<20',value : 7,color:'#a5c2d5'},				   	
	             {name : '20-30',value : 15,color:'#cbab4f'},
	             {name : '30-40',value : 10,color:'#76a871'},
				 {name : '>40',value : 5,color:'#cbab4f'},
			 ];
var data4 = [
				{name : 'IT',value : 57,color:'#a5c2d5'},
				{name : '分析师',value : 25,color:'#cbab4f'},
				{name : '普通',value : 12,color:'#76a871'},
			];
		 $(function(){	
			var chart1 = new iChart.Pie2D({
				background_color : '#EFEFEF',
				render : 'repostUserType',
				data: data,
				title : '用户类别分析',
				shadow:true,
				shadow_color:'#c7c7c7'
			});
			chart1.draw();
			
			var chart2 = new iChart.Pie2D({
				background_color : '#EFEFEF',
				render : 'repostSex',
				data: data2,
				title : '用户性别分析',
				shadow:true,
				shadow_color:'#c7c7c7'
			});
			chart2.draw();			
			
			var chart3 = new iChart.Pie2D({
				background_color : '#EFEFEF',
				render : 'repostage',
				data: data3,
				title : '用户年龄分析',
				shadow:true,
				shadow_color:'#c7c7c7'
			});
			chart3.draw();
			var chart4 = new iChart.Pie2D({
				background_color : '#EFEFEF',
				render : 'repostpro',
				data: data4,
				title : '用户职业分析',
				shadow:true,
				shadow_color:'#c7c7c7'
			});
			chart4.draw();
		});