package ru.job4j.cinema.service;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cinema.persistence.Account;
import ru.job4j.cinema.persistence.Place;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import javax.validation.ConstraintViolationException;

public class PsqlService implements CinemaService {

    private final BasicDataSource pool = new BasicDataSource();

    private static final Logger LOG = LoggerFactory.getLogger(PsqlService.class);

    public PsqlService() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final CinemaService INST = new PsqlService();
    }

    public static CinemaService instOf() {
        return Lazy.INST;
    }

    @Override
    public void buy(Account account, int id) {
        if (findByIdPlace(id).getStatus().equals("Occupied")) {
            throw new RuntimeException("Place is Occupied");
        } else {
            try (Connection connection = pool.getConnection();
                 PreparedStatement query = connection.prepareStatement(
                         "insert into accounts(username, phone_number) values (?, ?)",
                         PreparedStatement.RETURN_GENERATED_KEYS)) {
                query.setString(1, account.getUsername());
                query.setString(2, account.getPhoneNumber());
                query.executeUpdate();
                ResultSet keys = query.getGeneratedKeys();
                if (keys.next()) {
                    try (PreparedStatement updatePlace = connection.prepareStatement(
                            "update halls set account_id = ?, status = ? where idh = ?"
                    )) {
                        updatePlace.setInt(1, keys.getInt(1));
                        updatePlace.setString(2, "Occupied");
                        updatePlace.setInt(3, id);
                        updatePlace.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
    }

    @Override
    public List<Place> findAllPlaces() {
        List<Place> places = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM halls order by idH;")
        ) {
            ResultSet it = ps.executeQuery();
                while (it.next()) {
                    places.add(new Place(
                            it.getInt("idh"),
                            it.getInt("rowx"),
                            it.getInt("columnx"),
                            it.getInt("price"),
                            it.getString("status"),
                            new Account(it.getInt("account_id"))));
                }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return places;
    }

    @Override
    public Place findByIdPlace(int id) {
        Place place = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "select * from halls where idh = ?;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    place = new Place(
                            rs.getInt("idh"),
                            rs.getInt("rowx"),
                            rs.getInt("columnx"),
                            rs.getInt("price"),
                            rs.getString("status"),
                            new Account(rs.getInt("account_id")));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return place;
    }


    @Override
    public void close() throws Exception {
        pool.close();
    }
}
