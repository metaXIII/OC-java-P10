package com.librairie.batch.quartz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuartzJobLauncherTest {

    @InjectMocks
    private QuartzJobLauncher quartzJobLauncher;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private JobLocator jobLocator;

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private JobDataMap jobDataMap;

    @Mock
    private JobExecution jobExecution;

    @Test
    void executeInternal() throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException,
            JobRestartException,
            JobInstanceAlreadyCompleteException {
        when(applicationContext.getBean(JobLocator.class)).thenReturn(jobLocator);
        when(applicationContext.getBean(JobLauncher.class)).thenReturn(jobLauncher);
        when(jobExecutionContext.getMergedJobDataMap()).thenReturn(jobDataMap);
        when(jobDataMap.get(any())).thenReturn("aze");
        when(jobLauncher.run(any(), any())).thenReturn(jobExecution);
        assertDoesNotThrow(() -> quartzJobLauncher.executeInternal(jobExecutionContext));
    }


    @Test
    void shouldCatchException() {
        assertDoesNotThrow(() -> quartzJobLauncher.executeInternal(jobExecutionContext));
    }


}