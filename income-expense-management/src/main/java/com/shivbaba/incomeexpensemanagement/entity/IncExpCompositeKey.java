package com.shivbaba.incomeexpensemanagement.entity;

import java.time.LocalDate;

import com.shivbaba.incomeexpensemanagement.converter.CategoryConverter;
import com.shivbaba.incomeexpensemanagement.dto.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class IncExpCompositeKey {
	@Column(name="start_date")
	private LocalDate startDate;
	@Column(name="end_date")
	private LocalDate endDate;
	@Convert(converter=CategoryConverter.class)
	private Category category;
}
