package com.librairie.batch.job;

import com.librairie.batch.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomTaskTest {

    @InjectMocks
    private CustomTask customTask;

    @Mock
    private StepExecution stepExecution;

    @Mock
    private StepContribution stepContribution;

    @Mock
    private ChunkContext chunkContext;

    @Test
    void beforeStep() {
        assertDoesNotThrow(() -> customTask.beforeStep(stepExecution));
    }

    @Test
    void execute() {
        assertDoesNotThrow(() -> customTask.execute(stepContribution, chunkContext));
    }

    @Test
    void afterStep() {
        assertEquals("COMPLETED", Objects.requireNonNull(customTask.afterStep(stepExecution)).getExitCode());
    }
}