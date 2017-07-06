package com.wt.acountweb.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wt.acountweb.entity.AcountInfo;
import com.wt.acountweb.entity.NoteInfo;
import com.wt.acountweb.service.AcountService;
import com.wt.acountweb.service.NoteService;
import com.wt.acountweb.utils.JsonUtils;

@Controller
@RequestMapping("/acount")
public class AoucntController {

	@Autowired
	private AcountService acountService;

	@Autowired
	private NoteService noteService;

	@RequestMapping("/saveAll")
	@ResponseBody
	public JSONObject saveAll(@RequestBody JSONObject params) {
		JSONObject result = new JSONObject();
		JSONObject countJson = new JSONObject();
		countJson.put("inCount", 0);
		countJson.put("outCount", 0);
		countJson.put("noteCount", 0);
		countJson.put("reInCount", 0);
		countJson.put("reOutCount", 0);
		countJson.put("reNoteCount", 0);
		try {
			System.out.println("params:" + params.toJSONString());
			if (params.containsKey("in")) {
				JSONArray jaIn = params.getJSONArray("in");
				List<AcountInfo> aisIn = getPOJO(jaIn, AcountInfo.class, "in");
				acountService.deleteByStatus("in");
				acountService.saveAll(aisIn);
				countJson.put("inCount", aisIn.size());
			}
			if (params.containsKey("out")) {
				JSONArray jaOut = params.getJSONArray("out");
				List<AcountInfo> aisOut = getPOJO(jaOut, AcountInfo.class, "out");
				acountService.deleteByStatus("out");
				acountService.saveAll(aisOut);
				countJson.put("outCount", aisOut.size());
			}
			if (params.containsKey("note")) {
				JSONArray jaNote = params.getJSONArray("note");
				List<NoteInfo> note = getPOJO(jaNote, NoteInfo.class, "note");
				noteService.deleteByStatus("note");
				noteService.saveAll(note);
				countJson.put("noteCount", note.size());
			}
			if (params.containsKey("re_in")) {
				JSONArray jaReIn = params.getJSONArray("re_in");
				List<AcountInfo> reIn = getPOJO(jaReIn, AcountInfo.class, "re_in");
				acountService.deleteByStatus("re_in");
				acountService.saveAll(reIn);
				countJson.put("reInCount", reIn.size());
			}
			if (params.containsKey("re_out")) {
				JSONArray jaReOut = params.getJSONArray("re_out");
				List<AcountInfo> reOut = getPOJO(jaReOut, AcountInfo.class, "re_out");
				acountService.deleteByStatus("re_out");
				acountService.saveAll(reOut);
				countJson.put("reOutCount", reOut.size());
			}
			if (params.containsKey("re_note")) {
				JSONArray jaReNote = params.getJSONArray("re_note");
				List<NoteInfo> reNote = getPOJO(jaReNote, NoteInfo.class, "re_note");
				noteService.deleteByStatus("re_note");
				noteService.saveAll(reNote);
				countJson.put("reNoteCount", reNote.size());
			}
			result.put("error", 0);
			result.put("msg", "okle");
			result.put("result", countJson);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("msg", "内部错误");
			return result;
		}
	}

	@RequestMapping("/back")
	@ResponseBody
	public JSONObject huifuData(@RequestBody JSONObject params) {
		System.out.println("beifen back");
		JSONObject json = new JSONObject();
		JSONObject countJson = new JSONObject();
		countJson.put("inCount", 0);
		countJson.put("outCount", 0);
		countJson.put("noteCount", 0);
		countJson.put("reInCount", 0);
		countJson.put("reOutCount", 0);
		countJson.put("reNoteCount", 0);
		try {
			if (params.getBooleanValue("in")) {
				List<AcountInfo> ais = acountService.findByStatus("in");
				json.put("in", ais);
				countJson.put("inCount", ais.size());
				System.out.println("in");
			}
			if (params.getBooleanValue("out")) {
				List<AcountInfo> ais = acountService.findByStatus("out");
				json.put("out", ais);
				countJson.put("outCount", ais.size());
				System.out.println("out");
			}
			if (params.getBooleanValue("flag")) {
				List<NoteInfo> ais = noteService.findByStatus("note");
				json.put("note", ais);
				countJson.put("noteCount", ais.size());
				System.out.println("flag");
			}
			if (params.getBooleanValue("re_in")) {
				List<AcountInfo> ais = acountService.findByStatus("re_in");
				json.put("re_in", ais);
				countJson.put("reInCount", ais.size());
				System.out.println("re_in");
			}
			if (params.getBooleanValue("re_out")) {
				List<AcountInfo> ais = acountService.findByStatus("re_out");
				json.put("re_out", ais);
				countJson.put("reOutCount", ais.size());
				System.out.println("re_out");
			}
			if (params.getBooleanValue("re_flag")) {
				List<NoteInfo> ais = noteService.findByStatus("re_note");
				json.put("re_note", ais);
				countJson.put("reNoteCount", ais.size());
				System.out.println("re_flag");
			}
			json.put("error", 0);
			json.put("msg", "okle");
			json.put("result", countJson);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", 1);
			json.put("msg", "内部错误");
			return json;
		}

	}

	private <T> List<T> getPOJO(JSONArray ja, Class<T> clz, String status) {
		List<T> ts = new ArrayList<>();
		if (ja == null || ja.size() == 0)
			return ts;
		try {
			for (int i = 0; i < ja.size(); i++) {
				T t = JsonUtils.jsonToPOJO(ja.getJSONObject(i), clz);
				Method m = clz.getMethod("setStatus", String.class);
				m.invoke(t, status);
				ts.add(t);
			}
			return ts;
		} catch (Exception e) {
			e.printStackTrace();
			return ts;
		}
	}

}
