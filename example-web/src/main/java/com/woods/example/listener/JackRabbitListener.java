package com.woods.example.listener;

import org.apache.jackrabbit.core.TransientRepository;

import javax.jcr.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by lin on 2016/5/18.
 */

public class JackRabbitListener implements ServletContextListener {

    public static Session session;

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("****************************** JackRabbit init ************************************");
        try  {
            System.setProperty( "org.apache.jackrabbit.repository.home", "E:\\LearningMaterials\\jackrabbit\\jackrabbit_repository\\repository" );
            Repository repository =  new TransientRepository();
            session = repository.login( new SimpleCredentials( "admin", "admin".toCharArray()));
        }  catch  (Exception e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("****************************** JackRabbit contextDestroyed ************************************");
        session.logout();
    }

    public static Session getSession(){
        return session;
    }
}
