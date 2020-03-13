package account.storage.memory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import account.domain.Account;
import account.storage.AccountStorage;

public class AccountMemoryStorageImpl implements AccountStorage{
	final private static String fileName = "accounts.csv";
	final private static String encoding = "UTF-8";
	private List <Account> accounts = new ArrayList<Account>();
	private Long lastCreatedNumberAccount=0L;


	public AccountMemoryStorageImpl() {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis, encoding);
			br = new BufferedReader(isr);
			String s;
			while((s = br.readLine()) != null) {
				String a[] = s.split(";");
				Account account = new Account();
				account.setNumberAccount(Long.valueOf(a[0]));
				account.setBalanceAccount(Long.valueOf(a[1]));
				accounts.add(account);
				lastCreatedNumberAccount=Long.valueOf(a[0]);
			}
			br.close();
		} catch(IOException | IllegalArgumentException e) {
			System.out.println("Ошибка чтения файла.");
		}
	}
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