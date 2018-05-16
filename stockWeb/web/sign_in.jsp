<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huang
  Date: 2018/3/27
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.min.js"></script>
    <script>
        function validate()
        {
            var uname = document.getElementById("login-username").value;
            var psw = document.getElementById("login-password").value;
            $.ajax({
                type: "GET",
                url: "SignInServlet",
                data: {username:uname,password:psw},
                success: function(response){
                    if (response=="false") {
                        document.getElementById("error").innerHTML = "Invalid username or incorrect password, please try again.";
                    } else if(response=="true"){
                        window.location.href = "user.jsp";
                    }else if(response=="ban"){
                        document.getElementById("error").innerHTML = "This user is disabled by Administer, \n" +
                            "please contact us to unlock it.";
                    }
                }
            });
        }
    </script>
    <style type="text/css">
        ._1SQmm {
            list-style: none;
            color: #fff;
            margin: 0;
            padding: 0;
            height: 50px;
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
            display: block;
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

        .hide {
            display:none;
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
            background-color: white;
            list-style: none;
        <%--font-weight: 600; --%>
            color: #000;
            padding: 0 20px;
            margin: 0 auto;
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
        .login-box {
            box-sizing: border-box;
            background-color: #fff;
            box-shadow: 0 2px 4px 0 rgba(181,181,181,.7);
            width: 360px;
            right: 10px;
            min-height: 550px;
            z-index: 1;
            padding: 0 5px;
            border-top: 1px solid #f1f1f5;
            position: relative;
            top: 11px;
            margin: auto;
            text-align: center;
        }

        .username-challenge {
            max-width: 300px;
            margin: 0 auto;
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
            margin-top: 50px;
            margin-bottom: 50px;
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
            background: #c8c8c8;
            background: hsl(0, 0%, 78%);
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

<body>
<header data-reactroot class="header1">
    <ul class="_1SQmm Bgc">
        <li class=""><a class="h1c" id="home" href="index.jsp" data-rapid_p="1" data-v9y="1">
            <svg width="18" height="18" viewBox="0 0 32 32" style="fill: black;">
                <path d="M16.153 3.224L0 16.962h4.314v11.814h9.87v-8.003h3.934v8.003h9.84V16.962H32"></path>
            </svg>
            Home
        </a></li>

        <li class="" ><a class="h1c" href="sign_in.jsp" data-rapid_p="2" data-v9y="1">
            Sign in
        </a></li>

        <li class="" ><a class="h1c" href="sign_up.jsp" data-rapid_p="3" data-v9y="1">
            Sign up
        </a></li>

        <li class="line"></li>

        <li id="min-search">
            <form id="formUrl" action="searchServlet" method="get">
                <input id="pin-input" class="pin-input" type="text" name="kw" placeholder="Search for stocks...">
                <input class="btn" type="button" id="topSearchSubmit" data-eid="qd_A62" onclick="submit()">
            </form>
        </li>
    </ul>

    <div style="height:200px;"><img class="pic1" style="width:100%; height:100%;"  src="picture/background3.jpg" /></div>

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
<div class="mainContext">
    <div class="login-box">
        <div class="login-logo"><img src="picture/Chicken.png" alt="Yahoo" class="logo" width="200" height="">
        </div>
        <p id="error-offline" role="alert" class="row error-offline hide">Network connection timed out. Please try&nbsp;again.</p>
        <form id="login-username-form" class="username-challenge">
            <input type="hidden" name="crumb" value="CbNbmcLwbcx">
            <input type="hidden" name="acrumb" value="r7TscSmE">
            <input type="hidden" name="sessionIndex" value="QQ--">

            <h1 class="sign-in-title" id="mbr-login-greeting">
                Sign&nbsp;in
            </h1>




            <div id="username-country-code-field" class="username-country-code cci-dropdown-disabled code-of-length-1">
                <input class="phone-no " type="text" name="username" id="login-username" tabindex="1"  placeholder="Enter your&nbsp;username" required>
                <input class="phone-no " type="password" name="password" id="login-password" tabindex="2"  placeholder="Enter your&nbsp;password" required>
            </div>


            <button type="button" id="login-signin" class="orko-button-primary orko-button" onclick="validate()" tabindex="2">NEXT</button>
            <div class="col-md-6" id="error"></div>
            <p class="row sign-up">
                Don't have an&nbsp;account?
                <a href="sign_up.jsp">Sign&nbsp;up</a>
            </p>
        </form>

    </div>
</div>
</body>


</html>
