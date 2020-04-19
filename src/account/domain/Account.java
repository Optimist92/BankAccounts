package account.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account extends Entity{
	public static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	private Long numberAccount;
	private Long balanceAccount;
	private Date date;
	
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

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "[" + numberAccount + "]"
				+ "; $"
				+ (balanceAccount/100.0);
	}

}
