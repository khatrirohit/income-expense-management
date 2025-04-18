package com.shivbaba.incomeexpensemanagement.converter;

import com.shivbaba.incomeexpensemanagement.dto.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CategoryConverter implements AttributeConverter<Category, String> {
	@Override
	public String convertToDatabaseColumn(Category attribute) {
		return attribute.getCode();
	}
	
	@Override
	public Category convertToEntityAttribute(String dbData) {
		return Category.fromString(dbData);
	}
}
