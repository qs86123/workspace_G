package com.wt.pojo;

import javax.persistence.*;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:41 2017/8/9
 * @Email:tao8.wang@changhong.com
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class Change {

    private String path;

    private String diff;

    //注意：@Embeddable可嵌入类型不允许定义集合
//    private List<String> details;

    public Change(String path, String diff) {
        this.path = path;
        this.diff = diff;
    }

    public Change() {
    }

    @Column(name = "path", nullable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "diff", nullable = false)
    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

}
