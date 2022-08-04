package jm.task.core.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user", schema = "jdbctest")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="first_name")
  private String name;

  @Column(name="last_name")
  private String lastName;

  @Column(name="age")
  private byte age;

  public User(){

  }

  public User(String name, String lastName, byte age) {
    this.name = name;
    this.lastName = lastName;
    this.age = age;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setAge(byte age) {
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }


  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", lastName='" + lastName + '\'' +
        ", age=" + age +
        '}';
  }

}
