<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="#">网络舆情分析 </a>
            <div class="nav-collapse">
                <ul class="nav">
                    <s:url var="index_url" action="index"/>
                    <li id="weibo_li" class="active"><s:a href="twi.action">新浪微博</s:a></li>
                    <s:url var="about_url" action="about"/>
                    <li id="about_li"><s:a href="about.action">关于</s:a></li>
                    <li style="margin-top:10px"><iframe width="63" height="24" frameborder="0" allowtransparency="true" marginwidth="0" marginheight="0" scrolling="no" border="0" src="http://widget.weibo.com/relationship/followbutton.php?language=zh_cn&width=63&height=24&uid=1707591963&style=1&btn=red&dpc=1"></iframe></li>
                </ul>
            </div>
           
            <div class="pull-right">
				<ul class="nav nav-pills pull-right">
				 <c:choose>
				    <c:when test="${sessionScope.screenName == null}">
				    <li><a href="${sessionScope.loginurl}">微博登录</a></li>				     
				    </c:when>
				   <c:otherwise>  
				      <li><a href="#">${sessionScope.screenName}</a></li>
				   </c:otherwise>
			    </c:choose>
		          
		        </ul>
			</div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>
<script>

</script>