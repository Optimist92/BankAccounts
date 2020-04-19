package account.ui;

import account.domain.Account;
import account.logic.LogicException;

public class AccountOpenCommand extends AccountCommand {

	@Override
	public boolean exec(String[] args) throws LogicException{
		if(args.length == 1) {
			Account account = new Account();
			account.setBalanceAccount(Long.valueOf(args[0]));
			getAccountService().create(account);
			System.out.println("Данные успешно сохранены");
		} else {
			System.out.println("Неверное количество аргументов");
		}
		return true;
		
	}

}
