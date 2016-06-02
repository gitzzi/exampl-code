package com.woods.example.test.base;

import org.apache.jackrabbit.core.TransientRepository;
import org.junit.After;
import org.junit.Before;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

/**
 * Created by lin on 2016/5/19.
 */
public class JackRabbiBaseTest {

    public static Session session;

    @Before
    public void init(){
        System.out.println("****************************** JackRabbit init ************************************");

        try  {
            System.setProperty( "org.apache.jackrabbit.repository.home", "E:\\LearningMaterials\\jackrabbit\\jackrabbit_repository\\repository" );
            Repository repository =  new TransientRepository();
            session = repository.login( new SimpleCredentials( "admin", "admin".toCharArray()));
        }  catch  (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void afoter(){
        System.out.println("****************************** JackRabbit session logout ************************************");
        session.logout();
    }

}
