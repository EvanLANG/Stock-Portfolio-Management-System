<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Chen
  Date: 2018/3/22
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>

<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        function PaintLine(sym, data){
            var cv = document.getElementById(sym);
            cv.width = 300;
            cv.height = 60;
            cv.style.background = "#f7faff";
            var ctx = cv.getContext("2d");
            var color_up = "green";
            var color_down = "#f00";
            var maxNum = Math.max.apply(null, data);    //求数组中的最大值
            var times = 35,
                xLength = cv.width,    //x轴的长度
                yLength = cv.height,  //y轴的长度
                x0 = 0,  //原点x轴坐标
                y0 = (1 - data[0]/maxNum) * yLength * times,  //原点y轴坐标
                yArrow_x = cv.width,  //y轴箭头处坐标x
                yArrow_y = y0, //y轴箭头处坐标y
                pointsWidth = xLength/(data.length + 1);    //折线上每个点之间的距离
            ctx.beginPath();//控制绘制的折线不受坐标轴样式属性的影响

            //绘制y轴
            ctx.moveTo(x0, y0);
            ctx.lineTo(yArrow_x, yArrow_y);
            ctx.strokeStyle = "#92a0ac";
            //中断（坐标轴和折线的）连接
            ctx.stroke();

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

            ctx.stroke();
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
      text-align: center;
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


<script type="text/javascript">
    function get_new_messages() {
        if (${empty sessionScope.comp})
            {
                    $.ajax({
                    type: 'post',
                    url: 'onloadindexServlet',
                    data: {},
                    success: function myreload()
                    {
                        window.location.reload();
                        setTimeout('myreload()',6000);
                    }
                })
            }
    }
</script>


<body onload="get_new_messages()">
<header data-reactroot class="header1">
  <ul class="_1SQmm Bgc">

    <li class=""><a class="h1c" id="home" href="index.jsp" data-rapid_p="1" data-v9y="1">
      <svg width="18" height="18" viewBox="0 0 32 32" style="fill: white;">
        <path d="M16.153 3.224L0 16.962h4.314v11.814h9.87v-8.003h3.934v8.003h9.84V16.962H32"></path>
      </svg>
      Home
    </a></li>

      <li class="" >
          <c:choose>
              <c:when test="${not empty sessionScope.user_id}">
                  <a class="h1c" id="in" href="index.jsp">${sessionScope.user_id}</a>
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
      <form id="formUrl" action="" method="get" target="_blank" >
        <input id="pin-input" class="pin-input" type="text" name="kw" placeholder="Search for symbols...">
        <input class="btn" type="button" id="topSearchSubmit" data-eid="qd_A62" onclick="submit()">
      </form>
    </li>
  </ul>

  <img class="pic" src="picture/background3.jpg" />

  <ul class="h2c">
    <li><a class="text1" href="" data-rapid_p="21" data-v9y="1">Finance Home</a></li>
    <li><a class="text1" href="" data-rapid_p="22" data-v9y="1">Watchlists</a></li>
    <li><a class="text1" href="" data-rapid_p="23" data-v9y="1">My Portfolio</a></li>
    <li><a class="text1" href="" data-rapid_p="24" data-v9y="1">My Screeners</a></li>
    <li><a class="text1" href="" data-rapid_p="31" data-v9y="1">Markets</a></li>
    <li><a class="text1" href="" data-rapid_p="31" data-v9y="1">Industries</a></li>
    <li><a class="text1" href="" data-rapid_p="31" data-v9y="1">Personal Finance</a></li>
    <li><a class="text1" href="" data-rapid_p="31" data-v9y="1">Technology</a></li>
    <li><a class="text1" href="" data-rapid_p="31" data-v9y="1">Originals</a></li>
    <li><a class="text1" href="" data-rapid_p="31" data-v9y="1">Events</a></li>
  </ul>
</header>





<div id="container" class="mainContext">
<style>

    .stock-info {
        text-align: left;
        padding: 10px 20px;
        background-color: #f7faff;
        border-top: #e9edf0 solid 2px;
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
        border-top: #e9edf0 solid 1px;
        padding: 14px 0;
        height: 40px;
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
        width: 200px;
        height: 40px;
        background-color: #2e85ff;
        border: 0;
        border-radius: 4px;
        font-size: 16px;
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
</style>


    <div id="content_mid" class="">
        <c:choose>
            <c:when test="${empty sessionScope.comp}">
                <img alt="" src="picture/loading.gif" style="vertical-align: middle" />
            </c:when>
            <c:otherwise>

                <c:forEach items="${sessionScope.comp}" var="current_comp">


                    <div class="stock-info">
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
                                    <a class="stock-chart"><canvas width="300" height="100" id="${current_comp.symbol}"></canvas></a>
                                    <script>PaintLine('${current_comp.symbol}', ${sessionScope.pricelist.get(4)});</script>
                                    <a class="stock-add"><button class="">+ Favorite</button></a>
                                </c:when>
                                <c:otherwise>
                                    <strong class="_close s-down">${current_comp.current}</strong>
                                    <span class = s-down>${current_comp.change}</span>
                                    <span class = s-down>${current_comp.change_percent}%</span>
                                    <span class = s-down></span>
                                    <a class="stock-chart"><canvas width="300" height="100" id="${current_comp.symbol}"></canvas></a>
                                    <script>PaintLine('${current_comp.symbol}', ${sessionScope.pricelist.get(4)});</script>
                                    <a class="stock-add"><button class="">+ Favorite</button></a>
                                </c:otherwise>
                                </c:choose>


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
