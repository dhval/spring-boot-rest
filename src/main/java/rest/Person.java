package rest;

/**
 * Created by dhval on 2/6/15.
 */

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name="Person")
public class Person implements Serializable {

  public Person() {
    entryDate = new Timestamp(new Date().getTime());
  }

  @Id
  @GeneratedValue
  @Column(name="id", nullable = false, insertable = false, updatable = false)
  private Long id;

  @Column(name="name")
  String name;

  @Column(name="login")
  String login;

  @Column(name="email")
  String email;

  @Column(name="phone")
  String phone;

  @Column(name="agent")
  String agent;

  @Column(name="address")
  String address;

  @Column(name="comment")
  String comment;

  @Column(name="entryDate")
  Timestamp entryDate;

  @Column(name="modifyDate")
  Timestamp modifyDate;

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

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getAgent() {
    return agent;
  }

  public void setAgent(String agent) {
    this.agent = agent;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }
}
