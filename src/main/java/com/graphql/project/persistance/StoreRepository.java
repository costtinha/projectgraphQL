package com.graphql.project.persistance;

import com.graphql.project.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("StoreJpaRepository")
public interface StoreRepository extends JpaRepository<Store,Integer> {
    List<Store> findStoreByName(String storeName);
}
