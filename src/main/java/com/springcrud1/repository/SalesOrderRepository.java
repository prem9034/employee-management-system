package com.springcrud1.repository;

import com.springcrud1.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
    @Query("SELECT COUNT(s) FROM SalesOrder s WHERE FUNCTION('YEAR', s.orderDate) = :year")
    long countByYear(@Param("year") int year);
}