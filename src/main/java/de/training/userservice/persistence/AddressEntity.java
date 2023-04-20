package de.training.userservice.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

/**
 * Created by Thomas Becker (thomas.becker00@gmail.com) on 14.04.23.
 */
@Entity
public class AddressEntity extends BaseEntity {
    private String street;
    private String streetNr;
    private String zipCode;
    private String city;
    @ManyToOne
    private UserEntity user;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "street='" + street + '\'' +
                ", streetNr='" + streetNr + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
