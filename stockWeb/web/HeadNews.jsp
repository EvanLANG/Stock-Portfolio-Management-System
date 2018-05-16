<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LANG
  Date: 2018/4/26
  Time: 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
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

        .btn {
            background:url("picture/search.jpg");
            background-size: cover;
            no-repeat:left top;
            padding-bottom:4px;
            width: 22px;
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

        .sign-up {
            position: absolute;
            margin: 30px 5px;
            padding: 0;
            left: 0;
            bottom: 20px;
            right: 0;
            font-size: .82353em;
        }
        #content_mid {
            margin-left: auto;
            margin-right: auto;
            width: 1000px;
            text-align: left;


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
            background-image: url(picture/background.jpg)
        }

    </style>
</head>

<body onload="loadDoc()">
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
                <input class="btn" type="button" id="topSearchSubmit" data-eid="qd_A62" onclick="submit()">
            </form>
        </li>
    </ul>

    <div style="height:200px;"><img class="pic1" style="width:100%; height:100%;" src="picture/background3.jpg" /></div>

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
    <div style="width:100%;">
        <script>
            (function() {
                var cx = '017212697942039301577:vp-tqdegd6g';
                var gcse = document.createElement('script');
                gcse.type = 'text/javascript';
                gcse.async = true;
                gcse.src = 'https://cse.google.com/cse.js?cx=' + cx;
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(gcse, s);
            })();
        </script>
        <gcse:search></gcse:search>
    </div>
</header>
<div class="mainContext">
<div id="content_mid" class="">
    <!--     <h3>Table for records!</h3>
        <p>-----------------------------</p> -->
    <br><br>

    <h4 id="comment"></h4>



    <script>
        function loadDoc() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var obj = JSON.parse(this.responseText);
                    var item = obj.results;
                    myFunction(item);
                }
            };
            xhttp.open("GET", "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/Business%20Day/1.json?api-key=e716033797834288814805dc70eb4907", true);
            xhttp.send();
        }
        function myFunction(x) {
            var num = 0;
            var i;

            // there is somthing need to be fixed, index should start from 1
            var out="";
            var max = Math.min(10,x.length);

            for (i = 0; i < max; i++) {
                if(x[i].media[0]['media-metadata'][2]!=null||typeof(x[i].media[0]['media-metadata'][2])!='undefined'){
                    out += '<p><span><a href="' + x[i].url + '"target="_blank">' + x[i].title + "</a></span><br><img src="+ x[i].media[0]['media-metadata'][2].url +'><br><span class="small">'+x[i].abstract+'</span><br><span class="small">'+x[i].byline+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+x[i].published_date+'</span><br><br>';
                }else{
                    out += '<p><span><a href="' + x[i].url + '"target="_blank">' + x[i].title + '</a></span><br><span class="small">'+x[i].abstract+'</span><br><span class="small">'+x[i].byline+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+x[i].published_date+'</span><br><br>';
                }

            }
            document.getElementById("comment").innerHTML=out;

        }
    </script>

</div>
</div>
</body>
</html>
