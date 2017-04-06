package com.ratcooding.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ratcooding.model.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
	List<Expense> findByDescription(String description);
}
