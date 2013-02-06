<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>网络数据分析</title>
    <meta charset="UTF-8" />
    <meta name="keywords" content="舆情，数据抓取，微博，社交，数据分析" />
    <meta name="description" content="舆情分析，采集微博， 人人等网站数据进行舆情传播分析" />

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link id="bootstrap_styles" rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
    <link id="bootstrap_responsive_styles" rel="stylesheet" href="css/bootstrap-responsive.min.css" type="text/css"/>
    <script src="js/ichart-1.0.min.js"></script>
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="http://code.highcharts.com/modules/exporting.js"></script>
	<script src="js/twiChartQuery2.js"></script>

    <style type="text/css">
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
          margin-left: -20px;
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
        }
      }

      .footercontainer {
        TEXT-ALIGN: center;
        width: auto;
        max-width: 680px;
      }
    </style>
</head>
<body>
<jsp:include page="top_nav.jsp"/>
<div class="container">
    <div class="row">
        <div class="span3">
            <jsp:include page="sidebar.jsp"/>
        </div>
        <div class="span7">	   
			  
            <div class="well row-fluid">
		      <fieldset>
		        <s:iterator value="twis" id="twi">
				<legend></legend>
		    	<div>
		    	<p><s:property value = "#twi.text"/></p>
			    <div class="row-fluid">
			    <div class="span2"  style="font-size:13px"> </div>
			    <div class="pull-right span4" style="font-size:12px"><a target="_blank" href="http://e.weibo.com/<s:property value = "#twi.uid"/>/<s:property value = "#twi.twiMid"/>?type=repost">转发(<s:property value = "#twi.repostCount"/>)</a>
			      |  <a target="_blank" href="http://e.weibo.com/<s:property value = "#twi.uid"/>/<s:property value = "#twi.twiMid"/>">评论(<s:property value = "#twi.commentCount"/>)</a> | <a href="twi.action?twiMid=<s:property value = "#twi.twiMid"/>"><b>传播分析</b></a> </div>
			    </div>
		    	</div>
		    	</s:iterator>
              </fieldset>
            </div>
        </div>
    </div>
</div>
	<jsp:include page="footer.jsp"/>
<!-- /container -->
<script>
$("#twi_li").removeClass("active");
$("#beijinggov_li").removeClass("active");
$("#hottwi_li").addClass("active");
</script>
</body>
</html>
