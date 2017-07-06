package com.changhong.yywl.data.analyse.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.yywl.data.analyse.common.jdbc.entity.MapEntity;
import com.changhong.yywl.data.analyse.common.jdbc.response.RestApiResforMap;
import com.changhong.yywl.data.analyse.common.jdbc.service.MapService;

@Controller
public class MapController {

	@Autowired
	private MapService mapService;

	@RequestMapping("/map")
	@ResponseBody
	public RestApiResforMap getCityandProv() {
		RestApiResforMap ram = new RestApiResforMap();
		try {
			List<MapEntity> listProv = mapService.findDataofProv();
			List<MapEntity> listCity = mapService.findDataofCity();
			if (listProv != null && listProv.size() > 0) {
				// 以100进位取整人数，尾数不足100按100计
				Integer maxData = listProv.get(0).getValue();
				int i = maxData % 100;
				if (i > 0)
					maxData = (maxData / 100) * 100 + 100;
				ram.setMaxData(maxData);
			} else
				ram.setMaxData(0);
			ram.setCode(200);
			ram.setCountryData(listProv);
			ram.setCityData(listCity);
		} catch (Exception e) {
			e.printStackTrace();
			ram.setCode(0);
		}
		return ram;
	}

}
