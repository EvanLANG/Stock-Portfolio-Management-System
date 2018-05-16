<%--
  Created by IntelliJ IDEA.
  User: Chen
  Date: 2018/4/25
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/Chart.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/Chart.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript">
        function PaintLine(sym, data){
            var cv = document.getElementById(sym);
            cv.width = 300;
            cv.height = 100;
            cv.style.background = "#fff";
            var ctx = cv.getContext("2d");
            var color_up = "green";
            var color_down = "#f00";
            var maxNum = Math.max.apply(null, data);   //求数组中的最大值
            var times = data[0]/(Math.max.apply(null, data) - Math.min.apply(null, data)),
                xLength = cv.width,    //x轴的长度
                yLength = cv.height,  //y轴的长度
                x0 = 0,  //原点x轴坐标
                y0 = (1 - data[0]/maxNum) * yLength * times,  //原点y轴坐标
                yArrow_x = cv.width,  //y轴箭头处坐标x
                yArrow_y = y0, //y轴箭头处坐标y
                pointsWidth = xLength/(data.length + 1);    //折线上每个点之间的距离
            if (data.length==0) {ctx.font="25px Arial";  ctx.strokeText("No Daily data", 150,20); return;}
            ctx.globalAlpha = 0.25;
            ctx.font="50px Arial";  ctx.strokeText("Daily data", 20,65);
            ctx.beginPath();//控制绘制的折线不受坐标轴样式属性的影响
            //绘制y轴
            ctx.globalAlpha = 1;
            ctx.moveTo(x0, y0);
            ctx.lineTo(yArrow_x, yArrow_y);
            ctx.strokeStyle = "#92a0ac";
            //中断（坐标轴和折线的）连接
            ctx.stroke();
            ctx.globalAlpha = 0.6;
            //绘制折线
            for (var i = 0; i < data.length; i++) {
                var pointX =  (i + 1) * pointsWidth;
                var pointY = (1 - data[i]/maxNum) * yLength * times;

                if (pointY > (1 - data[0]/maxNum) * yLength * times) {
                    ctx.strokeStyle = color_down;
                } else {
                    ctx.strokeStyle = color_up;
                }

                ctx.lineWidth = pointsWidth - 2;
                ctx.beginPath();
                ctx.moveTo(pointX,(1 - data[0]/maxNum) * yLength * times);
                ctx.lineTo(pointX,pointY);
                ctx.closePath();
                ctx.stroke();
            }
        }
        function PaintMonthlyLine(sym, data){
            var cv = document.getElementById(sym);
            cv.width = 300;
            cv.height = 100;
            cv.style.background = "#fff";
            var ctx = cv.getContext("2d");
            var color_up = "green";
            var color_down = "#f00";
            var maxNum = Math.max.apply(null, data);   //求数组中的最大值
            var times = data[0]/(Math.max.apply(null, data) - Math.min.apply(null, data)),
                xLength = cv.width,    //x轴的长度
                yLength = cv.height,  //y轴的长度
                x0 = 0,  //原点x轴坐标
                y0 = (1 - data[0]/maxNum) * yLength * times,  //原点y轴坐标
                yArrow_x = cv.width,  //y轴箭头处坐标x
                yArrow_y = y0, //y轴箭头处坐标y
                pointsWidth = xLength/(data.length + 1);    //折线上每个点之间的距离
            if (data.length==0) {ctx.font="25px Arial";  ctx.strokeText("No Monthly data", 50,30); return;}
            ctx.globalAlpha = 0.25;
            ctx.font="50px Arial";  ctx.strokeText("Monthly data", 10,65);
            ctx.beginPath();//控制绘制的折线不受坐标轴样式属性的影响
            //绘制y轴
            ctx.globalAlpha = 1;
            ctx.moveTo(x0, y0);
            ctx.lineTo(yArrow_x, yArrow_y);
            ctx.strokeStyle = "#92a0ac";
            //中断（坐标轴和折线的）连接
            ctx.stroke();

            ctx.globalAlpha = 0.6;
            //绘制折线
            for (var i = 0; i < data.length; i++) {
                var pointX =  (i + 1) * pointsWidth;
                var pointY = (1 - data[i]/maxNum) * yLength * times;

                if (pointY > (1 - data[0]/maxNum) * yLength * times) {
                    ctx.strokeStyle = color_down;
                } else {
                    ctx.strokeStyle = color_up;
                }

                ctx.lineWidth = pointsWidth - 2;
                ctx.beginPath();
                ctx.moveTo(pointX,(1 - data[0]/maxNum) * yLength * times);
                ctx.lineTo(pointX,pointY);
                ctx.closePath();
                ctx.stroke();
            }
        }

        function changegraph(sig, sym, data, data2){
            ch=document.getElementById(sym + '_graph');
            if (sig == 'M') {
                PaintMonthlyLine(sym, data);
                ch.innerHTML="<button onclick=\"changegraph('D','"+sym+"',["+data2+"],["+data+"])\">To Daily</button>";
            }else {
                PaintLine(sym, data);
                ch.innerHTML="<button onclick=\"changegraph('M','"+sym+"',["+data2+"],["+data+"])\">To Monthly</button>";
            }
        }
        function cancel(sym) {
            $.ajax({
                type: 'post',
                url: 'updatefavoServlet',
                data: {'symbol':sym,'type':'c'},
                success: function(response)
                {
                    obj = document.getElementById(sym+"favo");
                    obj.parentNode.removeChild(obj);
                }
            })
        }

    </script>
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
        background-color: white;
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

    .pic {
        height: 200px;
    }

    .h2c {
        background-color: white;
        list-style: none;
    <%--font-weight: 600;--%>
    color: #000;
    padding: 0 20px;
    margin: 0 auto;
    border-bottom: 1px solid #f1f1f1;
    }
    .h2c>li {
        -webkit-transition: opacity .3s;
        transition: opacity .3s;
        display: inline-block;
        padding-bottom: 4px;
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
    #content_mid {
        margin-left: auto;
        margin-right: auto;
        width: 70%;
        text-align: center;

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


    h1 {
        display: block;
        font-size: 2em;
        -webkit-margin-before: 0.67em;
        -webkit-margin-after: 0.67em;
        -webkit-margin-start: 0px;
        -webkit-margin-end: 0px;
        font-weight: bold;
    }
    #content_right {
        float:right;
        margin-left: auto;
        width: 60%;
        text-align: center;
        border-right: 1px solid #BDBDBD;
        border-left: 1px solid #BDBDBD;
        background-color: white;
    }
    #content_left {
        float: left;
        text-align: left;
        margin-right: auto;
        width: 39%;
    }

</style>


<script type="text/javascript">
    function myreload(){
        window.location.reload();
    }
    function get_new_messages() {
        //var follow = "${sessionScope.user_id.followcoms}";
        //if(follow) {
        //    var arr = follow.split("#");
         //   var num = Math.min(10,arr.length);
        //    loadDocfirst(arr.slice(0,num));
        //}
        if ((${sessionScope.user_comp == null}) || (${sessionScope.where != "user"}))
            $.ajax({
                type: 'post',
                url: 'UserLoadingServlet',
                data: {},
                success: function(response)
                {
                    setTimeout(myreload(),6000);
                }
            });
    }
    function display_detail(sym,index,f){
        layer.open({
            type: 2,
            shade: 0,
            resize:false,
            offset: ['50px', ''],
            title:  ['Detail Chart', 'font-size:15px;font-family:Arial,Helvetica,sans-serif;'],
            moveType: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['600px', '450px'], //宽高
            content: '/DetailServlet?sym='+sym+'&index='+index+'&flag='+f
        });
    }
    function display_news(company) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var obj = JSON.parse(this.responseText);
                var x = obj.articles
                show_news(x);
            }
        };
        var begin = new Date();
        var end = new Date();
        begin.setDate(begin.getDate() - 30);
        var byear = begin.getFullYear();
        var bmonth = begin.getMonth() + 1;
        var bday = begin.getDate();
        var eyear = end.getFullYear();
        var emonth = end.getMonth() + 1;
        var eday = end.getDate();
        var bdate = byear + "-" + bmonth + "-" + bday;
        var edate = eyear + "-" + emonth + "-" + eday;
        //xhttp.open("GET", "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/Business%20Day/1.json?api-key=e716033797834288814805dc70eb4907", true);
        xhttp.open("GET", "https://newsapi.org/v2/everything?q=" + company + "&sources=google-news,abc-news,bbc-news,business-insider&sortBy=popularity&from="+bdate+"&to="+edate+"&apiKey=3d0faee4c870480d904014c95c5759fb", true);
        xhttp.send();
    }
    function show_news(x) {
        var i;
        var out='<style>' +
            '#sansserif{font-family:Arial,Helvetica,sans-serif;}' +
            '</style>';

        for (i = 0; i < x.length; i++) {
            if(x[i].urlToImage == null){
                out += '<p><span><a style="display:block"  href="' + x[i].url + '"target="_blank" id="sansserif">  ' + x[i].title + "</a></span><br>"+""+'<span id="sansserif" >  '+x[i].description+'</span><br><span class="small" id="sansserif">  '+x[i].author+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+x[i].publishedAt+'</span><br><br>';
            }else{
                out += '<p><span><a style="display:block"  href="' + x[i].url + '"target="_blank" id="sansserif">  ' + x[i].title + '</a></span><br><img  src='+ x[i].urlToImage +' alt="Error" width="210" height="140" ><br><span class="small" id="sansserif">  '+x[i].description+'</span><br><span class="small" id="sansserif">  '+x[i].author+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+x[i].publishedAt+'</span><br><br>';
            }

        }
        layer.open({
            type: 1,
            shade: 0,
            resize:false,
            offset: ['50px', ''],
            title:  ['Related News', 'font-size:15px;font-family:Arial,Helvetica,sans-serif;'],
            moveType: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['600px', '450px'], //宽高
            content: '<div style="padding:20px;">'+out+'</div>'
        });

    }
</script>


<body onload="get_new_messages()">
<header data-reactroot class="header1">
    <ul class="_1SQmm Bgc">

        <li class=""><a class="h1c" id="home" href="index.jsp" data-rapid_p="1" data-v9y="1">
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
            <form id="formUrl" action="searchServlet" method="get" >
                <input id="pin-input" class="pin-input" type="text" name="kw" placeholder="Search for stocks...">
                <input class="btn" type="button" id="topSearchSubmit" data-eid="qd_A62" onclick="submit()">
            </form>
        </li>
    </ul>

    <div style="height:200px;"><img class="pic" style="width:100%; height:100%;" src="picture/background3.jpg"  /></div>

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





<div id="container" class="mainContext">
    <style>

        .stock-info {
            text-align: left;
            padding: 10px 20px;
            background-color: #ffffff;
        }
        .stock-info .stock-bets h1 a {
            color: #333;
            margin-right: 15px;
            font-size: 28px;
        }
        .stock-info .stock-bets .price strong {
            font-size: 54px;
            line-height: 56px;
            margin-right: 20px;
        }
        strong, b {
            font-weight: bold;
        }

        .stock-info .stock-bets .price span {
            font-size: 18px;
            margin-right: 10px;
            font-weight: 700;
        }

        .stock-info .bets-content {
            margin-top: 20px;
        <%--border-top: #e9edf0 solid 1px;--%>
            padding: 14px 0;
            height: 40px;
            border-bottom: 1px solid #f0ecf0;
        }

        .stock-info .bets-content .bets-col-9 dl {
            width: 10.11%;
        }
        .stock-info .bets-content dl {
            float: left;
            width: 15%;
            line-height: 18px;
        }
        dl, dd {
            margin: 0;
        }
        .stock-info .bets-content dd {
            font-size: 16px;
            font-weight: 700;
        }
        .s-stop {
            color: #999;
        }
        .s-up {
            color: #1dbf60;
        }
        .s-down {
            color: #f24957;
        }
        ul, li {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        li {
            display: list-item;
            text-align: -webkit-match-parent;
        }
        .stock-info .stock-add button {
            width: 80px;
            height: 30px;
            background-color: #1dbf60;
            border: 20;
            border-radius: 5px;
            font-size: 10px;
            color: #FFF;
            cursor: pointer;
        }

        .stock-add {
            text-align: center;
            float:right;
        }

        .stock-info .bets-content dt {
            color: #92a0ac;
            font-size: 15px;
        }
        .bets-name {
            font-family: DIN,"Microsoft YaHei",Arial,sans-serif;
        }
        button {
            width: 80px;
            height: 30px;
            background-color: #1dbf60;
            border: 20;
            border-radius: 5px;
            font-size: 10px;
            color: #FFF;
            cursor: pointer;
        }
        .ranktop{
            padding-top: 30px;
            font-family:Arial,Helvetica,sans-serif;<%--marked --%>
            font-size:25px;
            font-weight:600;
        }
        .notshow{
            display:none;
        }

    </style>

    <div id="content_mid" class="mainContext" >
        <c:choose>
            <c:when test="${empty sessionScope.index_comp || sessionScope.where!='user'}">
                <%--<img alt="" src="picture/loading.gif" style="vertical-align: middle" />--%>
                <div class="ranktop">Chooce some stocks to follow : )</div>
            </c:when>
            <c:otherwise>

                <c:forEach items="${sessionScope.user_comp}" var="current_comp" varStatus="status">


                    <div class="stock-info" id=${current_comp.symbol}favo>
                        <div class="stock-bets">
                            <h1>
                                <a class="bets-name" href="">${current_comp.symbol}</a>
                            </h1>
                            <div class="price s-stop ">

                                <c:choose>
                                    <c:when test="${current_comp.sig == true}">
                                        <strong class="_close s-up">${current_comp.current}</strong>
                                        <span class = s-up>${current_comp.change}</span>
                                        <span class = s-up>${current_comp.change_percent}%</span>
                                        <span class = s-up></span>
                                    </c:when>
                                    <c:otherwise>
                                        <strong class="_close s-down">${current_comp.current}</strong>
                                        <span class = s-down>${current_comp.change}</span>
                                        <span class = s-down>${current_comp.change_percent}%</span>
                                        <span class = s-down></span>
                                    </c:otherwise>
                                </c:choose>
                                <a class="stock-chart">
                                    <canvas width="300" height="100" id="${current_comp.symbol}"></canvas>
                                    <script>PaintLine('${current_comp.symbol}', ${sessionScope.user_pricelist.get(status.index)});</script>
                                    <a style="" id="${current_comp.symbol}_graph">
                                        <button onclick="changegraph('M', '${current_comp.symbol}',${sessionScope.user_mpricelist.get(status.index)},${sessionScope.user_pricelist.get(status.index)} )">To Monthly</button>
                                    </a>
                                </a>
                                <a class="stock-add"><button  onclick="display_news('${current_comp.comname}')">News</button></a>
                                <button  onclick="display_detail('${current_comp.symbol}',${status.index},'u')">Detail</button>
                            </div>


                            <div class="bets-content">

                                <div class="bets-col-9">
                                    <c:choose>
                                        <c:when test="${current_comp.high > current_comp.open}">
                                            <dl><dt>High</dt><dd class="s-up">${current_comp.high}</dd></dl>
                                        </c:when>
                                        <c:otherwise>
                                            <dl><dt>High</dt><dd class="">${current_comp.high}</dd></dl>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${current_comp.low < current_comp.open}">
                                            <dl><dt>Low</dt><dd class="s-down">${current_comp.low}</dd></dl>
                                        </c:when>
                                        <c:otherwise>
                                            <dl><dt>Low</dt><dd class="">${current_comp.low}</dd></dl>
                                        </c:otherwise>
                                    </c:choose>
                                    <dl><dt>Open</dt><dd class="">${current_comp.open}</dd></dl>
                                    <dl><dt>Close</dt><dd>${current_comp.close}</dd></dl>
                                    <dl><dt>Volume</dt><dd>${current_comp.volume}</dd></dl>
                                    <a class="stock-add"><button  onclick="cancel('${current_comp.symbol}')">- Cancel</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </c:otherwise>
        </c:choose>
    </div>


</div>


</body>
</html>
