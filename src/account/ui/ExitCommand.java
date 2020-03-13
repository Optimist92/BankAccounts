package account.ui;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import account.domain.Account;

public class ExitCommand extends AccountCommand{
	final private static String fileName = "accounts.csv";
	final private static String encoding = "cp1251";
	
	@Override
	public void exec(String[] args){
		List<Account> accounts = getAccountService().findAll();
		String s;
		try {
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), encoding));
			for (Account account : accounts) {
				s = account.getNumberAccount().toString() + ";" + account.getBalanceAccount().toString();
				out.append(s).append("\r\n");
			}
			out.flush();
		    out.close();
			System.out.println("До свидания!");
			System.exit(0);
		}
		catch (Exception ex) {
			System.out.println("Ошибка записи в файл.");
		}
	}

}
