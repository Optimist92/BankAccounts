package account.ui;

import account.logic.LogicException;

public interface Command {
	boolean exec(String[] args) throws LogicException;
}
