package com.devsuperior.dsmeta.service;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.models.Sale;
import com.devsuperior.dsmeta.repository.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	@Autowired
	SaleRepository saleSepository;

	public void sendSms(Long id) {
		Sale sale = saleSepository.findById(id).get();

		String pattern="00";
	    DecimalFormat myFormatter = new DecimalFormat(pattern);
		
		String datePeriod =  myFormatter.format(sale.getDate().getMonth().getValue()) + "/" + sale.getDate().getYear();
		
		String messageContent = "Seller Name: " + sale.getSellerName()
					+ "\nAmout: R$" + String.format("%.2f", sale.getAmount())
					+ "\nMouth period: " + datePeriod;
		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, messageContent).create();

		System.out.println(message.getSid());
	}
}
