package controllers;

import models.User;
import ninja.Result;
import ninja.Results;
import repositories.ProtectedRepo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by douglasdrouillard on 3/25/18.
 */

@Singleton
public class ApiController {

    private final ProtectedRepo protectedRepo;

    @Inject
    public ApiController(ProtectedRepo protectedRepo) {
        this.protectedRepo = protectedRepo;
    }

    public Result users_index() {
        List<User> users = this.protectedRepo.fetchUsers();
        return Results.json().render(users);
    }
}
