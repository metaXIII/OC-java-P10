package com.librairie.batch.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public final class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory
        implements ApplicationContextAware {

    private AutowireCapableBeanFactory beanFactory;

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle)
            throws Exception {
        final Object job = bundle.getJobDetail() != null ? super.createJobInstance(bundle) : null;
        beanFactory.autowireBean(job);
        return job;
    }

    @Override
    public void setApplicationContext(final ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory();
    }
}

