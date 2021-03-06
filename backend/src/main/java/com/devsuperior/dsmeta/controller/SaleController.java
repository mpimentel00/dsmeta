package com.devsuperior.dsmeta.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.models.Sale;
import com.devsuperior.dsmeta.repository.SaleRepository;
import com.devsuperior.dsmeta.service.SmsService;

@RestController
@RequestMapping("/sales")
public class SaleController {
	
	@Autowired
	SaleRepository saleRepository;
	@Autowired
	SmsService smsService;
	
	@GetMapping
	public Page<Sale> list(@RequestParam(required = false) LocalDate minDate, @RequestParam(required = false) LocalDate maxDate, Pageable page) {
		if(minDate == null) minDate = LocalDate.now().minusDays(365);
		if(maxDate == null) maxDate = LocalDate.now();
		
		return saleRepository.findSales(minDate, maxDate, page);
	}
	
	
	@GetMapping("{id}/notification")
	public void notifySms(@PathVariable Long id) {
		smsService.sendSms(id);
	}
}
