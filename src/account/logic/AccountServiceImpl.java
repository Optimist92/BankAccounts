package account.logic;

import java.util.List;


import account.domain.Account;
import account.storage.AccountDao;
import account.storage.DaoException;

public class AccountServiceImpl implements AccountService {
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	@Override
	public Account read (Long numberAccount) throws LogicException{
		try {
			return accountDao.read(numberAccount);
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public List <Account> findAll() throws LogicException {
		try {
			return accountDao.read();
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void create(Account account) throws LogicException {
		try {
			accountDao.create(account);
		} catch(DaoException e) {
			throw new LogicException(e);
		}
		
	}

	@Override
	public boolean delete(Long numberAccount) throws LogicException {
		try {
			return accountDao.delete(numberAccount);
		} catch(DaoException e) {
			throw new LogicException(e);
		}
		
		
	}

	@Override
	public boolean transfer(Long accountFromNumber, Long accountOnNumber, Long balance) throws LogicException {
		try {
			if (balance<0) {
				System.out.println("Не верный баланс.");
				return false;
			} else {
				if (accountDao.read(accountFromNumber)==null && accountDao.read(accountOnNumber)==null) {
					System.out.println("Счетов не найдено. Перевод средств невозможен.");
					return false;
				} else if (accountDao.read(accountFromNumber)==null) {
					accountDao.read(accountOnNumber).setBalanceAccount(balance + accountDao.read(accountOnNumber).getBalanceAccount());
					System.out.println("На счёт № " + accountDao.read(accountOnNumber).getNumberAccount() + " наличными зачислено $" + balance/100.0);
					return true;
				} else if (accountDao.read(accountOnNumber)==null && accountDao.read(accountFromNumber).getBalanceAccount()>= balance) {
					accountDao.read(accountFromNumber).setBalanceAccount(accountDao.read(accountFromNumber).getBalanceAccount() - balance);
					System.out.println("Со счёта № " + accountDao.read(accountFromNumber).getNumberAccount() + " снято наличными $" + balance/100.0);
					return true;
				} else if (accountDao.read(accountFromNumber).getBalanceAccount()>= balance){
					accountDao.read(accountFromNumber).setBalanceAccount(accountDao.read(accountFromNumber).getBalanceAccount() - balance);
					accountDao.read(accountOnNumber).setBalanceAccount(accountDao.read(accountOnNumber).getBalanceAccount() + balance);
					System.out.println("Осуществлён перевод со счёта № " + accountDao.read(accountFromNumber).getNumberAccount() + " на счёт № " + accountDao.read(accountOnNumber).getNumberAccount() + " в размере $" + balance/100.0);
					return true;
				} else {
					System.out.println("На счёте № " + accountDao.read(accountFromNumber).getNumberAccount() + " недостаточно средств для перевода(снятия).");
					return false;
				}
			}
		} catch(DaoException e) {
			throw new LogicException(e);
		}
	}

}
