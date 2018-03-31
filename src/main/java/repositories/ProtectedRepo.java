package repositories;

import javax.inject.Singleton;

/**
 * Created by douglasdrouillard on 3/25/18.
 */

import com.avaje.ebean.Ebean;
import models.User;

import java.util.List;


@Singleton
public class ProtectedRepo {



    public List<User> fetchUsers() {
        List<User> users = Ebean.find(User.class).findList();

        return users;
    }
}
