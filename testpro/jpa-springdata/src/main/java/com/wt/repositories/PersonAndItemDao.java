package com.wt.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wt.pojo.PersonAndItem;

public interface PersonAndItemDao  extends JpaRepository<PersonAndItem, Serializable> {

}
