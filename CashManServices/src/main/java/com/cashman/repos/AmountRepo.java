package com.cashman.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cashman.model.Amount;

public interface AmountRepo extends JpaRepository<Amount, Long> {

}
