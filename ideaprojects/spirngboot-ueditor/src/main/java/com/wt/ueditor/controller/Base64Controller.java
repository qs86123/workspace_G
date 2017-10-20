package com.wt.ueditor.controller;

import com.wt.ueditor.utils.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:22 2017/10/20
 * @Email:tao8.wang@changhong.com
 */
@RestController
@RequestMapping("/base64")
public class Base64Controller {

    @RequestMapping("/str")
    public String getBase64Str(HttpServletResponse response) throws IOException {
        InputStream is = Base64Controller.class.getClassLoader().getResourceAsStream("pic.png");
        String str = Base64Utils.encode(is);
//        Base64Utils.toOutputStream(str,response.getOutputStream());
        return str;
    }

}
