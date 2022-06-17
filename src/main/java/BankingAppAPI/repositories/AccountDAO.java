package BankingAppAPI.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import BankingAppAPI.models.Account;
import BankingAppAPI.models.ClientAccountAlternate;
import BankingAppAPI.models.ClientAccountBoth;
import BankingAppAPI.utils.ConnectionUtil;

public class AccountDAO {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	// This method creates an account associated with a client id
	
	public Account createAccount (Account a) {
		String sql = "insert into bankingapp.accounts values (default, ?, ?, ?) returning *;";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getClientId());
			ps.setInt(2, a.getCheckingAmount());
			ps.setInt(3, a.getSavingsAmount());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				a.setAccountNumber(rs.getInt("account_number"));
				a.setClientId(rs.getInt("client_id"));
				a.setCheckingAmount(rs.getInt("checking"));
				a.setSavingsAmount(rs.getInt("savings"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	// This method gets all the accounts associated with a client id
	
	public List<ClientAccountBoth> getAllAccountsByClientId(int clientId) {
		String sql = "select id, first_name, last_name, account_number, checking, savings"
				+ " from bankingapp.clients c"
				+ " left join bankingapp.accounts a"
				+ " on c.id = a.client_id"
				+ " where c.id = ?;";
		List<ClientAccountBoth> joined = new ArrayList<>();
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, clientId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				joined.add(new ClientAccountBoth(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getInt("account_number"),
						rs.getInt("checking"),
						rs.getInt("savings")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joined;
	}
	
	 // This method gets an account associated with a client id
	 
	public ClientAccountBoth getSpecificAccountByClientId(int clientId, int accountNumber) {
		String sql = "select id, first_name, last_name, account_number, checking, savings"
				+ " from bankingapp.clients c"
				+ " left join bankingapp.accounts a"
				+ " on c.id = a.client_id"
				+ " where c.id = ? and a.account_number = ?;";
		ClientAccountBoth joined = null;
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, clientId);
			ps.setInt(2, accountNumber);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				joined = new ClientAccountBoth(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getInt("account_number"),
						rs.getInt("checking"),
						rs.getInt("savings"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joined;
	}
	
	// This method gets all the accounts associated with a client id which follow a certain value range
	 
	public ClientAccountAlternate getAccountsInValueRange(int clientId, String whichType, int low, int high) {
		if (whichType.equals("checking")) {
			String sqlChecking = "select id, first_name, last_name, account_number, checking from bankingapp.clients c "
					+ "left join bankingapp.accounts a "
					+ "on c.id = a.client_id "
					+ "where " + whichType + " > ? and " + whichType + " < ?;";
			ClientAccountAlternate joinedChecking = null;
			try (Connection connChecking = cu.getConnection()) {
				PreparedStatement ps = connChecking.prepareStatement(sqlChecking);
				ps.setInt(1, low);
				ps.setInt(2, high);
				
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					joinedChecking = new ClientAccountAlternate(
							rs.getInt("id"),
							rs.getString("first_name"),
							rs.getString("last_name"),
							rs.getInt("account_number"),
							rs.getInt("checking"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return joinedChecking;
		} else if (whichType.equals("savings")) {
			String sqlSavings = "select id, first_name, last_name, account_number, savings from bankingapp.clients c "
					+ "left join bankingapp.accounts a "
					+ "on c.id = a.client_id "
					+ "where " + whichType + " > ? and " + whichType + " < ?;";
			ClientAccountAlternate joinedSavings = null;
			try (Connection connSavings = cu.getConnection()) {
				PreparedStatement ps = connSavings.prepareStatement(sqlSavings);
				ps.setInt(1, low);
				ps.setInt(2, high);
				
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					joinedSavings = new ClientAccountAlternate(
							rs.getInt("id"),
							rs.getString("first_name"),
							rs.getString("last_name"),
							rs.getInt("account_number"),
							rs.getInt("savings"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return joinedSavings;
		}
		return null;
	}
	
	// This method updates an account associated with a client id

	public boolean updateAccount(int clientId, int accountNumber, int checkingAmount, int savingsAmount) {
		String sql = "update bankingapp.accounts set checking = "
				+ checkingAmount + ", savings = " + savingsAmount + " where "
				+ "account_number = " + accountNumber + " and "
				+ "client_id = " + clientId + ";";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			int updatedAccount = ps.executeUpdate();
			if (updatedAccount != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// This method withdraws funds from either a checking or savings sub-account in an account associated with a client id
	
	public boolean withdraw(int clientId, int accountNumber, String whichType, int amountToWithdraw) {
		ClientAccountBoth a = getSpecificAccountByClientId(clientId, accountNumber);
		if (whichType.equals("checking")) {
			int newAmount = a.getCheckingAmount() - amountToWithdraw;
			if (newAmount >= 0) {
				String sql = "update bankingapp.accounts set "
						+ whichType + " = " + newAmount + "where "
						+ "account_number = " + accountNumber + " and "
						+ "client_id = " + clientId + ";";
				try (Connection conn = cu.getConnection()) {
					PreparedStatement ps = conn.prepareStatement(sql);
					int checkWithdrawal = ps.executeUpdate();
					if (checkWithdrawal != 0) {
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else if (whichType.equals("savings")) {
			int newAmount = a.getSavingsAmount() - amountToWithdraw;
			if (newAmount >= 0) {
				String sql = "update bankingapp.accounts set "
						+ whichType + " = " + newAmount + "where "
						+ "account_number = " + accountNumber + " and "
						+ "client_id = " + clientId + ";";
				try (Connection conn = cu.getConnection()) {
					PreparedStatement ps = conn.prepareStatement(sql);
					int checkWithdrawal = ps.executeUpdate();
					if (checkWithdrawal != 0) {
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	// This method deposits funds into either a checking or savings sub-account in an account associated with a client id
	 
	public boolean deposit(int clientId, int accountNumber, String whichType, int amountToDeposit) {
		ClientAccountBoth a = getSpecificAccountByClientId(clientId, accountNumber);
		if (whichType.equals("checking")) {
			int newAmount = a.getCheckingAmount() + amountToDeposit;
			if (newAmount >= 0) {
				String sql = "update bankingapp.accounts set "
						+ whichType + " = " + newAmount + "where "
						+ "account_number = " + accountNumber + " and "
						+ "client_id = " + clientId + ";";
				try (Connection conn = cu.getConnection()) {
					PreparedStatement ps = conn.prepareStatement(sql);
					int checkDeposit = ps.executeUpdate();
					if (checkDeposit != 0) {
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else if (whichType.equals("savings")) {
			int newAmount = a.getSavingsAmount() + amountToDeposit;
			if (newAmount >= 0) {
				String sql = "update bankingapp.accounts set "
						+ whichType + " = " + newAmount + "where "
						+ "account_number = " + accountNumber + " and "
						+ "client_id = " + clientId + ";";
				try (Connection conn = cu.getConnection()) {
					PreparedStatement ps = conn.prepareStatement(sql);
					int checkDeposit = ps.executeUpdate();
					if (checkDeposit != 0) {
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	// This method transfers funds from either a checking or savings account to another account, both of which are associated with a client id
	
	public boolean transfer(int clientId, int fromAccount, int toAccount, 
			String fromWhichType, String toWhichType, int amount) {
		AccountDAO ad = new AccountDAO();
		int amountOne = 0;
		int amountTwo = 0;
		if (fromWhichType.equals("checking")) {
			amountOne = ad.getSpecificAccountByClientId(clientId, fromAccount).getCheckingAmount() - amount;
		} else if (fromWhichType.equals("savings")) {
			amountOne = ad.getSpecificAccountByClientId(clientId, fromAccount).getSavingsAmount() - amount;
		}
		
		if (fromWhichType.equals("checking")) {
			amountTwo = ad.getSpecificAccountByClientId(clientId, toAccount).getCheckingAmount() + amount;
		} else if (fromWhichType.equals("savings")) {
			amountTwo = ad.getSpecificAccountByClientId(clientId, toAccount).getSavingsAmount() + amount;
		}
		String sql = "update bankingapp.accounts set " + fromWhichType + " = "
				+ amountOne + " where account_number = " + fromAccount + " and "
				+ "client_id = " + clientId + "; update bankingapp.accounts set "
				+ toWhichType + " = " + amountTwo + " where account_number = "
				+ toAccount + " and client_id = " + clientId + ";";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			int checkTransfer = ps.executeUpdate();
			if (checkTransfer != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// This methods deletes an account associated with a client id
	
	public boolean deleteAccount(int clientId, int accountNumber) {
		String sql = "delete from bankingapp.accounts where account_number = ? "
				+ "and client_id = ?;";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accountNumber);
			ps.setInt(2, clientId);
			int ifDeleted = ps.executeUpdate();
			if (ifDeleted != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}