package com.evaitcs.securebank12july.service;

import com.evaitcs.securebank12july.model.Beneficiary;
import com.evaitcs.securebank12july.model.Customer;
import com.evaitcs.securebank12july.repository.BeneficiaryRepository;
import com.evaitcs.securebank12july.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final CustomerRepository customerRepository;

    // إضافة مستفيد لعميل
    // Add a beneficiary for a customer
    public Beneficiary addBeneficiary(
            Long customerId,
            Beneficiary beneficiary) {

        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow();

        beneficiary.setCustomer(customer);

        return beneficiaryRepository.save(beneficiary);
    }

    // جلب جميع المستفيدين
    // Get all beneficiaries
    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryRepository.findAll();
    }

    // حذف مستفيد
    // Delete a beneficiary
    public void deleteBeneficiary(Long id) {
        beneficiaryRepository.deleteById(id);
    }
}