package com.ljl.tlgp.order.repository;

import com.ljl.tlgp.order.entity.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// src/main/java/com/example/demo/repository/FirmRepository.java
public interface FirmRepository extends JpaRepository<Firm, Long> {
    @Query(value="SELECT a.firmid, a.firmname, " +
            "COALESCE(b.marketvalue, a.marketvalue) as marketvalue, " +
            "COALESCE(b.markettime, a.markettime) as markettime, " +
            "COALESCE(b.firmintro, a.firmintro) as firmintro " +
            "FROM firm_table a " +
            "LEFT JOIN firm_info_table b ON a.firmid = b.firmid", 
            nativeQuery = true)
    List<Firm> findFirmWithInfo();
}
