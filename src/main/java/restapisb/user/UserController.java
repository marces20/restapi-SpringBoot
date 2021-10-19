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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import restapisb.user.domain.Phone;
import restapisb.user.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService ur;

  @Autowired
  private PhoneService pr;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<Object> createUser(@RequestBody User user) throws Exception {

    try {

      if (!ValidarMail(user.getEmail())) {
        // El correo debe seguir una expresión regular para validar que formato sea el correcto.(aaaaaaa@dominio.cl)
        return new ResponseEntity<>("{\"mensaje\" : \"Email invalido\"}", HttpStatus.FORBIDDEN);
      }

      if (!ValidarPassword(user.getPassword())) {
        // La clave debe seguir una expresión regular para validar que formato sea el correcto. (UnaMayúscula, letras minúsculas, y dos números)
        return new ResponseEntity<>("{\"mensaje\" : \"Password invalido\"}", HttpStatus.FORBIDDEN);
      }


      List<User> ul = ur.getUserByEmail(user.getEmail());
      if (ul.size() > 0) {
        // En caso que el correo exista en la base de datos, deberá retornar un error "El correo ya registrado".
        return new ResponseEntity<>("{\"mensaje\" : \"El correo ya registrado\"}", HttpStatus.FORBIDDEN);
      }

      User newUser = ur.createUser(user);

      List<Phone> lp = user.getPhones();
      if (lp.size() > 0) {
        lp.forEach(ll -> {
          ll.setUser(newUser);
          try {
            pr.createPhone(ll);
          } catch (Exception e) {

          }
        }

        );
      }

      return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PutMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<Object> updateUser(@RequestBody User user) throws Exception {

    try {

      if (!ValidarMail(user.getEmail())) {
        // El correo debe seguir una expresión regular para validar que formato sea el correcto.(aaaaaaa@dominio.cl)
        return new ResponseEntity<>("{\"mensaje\" : \"Email invalido\"}", HttpStatus.FORBIDDEN);
      }

      if (!ValidarPassword(user.getPassword())) {
        // La clave debe seguir una expresión regular para validar que formato sea el correcto. (UnaMayúscula, letras minúsculas, y dos números)
        return new ResponseEntity<>("{\"mensaje\" : \"Password invalido\"}", HttpStatus.FORBIDDEN);
      }

      return new ResponseEntity<>(ur.updateUser(user.getId(), user), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
    try {
      ur.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/deleteAll")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
      ur.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/{id}")
  public ResponseEntity<User> userById(@PathVariable("id") long id) throws Exception {

    try {
      Optional<User> userData = ur.getUserById(id);

      if (userData.isPresent()) {
        return new ResponseEntity<>(userData.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<User>> getUserAll() {
    try {

      List<User> users = ur.getUserAll();

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private static boolean ValidarMail(String email) {
    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    Matcher mather = pattern.matcher(email);
    return mather.find();
  }

  private static boolean ValidarPassword(String pass) {

    Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z]{1})(?=.*[0-9]{2}){6,10}");
    Matcher mather = pattern.matcher(pass);
    return mather.find();
  }

  @PostMapping(path = "/login")
  public ResponseEntity<Object> login(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {

    List<User> userList = ur.getUserByEmail(email);

    if (userList.size() == 1) {

      User user = userList.get(0);
      String token = getJWTToken(user.getEmail());


      user.setToken(token);
      user.setLast_login(new Date());
      user.setIsactive(true);

      User uu = ur.updateUser(user.getId(), user);


      return new ResponseEntity<>(uu, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  private String getJWTToken(String username) {

    String secretKey = "mySecretKey";
    // List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

    String token = Jwts.builder()
        .setId("softtekJWT")
        .setSubject(username)
        // .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 600000))
        .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
        .compact();

    return "Bearer " + token;
  }


}
