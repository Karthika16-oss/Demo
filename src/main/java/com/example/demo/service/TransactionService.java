package com.example.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public String transfer(TransactionDto transactionDto) {
		Transaction transaction= new Transaction();
		BeanUtils.copyProperties(transactionDto, transaction);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transactionRepository.save(transaction);
		Account fromAcc=accountRepository.findByAccountName(transactionDto.getFromAccount());
		fromAcc.setAmount(fromAcc.getAmount()-transactionDto.getTransferAmount());
		accountRepository.save(fromAcc);
		return "trnsfered successfull";
		
	}

}
