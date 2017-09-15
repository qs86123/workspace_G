package com.wt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseJpaDao<T> extends JpaRepository<T, Serializable>, JpaSpecificationExecutor<T>, PagingAndSortingRepository<T, Serializable> {

}
