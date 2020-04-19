package account.ui;

import accounts.Factory;

import java.util.LinkedHashMap;
import java.util.Map;

import account.ui.Command;

import account.logic.LogicException;




public class CommandManager implements Command, AutoCloseable {
	private Factory factory = new Factory();
	private Map <String, Command> commands = new LinkedHashMap<String, Command>();

	public CommandManager() throws LogicException{
		commands.put("help", this);
		commands.put("list", factory.getAccountListCommand());
		commands.put("open", factory.getAccountOpenCommand());
		commands.put("delete", factory.getAccountDeleteCommand());
		commands.put("balance", factory.getAccountBalanceCommand());
		commands.put("transfer", factory.getAccountTransferCommand());
		commands.put("refill", factory.getAccountRefillCommand());
		commands.put("withdrawal", factory.getAccountWithdrawalCommand());
		commands.put("exit", new ExitCommand());
		
	}

	public boolean exec(String commandLine) {
		String pair[] = commandLine.split(" ");
		if(pair.length > 0) {
			Command command = commands.get(pair[0]);
			if(command != null) {
				String args[];
				if(pair.length > 1) {
					args = pair[1].split(";");
				} else {
					args = new String[] {};
				}
				try {
					return command.exec(args);
				} catch(LogicException e) {
					System.out.println("Команда не может быть выполнена");
				}
			} else {
				System.out.println("Неизвестная команда");
			}
		}
		return true;
	}

	@Override
	public boolean exec(String[] args) {
		System.out.println("Доступны следующие команды:");
		for(String command : commands.keySet()) {
			System.out.println("\t" + command);
		}
		return true;
	}

	@Override
	public void close() {
		factory.close();
	}
}
