package account.storage;

import java.util.List;

import account.domain.Account;

public interface AccountDao extends Dao<Account>{
	List <Account> read() throws DaoException;
}
