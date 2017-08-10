package com.wt.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:38 2017/8/9
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "patch")
public class Patch extends AbstractMappedType {

    private String name;

    @ElementCollection
    //指定从表的表名patch_change,外键列名patch_id
    @CollectionTable(
            name = "patch_change",
            joinColumns = @JoinColumn(name = "patch_id")
    )
    //指定从表的主键名index_id，可以不指定
    @OrderColumn(name = "index_id")
    private List<Change> changes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "patch_phone",
            joinColumns = @JoinColumn(name = "phone_id")
    )
    //指定关联表的列名
    @Column(name = "column_phone_ids")
    private List<String> phoneIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Change> getChanges() {
        return changes;
    }

    public void setChanges(List<Change> changes) {
        this.changes = changes;
    }

    public List<String> getPhoneIds() {
        return phoneIds;
    }

    public void setPhoneIds(List<String> phoneIds) {
        this.phoneIds = phoneIds;
    }
}
