package com.welcome.server;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.welcome.server.bot.ContextFirebaseListener;
import com.welcome.server.config.WebConfig;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.FileInputStream;

/**
 * Created by @mistreckless on 10/3/16.!
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    private static final String DISPATCHER = "welcome_dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));
        servletContext.addListener(new ContextFirebaseListener());

        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(DISPATCHER, new DispatcherServlet(ctx));
        servletRegistration.addMapping("/");
        servletRegistration.setLoadOnStartup(1);

        //configure firebase
        try {
            LoggerFactory.getLogger("application").info("Sramota");
            FileInputStream serviceAccount = new FileInputStream(getClass().getClassLoader().getResource("WelcomePush-c160a6e929f1.json").getFile());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl("https://welcomepush-96f73.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot load json file");
        }

    }

}
