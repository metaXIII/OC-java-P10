package com.librairie.reservation.beans;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserBeanTest {

    private UserBean userBean;

    @BeforeEach
    public void init() {
        userBean = new UserBean();
        userBean.setId(1);
        userBean.setUsername("aze");
    }

    @AfterEach
    public void end() {
        userBean = null;
    }

    @Test
    void setId() {
        assertDoesNotThrow(() -> userBean.setId(2));
    }

    @Test
    void setUsername() {
        assertDoesNotThrow(() -> userBean.setUsername("aze2"));
    }

    @Test
    void getId() {
        assertEquals(1, userBean.getId());
    }

    @Test
    void getUsername() {
        assertEquals("aze", userBean.getUsername());
    }
}