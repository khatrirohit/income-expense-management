package com.shivbaba.incomeexpensemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivbaba.incomeexpensemanagement.dto.NetAmountDto;
import com.shivbaba.incomeexpensemanagement.service.IncExpMgmtService;

@RestController
@RequestMapping(value="/", produces={"application/json"})
public class IncExpMgmtController {
	
	private final IncExpMgmtService incExpMgmtService;
	
	public IncExpMgmtController(IncExpMgmtService incExpMgmtService) {
		this.incExpMgmtService = incExpMgmtService;
	}
	
	@GetMapping(value="netAmount")
	public NetAmountDto getNetAmount() {
		return incExpMgmtService.getNetAmount();
	}
}
