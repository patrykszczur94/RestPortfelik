package com.ratcooding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ratcooding.model.Expense;
import com.ratcooding.repository.ExpenseRepository;

@Controller
@RequestMapping(path="/demo")
public class MainController {

	@Autowired
	ExpenseRepository expenseRepository;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Expense> getAllExpenses() {
		return expenseRepository.findAll();
	}
	@GetMapping(path="/add")
	public @ResponseBody String addExpense(@RequestParam String price, @RequestParam String description) {
		Expense expense = new Expense();
		expense.setDescription(description);
		expense.setPrice(price);
		expenseRepository.save(expense);
		return "OK";
	}
	
}
