package com.shivbaba.incomeexpensemanagement.dto;

public enum Category {
	BODY("B"), CLEANING("C"), FOOD("F"), INCOME("I"), SERVICE("S"), TRAVEL("T");
	private String code;
	Category(String code) {
		this.code=code;
	}
	public String getCode() {
		return code;
	}
	public static Category fromString(String code) {
		if(code == null) return null;
		for(Category c: values()) {
			if(c.code.equals(code.trim())) {
				return c;
			}
		}
		return null;
	}
}
