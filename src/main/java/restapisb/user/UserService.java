/*

 */
package restapisb.user;

import java.util.List;
import java.util.Optional;

import restapisb.user.domain.User;

public interface UserService {

  User createUser(User user) throws Exception;

  User updateUser(Long id, User user) throws Exception;

  void deleteById(Long id) throws Exception;

  void deleteAll() throws Exception;

  Optional<User> getUserById(Long id) throws Exception;

  List<User> getUserAll() throws Exception;

  List<User> getUserByEmail(String email) throws Exception;
}
