package com.example.module_4_exam.backend.repository;

import com.example.module_4_exam.backend.model.PromotionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<PromotionDto, Long> {

    @Query("SELECT p FROM PromotionDto p WHERE " +
            "(:discount IS NULL OR p.discountAmount = :discount) AND " +
            "(:start IS NULL OR p.startDate >= :start) AND " +
            "(:end IS NULL OR p.endDate <= :end)")
    List<PromotionDto> search(@Param("discount") Long discount,
                              @Param("start") LocalDate start,
                              @Param("end") LocalDate end);
}
