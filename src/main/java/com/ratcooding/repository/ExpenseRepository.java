package com.ratcooding.repository;

import org.springframework.data.repository.CrudRepository;

import com.ratcooding.model.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

}
