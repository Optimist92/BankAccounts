package account.storage;

import java.util.List;

import account.domain.Account;

public interface AccountStorage {
	Long create(Account account);

	Account read(Long numberAccount);

	List <Account> read();

	void update(Account account);

	boolean delete(Long numberAccount);
}
