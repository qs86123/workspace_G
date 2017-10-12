package com.wt.apptest;

import com.wt.pojo.Change;
import com.wt.pojo.Patch;
import com.wt.repositories.PatchRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:13:46 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
public class EmbeddableElementCollectionTest extends SupperTest {
    private PatchRepository pr = context.getBean(PatchRepository.class);

    @Test
    public void save() {
        Patch p = new Patch();
        p.setName("name");
        List<Change> changes = new ArrayList<>();
        changes.add(new Change("c1", "c2"));
        changes.add(new Change("c11", "c22"));
        p.setChanges(changes);
        p.setPhoneIds(Arrays.asList("phone_id_1", "phone_id_2"));
        pr.save(p);
    }

    @Test
    public void find() {
        List<Patch> all = pr.findAll();
        show(all);
    }


}
