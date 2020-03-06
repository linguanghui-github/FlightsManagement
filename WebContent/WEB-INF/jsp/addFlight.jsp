<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div{
	line-height: 50px;
	text-align: center;
}
form{
	text-align: left;
	margin-left: 580px;
}

</style>
</head>
<body>
<div>
	<h2>航班添加页面</h2>
	<form action="${pageContext.request.contextPath}/flight/addFlightInfo" method="post">
		<label>航班编号：</label>
		<input type="text" onblur="return checkFlightNo()" name="flightNo" id="flightNo" ><font color="red"></font><br>
		<label>起飞城市：</label>
		<select name="departureCity" id="departureCity">
			<option value="0">--请选择起飞城市--</option>
			<c:forEach var="city" items="${citys}">
			<option value="${city.id}">${city.cityName}</option>
			</c:forEach>
		</select>
		<label>起飞时间：</label>
		<input type="text" name="departureTime" onblur="" id="departureTime"><font color="red"></font>
		<br>
		<label>到达城市：</label>
		<select name="arrivalCity" id="arrivalCity">
			<option value="0">--请选择到达城市--</option>
			<c:forEach var="city" items="${citys}">
			<option value="${city.id}">${city.cityName}</option>
			</c:forEach>
		</select>
		<label>到达时间：</label>
		<input type="text" name="arrivalTime" onblur="" id="arrivalTime"><font color="red"></font><br>
		<input type="submit"value="添加"/>
	</form>
	</div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.js"></script>
<script>
		function checkFlightNo(){
			var flag = true;
			var flightNo = $("#flightNo").val().trim();
			$("#flightNo").next().html("");
			if(flightNo.length==0||flightNo==""){
				$("#flightNo").next().html("航班编号不能为空");
				flag=false;
			}else{
				$.ajax({
					url:"${pageContext.request.contextPath}/flight/findCountFlight",
					data:{"flightNo":flightNo},
					dataType:"json",
					type:"post",
					success: function(data) {
						if(data.msg){
							$("#flightNo").next().html("航班编号可以使用");
							flag = true;
						}else{
							$("#flightNo").next().html("航班编号已存在,请重新输入");
							flag = false;
						}
					}
				})
			}
			return flag;
		};
		$(function(){
			$("form").submit(function() {
				var flag=true;
				if(!checkdepartureTime())flag=false;
				if(!checkArrivalTime())flag=false;
				if(!checkForm())flag=false;
				if(!checkFlightNo())flag=false;
				return flag;
			});
			$("#arrivalTime").blur(checkArrivalTime);
			$("#departureTime").blur(checkdepartureTime);
		})
		var regExp =/^\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}$/;
		//var regExp =/^2019-[0][1-9]|[1][1-2]-[0-2][1-9]|[3][0-1]\s[0-1][0-9]|[2][0-4]:[0-5][0-9]:[0-5][0-9]$/;
		function checkdepartureTime() {
			var flag=false;
			var departureTime = $("#departureTime").val();
			if(departureTime.lenght==0||departureTime==""){
				$("#departureTime").next().html("起飞时间不能为空");
				flag = false;
			}else if(!regExp.test(departureTime)){
				$("#departureTime").next().html("起飞时间格式不正确");
				flag = false;
			} else{
				$("#departureTime").next().html("");
				flag = true;
			}
			return flag;
		}
		function checkArrivalTime(){
			var arrivalTime = $("#arrivalTime").val();
			var flag=false;
			if(arrivalTime.lenght==0||arrivalTime==""){
				$("#arrivalTime").next().html("到达时间不能为空");
				flag = false;
			}else if(!regExp.test(arrivalTime)){
				$("#arrivalTime").next().html("到达时间格式不正确");
				flag = false;
			}else{
				$("#arrivalTime").next().html("");
				flag = true;
			}
			return flag;
		}
      function checkForm(){
    	var departureCity = $("#departureCity").val();
  		var arrivalCity = $("#arrivalCity").val();
			if(departureCity==0){
				alert("请选择起飞城市");
				return false;
			}
			if(arrivalCity==0){
				alert("请选择到达城市");
				return false;
			};
			if(arrivalCity==departureCity){
				alert("起飞城市和到达城市不能相同");
				return false;
			};
			return true;
       }
</script>