package ru.job4j.cinema.persistence;

import java.util.Objects;

public class Account {
    private int idA;
    private String username;
    private String phoneNumber;

    public Account() {
    }

    public Account(int idA) {
        this.idA = idA;
    }

    public Account(int idA, String username, String phoneNumber) {
        this.idA = idA;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public Account(String username, String phoneNumber) {
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(username, account.username)
                && Objects.equals(phoneNumber, account.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, phoneNumber);
    }

    @Override
    public String toString() {
        return "Account{"
                + "idA="
                + idA
                + ", username='"
                + username
                + '\''
                + ", phoneNumber='"
                + phoneNumber
                + '\''
                + '}';
    }
}
