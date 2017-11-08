package clive.hua.app.simpleImageTool;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Description
 * @Author: wangtao
 * @Date:12:09 2017/10/20
 * @Email:tao8.wang@changhong.com
 */
public class Base64Utils {

    public static String encode(InputStream is) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] data = new byte[is.available()];
        is.read(data);
        is.close();
        String str = encoder.encode(data);
        return str;
    }

    public static InputStream decode(String file) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(file);
        //测试时发现不调整异常数据也正确，为了避免以后出错，加上这一段
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        InputStream in = new ByteArrayInputStream(bytes);
        return in;
    }

    public static void toOutputStream(String file, OutputStream os) {
        try {
            InputStream in = decode(file);
            inputStreamToOutputStream(in, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void inputStreamToOutputStream(InputStream in, OutputStream os) throws IOException {
        byte[] b = new byte[4096];
        Integer len = -1;
        while ((len = in.read(b)) != -1) {
            os.write(b, 0, len);
        }
        os.flush();
        in.close();
        os.close();
    }
}
