package account.ui;

import account.logic.AccountService;

abstract public class AccountCommand implements Command {
	private AccountService accountService;

	protected AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
}
