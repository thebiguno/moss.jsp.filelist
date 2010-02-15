<?xml version="1.0" encoding="iso-8859-1"?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="/WEB-INF/tlds/filelist-tags.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<h1>Foo</h1>

<f:list-files folder="/foo" regex=".*txt$|.*xml$" descriptionExtension="foo"/>

</body>
</html>