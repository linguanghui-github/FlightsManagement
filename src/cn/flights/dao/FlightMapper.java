package cn.flights.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.flights.entity.City;
import cn.flights.entity.Flight;

public interface FlightMapper {
	//查询所有城市信息
	public List<City> findAllCity()throws Exception;
	//根据起习城市ID和到达城市ID查询航班信息
	public List<Flight> findflightsByDepartureIdAndArrivalId(Map<String, Object> map)throws Exception;
	//航班记录数查询
	public Integer findCountFlight(String flightNo)throws Exception;
	//添加航班信息
	public int addFlightInfo(Flight flight)throws Exception;
	
}
