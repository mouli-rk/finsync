package in.syncaccount.service.impl;

import org.springframework.stereotype.Service;

import in.syncaccount.entity.Account;
import in.syncaccount.model.CommonModel;
import in.syncaccount.model.EmailDetails;
import in.syncaccount.repository.AccountRepository;
import in.syncaccount.service.AccountService;
import in.syncaccount.service.EmailSenderService;
import in.syncaccount.service.SyncUserClient;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
	
	private final SyncUserClient syncUserClient;
	private final AccountRepository accountRepository;
	private final EmailSenderService emailSenderService;
		
	@Override
	@Transactional
	public CommonModel createAccount(CommonModel accountModel) {
		String accountNumber = generateAccountNumber(accountModel.getPrefix());
		Account account = new Account();
		account.setCode(accountNumber);
		account.setAccountType(accountModel.getAccountType());
		account.setAccountNumber(accountNumber);
		account.setAccountBalance(accountModel.getAccountBalance());
		account.setStatus(accountModel.getStatus());
		account.setLastModifiedBy(1L);

		accountModel = syncUserClient.createUser(accountModel);
		account.setUserId(accountModel.getUserId());
		account = accountRepository.save(account);
		
		accountModel.setId(account.getId());
		accountModel.setAccountType(account.getAccountType());
		accountModel.setAccountNumber(account.getAccountNumber());
		accountModel.setAccountBalance(account.getAccountBalance());
		accountModel.setStatus(account.getStatus());
		
		if (account.getId() != null) {
			EmailDetails emailParams = emailSenderService.configureEmailParams(accountModel);
			emailSenderService.sendEmailWithAttachment(emailParams);
		}
		
		return accountModel;
	}
	
	public String generateAccountNumber(String prefix) {
		Account lastAccount = accountRepository.findTopByOrderByIdDesc();
		Long suffix = 0L;
		
		prefix = prefix!=null?prefix:"FIN";
		
		if (lastAccount != null) {
			String lastAccountNo = lastAccount.getAccountNumber();
			suffix = Long.parseLong(lastAccountNo.substring(prefix.length())) + 1L;
		}
		else {	
			suffix = 1000234100L;
		}
		return prefix + suffix;

	}

}
