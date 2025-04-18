package com.shivbaba.incomeexpensemanagement.persistence;

import org.springframework.data.repository.ListCrudRepository;

import com.shivbaba.incomeexpensemanagement.entity.IncExpCompositeKey;
import com.shivbaba.incomeexpensemanagement.entity.IncomeExpenseMgmt;

public interface IncExpMgmtRepository extends ListCrudRepository<IncomeExpenseMgmt, IncExpCompositeKey> {
	
}
