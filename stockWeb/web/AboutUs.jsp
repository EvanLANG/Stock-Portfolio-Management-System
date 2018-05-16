<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LANG
  Date: 2018/4/25
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style type="text/css">
        .jumbotron {
            background-color: #7c0bf4;
            color: #fff;
        }
        .bg-grey {
            background-color: #f6f6f6;
        }
        ._1SQmm {
            list-style: none;
            color: #fff;
            margin: 0;
            padding: 0;
            height: 50px;
            width: 100%;
        }
        ._1SQmm>li {
            display: inline-block;
            margin-right: 20px;
            position: relative;
            top: 30%;
            font-family: PingFangSC-Regular,HelveticaNeue-Light,'Helvetica Neue Light','Microsoft YaHei',sans-serif;
        }
        .Bgc {
            background-color: #ffffff;
        }
        .h1c {
            padding: 3.33333px;
            color: black;
        }

        .header1 {
            border-bottom: 1px solid #e0e4e9;
            background-color: #fff;
            min-width: 1024px;
        }
        a:link {text-decoration: none;}
        a:visited {text-decoration: none;}
        a:hover {text-decoration: none;}
        a:active {text-decoration: none;}

        .pin-input {
            font-family: PingFangSC-Regular,HelveticaNeue-Light,'Helvetica Neue Light','Microsoft YaHei',sans-serif;
            font-size: 14px;
            position: relative;
            z-index: 1;
            width: 450px;
            height: 26px;
            transition: color .3s,border .3s;
            color: black;
            border-bottom: 1px solid #d6d6d6;
            border-top: 1px solid #d6d6d6;
            border-left: 1px solid #d6d6d6;
            border-right: 1px solid #d6d6d6;
            background: #ffffff;
        }

        input {
            font-family: PingFangSC-Regular,HelveticaNeue-Light,'Helvetica Neue Light','Microsoft YaHei',sans-serif;
            border: none;
            outline: 0;
        }

        .btnn {
            background:url("picture/search.jpg");
            background-size: cover;
            no-repeat:left top;
            padding-bottom:6px;
            width: 27px;
        }


        li.line {
            height: 18px;
            border-right: 1px solid #515056;
        }

        .pic1 {
            height: 200px;
        }


        .h2c {
            background-color: #ffffff;
            list-style: none;
        <%--font-weight: 600; --%>
            color: #030303;
            padding: 0 20px;
            margin: 0 auto;
            width:100%;
            border-bottom: 1px solid #f1f1f1;
        }
        .h2c>li {
            -webkit-transition: opacity .3s;
            transition: opacity .3s;
            display: inline-block;

            margin-right: 30px;
        }
        .h2c a:link, .h2c a:visited {
            color: #030303;
            text-decoration: none;
            display: block;
        }
        .text1 {
            font-family:Arial,Helvetica,sans-serif;<%--marked --%>
            display: block;
            cursor: pointer;
            color:black;
            line-height: 36px;
        }
        h1 {
            display: block;
            font-size: 2em;
            -webkit-margin-before: 0.67em;
            -webkit-margin-after: 0.67em;
            -webkit-margin-start: 0px;
            -webkit-margin-end: 0px;
            font-weight: bold;
        }

        .orko, body {
            background: #fff;
            color: #26282a;
            font-family: "Helvetica Neue",Helvetica,Arial;
            letter-spacing: .5px;
            width: 100%;
            max-width: 100%;
        }
        .orko form {
            margin-bottom: 1em;
        }

        .login-box .login-logo {
            direction: ltr;
            margin-top: 50px;
            height: 70px;
        }
        .orko-button-primary, input.orko-button-primary {
            background: #188fff;
            color: #fff;
        }

        .username-challenge input {
            position: relative;
            padding: 6px 40px;
            height: 40px;
            border: 0;
            top: 20px;
            border-radius: 0;
            border-bottom: 1px solid #cfd2d5;
            letter-spacing: normal;
            font-size: .94118em;
            z-index: 1;
        }

        .orko-button, input.orko-button {
            background: #ccc;
            background: hsla(0,0%,0%,1);
            border: 2px solid transparent;
            border-radius: .25em;
            box-sizing: border-box;
            color: #fff;
            display: inline-block;
            height: 42px;
            width: 200px;
            top: 50px;
            line-height: 1;
            outline: 0;
            overflow: hidden;
            tap-highlight-color: transparent;
            text-align: center;
            text-overflow: ellipsis;
            text-transform: none;
            vertical-align: middle;
            white-space: nowrap;
            zoom: 1;
        }

    </style>
</head>
<style type="text/css">
    #content_mid {
        margin-left: auto;
        margin-right: auto;
        width: 60%;
        text-align: ;
        border-right: 1px solid #BDBDBD;
        border-left: 1px solid #BDBDBD;
        background-color: white;
    }

    .mainContext
    {
        width: 100%;
        margin:0;
        padding: 0;
        position:relative;
        margin-left: auto;
        margin-right: auto;
        background-color: #26282a;
    }


    h1 {
        display: block;
        font-size: 2em;
        -webkit-margin-before: 0.67em;
        -webkit-margin-after: 0.67em;
        -webkit-margin-start: 0px;
        -webkit-margin-end: 0px;
        font-weight: bold;
    }
</style>
<body>
<header data-reactroot class="header1">
    <ul class="_1SQmm Bgc">
        <li class=""><a class="h1c" href="index.jsp" data-rapid_p="1" data-v9y="1">
            <svg width="18" height="18" viewBox="0 0 32 32" style="fill: black;">
                <path d="M16.153 3.224L0 16.962h4.314v11.814h9.87v-8.003h3.934v8.003h9.84V16.962H32"></path>
            </svg>
            Home
        </a></li>

        <li class="" >
            <c:choose>
                <c:when test="${not empty sessionScope.user_id}">
                    <a class="h1c" id="in" href="user.jsp">${sessionScope.user_id.id}</a>
                </c:when>
                <c:otherwise>
                    <a class="h1c" id="uid" href="sign_in.jsp">Sign in</a>
                </c:otherwise>
            </c:choose>
        </li>

        <li class="" >
            <c:choose>
                <c:when test="${not empty sessionScope.user_id}">
                    <a class="h1c" id="up" href="logoutServlet">Log out</a>
                </c:when>
                <c:otherwise>
                    <a class="h1c" id="logout" href="sign_up.jsp">Sign up</a>
                </c:otherwise>
            </c:choose>
        </li>

        <li class="line"></li>

        <li id="min-search">
            <form id="formUrl" action="searchServlet" method="get">
                <input id="pin-input" class="pin-input" type="text" name="kw" placeholder="Search for stocks...">
                <input class="btnn" type="button" id="topSearchSubmit" data-eid="qd_A62" onclick="submit()">
            </form>
        </li>
    </ul>

    <div style="height:200px;"><img class="pic1" style="width:100%; height:100%;" src="picture/background3.jpg"/></div>

    <ul class="h2c">
        <li><a class="text1" href="/index.jsp" data-rapid_p="21" data-v9y="1">Finance Home</a></li>
        <li><a class="text1" href="rankServlet" data-rapid_p="31" data-v9y="1">Markets</a></li>
        <c:choose>
            <c:when test="${not empty sessionScope.user_id}">
                <li><a class="text1" href="/user.jsp" data-rapid_p="31" data-v9y="1">Personal Finance</a></li>
            </c:when>
            <c:otherwise>
                <li><a class="text1" href="/sign_in.jsp" data-rapid_p="31" data-v9y="1">Personal Finance</a></li>
            </c:otherwise>
        </c:choose>
        <li><a class="text1" href="/HeadNews.jsp" data-rapid_p="31" data-v9y="1">Events</a></li>
        <li><a class="text1" href="/AboutUs.jsp" data-rapid_p="31" data-v9y="1">AboutUs</a></li>
        <li><a class="text1" href="/Contactus.jsp" data-rapid_p="31" data-v9y="1">ContactUs</a></li>
    </ul>

</header>
<h2 align="center">Us</h2>


<div class="jumbotron text-center">
    <h1>E-Finance</h1>
    <p>We specialize in investing.</p>
    <form class="form-inline" action="subscribe" method="post">

        <div class="input-group">
            <input type="email" class="form-control" size="50" name="email" placeholder="Email Address" required>
            <div class="input-group-btn">
                <button type="submit" class="btn btn-default">Subscribe</button>
            </div>
        </div>
    </form>
</div>

<!-- Container (About Section) -->
<div class="container-fluid">
    <h2>About Company</h2>
    <h4>In April 2020, E-Finance! Finance was named the No. 1 site "favored by Republicans 18 or older with annual household incomes of $100,000 or more" by conservative media organization Newsmax, based on viewership numbers tracked using the comScore Plan Metrix research service.</h4>
    <p></p>
    <button type="button" class="btn btn-default" onclick="skipto()">Get in Touch</button>
</div>
<script>
    function skipto()
    {

        window.location.href="/Contactus.jsp";
    }
</script>
<div class="container-fluid bg-grey">
    <h2>Our Values</h2>
    <h4><strong></strong> We provide financial news, data and commentary including stock quotes, press releases, financial analysis, and original content.</h4>
    <h4><strong></strong> We also offer some online tools for personal finance management. In addition to posting partner content from a wide range of other web sites, it posts original stories by its team of staff journalists.</h4>
</div>
</body>


</html>

