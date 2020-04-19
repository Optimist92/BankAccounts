package account.storage.memory;

import java.util.ArrayList;
import java.util.List;

import account.domain.Account;
import account.storage.AccountDao;

public class AccountMemoryDaoImpl implements AccountDao{
	private List <Account> accounts = new ArrayList<Account>();
	private Long lastCreatedNumberAccount=0L;

	@Override
	public Long create(Account account) {
		lastCreatedNumberAccount++;
		account.setNumberAccount(lastCreatedNumberAccount);
		accounts.add(account);
		return lastCreatedNumberAccount;
	}

	@Override
	public Account read(Long numberAccount) {
		for (Account account : accounts) {
			if (account.getNumberAccount().equals(numberAccount)) {
				return account;
			}
		}
		return null;
	}

	@Override
	public List<Account> read() {
		return accounts;
	}

	@Override
	public void update(Account account) {
		for (Account account1 : accounts) {
			if (account1.equals(account)) {
				accounts.set(accounts.indexOf(account1), account);
				break;
			}
		}
	}

	@Override
	public boolean delete(Long numberAccount) {
		for (Account account : accounts) {
			if (account.getNumberAccount().equals(numberAccount)) {
				accounts.remove(account);
				return true;
			}
		}
		return false;
	}
	

}