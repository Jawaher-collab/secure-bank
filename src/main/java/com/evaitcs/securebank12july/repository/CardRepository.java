package com.evaitcs.securebank12july.repository;

import com.evaitcs.securebank12july.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}