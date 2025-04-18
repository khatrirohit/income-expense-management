package com.shivbaba.incomeexpensemanagement.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shivbaba.incomeexpensemanagement.dto.Category;
import com.shivbaba.incomeexpensemanagement.dto.IncomeExpenseMgmtDto;
import com.shivbaba.incomeexpensemanagement.dto.NetAmountDto;
import com.shivbaba.incomeexpensemanagement.entity.IncomeExpenseMgmt;
import com.shivbaba.incomeexpensemanagement.persistence.IncExpMgmtRepository;

@Service
public class IncExpMgmtService {
	
	private final IncExpMgmtRepository incExpMgmtRepository;
	
	public IncExpMgmtService(IncExpMgmtRepository incExpMgmtRepository) {
		this.incExpMgmtRepository = incExpMgmtRepository;
	}
	
	public NetAmountDto getNetAmount() {
		List<IncomeExpenseMgmt> records = incExpMgmtRepository.findAll();
		List<IncomeExpenseMgmtDto> incomeExpenseMgmtDtos = new ArrayList<>();
		
		//mapping IncomeExpenseMgmt into IncomeExpenseMgmtDto
		records.forEach(incomeExpenseMgmtRecord -> {
			IncomeExpenseMgmtDto incomeExpenseMgmtDto = new IncomeExpenseMgmtDto();
			incomeExpenseMgmtDto.setAmount(incomeExpenseMgmtRecord.getAmount());
			incomeExpenseMgmtDto.setStartDate(incomeExpenseMgmtRecord.getIncExpCmpKey().getStartDate());
			incomeExpenseMgmtDto.setEndDate(incomeExpenseMgmtRecord.getIncExpCmpKey().getEndDate());
			incomeExpenseMgmtDto.setCategory(incomeExpenseMgmtRecord.getIncExpCmpKey().getCategory());
			incomeExpenseMgmtDtos.add(incomeExpenseMgmtDto);
		});
		
		NetAmountDto netAmountDto= new NetAmountDto();
		//finding minimum start date
		LocalDate minStartDate=null;
		LocalDate maxEndDate=null;
		Optional<IncomeExpenseMgmtDto> recordWithMinStartDt = incomeExpenseMgmtDtos
				.stream()
				.min(
						(o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()));
		if(recordWithMinStartDt.isPresent()) {
			minStartDate = recordWithMinStartDt.get().getStartDate();
		}
		netAmountDto.setStartDate(minStartDate);
		
		//finding maximum end date
		Optional<IncomeExpenseMgmtDto> recordWithMaxEndDt = incomeExpenseMgmtDtos
				.stream()
				.max(
						(o1, o2) -> o1.getEndDate().compareTo(o2.getEndDate()));
		if(recordWithMaxEndDt.isPresent()) {
			maxEndDate = recordWithMaxEndDt.get().getEndDate();
		}
		netAmountDto.setEndDate(maxEndDate);
		
		//finding net in-amount
		BigDecimal netInAmt = incomeExpenseMgmtDtos
				.stream()
				.filter(
							o -> o.getCategory().equals(Category.INCOME))
				.reduce(BigDecimal.ZERO, 
						(partialSum, o) -> partialSum.add(o.getAmount()), 
						(x, y) -> x.add(y));
		netAmountDto.setNetInAmount(netInAmt.plus());
		
		//finding net out-amount
		BigDecimal netOutAmt = incomeExpenseMgmtDtos
				.stream()
				.filter(
							o -> !o.getCategory().equals(Category.INCOME))
				.reduce(BigDecimal.ZERO, 
						(partialSum, o) -> partialSum.add(o.getAmount()), 
						(x, y) -> x.add(y));
		netAmountDto.setNetOutAmount(netOutAmt.negate());
		
		netAmountDto.setNetAmount(netInAmt.subtract(netOutAmt));
		return netAmountDto;
	}
}
