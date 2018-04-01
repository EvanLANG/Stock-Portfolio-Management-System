<%--
  Created by IntelliJ IDEA.
  User: Chen
  Date: 2018/3/22
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
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
  #content_left {
    float: left;
    width: 750px;
    border-bottom: 1px solid #BDBDBD;
  }
  .leftContext
  {
    float: left;
    width: 750px;
    height:320px;
    border-bottom: 1px solid #BDBDBD;
  }
  #content_right {
    float: right;
    width: 328px;
    height: 100%;
    border-left: 1px solid #BDBDBD;
  }
  .mainContext
  {
    border-bottom:solid 1px #BDBDBD;
    border-left:solid 1px #BDBDBD;
    border-right:solid 1px #BDBDBD;
    width: 100%;
    height: 100%;
    background-color:#FFFCEC;
    margin:0;
    padding: 0;
    position:relative;
    margin-left: auto;
    margin-right: auto;
  }
</style>

<body>
<header data-reactroot class="header1">
  <ul class="_1SQmm Bgc">

    <li class=""><a class="h1c" id="home" href="index.jsp" data-rapid_p="1" data-v9y="1">
      <svg width="18" height="18" viewBox="0 0 32 32" style="fill: white;">
        <path d="M16.153 3.224L0 16.962h4.314v11.814h9.87v-8.003h3.934v8.003h9.84V16.962H32"></path>
      </svg>
      Home
    </a></li>

    <li class="" ><a class="h1c" id="signin_or_userid" href="sign_in.jsp" data-rapid_p="2" data-v9y="1">
      Sign in
    </a></li>

    <li class="" ><a class="h1c" id="signup_or_logout" href="sign_up.jsp" data-rapid_p="3" data-v9y="1">
      Sign up
    </a></li>

      <script type="javascript">
          function initservlet(){
              $.ajax({
                  type:"POST",
                  url:"initializingservlet",
                  data:{user:"${sessionScope.user_id}"},
                  success: change()
                  });
          }
      </script>

    <script type="javascript">
    function change() {
        var current_user = sessionScope.user_id;
        if (current_user) {
            document.getElementById("signin_or_userid").href = "user_page.jsp";
            document.getElementById("signin_or_userid").innerHTML = current_user;
            document.getElementById("signup_or_logout").href = "index.jsp";
            document.getElementById("signup_or_logout").innerHTML = "Log out";
        }
    }
    </script>

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

  <div id="content_right" class="">

  </div>

  <div id="content_left" class="">

  </div>

</div>


</body>
</html>
