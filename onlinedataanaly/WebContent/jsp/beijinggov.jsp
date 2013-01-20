<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>北京政务微博--网络数据分析</title>
    <meta charset="UTF-8" />
    <meta name="keywords" content="舆情，数据抓取，微博，社交，数据分析" />
    <meta name="description" content="网络舆情分析，采集微博， 人人等网站数据进行舆情传播分析" />

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link id="bootstrap_styles" rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
    <link id="bootstrap_responsive_styles" rel="stylesheet" href="css/bootstrap-responsive.min.css" type="text/css"/>
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/beijingGovQuery.js"></script>
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
                <select class="span2" id="dayOfstat">
                <option value="1">1天</option>
                <option value="2">2天</option>
                <option value="3">3天</option>
                <option value="4">4天</option>
                <option value="5">5天</option>
              </select>
				<input type="button" id="analysis" name="analysis" value="生成报表" class="btn"/>

            </div>
            <div id="progress_div" class="progress progress-striped" style="display:none">
			  	<div id="progress_bar" class="bar" style="width:1%"></div>
			  </div>
			<div class="alert alert-error" id="error_div" style="display:none">
			    <s:actionerror/>
    	    </div>
			  
            <div class="well row-fluid" id="table_div">
		      <fieldset>
			    <legend><b>政务微博列表</b></legend>
			       <table class="table table-striped">
		              <thead>
		                <tr>
		                  <th>#</th>
		                  <th>报表名称</th>
		                  <th>生成时间</th>
		                </tr>
		              </thead>
		              <tbody id="table_data">						
						<s:iterator value="filenames.descendingKeySet()" id="id" status='st'>
		                <tr id="row<s:property value='#st.index + 1'/>">
		                  <td><s:property value='#st.index + 1'/> </td>
		                  <td><a href="download?path=resources/<s:property value="#id"/>"><s:property value="#id"/></a></td>
		                  <td><s:property value="filenames.get(#id)"/> </td>
		                </tr>
		                </s:iterator>
		                
		              </tbody>
	            </table>
                
              </fieldset>
            </div>
        </div>
    </div>
</div>
	<jsp:include page="footer.jsp"/>
<!-- /container -->
<script>
$("#twi_li").removeClass("active");
$("#beijinggov_li").addClass("active");
</script>
</body>
</html>
