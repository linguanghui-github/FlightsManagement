package cn.flights.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cn.flights.dao.FlightMapper;
import cn.flights.entity.City;
import cn.flights.entity.Flight;
@Service
public class FlightServiceImpl implements FlightService {
	@Resource
	FlightMapper flightMapper;
	@Override
	public List<City> findAllCity() throws Exception {
		// TODO Auto-generated method stub
		return flightMapper.findAllCity();
	}

	@Override
	public List<Flight> findflightsByDepartureIdAndArrivalId(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return flightMapper.findflightsByDepartureIdAndArrivalId(map);
	}

	@Override
	public Integer findCountFlight(String flightNo) throws Exception {
		// TODO Auto-generated method stub
		return flightMapper.findCountFlight(flightNo);
	}

	@Override
	public int addFlightInfo(Flight flight) throws Exception {
		// TODO Auto-generated method stub
		return flightMapper.addFlightInfo(flight);
	}

}
