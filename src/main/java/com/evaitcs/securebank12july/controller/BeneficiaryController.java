package com.evaitcs.securebank12july.controller;

import com.evaitcs.securebank12july.model.Beneficiary;
import com.evaitcs.securebank12july.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaries")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    // إضافة مستفيد لعميل
    // Add a beneficiary for a customer
    @PostMapping("/{customerId}")
    public Beneficiary addBeneficiary(
            @PathVariable Long customerId,
            @RequestBody Beneficiary beneficiary) {

        return beneficiaryService.addBeneficiary(
                customerId,
                beneficiary
        );
    }

    // جلب جميع المستفيدين
    // Get all beneficiaries
    @GetMapping
    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryService.getAllBeneficiaries();
    }

    // حذف مستفيد
    // Delete a beneficiary
    @DeleteMapping("/{id}")
    public void deleteBeneficiary(@PathVariable Long id) {
        beneficiaryService.deleteBeneficiary(id);
    }
}