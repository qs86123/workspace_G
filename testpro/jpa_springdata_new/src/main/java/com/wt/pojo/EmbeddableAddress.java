package com.wt.pojo;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:18 2017/8/8
 * @Email:tao8.wang@changhong.com
 */
@Embeddable
public class EmbeddableAddress {

    private String line1;

    private String line2;

    //@Embeddable可用于描述映射类型本身(例如名称)。@Embedded用于引用一个给定的可嵌入类型(通常情况下该注解可以不需要)。
    @Embedded
    private ZipCode zipCode;

    public EmbeddableAddress() {
    }

    public EmbeddableAddress(String line1, String line2, ZipCode zipCode) {
        this.line1 = line1;
        this.line2 = line2;
        this.zipCode = zipCode;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }

    @Embeddable
    public static class ZipCode {
        private String postalCode;
        private String plus;

        public ZipCode() {
        }

        public ZipCode(String postalCode, String plus) {
            this.postalCode = postalCode;
            this.plus = plus;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getPlus() {
            return plus;
        }

        public void setPlus(String plus) {
            this.plus = plus;
        }
    }
}
