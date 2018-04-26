<%--
  Created by IntelliJ IDEA.
  User: LANG
  Date: 2018/4/25
  Time: 16:46
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
        }
        ._1SQmm>li {
            display: inline-block;
            margin-right: 20px;
            position: relative;
            top: 30%;
            font-family: PingFangSC-Regular,HelveticaNeue-Light,'Helvetica Neue Light','Microsoft YaHei',sans-serif;
        }
        .Bgc {
            background-color: black;
        }
        .h1c {
            padding: 3.33333px;
            color: white;
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
            color: white;
            border-bottom: 1px solid #aaa;
            background: black;
        }

        .hide {
            display:none;
        }

        input {
            font-family: PingFangSC-Regular,HelveticaNeue-Light,'Helvetica Neue Light','Microsoft YaHei',sans-serif;
            border: none;
            outline: 0;
        }

        .btnn {
            background:url("picture/search.jpg")
            no-repeat left top;
            padding-bottom:4px;
            width: 20px;
        }

        li.line {
            height: 18px;
            border-right: 1px solid #515056;
        }

        .pic1 {
            width: 100%;
            height: 200px;
        }

        .pic2 {
            width: 40%;
            height: 200px;
        }

        .h2c {
            list-style: none;
            font-weight: 600;
            color: #000;
            padding: 0 20px;
            margin: 0 auto;
        }
        .h2c>li {
            -webkit-transition: opacity .3s;
            transition: opacity .3s;
            display: inline-block;
            padding-bottom: 4px;
            margin-right: 30px;
        }
        .h2c a:link, .h2c a:visited {
            color: #000;
            text-decoration: none;
            display: block;
        }
        .text1 {
            display: block;
            cursor: pointer;
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

    </style>
</head>
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
        background-color: black;
    }
    .h1c {
        padding: 3.33333px;
        color: white;
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
        color: white;
        border-bottom: 1px solid #aaa;
        background: black;
    }

    .hide {
        display:none;
    }

    input {
        font-family: PingFangSC-Regular,HelveticaNeue-Light,'Helvetica Neue Light','Microsoft YaHei',sans-serif;
        border: none;
        outline: 0;
    }


    li.line {
        height: 18px;
        border-right: 1px solid #515056;
    }

    .pic {
        width: 100%;
        height: 200px;
    }

    .h2c {
        list-style: none;
        font-weight: 600;
        color: #000;
        padding: 0 20px;
        margin: 0 auto;
    }
    .h2c>li {
        -webkit-transition: opacity .3s;
        transition: opacity .3s;
        display: inline-block;
        padding-bottom: 4px;
        margin-right: 30px;
    }
    .h2c a:link, .h2c a:visited {
        color: #000;
        text-decoration: none;
        display: block;
    }
    .text1 {
        display: block;
        cursor: pointer;
        line-height: 36px;
    }
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
            <svg width="18" height="18" viewBox="0 0 32 32" style="fill: white;">
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
            <form id="formUrl" action="" method="get" target="_blank">
                <input id="pin-input" class="pin-input" type="text" name="kw" placeholder="Search for symbols...">
                <input class="btnn" type="button" id="topSearchSubmit" data-eid="qd_A62" onclick="submit()">
            </form>
        </li>
    </ul>

    <img class="pic1" src="picture/background3.jpg" />

    <ul class="h2c">
        <li><a class="text1" href="/index.jsp" data-rapid_p="21" data-v9y="1">Finance Home</a></li>
        <li><a class="text1" href="" data-rapid_p="31" data-v9y="1">Markets</a></li>
        <li><a class="text1" href="" data-rapid_p="31" data-v9y="1">Personal Finance</a></li>
        <li><a class="text1" href="/HeadNews.jsp" data-rapid_p="31" data-v9y="1">Events</a></li>
        <li><a class="text1" href="/AboutUs.jsp" data-rapid_p="31" data-v9y="1">AboutUs</a></li>
        <li><a class="text1" href="/Contactus.jsp" data-rapid_p="31" data-v9y="1">ContactUs</a></li>
    </ul>

</header>
<h2 align="center">Us</h2>


<div class="jumbotron text-center">
    <h1>E-Finance</h1>
    <h3>Congratulations, <%= ((String)request.getAttribute("email"))%>, Subscribed successfully!.</h3>
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
