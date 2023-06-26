package com.fiap.techchallenge.fourlanches.adapter.driven.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {

}
