package account.ui;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import account.domain.Account;


public class AccountTransferCommand extends AccountCommand{
	final private static String fileName = "operations.csv";
	final private static String encoding = "cp1251";
	
	
	@Override
	public void exec(String[] args) {
		List<Account> accounts = getAccountService().findAll();
		String s;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
		Date currentDate = new Date();
		
		if(args.length == 3) {
			int count=0;
			int count1=0;
			for (Account account: accounts) {
				if (account.getNumberAccount().equals(Long.valueOf(args[0]))) {
					count++;
				} else if (account.getNumberAccount().equals(Long.valueOf(args[1]))) {
					count1++;
				}
			}
			if(count==0 && count1==0) {
				System.out.println("Извините, счётов не найдено.");
			} else if (count==0 && count1==1) {
				System.out.printf("Извините, счёта №%d не найдено.\n", Long.valueOf(args[0]));
			} else if (count==1 && count1==0) {
				System.out.printf("Извините, счёта №%d не найдено.\n", Long.valueOf(args[1]));
			} else {
				if (getAccountService().transfer(Long.valueOf(args[0]), Long.valueOf(args[1]), Long.valueOf(args[2]))) {
					try {
						Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), encoding));
						s =  args[0] + ";" + args[1] + ";" + dateFormat.format(currentDate).toString() + ";"+ args[2];
						out.append(s).append("\r\n");
						out.flush();
						   out.close();
					}
					catch (Exception ex) {
						System.out.println("Ошибка записи в файл.");
					}
				}
			}
		} else {
			System.out.println("Неверное количество аргументов");
		}
	}

}