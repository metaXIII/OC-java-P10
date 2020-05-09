package com.librairie.batch.job;

import com.librairie.batch.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomTask implements Tasklet, StepExecutionListener {

    private final EmailService emailService;

    public CustomTask(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Début de l'envoi des mails.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext chunkContext) throws Exception {
        try {
            log.info("Tache en cours ...");
            emailService.sendEmail();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Fin de l'envoi des mails.");
        return ExitStatus.COMPLETED;
    }
}
