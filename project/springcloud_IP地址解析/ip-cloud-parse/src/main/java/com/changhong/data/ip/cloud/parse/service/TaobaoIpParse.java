package com.changhong.data.ip.cloud.parse.service;


import com.changhong.data.ip.cloud.parse.entity.AddressEntity;
import com.changhong.data.ip.cloud.parse.util.HttpUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;



/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月25日
 */
@Service
public class TaobaoIpParse{
    private final String REQ_URL ="http://ip.taobao.com/service/getIpInfo.php";

    @Autowired
    private HttpUtils httpUtils;

    @Cacheable(value = "ipToAddress",key = "#root.args",unless = "#result.get_country()==null")
    public AddressEntity parse(String ip) {

        AddressEntity addressEntity=null;

        addressEntity=new AddressEntity();
        try {
            String req=httpUtils.doGet(REQ_URL +"?ip="+ip);
            //System.out.println(req);
            JSONObject jsonObject=JSONObject.fromObject(req);
            if(jsonObject.getInt("code")==0)
            {
                addressEntity.set_country(jsonObject.getJSONObject("data").getString("country"));
                System.out.println("country:"+jsonObject.getJSONObject("data").getString("country"));
                System.out.println("area:"+jsonObject.getJSONObject("data").getString("area"));
                addressEntity.set_pro(jsonObject.getJSONObject("data").getString("region"));
                System.out.println("region:"+jsonObject.getJSONObject("data").getString("region"));
                addressEntity.set_city(jsonObject.getJSONObject("data").getString("city"));
                System.out.println("city:"+jsonObject.getJSONObject("data").getString("city"));
                System.out.println("county:"+jsonObject.getJSONObject("data").getString("county"));
                System.out.println("isp:"+jsonObject.getJSONObject("data").getString("isp"));
            }
        }catch (Exception e)
        {
            System.out.println(ip);
        }
        return addressEntity;
    }



}
