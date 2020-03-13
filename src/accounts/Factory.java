package accounts;

import account.logic.AccountService;
import account.logic.AccountServiceImpl;
import account.storage.AccountStorage;
import account.storage.memory.AccountMemoryStorageImpl;
import account.ui.AccountBalanceCommand;
import account.ui.AccountDeleteCommand;
import account.ui.AccountListCommand;
import account.ui.AccountOpenCommand;
import account.ui.AccountRefillCommand;
import account.ui.AccountTransferCommand;
import account.ui.AccountWithdrawalCommand;
import account.ui.Command;
import account.ui.ExitCommand;

public class Factory {
	
	private AccountStorage accountStorage = null;
	
	public AccountStorage getAccountStorage () {
		if (accountStorage==null) {
			accountStorage = new AccountMemoryStorageImpl();
		}
		return accountStorage;
	}
	
	private AccountService accountService = null;
	public AccountService getAccountService () {
		if (accountService==null) {
			AccountServiceImpl service = new AccountServiceImpl();
			accountService = service;
			service.setAccountStorage(getAccountStorage());
		}
		return accountService;
	}
	
	private Command accountOpenCommand = null;
	public Command getAccountOpenCommand () {
		if (accountOpenCommand == null) {
			AccountOpenCommand command = new AccountOpenCommand();
			accountOpenCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountOpenCommand;
	}
	
	private Command accountDeleteCommand = null;
	public Command getAccountDeleteCommand () {
		if (accountDeleteCommand == null) {
			AccountDeleteCommand command = new AccountDeleteCommand();
			accountDeleteCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountDeleteCommand;
	}
	
	private Command accountBalanceCommand = null;
	public Command getAccountBalanceCommand () {
		if (accountBalanceCommand == null) {
			AccountBalanceCommand command = new AccountBalanceCommand();
			accountBalanceCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountBalanceCommand;
	}
	
	private Command accountTransferCommand = null;
	public Command getAccountTransferCommand () {
		if (accountTransferCommand == null) {
			AccountTransferCommand command = new AccountTransferCommand();
			accountTransferCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountTransferCommand;
	}
	
	private Command accountRefillCommand = null;
	public Command getAccountRefillCommand () {
		if (accountRefillCommand == null) {
			AccountRefillCommand command = new AccountRefillCommand();
			accountRefillCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountRefillCommand;
	}
	
	private Command accountWithdrawalCommand = null;
	public Command getAccountWithdrawalCommand () {
		if (accountWithdrawalCommand == null) {
			AccountWithdrawalCommand command = new AccountWithdrawalCommand();
			accountWithdrawalCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountWithdrawalCommand;
	}
	private Command accountListCommand = null;
	public Command getAccountListCommand () {
		if (accountListCommand == null) {
			AccountListCommand command = new AccountListCommand();
			accountListCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountListCommand;
	}
	private Command exitCommand = null;
	public Command getExitCommand () {
		if (exitCommand == null) {
			ExitCommand command = new ExitCommand();
			exitCommand=command;
			command.setAccountService(getAccountService());
		}
		return exitCommand;
	}
}
