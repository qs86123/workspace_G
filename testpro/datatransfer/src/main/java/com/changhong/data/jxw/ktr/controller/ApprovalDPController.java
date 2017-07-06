package com.changhong.data.jxw.ktr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.changhong.data.jxw.ktr.common.Constant;
import com.changhong.data.jxw.ktr.entity.DataPackage;
import com.changhong.data.jxw.ktr.service.ApprovalDP.Impl.ApprovalDPServiceImpl;

@RequestMapping("/rest/ktr/approval")
@Controller
public class ApprovalDPController
{

    @Autowired
    private ApprovalDPServiceImpl approvalDPService;

    @RequestMapping(value = "/approval", method = RequestMethod.PUT)
    public String restPut(@RequestParam(value = "id") Integer id)
    {
        DataPackage dataPackage = new DataPackage();
        dataPackage.setId(id.toString());
        dataPackage.setDescription("pass");
        approvalDPService.updataDP(dataPackage);
        return Constant.SUCCESS;
    }

}
