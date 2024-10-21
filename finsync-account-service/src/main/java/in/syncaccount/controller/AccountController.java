package in.syncaccount.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.syncaccount.model.CommonModel;
import in.syncaccount.service.AccountService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/syncaccount/account")
public class AccountController {
	
	private final AccountService accountService;
	
	@PostMapping("/createAccount")
	public CommonModel createAccount(@RequestBody CommonModel accountModel) {
		return accountService.createAccount(accountModel);
	}

}
