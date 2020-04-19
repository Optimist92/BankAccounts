package account.ui;

import java.util.List;

import account.domain.Account;
import account.logic.LogicException;


public class AccountListCommand extends AccountCommand{
	@Override
	public boolean exec(String[] args) throws LogicException{
		List<Account> accounts = getAccountService().findAll();
		if(accounts.size() > 0) {
			for(Account account: accounts) {
				System.out.println(account.toString());
			}
		} else {
			System.out.println("Список счетов пуст");
		}
		return true;
	}
}
