package account.ui;

import java.util.List;

import account.domain.Account;
import account.logic.LogicException;


public class AccountBalanceCommand extends AccountCommand{

	@Override
	public boolean exec(String[] args) throws LogicException{
		if(args.length == 1) {
			List<Account> accounts = getAccountService().findAll();
			int count = 0;
			for (Account account: accounts) {
				if (account.getNumberAccount().equals(Long.valueOf(args[0]))) {
					System.out.println("Балланс вашего счёта равен:  " + "$"
						+ (account.getBalanceAccount()/100.0));
					count++;
					break;
				}
			}
			if (count==0) {
				System.out.println("Извините. Счёта не существует.");
			}
		} else {
			System.out.println("Неверное количество аргументов");
		}
		return true;
	}

}
