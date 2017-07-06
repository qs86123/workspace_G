package com.wt.acountweb.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseJpaDao<T> extends JpaRepository<T,Serializable>, JpaSpecificationExecutor<T>, PagingAndSortingRepository<T, Serializable>{

}
