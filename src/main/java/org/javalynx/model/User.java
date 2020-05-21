package org.javalynx.model;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class User {

    private String firstName;

    private String lastName;

    private long id;

    private String password;

    private String description;

    public User() {

    }

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        setPassword(password);
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = Hashing.sha512()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        return this;
    }



}
