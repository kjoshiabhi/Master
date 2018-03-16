package com.cashman.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cashman.model.CashMan;
import com.cashman.model.CashMan.Note;

public interface CashManRepo extends JpaRepository<CashMan, Long> {
	
	
	CashMan findByNote(Note note);

}
