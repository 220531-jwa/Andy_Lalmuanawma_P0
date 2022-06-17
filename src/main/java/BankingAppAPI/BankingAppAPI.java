package BankingAppAPI;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import BankingAppAPI.controllers.AccountController;
import BankingAppAPI.controllers.ClientController;
import io.javalin.Javalin;

public class BankingAppAPI {

	public static void main(String[] args) {
		Javalin app = Javalin.create();
		app.start();

		app.routes(() -> {
			path("/clients", () -> {
				get(ClientController::getAllClients);
				post(ClientController::createClient);
				path("/{id}", () -> {
					get(ClientController::getClientById);
					put(ClientController::updateClientById);
					delete(ClientController::deleteClientById);
					path("/accounts", () -> {
						post(AccountController::createAccount);
						get(AccountController::getAllAccountsByClientId);
						path("/{which_type}", () -> {
							get(AccountController::getAccountsInValueRange);

						});
						path("/{account_number}", () -> {
							get(AccountController::getSpecificAccountByClientId);
							put(AccountController::updateAccount);
							delete(AccountController::deleteAccount);
							path("/{which_type_dw}", () -> {
								path("/deposit/{amount_d}", () -> {
									patch(AccountController::deposit);
								});
								path("/withdraw/{amount_w}", () -> {
									patch(AccountController::withdraw);
								});
							});
							path("/{which_type_tf}/transfer/{other_account}/{which_type_tt}/{amount}", () -> {
								patch(AccountController::transfer);
							});
						});
					});
				});
			});
		});

		app.exception(Exception.class, (e, ctx) -> {
			ctx.status(404);
			ctx.result("Not found");
		});
	}

}