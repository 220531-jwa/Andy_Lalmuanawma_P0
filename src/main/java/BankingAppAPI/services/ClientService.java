package BankingAppAPI.services;
import java.util.List;
import BankingAppAPI.models.Client;
import BankingAppAPI.repositories.ClientDAO;

public class ClientService {
	private static ClientDAO clientDao;
	
	public ClientService(ClientDAO clientDao) {
		ClientService.clientDao = clientDao;
	}
	
	// This method creates a client

	public Client createClient(Client c) {
		return clientDao.createClient(c);
	}
	
	// This method gets all the clients

	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}
	
	// This method gets a client by their id. 
	// It throws a custom Exception which gives the HTTP status of 404 if the client id was found
	
	public Client getClientById(int id) throws Exception {
		return clientDao.getClientById(id);
	}
	
	// This method updates a client by their id
	
	public boolean updateClient(Client changedClient) {
		return clientDao.updateClientById(changedClient);
	}

	// This method deletes a client by their id
	
	public boolean deleteClientById(int id) {
		return clientDao.deleteClientById(id);
	}
}