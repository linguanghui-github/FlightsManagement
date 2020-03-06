package cn.flights.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.flights.entity.City;
import cn.flights.entity.Flight;

public interface FlightService {
	// ��ѯ���г�����Ϣ
	public List<City> findAllCity() throws Exception;

	// ������ϰ����ID�͵������ID��ѯ������Ϣ
	public List<Flight> findflightsByDepartureIdAndArrivalId(Map<String, Object> map) throws Exception;

	// �����¼����ѯ
	public Integer findCountFlight(String flightNo) throws Exception;

	// ��Ӻ�����Ϣ
	public int addFlightInfo(Flight flight) throws Exception;
}
