package com.evaitcs.securebank12july.repository;

import com.evaitcs.securebank12july.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository
        extends JpaRepository<Beneficiary, Long> {
}