package ru.job4j.cinema.persistence;

import java.util.Objects;

public class Place {
    private int idH;
    private int rowX;
    private int columnX;
    private int price;
    private String status;
    private Account accountId;

    public Place() {
    }

    public Place(int idH, int rowX, int columnX, int price, String status) {
        this.idH = idH;
        this.rowX = rowX;
        this.columnX = columnX;
        this.price = price;
        this.status = status;
    }

    public Place(int rowX, int columnX, int price) {
        this.rowX = rowX;
        this.columnX = columnX;
        this.price = price;
    }

    public Place(int idH, int rowX, int columnX, int price, String status, Account accountId) {
        this.idH = idH;
        this.rowX = rowX;
        this.columnX = columnX;
        this.price = price;
        this.status = status;
        this.accountId = accountId;
    }

    public int getIdH() {
        return idH;
    }

    public void setIdH(int idH) {
        this.idH = idH;
    }

    public int getRowX() {
        return rowX;
    }

    public void setRowX(int rowX) {
        this.rowX = rowX;
    }

    public int getColumnX() {
        return columnX;
    }

    public void setColumnX(int columnX) {
        this.columnX = columnX;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Place place = (Place) o;
        return rowX == place.rowX
                && columnX == place.columnX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowX, columnX);
    }

    @Override
    public String toString() {
        return "Place{"
                + "idH="
                + idH
                + ", rowX="
                + rowX
                + ", columnX="
                + columnX
                + ", price="
                + price
                + ", status='"
                + status
                + '\''
                + ", accountId="
                + accountId
                + '}';
    }
}
