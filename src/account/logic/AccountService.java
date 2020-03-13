package account.logic;

import java.util.List;

import account.domain.Account;

public interface AccountService {
	List <Account> findAll();
	
	Account read (Long numberAccount);
	
	void create (Account account);
	
	boolean delete (Long numberAccount);
	
	boolean transfer (Long accountFromNumber, Long accountOnNumber, Long balance);
}
