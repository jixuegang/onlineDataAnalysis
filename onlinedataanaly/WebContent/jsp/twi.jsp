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
	<script src="js/twiChartQuery.js"></script>

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
        <div class="span9">
            <div class="well row-fluid" id="search_div">
            <input type="text" name="twiMid" value="<s:property value="#parameters.twiMid"/>" id="twiMid" placeholder="微博 id"/>
               
				<input type="button" id="analysis" name="analysis" value="查询" class="btn"/>

            </div>
            <div id="progress_div" class="progress progress-striped" style="display:none">
			  	<div id="progress_bar" class="bar" style="width:1%"></div>
			  </div>
			<div class="alert alert-error" id="error_div" style="display:none">
			    <s:actionerror/>
    	    </div>
		    <div class="well row-fluid" id="help_div">
		    <fieldset>
		      <legend><b>如何获取微博id</b></legend>
			  <div class="row-fluid">
	            <ul class="thumbnails">
	              <li class="span10">
	                <div class="thumbnail">
	                  <img alt="300x200" src="images/tweet.jpg">
	                  <div class="caption">
	                    <p><span class="badge badge-info">1</span> 点击要微博评论链接.</p>
	                  </div>
	                </div>
	              </li>
	              <li class="span10">
	                <div class="thumbnail">
	                  <img alt="300x200" src="images/more.jpg">
	                  <div class="caption">
	                    <p><span class="badge badge-info">2</span> 点击查看更多的评论.</p>
	                  </div>
	                </div>
	              </li>
	             <li class="span10">
	                <div class="thumbnail">
	                  <img alt="300x200" src="images/url.jpg">
	                  <div class="caption">
	                    <p><span class="badge badge-info">3</span> 浏览器地址栏里面的最后一个参数"zcMAFsOYz"即是微博ID.</p>
	                  </div>
	                </div>
	              </li>
	            </ul>
          	  </div>
          	  </fieldset>
			</div>
			  
            <div class="well row-fluid" id="chart_div" style="display:none">
		      <fieldset>
			    <legend><b>微博内容</b></legend>
			    <p id="twiText"></p>
		    	<legend><b>转发综合用户分析</b></legend>
                <div class="span5">
                <div id='repostRatio'></div>
		        </div>
		        <div class="span5">
		         <div id='repostUserType'></div>
		        </div>
		        <div class="span5">
                <div id='repostSex'></div>
		        </div>
		        <div class="span5">
		         <div id='repostLocation'></div>
		        </div>
		        <legend><b>关键用户分析</b></legend>
		        <div class="span10">
		         <div id='topRepostData'></div>
		        </div>
		        <div class="span10">
		         <div id='topFollowersData'></div>
		        </div>
		        <div class="span10">
		         <p style="font-size:20px; font-weight:bold; text-align:center; padding-top:20px;padding-bottom:20px">认证用户列表</p>
		         <div id='vuserlist'></div>
		        </div>
              </fieldset>
            </div>
        </div>
    </div>
</div>
	<jsp:include page="footer.jsp"/>
<!-- /container -->
</body>
</html>
