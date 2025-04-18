package com.shivbaba.incomeexpensemanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NetAmountDto {
	private LocalDate startDate;
	private LocalDate endDate;
	private BigDecimal netInAmount;
	private BigDecimal netOutAmount;
	private BigDecimal netAmount;
}
