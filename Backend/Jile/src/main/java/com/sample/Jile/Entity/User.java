package com.sample.Jile.Entity;


import jakarta.persistence.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import com.sample.Jile.Entity.Images;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id = 1L;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;
    @Temporal(TemporalType.DATE)
    @Column(name="dateofbirth")
    private Date dateofbirth;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="phonenumber")
    private Long phonenumber;

    @Column(name ="gender")
    private String gender;

    @Column(name="feildOfInterest")
    private String feildOfInterest;


    @OneToOne(cascade = CascadeType.ALL)
    private Images emp_Images;

    @Column(name = "Created_date" )
    @CreationTimestamp
    private Date Created_date;


    public Date getCreated_date() {
        return Created_date;
    }

    public void setCreated_date(Date created_date) {
        Created_date = created_date;
    }

    public Images getEmp_Images() {
        return emp_Images;
    }

    public void setEmp_Images(Images emp_Images) {
        this.emp_Images = emp_Images;
    }

    public String getFeildOfInterest() {
        return feildOfInterest;
    }

    public void setFeildOfInterest(String feildOfInterest) {
        this.feildOfInterest = feildOfInterest;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }
}
