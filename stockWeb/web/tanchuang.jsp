<%--
  Created by IntelliJ IDEA.
  User: Chen
  Date: 2018/5/17
  Time: 4:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Detail</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/Chart.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/Chart.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/layer-v3.1.1/layer/layer.js"></script>
    <script>
        function detail_daily_graph(sym,data,label,data2,label2){
            var lineChartData = {
                //表的X轴参数
                labels : label.split(","),
                datasets : [
                    {
                        fillColor : "transparent",     //背景色，常用transparent透明
                        borderColor : "#6ac4c7",  //线条颜色，也可用"#ffffff"
                        pointBackgroundColor : "#ffffff",   //点的填充颜色
                        pointBorderColor : "#77ffd8",            //点的外边框颜色
                        data : data,      //点的Y轴值
                        lineTension:0,
                        backgroundColor:"#fff",
                        fill:false,
                        label:"prize"
                    },
                ]
            }
            var ctx = document.getElementById(sym+"_myChart_day").getContext("2d");
            var lineChart = new Chart(ctx,{
                type:'line',
                data: lineChartData,
                options: {
                    title: {
                        display: true,
                        text: sym + ' Detail(daily)'
                    }
                }
            });
            var lineChartData2 = {
                //表的X轴参数
                labels : label2.split(","),
                datasets : [
                    {
                        fillColor : "transparent",     //背景色，常用transparent透明
                        borderColor : "#6ac4c7",  //线条颜色，也可用"#ffffff"
                        pointBackgroundColor : "#ffffff",   //点的填充颜色
                        pointBorderColor : "#77ffd8",            //点的外边框颜色
                        data : data2,      //点的Y轴值
                        lineTension:0,
                        backgroundColor:"#fff",
                        fill:false,
                        label:"prize",
                        pointHitRadius:3
                    },
                ]
            }
            var ctx2 = document.getElementById(sym+"_myChart_year").getContext("2d");
            var lineChart2 = new Chart(ctx2,{
                type:'line',
                data: lineChartData2,
                options: {
                    title: {
                        display: true,
                        text: sym + ' Detail(past two years)'
                    }
                }
            });
        }
    </script>
</head>
<body>
<div id="${param.symbol}_hide">
    <canvas id="${requestScope.sym}_myChart_day" width="500" height="350"></canvas>
    <canvas id="${requestScope.sym}_myChart_year" width="500" height="350"></canvas>
    <c:choose>
        <c:when test="${requestScope.flag == 'u'}">
            <script>
                detail_daily_graph('${requestScope.sym}',${sessionScope.user_pricelist.get(requestScope.index)},'${sessionScope.user_datelist.get(requestScope.index)}',${sessionScope.user_mpricelist.get(requestScope.index)},'${sessionScope.user_mdatelist.get(requestScope.index)}');
            </script>
        </c:when>
        <c:when test="${requestScope.flag == 's'}">
            <script>
                detail_daily_graph('${requestScope.sym}',${sessionScope.pricelist.get(requestScope.index)},'${sessionScope.datelist.get(requestScope.index)}',${sessionScope.mpricelist.get(requestScope.index)},'${sessionScope.mdatelist.get(requestScope.index)}');
            </script>
        </c:when>
        <c:otherwise>
            <script>
                detail_daily_graph('${requestScope.sym}',${sessionScope.pricelist1.get(requestScope.index)},'${sessionScope.datelist1.get(requestScope.index)}',${sessionScope.mpricelist1.get(requestScope.index)},'${sessionScope.mdatelist1.get(requestScope.index)}');
            </script>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
