package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "clients") //Para personalizar el nombre de la tabla que se creará
//Se recomienda implementar Serializable porque por lo general estos obj van a viajar en forma de bytes
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //Indica la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private String email;
    private String phoneNumber;

    @Column(name = "created_date") //Para customizar el nombre de la columna o cualquier tipo de especificacion del campo
    @Temporal(TemporalType.DATE) //Para definir si se guarda fecha, hora, o fechahora
    private Date createdDate;

    public Client() {
    }

    public Client(Long id, String name, String lastname, String email, String phoneNumber, Date createdDate) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
