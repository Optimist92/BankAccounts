package account.ui;

import java.util.List;

import account.domain.Account;


public class AccountListCommand extends AccountCommand{
	@Override
	public void exec(String[] args) {
		List<Account> accounts = getAccountService().findAll();
		if(accounts.size() > 0) {
			for(Account account: accounts) {
				System.out.println(account.toString());
			}
		} else {
			System.out.println("Список счетов пуст");
		}
	}
}
