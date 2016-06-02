package com.woods.example.controller;

import com.woods.example.listener.JackRabbitListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

/**
 * Created by lin on 2016/5/18.
 */
@Controller
@RequestMapping("jr")
public class JackRabbitController {

    @RequestMapping("save")
    public void save(){
        try {
            System.out.println("save...");
            Session session = JackRabbitListener.getSession();
            Node rootNode = session.getRootNode();

            Node blogEntry = rootNode.addNode( "blogEntry" );
            blogEntry.setProperty( "title", "titile..");
            blogEntry.setProperty( "blogContent", "blogContent..");
            blogEntry.setProperty( "creationTime", "creationTime..");
            blogEntry.setProperty( "userName", "userName..");
            session.save();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("query1")
    public void query1(){

        try {
            System.out.println("query...");
            Session session = JackRabbitListener.getSession();
            Workspace ws = session.getWorkspace();
            // 查找
            QueryManager qm = ws.getQueryManager();
            Query q = qm.createQuery("//wiki:encyclopedia/wiki:entry[@wiki:title = 'rose']", Query.XPATH);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node entry = it.nextNode();
                // 简单的输出，后面会有输出详细内容的方法
                System.out.println(entry.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("query2")
    public void query2(){

        try {
            System.out.println("query2...");
            Session session = JackRabbitListener.getSession();
            Workspace ws = session.getWorkspace();
            // 查找
            QueryManager qm = ws.getQueryManager();
            Query q = qm.createQuery("//wiki:encyclopedia/wiki:entry[@wiki:title = 'rose']", Query.XPATH);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node entry = it.nextNode();
                // 简单的输出，后面会有输出详细内容的方法
                System.out.println(entry.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
