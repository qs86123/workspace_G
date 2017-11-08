package com.wt.imgcreate;

import clive.hua.app.simpleImageTool.Base64Utils;
import com.wt.imgcreate.util.QRCodes;
import org.apache.commons.codec.binary.Base64;
import com.wt.imgcreate.util.ValidateCode;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:50 2017/11/8
 * @Email:tao8.wang@changhong.com
 */
public class CreateImageTest {

    @Test
    public void validateCreateToBase64() throws FileNotFoundException {
        ValidateCode vc = new ValidateCode();
        String vCode64 = "data:image/png;base64,";
        // 创建字符流
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        // 写入字符流
        try {
            vc.write(bs);
        } catch (IOException e) {
            System.out.printf("将图片希尔字符流失败，原因是：{}", e.getMessage());
        }
        // 转码成字符串
        String imgsrc = Base64.encodeBase64String(bs.toByteArray());
        Base64Utils.toOutputStream(imgsrc, new FileOutputStream("D:vc.png"));
    }

    @Test
    public void validateCreateTofile() throws FileNotFoundException {
        ValidateCode vc = new ValidateCode();
        String vCode64 = "data:image/png;base64,";
        // 创建字符流
        FileOutputStream bs = new FileOutputStream("D:vc.png");
        // 写入字符流
        try {
            vc.write(bs);
        } catch (IOException e) {
            System.out.printf("将图片希尔字符流失败，原因是：{}", e.getMessage());
        }
    }

    //失败
    @Test
    public void qrCodeCreate() throws FileNotFoundException {
        String str = new QRCodes().createQRCode("hahaha", 12, 12, "");
        Base64Utils.toOutputStream(str, new FileOutputStream("D:qr111.png"));
    }

}
