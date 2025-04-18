package com.shivbaba.incomeexpensemanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeExpenseMgmtDto {
	private LocalDate startDate;
	private LocalDate endDate;
	private Category category;
	private BigDecimal amount;
}
