package com.wt.ueditor.controller;

import com.wt.ueditor.response.UeditorResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:22 2017/10/16
 * @Email:tao8.wang@changhong.com
 */
@RestController
public class UploadController {

    /**
     * ueditor的图片上传，返回ueditor能识别的参数类型，这个表单名字files是在config.json里面配置过的，默认为upfile
     * 这里演示的仅仅是是否上传成功，所以上传的图片全部默认保存到了指定的路径，ueditor上是不能显示的
     *
     * @param files
     * @param req
     * @return
     */
    @RequestMapping("/upload")
    public Object upload(@RequestParam(value = "files") MultipartFile files, HttpServletRequest req) {
        String rootPath = req.getServletContext().getRealPath("/");
        System.out.println(rootPath);
        UeditorResponse ur = new UeditorResponse();
        ur.setType(files.getOriginalFilename());
        ur.setOriginal(files.getOriginalFilename());
        try {
            System.out.println(files.getName());
            System.out.println(files.getOriginalFilename());
            String path = "D:/" + files.getOriginalFilename();
            files.transferTo(new File(path));
            ur.setUrl(path);
            ur.setState("SUCCESS");
        } catch (IOException e) {
            e.printStackTrace();
            ur.setState("图片上传失败");
            return ur;
        }
        return ur;
    }

    @RequestMapping("/uploadbase64")
    public Object uploadbase64(String files) {
        System.out.println(files);
        UeditorResponse ur = new UeditorResponse();
        savebase64(files, "D:/xxx.png");
        ur.setState("SUCCESS");
        ur.setUrl("url");
        ur.setOriginal("xxx.png");
        return ur;
    }

    //保存base64为图片
    public Object savebase64(String file, String path) {
        file = file.substring(file.indexOf(",") + 1);
        System.out.println(file);
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(file);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            InputStream in = new ByteArrayInputStream(bytes);
            FileOutputStream fos = new FileOutputStream(new File(path));
            byte[] b = new byte[4096];
            Integer len = -1;
            while ((len = in.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.flush();
            in.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
