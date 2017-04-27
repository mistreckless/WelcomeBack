package com.welcome.server.bot;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by @mistreckless on 11.03.2017.!
 */
public class ContextFirebaseListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        FirebaseBot.getInstance().start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        FirebaseBot.getInstance().stop();
    }
}
