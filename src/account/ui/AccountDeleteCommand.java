package account.ui;

import account.logic.LogicException;

public class AccountDeleteCommand extends AccountCommand{

	@Override
	public boolean exec(String[] args) throws LogicException{
		if(args.length == 1) {
			if (getAccountService().read(Long.valueOf(args[0])).getBalanceAccount()==0) {
				if (getAccountService().delete(Long.valueOf(args[0]))) {
					System.out.println("Счёт №" + Long.valueOf(args[0]) + " удалён.");
				} else {
					System.out.println("Счёт №" + Long.valueOf(args[0]) + " не найдён.");
				}
			} else {
				System.out.println("Вы можете удалить счёт только после снятия всех средств с него.");
			}
		} else {
			System.out.println("Неверное количество аргументов");
		}
		return true;
	}

}
