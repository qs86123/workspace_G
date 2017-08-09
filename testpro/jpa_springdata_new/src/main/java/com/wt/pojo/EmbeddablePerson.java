package com.wt.pojo;

import javax.persistence.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:21 2017/8/8
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "embeddable_person")
public class EmbeddablePerson extends AbstractMappedType {

    private String name;

    @Embedded
    //当某个实体同时引入了相同的可嵌入实体时，字段会重复，因此，可以使用@AttributeOverrides注解来解决
    @AttributeOverrides({
            @AttributeOverride(
                    name = "line1",
                    column = @Column(name = "home_address_line1")
            ),
            @AttributeOverride(
                    name = "line2",
                    column = @Column(name = "home_address_line2")
            ),
            @AttributeOverride(
                    name = "zipCode.postalCode",
                    column = @Column(name = "home_address_postal_cd")
            ),
            @AttributeOverride(
                    name = "zipCode.plus",
                    column = @Column(name = "home_address_postal_plus")
            )}
    )
    private EmbeddableAddress homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "line1",
                    column = @Column(name = "mailing_address_line1")
            ),
            @AttributeOverride(
                    name = "line2",
                    column = @Column(name = "mailing_address_line2")
            ),
            @AttributeOverride(
                    name = "zipCode.postalCode",
                    column = @Column(name = "mailing_address_postal_cd")
            ),
            @AttributeOverride(
                    name = "zipCode.plus",
                    column = @Column(name = "mailing_address_postal_plus")
            )}
    )
    private EmbeddableAddress mailingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "line1",
                    column = @Column(name = "work_address_line1")
            ),
            @AttributeOverride(
                    name = "line2",
                    column = @Column(name = "work_address_line2")
            ),
            @AttributeOverride(
                    name = "zipCode.postalCode",
                    column = @Column(name = "work_address_postal_cd")
            ),
            @AttributeOverride(
                    name = "zipCode.plus",
                    column = @Column(name = "work_address_postal_plus")
            )}
    )
    private EmbeddableAddress workAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmbeddableAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(EmbeddableAddress homeAddress) {
        this.homeAddress = homeAddress;
    }

    public EmbeddableAddress getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(EmbeddableAddress mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public EmbeddableAddress getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(EmbeddableAddress workAddress) {
        this.workAddress = workAddress;
    }
}
