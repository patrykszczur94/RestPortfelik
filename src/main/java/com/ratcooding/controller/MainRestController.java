package com.ratcooding.controller;

import java.util.Collection;

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
		public ResponseEntity<Collection<Expense>> getAllExpenses() {
			return new ResponseEntity<>((Collection<Expense>) repository.findAll(), HttpStatus.OK);   
		}
		@RequestMapping(method = RequestMethod.POST)
		public Expense addExpense(@RequestBody Expense expense) {
			return repository.save(expense);
		}
		@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
		public void deleteExpense(@PathVariable("id") Long id) {
			repository.delete(id);
		}
		@RequestMapping(method=RequestMethod.PUT, value="/{idExpense}")
		public Expense updateExpense(@PathVariable("idExpense") Long idExpense, @RequestBody Expense expense) {
			Expense update = repository.findOne(idExpense);
			update.setDescription(expense.getDescription());
			update.setPrice(expense.getPrice());
			return repository.save(update);
		}
	}