package com.wt.apptest;

import com.wt.pojo.MapPerson;
import com.wt.repositories.MapPersonRepository;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:11 2017/8/11
 * @Email:tao8.wang@changhong.com
 */
public class MapPersonTest extends SupperTest{

    private MapPersonRepository mpr=context.getBean(MapPersonRepository.class);

    @Test
    public void save(){
        MapPerson mp=new MapPerson();
        mp.setName("wt");
        mp.getPhoneRegister().put(new MapPerson.MapPhone(MapPerson.MapPhone.PhoneType.LAND_LINE,"12345"),new Date());
        mp.getPhoneRegister().put(new MapPerson.MapPhone(MapPerson.MapPhone.PhoneType.MOBILE,"54321"),new Date());
        mpr.save(mp);
    }

    @Test
    public void find(){
        MapPerson mp=mpr.findOne("21f6991c-4fe5-4abe-a122-5f9160b9a666");
        Map<MapPerson.MapPhone, Date> phoneRegister = mp.getPhoneRegister();
        for(MapPerson.MapPhone phone:phoneRegister.keySet()){
            System.out.println(phone);
            System.out.println(phoneRegister.get(phone));
            System.out.println("-----------------");
        }

    }

}
