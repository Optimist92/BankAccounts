package account.ui;

import accounts.Factory;

import java.util.HashMap;
import java.util.Map;


public class CommandManager implements Command {
	private Factory factory = new Factory();
	private Map <String, Command> commands = new HashMap<String, Command>();

	public CommandManager() {
		commands.put("help", this);
		commands.put("list", factory.getAccountListCommand());
		commands.put("open", factory.getAccountOpenCommand());
		commands.put("delete", factory.getAccountDeleteCommand());
		commands.put("balance", factory.getAccountBalanceCommand());
		commands.put("transfer", factory.getAccountTransferCommand());
		commands.put("refill", factory.getAccountRefillCommand());
		commands.put("withdrawal", factory.getAccountWithdrawalCommand());
		commands.put("exit", factory.getExitCommand());
		
	}

	public void exec(String commandLine) {
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
				command.exec(args);
			} else {
				System.out.println("Неизвестная команда");
			}
		}
	}

	@Override
	public void exec(String[] args) {
		System.out.println("Доступны следующие команды:");
		for(Map.Entry<String, Command> item : commands.entrySet()) {
		System.out.println("\t" + item.getKey());
		}
	}
}
