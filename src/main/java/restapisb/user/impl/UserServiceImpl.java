/*

 */
package restapisb.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapisb.user.UserRepository;
import restapisb.user.UserService;
import restapisb.user.domain.User;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository ur;

  @Override
  public User createUser(User user) throws Exception {
    User u;
    try {
      u = ur.save(user);
    } catch (Exception e) {
      throw e;
    }
    return u;
  }

  @Override
  public User updateUser(Long id, User user) throws Exception {
    User updated = null;
    try {

      Optional<User> old = ur.findById(id);

      if (old.isPresent()) {
        updated = old.get().updateWith(user);
        return ur.save(updated);
      }


    } catch (Exception e) {
      throw e;

    }
    return updated;
  }


  @Override
  public Optional<User> getUserById(Long id) throws Exception {
    Optional<User> u;

    try {
      u = ur.findById(id);


    } catch (Exception e) {
      throw e;
    }

    return u;
  }

  @Override
  public List<User> getUserAll() throws Exception {
    List<User> u;

    try {
      u = ur.findAll();


    } catch (Exception e) {
      throw e;
    }

    return u;
  }

  @Override
  public List<User> getUserByEmail(String email) throws Exception {
    List<User> u;

    try {
      u = ur.findByEmail(email);


    } catch (Exception e) {
      throw e;
    }

    return u;
  }


  @Override
  public void deleteById(Long id) throws Exception {

    try {

      Optional<User> u = ur.findById(id);
      ur.delete(u.get());

    } catch (Exception e) {
      throw e;
    }

  }

  @Override
  public void deleteAll() throws Exception {

    try {

      ur.deleteAll();

    } catch (Exception e) {
      throw e;
    }

  }


}
