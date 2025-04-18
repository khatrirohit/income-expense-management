package com.shivbaba.incomeexpensemanagement.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="income_expense_management", schema="rohit_khatri_schema")
@Getter
@Setter
public class IncomeExpenseMgmt {
	@EmbeddedId
	private IncExpCompositeKey incExpCmpKey;
	@Column(name="amount", nullable=false)
	private BigDecimal amount;
}
