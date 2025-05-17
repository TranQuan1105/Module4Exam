package com.example.module_4_exam.backend.service;

import com.example.module_4_exam.backend.model.PromotionDto;
import com.example.module_4_exam.backend.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository repository;

    public List<PromotionDto> getAll() {
        return repository.findAll();
    }

    public void save(PromotionDto promotion) {
        repository.save(promotion);
    }

    public PromotionDto get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<PromotionDto> search(Long discount, LocalDate start, LocalDate end) {
        return repository.search(discount, start, end);
    }
}