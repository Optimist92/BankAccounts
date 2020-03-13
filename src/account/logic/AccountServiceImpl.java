package account.logic;

import java.util.List;

import account.domain.Account;
import account.storage.AccountStorage;

public class AccountServiceImpl implements AccountService {
	private AccountStorage accountStorage;

	public void setAccountStorage(AccountStorage accountStorage) {
		this.accountStorage = accountStorage;
	}
	
	@Override
	public Account read (Long numberAccount) {
		return accountStorage.read(numberAccount);
		
	}

	@Override
	public List <Account> findAll() {
		return accountStorage.read();
	}

	@Override
	public void create(Account account) {
		accountStorage.create(account);
		
	}

	@Override
	public boolean delete(Long numberAccount) {
		return accountStorage.delete(numberAccount);
		
	}

	@Override
	public boolean transfer(Long accountFromNumber, Long accountOnNumber, Long balance) {
		if (balance<0) {
			System.out.println("Не верный баланс.");
			return false;
		} else {
			if (accountStorage.read(accountFromNumber)==null && accountStorage.read(accountOnNumber)==null) {
				System.out.println("Счетов не найдено. Перевод средств невозможен.");
				return false;
			} else if (accountStorage.read(accountFromNumber)==null) {
				accountStorage.read(accountOnNumber).setBalanceAccount(balance + accountStorage.read(accountOnNumber).getBalanceAccount());
				System.out.println("На счёт № " + accountStorage.read(accountOnNumber).getNumberAccount() + " наличными зачислено $" + balance/100.0);
				return true;
			} else if (accountStorage.read(accountOnNumber)==null && accountStorage.read(accountFromNumber).getBalanceAccount()>= balance) {
				accountStorage.read(accountFromNumber).setBalanceAccount(accountStorage.read(accountFromNumber).getBalanceAccount() - balance);
				System.out.println("Со счёта № " + accountStorage.read(accountFromNumber).getNumberAccount() + " снято наличными $" + balance/100.0);
				return true;
			} else if (accountStorage.read(accountFromNumber).getBalanceAccount()>= balance){
				accountStorage.read(accountFromNumber).setBalanceAccount(accountStorage.read(accountFromNumber).getBalanceAccount() - balance);
				accountStorage.read(accountOnNumber).setBalanceAccount(accountStorage.read(accountOnNumber).getBalanceAccount() + balance);
				System.out.println("Осуществлён перевод со счёта № " + accountStorage.read(accountFromNumber).getNumberAccount() + " на счёт № " + accountStorage.read(accountOnNumber).getNumberAccount() + " в размере $" + balance/100.0);
				return true;
			} else {
				System.out.println("На счёте № " + accountStorage.read(accountFromNumber).getNumberAccount() + " недостаточно средств для перевода(снятия).");
				return false;
			}
		}
	}

}
