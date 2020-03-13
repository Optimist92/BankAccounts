package homeworkAccounts;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import account.domain.Account;
import account.storage.AccountStorage;
import account.storage.memory.AccountMemoryStorageImpl;

public class CheckBalance {
	final private static String fileName = "operations.csv";
	final private static String encoding = "UTF-8";
	private static AccountStorage accountStorage = new AccountMemoryStorageImpl();;
	private static List<Account> accounts;
	
	public static void main(String[] args) {
		accounts = accountStorage.read();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		int count=0;
		for (Account account: accounts) {
			try {
				fis = new FileInputStream(fileName);
				isr = new InputStreamReader(fis, encoding);
				br = new BufferedReader(isr);
				String s;
				String numb;
				Long balance = 0L;
				while((s = br.readLine()) != null) {
					String a[] = s.split(";");
					numb=String.valueOf(account.getNumberAccount());
					if(numb.equals(a[0])) {
						balance-=Long.valueOf(a[3]);
					} else if (numb.equals(a[1])) {
						balance+=Long.valueOf(a[3]);
					}
				}
				br.close();
				if (!account.getBalanceAccount().equals(balance)) {
					System.out.println("Баланс счёта №" + account.getNumberAccount() + " равный " + account.getBalanceAccount() + " не равен актуальному " + balance );
					count++;
				}
			} catch(IOException e) {
				System.out.println("Ошибка чтения файла.");
			}
		}
		if (count==0) {
			System.out.println("Балансы проверены. Отличий не найдено.");
		}
		
		

	}

}
