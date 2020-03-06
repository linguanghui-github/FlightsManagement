package cn.flights.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.flights.entity.City;
import cn.flights.entity.Flight;
import cn.flights.service.FlightService;

@Controller
@RequestMapping("/flight")
public class FlightController {
	@Resource
	FlightService flightService;
	
	@RequestMapping("/findAllCity")
	public String findAllCity(Model model) {
		try {
			List<City> citys = flightService.findAllCity();
			model.addAttribute("citys", citys);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return"flights";
	}						
	@RequestMapping(value="/findflightsById")
	public String findflightsById(@RequestParam(value="departureCity",required=false)Integer departureCity,
			@RequestParam(value="arrivalCity",required=false)Integer arrivalCity,
			HttpServletRequest request,Model model) {
		try {
			List<City> citys = flightService.findAllCity();
			model.addAttribute("citys",citys);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("depar", departureCity);
			map.put("arrival", arrivalCity);
			model.addAllAttributes(map);
			List<Flight> flights = flightService.findflightsByDepartureIdAndArrivalId(map);
			if (flights.size()==0) {
				request.setAttribute("message", "没有找到符合条件的航班，请改变查询条件重试");
				return"flights";
			}
			model.addAttribute("flights", flights);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return"flights";
	}
	@RequestMapping("/toAddFlightInfo")
	public String toAddFlightInfo(Model model) {
		try {
			List<City> citys = flightService.findAllCity();
			model.addAttribute("citys",citys);
			/* model.addAttribute("flight",flight); */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return"addFlight";
	}
	
	@RequestMapping(value="/addFlightInfo",method=RequestMethod.POST)
	public String addFlightInfo(Flight flight,HttpServletResponse response) {
		System.out.println("flight.getDepartureCityName()============>"+flight.getDepartureCityName());
		try {
			int num = flightService.addFlightInfo(flight);
			if (num>0) {		
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "redirect:/flight/findflightsById"; 
	}
	@RequestMapping(value="/findCountFlight",method=RequestMethod.POST)
	@ResponseBody
	public String findCountFlight(String flightNo) {
		/* System.out.println("flightNo=========================>"+flightNo); */
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer count = flightService.findCountFlight(flightNo);
			/* System.out.println("count=========================>"+count); */
			if (count>0) {
				resultMap.put("msg",false);
			}else {
				resultMap.put("msg",true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return JSON.toJSONString(resultMap);
	}
}
