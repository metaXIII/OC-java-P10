package com.librairie.batch.quartz;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.Trigger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TriggerMonitorTest {

    @InjectMocks
    private TriggerMonitor triggerMonitor;

    @Mock
    private Trigger trigger;

    @BeforeEach
    public void init() {
        triggerMonitor = new TriggerMonitor();
        triggerMonitor.setTrigger(trigger);
    }

    @AfterEach
    public void end() {
        triggerMonitor = null;
    }

    @Test
    void setTrigger() {
        assertDoesNotThrow(() -> triggerMonitor.setTrigger(trigger));
    }

    @Test
    void getTrigger() {
        assertEquals(trigger, triggerMonitor.getTrigger());
    }
}