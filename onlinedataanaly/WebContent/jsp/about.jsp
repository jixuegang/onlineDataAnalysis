<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
   <title>北京政务微博--网络舆情分析</title>
    <meta charset="UTF-8" />
    <meta name="keywords" content="舆情，数据抓取，微博，社交，数据分析" />
    <meta name="description" content="网络舆情分析，采集微博， 人人等网站数据进行舆情传播分析" />

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
     <link id="bootstrap_styles" rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
    <link id="bootstrap_responsive_styles" rel="stylesheet" href="css/bootstrap-responsive.min.css" type="text/css"/>
	<script src="js/jquery-1.8.2.min.js"></script>
    <style type="text/css">
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
</head>
<body>
<jsp:include page="top_nav.jsp"/>

<div class="container">

    <h1>网络舆情监测</h1>
	<div id="footer">
      <div class="footercontainer ">
        <p class="muted credit"> Copyright © 2012-2013 <a href="mailto:jixuegang@gmail.com">jixuegang</a>.</p>
      </div>
    </div>

</div>
<script>
$("#weibo_li").removeClass("active");
$("#about_li").addClass("active");
</script>
<!-- /container -->
</body>
</html>
