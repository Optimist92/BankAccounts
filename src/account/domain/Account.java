package account.domain;

import java.io.Serializable;

public class Account implements Serializable{
	private Long numberAccount;
	private Long balanceAccount;
	
	public Long getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(Long numberAccount) {
		this.numberAccount = numberAccount;
	}

	public Long getBalanceAccount() {
		return balanceAccount;
	}

	public void setBalanceAccount(Long balanceAccount) {
		this.balanceAccount = balanceAccount;
	}

	@Override
	public String toString() {
		return "[" + numberAccount + "]"
				+ "; $"
				+ (balanceAccount/100.0);
	}

}
