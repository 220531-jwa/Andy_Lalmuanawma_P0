package BankingAppAPI.services;
import java.util.List;
import BankingAppAPI.models.Account;
import BankingAppAPI.models.ClientAccountAlternate;
import BankingAppAPI.models.ClientAccountBoth;
import BankingAppAPI.repositories.AccountDAO;

public class AccountService {
	private static AccountDAO accountDao;
	
	public AccountService(AccountDAO accountDao) {
		AccountService.accountDao = accountDao;
	}
	
	// This method creates an account associated with a client id
	 
	public Account createAccount(Account a) {
		Account newAccount = accountDao.createAccount(a);
		return newAccount;
	}
	
	// This method gets all the accounts associated with a client id
	 
	public List<ClientAccountBoth> getAllAccountsByClientId(int clientId) {
		return accountDao.getAllAccountsByClientId(clientId);
	}
	
	// This method gets an account associated with a client id 
	 
	public ClientAccountBoth getSpecificAccountByClientId(int clientId, int accountNumber) throws Exception {
		return accountDao.getSpecificAccountByClientId(clientId, accountNumber);
	}
	
	// This method gets all the accounts associated with a client id which follow a certain value range
	
	public ClientAccountAlternate getAccountsInValueRange(int clientId, String whichType, int low, int high) {
		return accountDao.getAccountsInValueRange(clientId, whichType, low, high);
	}
	
	// This method updates an account associated with a client id
	
	public boolean updateAccount(int clientId, int accountNumber, int checkingAmount, int savingsAmount) {
		return accountDao.updateAccount(clientId, accountNumber, checkingAmount, savingsAmount);
	}
	
	// This method withdraws funds from either a checking or savings sub-account in a account associated with a client id
	
	public boolean withdraw(int clientId, int accountNumber, String whichType, int amountToWithdraw) {
		return accountDao.withdraw(clientId, accountNumber, whichType, amountToWithdraw);
	}
	
	// This method deposits funds in either a checking or savings sub-account in an account associated with a client id
	
	public boolean deposit(int clientId, int accountNumber, String whichType, int amountToDeposit) {
		return accountDao.deposit(clientId, accountNumber, whichType, amountToDeposit);
	}
	
	// This method transfers funds from one account either checking or savings to another account, both of which are associated with a client id
	
	public boolean transfer(int clientId, int fromAccount, int toAccount, 
			String fromWhichType, String toWhichType, int amount) {
		return accountDao.transfer(clientId, fromAccount, toAccount, 
			fromWhichType, toWhichType, amount);
	}
	
	// This methods deletes an account associated with a client id
	
	public boolean deleteAccount(int clientId, int accountNumber) {
		return accountDao.deleteAccount(clientId, accountNumber);
	}
}