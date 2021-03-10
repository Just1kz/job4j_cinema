package ru.job4j.cinema.service;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbsTest {

    @Test
    public void asd() {
        assertThat(Abs.asd(), is(Boolean.TRUE));
    }
}