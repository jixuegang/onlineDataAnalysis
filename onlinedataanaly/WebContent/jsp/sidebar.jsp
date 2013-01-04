<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <div class="well sidebar-nav">
     <ul class="nav nav-list">
        <li class="nav-header"></li>
        <s:url var="index_url" action="index"/>
        <li class="active"><s:a href="/twi.action">单条微博传播分析</s:a></li>
        <s:url var="vertical_url" action="vertical"/>
        <li><s:a href="">微博用户活跃度分析</s:a></li>
        <s:url var="validation_url" action="validation"/>
        <li><s:a href="">焦点分析</s:a></li>
      </ul>
 </div>
