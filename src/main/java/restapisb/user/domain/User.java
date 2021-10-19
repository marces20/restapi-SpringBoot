/*

 */
package restapisb.user.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Users")
public class User implements Serializable {

  private static final long serialVersionUID = 4411114518553060573L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Long id;
  private String name;
  private String email;
  private String password;
  @Column(name = "token", length = 1024)
  private String token;
  private Date last_login;
  private boolean isactive;


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @JsonManagedReference
  private List<Phone> phones;


  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Date updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Phone> getPhones() {
    return phones;
  }

  public void setPhones(List<Phone> phones) {
    this.phones = phones;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Date getLast_login() {
    return last_login;
  }

  public void setLast_login(Date last_login) {
    this.last_login = last_login;
  }

  public boolean isIsactive() {
    return isactive;
  }

  public void setIsactive(boolean isactive) {
    this.isactive = isactive;
  }


  public User() {
    super();
  }

  public User(Long id, String name, String email, String password, String token, Date last_login, boolean isactive) {
    super();
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.token = token;
    this.last_login = last_login;
    this.isactive = isactive;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phones=" + phones + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, id, name, password, phones);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
        && Objects.equals(password, other.password) && Objects.equals(phones, other.phones);
  }

  public User updateWith(User u) {
    return new User(this.id, u.name, u.email, u.password, u.token, u.last_login, u.isactive);
  }
}
