package com.changhong.data.meeting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.entity.MeetingPlanInfo;
import com.changhong.data.meeting.entity.MeetingRoomInfo;
import com.changhong.data.meeting.entity.MeetingSeatInfo;
import com.changhong.data.meeting.service.MeetingPlanInfoService;
import com.changhong.data.meeting.service.MeetingRoomInfoService;
import com.changhong.data.meeting.service.MeetingSeatInfoService;
import com.changhong.data.meeting.service.QRMeetingService;
import com.changhong.data.meeting.service.UserQRSignService;

/**
 * 会议室接口
 * @author wangtao
 *
 */
@Controller
@RequestMapping("/meetingRoom")
public class MeetingRoomInfoController {

	@Autowired
	private MeetingSeatInfoService msiService;

	@Autowired
	private MeetingRoomInfoService mrService;

	@Autowired
	private MeetingPlanInfoService mpiService;

	@Autowired
	private QRMeetingService qrService;

	@Autowired
	private UserQRSignService uqService;

	/**
	 * 批量录入位置坐标，添加测试数据的时候用
	 * 
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Object save() {
		double x = 0;
		double y = 0;
		double degree = 0;
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			x += 10;
			y += 5;
			degree += 36;
			MeetingSeatInfo entity = new MeetingSeatInfo();
			entity.setX(x);
			entity.setY(y);
			entity.setDegree(degree);
			entity.setMeetingId("8a87c9d8585159480158515b66160002");
			String id = msiService.save(entity);
			list.add(id);
		}
		return list;
	}

	/**
	 * 保存会议室
	 * 
	 * @param entity
	 * @return
	 */

	@RequestMapping("/saveRoom")
	@ResponseBody
	public Object saveRoom(@RequestBody MeetingRoomInfo entity) {
		return saveOrUpdateRoom(entity);
	}

	/**
	 * 更新会议室
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping("/updateRoom")
	@ResponseBody
	public Object updateRoom(@RequestBody MeetingRoomInfo entity) {
		JSONObject result = new JSONObject();
		String roomId = entity.getId();
		List<MeetingPlanInfo> mpis = mpiService.findByRoomId(roomId);
		if (mpis != null && mpis.size() != 0) {
			// 如果存在会议计划，则不能修改会议室图片
			if (entity.getImage() == null || entity.getImage().trim().equals("")) {
				MeetingRoomInfo mr = mrService.getById(entity.getId());
				entity.setImage(mr.getImage());
				return result;
			} else {
				result.put("error", 1);
				result.put("msg", "该会议室存在会议计划，不允许修改会议室背景图");
				return result;
			}
		}
		// 如果不存在会议计划，可以修改会议室图片，这里跟保存会议室一样，只是多个会议室id，会直接更新
		return saveOrUpdateRoom(entity);
	}

	/**
	 * 根据会议室id删除会议室
	 * 
	 * @param roomId
	 */
	@RequestMapping("/deleteRoom/{roomId}")
	@ResponseBody
	public void deleteRoom(@PathVariable("roomId") String roomId) {
		try {
			List<MeetingPlanInfo> mpis = mpiService.findByRoomId(roomId);
			for (MeetingPlanInfo mpi : mpis) {
				// 先删除签到信息
				uqService.deleteByMeetingId(mpi.getId());
				// 再删除会议计划二维码信息
				qrService.deleteByMeetingId(mpi.getId());
				// 接着删会议计划座位信息
				msiService.deleteByMeetingId(mpi.getId());
			}
			// 接着删会议计划信息
			mpiService.deleteByRoomId(roomId);
			// 最后删除room信息，如果删除中出现差错导致一些信息没删除，下次再删除的时候有roomId就可以继续删除
			mrService.deleteById(roomId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 返回所有会议室
	 * 
	 * @return
	 */
	@RequestMapping("/getRoom")
	@ResponseBody
	public Object getRoom() {
		List<MeetingRoomInfo> mrs = mrService.get();
		return mrs;
	}

	/**
	 * 根据id返回某个会议
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getRoomById/{id}")
	@ResponseBody
	public MeetingRoomInfo getRoomById(@PathVariable("id") String id) {
		MeetingRoomInfo mr = mrService.getById(id);
		return mr;
	}

	/**
	 * 根据会议id返回会议室信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getRoomByMeetingId/{meetingId}")
	@ResponseBody
	public Object getRoomByMeetingId(@PathVariable("meetingId") String meetingId) {
		MeetingPlanInfo mpi = mpiService.findById(meetingId);
		if (mpi == null) {
			return "没有找到该会议信息";
		}
		MeetingRoomInfo mr = mrService.getById(mpi.getRoomId());
		return mr;
	}

	/**
	 * 更新会议计划也是这个借口， 如果是更新计划的话传回来的实体有id，直接更新
	 * 
	 * @param entity
	 * @return
	 */
	private Object saveOrUpdateRoom(MeetingRoomInfo entity) {
		JSONObject result = new JSONObject();
		result.put("error", 0);
		if (entity.getAddress() == null || entity.getAddress().trim().equals("")) {
			result.put("error", 1);
			result.put("msg", "会议室地点不能为空");
			return result;
		}
		if (entity.getName() == null || entity.getName().trim().equals("")) {
			result.put("error", 1);
			result.put("msg", "会议室名字不能为空");
			return result;
		}
		if (entity.getImage() == null || entity.getImage().trim().equals("")) {
			result.put("error", 1);
			result.put("msg", "会议室背景图不能为空");
			return result;
		}
		try {
			mrService.save(entity);
		} catch (Exception e) {
			result.put("error", 1);
			result.put("msg", "内部错误，操作失败");
			return result;
		}
		return result;
	}

}
