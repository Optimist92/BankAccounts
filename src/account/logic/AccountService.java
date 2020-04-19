package account.logic;

import java.util.List;

import account.domain.Account;

public interface AccountService {
	List <Account> findAll() throws LogicException;
	
	Account read (Long numberAccount) throws LogicException;
	
	void create (Account account) throws LogicException;
	
	boolean delete (Long numberAccount) throws LogicException;
	
	boolean transfer (Long accountFromNumber, Long accountOnNumber, Long balance) throws LogicException;
}
