<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
   <title>关于我们--网络数据分析</title>
    <meta charset="UTF-8" />
    <meta name="keywords" content="舆情，数据抓取，微博，社交，数据分析" />
    <meta name="description" content="网络数据分析，采集微博， 人人等网站数据进行舆情传播分析" />

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
    <div class="hero-unit">
    <h2>自动化数据采集分析</h2>
    <p>厌烦了每天的数据采集分析？想了解更多网上业务相关数据？微博，社交，论坛，门户网站数据我们都可以以最小的成本帮您做好！告诉您的需求，发送邮件到<a href="mailto:jixuegang@gmail.com">jixuegang@gmail.com</a>.</p>
    </div>
 
	    <div class="well row-fluid" style="display:none">
			<fieldset>
			   <legend>关于我们</legend>
			   <div class="row-fluid">
			           <p>本系统提供专业的网络数据采集分析功能，基本功能我们将提供给您免费使用。 如果您有特殊的数据需求，我们的技术团队将会尽快帮您实现， 我们专注于小而快速的解决方案， 复杂的系统需求超出我们的能力，请谅解。</p>
			           <p> 目前 技术团队由数名资深软件开发工程师组成，对国内外数据抓取分析有丰富的开发经验。</p>
		       </div>
		     </fieldset>
	     </div>
	     <div class="well row-fluid">
	     <fieldset>
			   <legend>系统特点</legend>
            <ul class="thumbnails">
              <li class="span4">
                <div class="thumbnail">
                  <div class="caption">
                    <h3>开发周期短</h3>
                    <p>我们致力于敏捷开发，对您的需求会以最快的速度实现。开发人员丰富的数据采集分析经验将有助于需求的快速实现。</p>
                    
                  </div>
                </div>
              </li>
              <li class="span4">
                <div class="thumbnail">
                  <div class="caption">
                    <h3>无需维护</h3>
                    <p>系统基于云计算平台部署，所有的需求都可以集成在本系统。个人或企业不需要购买和维护服务器，节省系统开发维护成本。</p>                    
                  </div>
                </div>
              </li>
              <li class="span4">
                <div class="thumbnail">
                  <div class="caption">
                    <h3>灵活的合作模式</h3>
                    <p>您可以选择合作模式，我们负责整个系统的开发维护，您也可以选择按月来付使用费，无需预先支付开发成本。</p>                   
                  </div>
                </div>
              </li>
            </ul>
             </fieldset>
          </div>


	<jsp:include page="footer.jsp"/>

</div>
<script>
$("#weibo_li").removeClass("active");
$("#about_li").addClass("active");
</script>
<!-- /container -->
</body>
</html>
