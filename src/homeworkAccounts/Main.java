package homeworkAccounts;

import java.util.Scanner;

import account.logic.LogicException;
import account.ui.CommandManager;


public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
		try(Scanner scanner = new Scanner(System.in)) {
			Class.forName("org.postgresql.Driver");
			try(CommandManager manager = new CommandManager()) {
				boolean goOn = true;
				while(goOn) {
					System.out.print("> ");
					String command = scanner.nextLine();
					goOn = manager.exec(command);
				}
			} catch(LogicException e) {
				System.out.println("Ошибка доступа к базе данных");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch(ClassNotFoundException e) {
			System.out.println("Ошибка запуска приложения");
		}
	}
	/*public static AccountServiceImpl getService() {
		AccountServiceImpl service = new AccountServiceImpl();
		AccountStorage storage = new AccountMemoryStorageImpl();
		service.setAccountStorage(storage);
		return service;
	}
	public static void output (List<Account> accounts) {
		System.out.println("*********************");
		for(int i = 0; i < accounts.size(); i++) {
			System.out.println(accounts.get(i));
		}
	}
	public static void main(String[] args) {
		AccountServiceImpl service = getService();
		Account account;
		account = new Account();
		account.setBalanceAccount(12983400L);
		service.create(account);
		account = new Account();
		account.setBalanceAccount(35000L);
		service.create(account);
		account = new Account();
		account.setBalanceAccount(125450L);
		service.create(account);
		account = new Account();
		account.setBalanceAccount(2200L);
		service.create(account);
		account = new Account();
		account.setBalanceAccount(983400L);
		service.create(account);
		account = new Account();
		account.setBalanceAccount(1500L);
		service.create(account);
		account = new Account();
		account.setBalanceAccount(100L);
		service.create(account);
		output(service.findAll());
		service.transfer(1L, 4L, 125000L);
		output(service.findAll());
		service.transfer(2L, 3L, 35100L);
		output(service.findAll());
		service.transfer(null, 7L, 128950L);
		output(service.findAll());
		service.transfer(6L, null, 98501L);
		output(service.findAll());
		service.transfer(1L, null, 9501L);
		output(service.findAll());
		service.transfer(null, null, 1230495L);
		output(service.findAll());
	}
	public static Random random = new Random();
	public static Account generate () {
		switch(random.nextInt(3)) {
			case 0:
				return new Account(random.nextInt(101), (random.nextInt(10000)));
			case 1:
				return new LegalEntityAccount((random.nextInt(101) + 100), random.nextInt(10000), random.nextInt(901));
			case 2:
				return new VipAccount((random.nextInt(101) + 200), (random.nextInt(10000)));
			default: return null;
		}
	}
	public static void transfer (Account account1, Account account2, int transfer) {
		System.out.println("Со счёта № " + account1.getNumberAccount() + " на счёт № " + account2.getNumberAccount() + " необходимо перевести: " + transfer + " руб. ");
		if (account1.writeOff(transfer)) {
			account2.refill(transfer);
		}
	}

	}*/

}
