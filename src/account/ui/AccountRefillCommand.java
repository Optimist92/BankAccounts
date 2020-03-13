package account.ui;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import account.domain.Account;
public class AccountRefillCommand extends AccountCommand{
	final private static String fileName = "operations.csv";
	final private static String encoding = "cp1251";
	
	@Override
	public void exec(String[] args) {
		List<Account> accounts = getAccountService().findAll();
		String s;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
		Date currentDate = new Date();
		if(args.length == 2) {
			int count=0;
			for (Account account: accounts) {
				if ((account.getNumberAccount().equals(Long.valueOf(args[0])))) {
					if (getAccountService().transfer(null, Long.valueOf(args[0]), Long.valueOf(args[1]))) {
						try {
							Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), encoding));
							s = ";"+ args[0] + ";" + dateFormat.format(currentDate).toString() + ";"+ args[1];
							out.append(s).append("\r\n");
							out.flush();
						    out.close();
						}
						catch (Exception ex) {
							System.out.println("Ошибка записи в файл.");
						}
						count++;
					}
					break;
				}
			}
			if (count==0) {
				System.out.println("Извините, счёта не найдено.");
			}
		} else {
			System.out.println("Неверное количество аргументов");
		}
	}

}
