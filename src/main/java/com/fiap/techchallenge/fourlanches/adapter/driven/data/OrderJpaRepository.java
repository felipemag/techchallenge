package com.fiap.techchallenge.fourlanches.adapter.driven.data;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.entities.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {

    @Query("SELECT o FROM OrderJpaEntity o WHERE o.status = :status")
    List<OrderJpaEntity> findByStatus(@Param("status") String status);

    @Query("SELECT o FROM OrderJpaEntity o \n" +
            "WHERE o.status <> 'FINISHED' \n" +
            "ORDER BY case \n" +
            "WHEN o.status = 'READY' THEN 1\n" +
            "WHEN o.status = 'RECEIVED' THEN 2\n" +
            "WHEN o.status = 'IN_PREPARATION' THEN 3\n" +
            "WHEN o.status = 'CREATED' THEN 4 END, o.createdAt ASC ")
    List<OrderJpaEntity> getAllPendingOrdersOrderedByStatusAndCreatedAt();
}
