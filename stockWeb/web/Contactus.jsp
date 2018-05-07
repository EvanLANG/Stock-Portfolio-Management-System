<%--
  Created by IntelliJ IDEA.
  User: LANG
  Date: 2018/4/24
  Time: 23:05
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

        .btn {
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

    .btn {
        background:url("picture/search.jpg")
        no-repeat left top;
        padding-bottom:4px;
        width: 20px;
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
            <form id="formUrl" action="searchServlet" method="get" target="_blank">
                <input id="pin-input" class="pin-input" type="text" name="kw" placeholder="Search for symbols...">
                <input class="btn" type="button" id="topSearchSubmit" data-eid="qd_A62" onclick="submit()">
            </form>
        </li>
    </ul>

    <img class="pic1" src="picture/background3.jpg" />

    <ul class="h2c">
        <li><a class="text1" href="/index.jsp" data-rapid_p="21" data-v9y="1">Finance Home</a></li>
        <li><a class="text1" href="rankServlet" data-rapid_p="31" data-v9y="1">Markets</a></li>
        <li><a class="text1" href="" data-rapid_p="31" data-v9y="1">Personal Finance</a></li>
        <li><a class="text1" href="/HeadNews.jsp" data-rapid_p="31" data-v9y="1">Events</a></li>
        <li><a class="text1" href="/AboutUs.jsp" data-rapid_p="31" data-v9y="1">AboutUs</a></li>
        <li><a class="text1" href="/Contactus.jsp" data-rapid_p="31" data-v9y="1">ContactUs</a></li>
    </ul>
</header>
<h2 align="center">CONTACT</h2>

<!-- Add Google Maps -->
<div id="googleMap" style="height:400px;width:100%;"></div>
<script>
    function myMap() {
        var myCenter = new google.maps.LatLng(-33.917778, 151.231111);
        var mapProp = {center:myCenter, zoom:12, scrollwheel:true, draggable:true, mapTypeId:google.maps.MapTypeId.ROADMAP};
        var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
        var marker = new google.maps.Marker({position:myCenter});
        marker.setMap(map);
    }
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA2UQazoPOdbzjPm4mPxvW95vryHpouYZ0&callback=myMap"></script>
<div id="contact" class="container-fluid bg-grey">

    <div class="row">
        <div class="col-sm-5">
            <p>Please feel free to contact us.</p>
            <p><span class="glyphicon glyphicon-map-marker"></span> Sydney, NSW, Australia</p>
            <p><span class="glyphicon glyphicon-phone"></span> +61 0400 000 000</p>
            <p><span class="glyphicon glyphicon-envelope"></span> z5119297@student.unsw.edu.au,z5099130@student.unsw.edu.au</p>
            <p><span class="glyphicon glyphicon-envelope"></span> z5103319@student.unsw.edu.au,z5103300@student.unsw.edu.au</p>
        </div>
    </div>

</div>
</body>


</html>
