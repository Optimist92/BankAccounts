package accounts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import account.logic.AccountService;
import account.logic.AccountServiceImpl;
import account.logic.LogicException;
import account.storage.AccountDao;
import account.storage.memory.AccountMemoryDaoImpl;
import account.storage.postgres.AccountDbDaoImpl;
import account.ui.AccountBalanceCommand;
import account.ui.AccountDeleteCommand;
import account.ui.AccountListCommand;
import account.ui.AccountOpenCommand;
import account.ui.AccountRefillCommand;
import account.ui.AccountTransferCommand;
import account.ui.AccountWithdrawalCommand;
import account.ui.Command;
import account.ui.ExitCommand;

public class Factory implements AutoCloseable{
	
	private AccountDao accountDao = null;
	
	public AccountDao getAccountDao () throws LogicException{
		if(accountDao == null) {
			AccountDbDaoImpl accountDaoImpl = new AccountDbDaoImpl();
			accountDao = accountDaoImpl;
			accountDaoImpl.setConnection(getConnection());
		}
		return accountDao;
	}
	
	private AccountService accountService = null;
	public AccountService getAccountService () throws LogicException {
		if (accountService==null) {
			AccountServiceImpl service = new AccountServiceImpl();
			accountService = service;
			service.setAccountDao(getAccountDao());
		}
		return accountService;
	}
	
	private Command accountOpenCommand = null;
	public Command getAccountOpenCommand () throws LogicException {
		if (accountOpenCommand == null) {
			AccountOpenCommand command = new AccountOpenCommand();
			accountOpenCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountOpenCommand;
	}
	
	private Command accountDeleteCommand = null;
	public Command getAccountDeleteCommand () throws LogicException {
		if (accountDeleteCommand == null) {
			AccountDeleteCommand command = new AccountDeleteCommand();
			accountDeleteCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountDeleteCommand;
	}
	
	private Command accountBalanceCommand = null;
	public Command getAccountBalanceCommand () throws LogicException {
		if (accountBalanceCommand == null) {
			AccountBalanceCommand command = new AccountBalanceCommand();
			accountBalanceCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountBalanceCommand;
	}
	
	private Command accountTransferCommand = null;
	public Command getAccountTransferCommand () throws LogicException {
		if (accountTransferCommand == null) {
			AccountTransferCommand command = new AccountTransferCommand();
			accountTransferCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountTransferCommand;
	}
	
	private Command accountRefillCommand = null;
	public Command getAccountRefillCommand () throws LogicException {
		if (accountRefillCommand == null) {
			AccountRefillCommand command = new AccountRefillCommand();
			accountRefillCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountRefillCommand;
	}
	
	private Command accountWithdrawalCommand = null;
	public Command getAccountWithdrawalCommand () throws LogicException {
		if (accountWithdrawalCommand == null) {
			AccountWithdrawalCommand command = new AccountWithdrawalCommand();
			accountWithdrawalCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountWithdrawalCommand;
	}
	private Command accountListCommand = null;
	public Command getAccountListCommand () throws LogicException {
		if (accountListCommand == null) {
			AccountListCommand command = new AccountListCommand();
			accountListCommand=command;
			command.setAccountService(getAccountService());
		}
		return accountListCommand;
	}
	
	private Connection connection = null;
	public Connection getConnection() throws LogicException {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost/store_db", "root", "root");
			} catch(SQLException e) {
				throw new LogicException(e);
			}
		}
		return connection;
	}
	
	@Override
	public void close() {
		try { connection.close(); } catch(Exception e) {}
	}
}
