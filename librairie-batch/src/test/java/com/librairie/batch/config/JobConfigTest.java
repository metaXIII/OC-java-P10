package com.librairie.batch.config;

import com.librairie.batch.job.CustomTask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobConfigTest {

    @InjectMocks
    private JobConfig jobConfig;

    @Mock
    private StepBuilderFactory stepBuilderFactory;

    @Mock
    private StepBuilder stepBuilder;

    @Mock
    private TaskletStepBuilder taskletStepBuilder;

    @Mock
    private TaskletStep step;

    @Mock
    private JobBuilderFactory jobBuilderFactory;

    @Mock
    private JobBuilder jobBuilder;

    @Mock
    private SimpleJobBuilder simpleJobBuilder;

    @Test
    void customStep() {
        when(stepBuilderFactory.get(anyString())).thenReturn(stepBuilder);
        when(stepBuilder.tasklet(any())).thenReturn(taskletStepBuilder);
        when((stepBuilderFactory.get(anyString())).tasklet(any(CustomTask.class)).build()).thenReturn(step);
        assertDoesNotThrow(() -> jobConfig.customStep(stepBuilderFactory));
    }

    @Test
    void customJob() {
        when(stepBuilderFactory.get(anyString())).thenReturn(stepBuilder);
        when(stepBuilder.tasklet(any())).thenReturn(taskletStepBuilder);
        when((stepBuilderFactory.get(anyString())).tasklet(any(CustomTask.class)).build()).thenReturn(step);
        when(jobBuilderFactory.get(anyString())).thenReturn(jobBuilder);
        when(jobBuilder.start(any(Step.class))).thenReturn(simpleJobBuilder);
        assertDoesNotThrow(() -> jobConfig.customJob(jobBuilderFactory, stepBuilderFactory));
    }
}