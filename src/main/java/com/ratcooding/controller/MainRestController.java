package com.ratcooding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ratcooding.model.Expense;
import com.ratcooding.repository.ExpenseRepository;

/*
 * SQL 
 * INSERT INTO `Portfelik`.`expense` (`id_expense`, `description`, `price`) VALUES ('2', 'soda', '100');
 */

	@RestController
	@RequestMapping("/api")
	public class MainRestController {
    
		@Autowired	    
		private ExpenseRepository repository;

		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<Iterable<Expense>> getAllExpenses() {
			return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);   
		}
		@RequestMapping(method=RequestMethod.GET, value="/{id}")
		public Expense getExpenseById(@PathVariable("id") Long id) {
			return repository.findOne(id);
		}
		@RequestMapping(method=RequestMethod.GET, value="/{description}")
		public ResponseEntity<List<Expense>> getExpenseByDescription(@PathVariable("description") String description ) {
			return new ResponseEntity<>(repository.findByDescription(description), HttpStatus.OK);
		}
		@RequestMapping(method = RequestMethod.POST)
		public Expense addExpense(@RequestBody Expense expense) {
			return repository.save(expense);
		}
		@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
		public void deleteExpense(@PathVariable("id") Long id) {
			repository.delete(id);
		}
		@RequestMapping(method=RequestMethod.PUT, value="/{id}")
		public ResponseEntity<Expense> updateExpense(@PathVariable("id") Long id, @RequestBody Expense expense) {
			Expense update = repository.findOne(id);
			update.setDescription(expense.getDescription());
			update.setPrice(expense.getPrice());
			repository.save(update);
			return new ResponseEntity<Expense>(update, HttpStatus.OK);
		}
	}