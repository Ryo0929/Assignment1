package com.example.client;

import com.example.model.CreditCard;
import com.example.model.Response;
import com.example.mysql_api.buyer.Buyers;
import com.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Brayden
 * @create 2/20/22 10:23 PM
 * @Description
 */

@RestController
@RequestMapping(path="/transaction")
public class TransactionController
{
	@Autowired
	TransactionService transactionService;

	@PostMapping("/credit")
	public Response creditTransaction(@RequestBody CreditCard card){
		return transactionService.credit();
	}
}
