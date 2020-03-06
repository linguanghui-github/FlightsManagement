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
		 table,div{
			width:800px;
			margin:0px auto;
			text-align: center;
		}
		
		table tr:nth-of-type(even){
			background: pink;
		}
		
		table tr:nth-of-type(2){
			background: green;
		}
		p{
		text-align:center;
		color: red;
		}
		form,h1{
			text-align: center;
		}
	</style>

</head>
<body>
		<h1>航班查询页面</h1>
		<form>
		<label>起飞城市：</label>
		<select name="departureCity" id="departureCity">
			<option value="0">--请选择起飞城市--</option>
			<c:forEach var="city" items="${citys}">
			<option value="${city.id}" <c:if test="${depar==city.id}">selected</c:if>>${city.cityName}</option>
			</c:forEach>
		</select>
		<label>到达城市：</label>
		<select name="arrivalCity" id="arrivalCity">
			<option value="0">--请选择到达城市--</option>
			<c:forEach var="city" items="${citys}">
			
			<option value="${city.id}" <c:if test="${arrival==city.id}">selected</c:if>>${city.cityName}</option>
			</c:forEach>
		</select>
		<input type="button" id="submit" value="查询">
		</form>
		<div>
    <table >
    <tr>
     <td colspan="7">帮您查找到以下航班</td>
     </tr>
     <tr>
     <th>航班编号</th>
     <th>起飞城市</th>
     <th>起飞时间</th>
     <th>到达城市</th>
     <th>到达时间</th>
     </tr>
     <c:forEach var="li" items="${flights}">
     <tr>
    
     <td>${li.flightNo}</td>
     <td>${li.departureCityName}</td>
      <td>
      <fmt:formatDate value="${li.departureTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
      </td>
      <td>${li.arrivalCityName}</td>
     <td>
      <fmt:formatDate value="${li.arrivalTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
     </td>
   
     </tr>
    </c:forEach>   
    </table>
    <p>${message}</p>
    </div>
  </body>
		
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.js"></script>
<script>
	$(function () {
		$("#submit").click(function() {
			var departureCity =$("#departureCity").val();
			var arrivalCity =$("#arrivalCity").val();
			if(departureCity.length==0||departureCity==0){
				alert("请选择起飞城市");
				return false;
			}
			if(arrivalCity.length==0||arrivalCity==0){
				alert("请选择到达城市");
				return false;
			}
			if(departureCity==arrivalCity){
				alert("起飞城市和到达城市不能相同");
				return false;
			}
			location.href="${pageContext.request.contextPath}/flight/findflightsById?&departureCity="+departureCity
					+"&arrivalCity="+arrivalCity;
		})
		
	})


</script>