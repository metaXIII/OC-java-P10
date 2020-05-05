package com.librairie.batch.quartz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class AutowiringSpringBeanJobFactoryTest {

    @InjectMocks
    private AutowiringSpringBeanJobFactory autowiringSpringBeanJobFactory;

    @Mock
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Mock
    private TriggerFiredBundle triggerFiredBundle;

    @Mock
    private ApplicationContext applicationContext;


    @Test
    void createJobInstance() throws Exception {
        ReflectionTestUtils.setField(autowiringSpringBeanJobFactory, "beanFactory", autowireCapableBeanFactory);
        Mockito.doNothing().when(autowireCapableBeanFactory).autowireBean(null);
        assertNull(autowiringSpringBeanJobFactory.createJobInstance(triggerFiredBundle));
    }

    @Test
    void setApplicationContext() {
        assertDoesNotThrow(() -> autowiringSpringBeanJobFactory.setApplicationContext(applicationContext));
    }
}