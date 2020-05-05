package com.librairie.batch.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BatchConfigTest {

    @InjectMocks
    private BatchConfig batchConfig;

    private DataSource dataSource;

    @Test
    void setDataSource() {
        assertDoesNotThrow(() -> batchConfig.setDataSource(dataSource));
    }
}