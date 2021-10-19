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
package restapisb.user.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Phones")
public class Phone implements Serializable {

  private static final long serialVersionUID = 8387899286310765339L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Long id;
  private String number;
  private Integer citycode;
  private Integer contrycode;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID")
  @JsonBackReference
  private User user;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Integer getCitycode() {
    return citycode;
  }

  public void setCitycode(Integer citycode) {
    this.citycode = citycode;
  }

  public Integer getContrycode() {
    return contrycode;
  }

  public void setContrycode(Integer contrycode) {
    this.contrycode = contrycode;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Phone [id=" + id + ", number=" + number + ", citycode=" + citycode + ", contrycode=" + contrycode + ", user=" + user + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(citycode, contrycode, id, number, user);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Phone other = (Phone) obj;
    return Objects.equals(citycode, other.citycode) && Objects.equals(contrycode, other.contrycode) && Objects.equals(id, other.id)
        && Objects.equals(number, other.number) && Objects.equals(user, other.user);
  }


}
