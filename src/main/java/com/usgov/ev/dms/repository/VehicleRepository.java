package com.usgov.ev.dms.repository;

import java.math.BigDecimal;
import com.usgov.ev.dms.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Vehicle v SET v.baseMsrp = :baseMsrp WHERE v.make = :make AND v.model = :model")
    int updateFieldsByCriteria(@Param("make") String make, @Param("model") String model, @Param("baseMsrp") BigDecimal baseMsrp);
}
