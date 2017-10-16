package com.wt.ueditor.controller;

import com.wt.ueditor.service.MyConfigManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description 这个类的路径不定，ueditor是什么路径，这里访问路径就写什么最方便，不用改太多。最简单的方式
 *              就是配置好ueditor，然后直接运行，页面上刷新的时候会后端配置文件获取错误，上传插件不能用，
 *              这个路径就是访问配置文件的路径，这个controller就配这个路径，完美。
 * @Author: wangtao
 * @Date:10:53 2017/6/8
 * @Email:tao8.wang@changhong.com
 */
@RestController
@RequestMapping("/ueditor")
public class UeditorController {

    @RequestMapping("/servlet")
    public void servlet(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");

            String action = request.getParameter("action");
            String result = "";
            if (action.equals("config")) {
                MyConfigManager configManager= MyConfigManager.getInstance();
                result=configManager.getAllConfig().toString();
            }
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
