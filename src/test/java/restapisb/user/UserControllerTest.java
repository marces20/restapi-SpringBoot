/*

 */
package restapisb.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import restapisb.App;
import restapisb.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserControllerTest {

  @Autowired
  private UserService ur;

  @Test
  public void testCreateUser() throws Exception {

    User created = ur.createUser(getUser());
    assertTrue(created != null);
    assertNotNull(ur.getUserById(created.getId()).get());
  }

  @Test
  public void testUpdateUser() throws Exception {
    // fail("Not yet implemented");
    User created = ur.createUser(getUser());

    User u = ur.getUserById(created.getId()).get();
    u.setEmail("marces2000@gmail.com");
    ur.updateUser(u.getId(), u);

    User updated = ur.getUserById(u.getId()).get();

    assertNotEquals("xxxxxxx@gmail.com", updated.getEmail());
    assertEquals("marces2000@gmail.com", updated.getEmail());


  }

  @Test
  public void testDeleteById() throws Exception {
    // fail("Not yet implemented");

    User created = ur.createUser(getUser());
    ur.deleteById(created.getId());
    assertTrue(!ur.getUserById(created.getId()).isPresent());
  }


  @Test
  public void testUserById() throws Exception {
    User created = ur.createUser(getUser());
    assertTrue(created != null);
    assertNotNull(ur.getUserById(created.getId()).get());
  }


  @Test
  public void testGetUserAll() throws Exception {
    User created = ur.createUser(getUser());
    List<User> list = ur.getUserAll();
    assertThat(list).size().isGreaterThan(0);
  }

  private User getUser() {

    User u = new User();
    u.setEmail("aaaaa");
    u.setIsactive(true);
    u.setLast_login(new Date());
    u.setName("bbbbbb");
    u.setPassword("xxxxxx");

    return u;
  }

}
