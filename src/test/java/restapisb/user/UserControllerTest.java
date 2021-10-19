/*
 * Copyright (C) Intraway Corporation - All Rights Reserved (2020)
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *
 * Proprietary and confidential
 *
 * This file is subject to the terms and conditions defined in file LICENSE.txt, which is part of this source code
 * package.
 */
package restapisb.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


public class UserControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserController uc;

  @Test
  public void testCreateUser() {
    // fail("Not yet implemented");
  }

  @Test
  public void testUpdateUser() {
    // fail("Not yet implemented");
  }

  @Test
  public void testDeleteById() {
    // fail("Not yet implemented");
  }

  @Test
  public void testDeleteAllTutorials() {
    // fail("Not yet implemented");
  }

  @Test
  public void testUserById() {
    // fail("Not yet implemented");
  }

  @Test
  public void testGetUserAll() {
    // fail("Not yet implemented");
  }

  @Test
  public void testLogin() {
    // fail("Not yet implemented");
  }

}
