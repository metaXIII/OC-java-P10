package com.librairie.batch.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.spi.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class QuartzConfigTest {

    @InjectMocks
    private QuartzConfig quartzConfig;

    @Mock
    private JobRegistry jobRegistry;

    @Mock
    private JobFactory jobFactory;

    @Mock
    private ApplicationContext applicationContext;

    @Test
    void jobRegistryBeanPostProcessor() {
        assertDoesNotThrow(() -> quartzConfig.jobRegistryBeanPostProcessor(jobRegistry));
        assertNotNull(quartzConfig.jobRegistryBeanPostProcessor(jobRegistry));
    }

    @Test
    void jobDetailFactoryBean() {
        assertDoesNotThrow(() -> quartzConfig.jobDetailFactoryBean());
        assertNotNull(quartzConfig.jobDetailFactoryBean());
    }

    @Test
    void cronTriggerFactoryBean() {
        assertDoesNotThrow(() -> quartzConfig.cronTriggerFactoryBean());
        assertNotNull(quartzConfig.cronTriggerFactoryBean());
    }

    @Test
    void triggerMonitor() {
        assertDoesNotThrow(() -> quartzConfig.triggerMonitor());
        assertNotNull(quartzConfig.triggerMonitor());
    }

    @Test
    void jobFactory() {
        ReflectionTestUtils.setField(quartzConfig, "applicationContext", applicationContext);
        assertDoesNotThrow(() -> quartzConfig.jobFactory());
        assertNotNull(quartzConfig.jobFactory());
    }

    @Test
    void schedulerFactoryBean() {
        ReflectionTestUtils.setField(quartzConfig, "applicationContext", applicationContext);
        assertDoesNotThrow(() -> quartzConfig.schedulerFactoryBean(jobFactory));
        assertNotNull(quartzConfig.schedulerFactoryBean(jobFactory));
    }
}