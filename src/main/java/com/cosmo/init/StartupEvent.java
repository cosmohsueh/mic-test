package com.cosmo.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.cosmo.service.UdpServer;

public class StartupEvent implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupEvent.class);

    private static ApplicationContext context;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            context = event.getApplicationContext();

            SysConfig sysConfig = (SysConfig) context.getBean(SysConfig.class);

            UdpServer udpServer = (UdpServer) context.getBean(UdpServer.class);
            udpServer.run(sysConfig.getUdpReceivePort());

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static Object getBean(Class beanName) {
        return context != null ? context.getBean(beanName) : null;
    }

}
