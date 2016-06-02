package com.woods.example.test.other.jackrabbit;

import com.woods.example.test.base.JackRabbiBaseTest;
import org.junit.Test;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.util.*;

/**
 * Created by lin on 2016/5/22.
 */
public class TestJackrabbit02 extends JackRabbiBaseTest {

    @Test
    public void xpath2(){
        try {
            System.out.println("query ********************************************************");
            Workspace ws = session.getWorkspace();
            // 查找
            QueryManager qm = ws.getQueryManager();
            String s = "//element(*,nt:unstructured)[@gendoc:pref='root.xml']";
            Query q = qm.createQuery(s, Query.XPATH);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node entry = it.nextNode();
                // 简单的输出，后面会有输出详细内容的方法
                System.out.println(entry.toString());
                System.out.println("name: " + entry.getName());
                System.out.println("path: " + entry.getPath());
                System.out.println(".......................分割线..............................");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查找某个标签下的文本内容
     */
    @Test
    public void xpath(){
        try {
            System.out.println("query ********************************************************");
            Workspace ws = session.getWorkspace();
            // 查找
            QueryManager qm = ws.getQueryManager();
            //jcr:root后面只能是rootNode 只获取rootNode下一节点
//            String s = "/jcr:root/GTCT0001/element(*, nt:unstructured)";
            //jcr:root后面只能是rootNode 获取所有节点
            String s = "/jcr:root/GTCT0001//element(标题, nt:unstructured)/jcr:xmltext";
//            String s = "//element(标题, nt:unstructured)/jcr:xmltext";
            Query q = qm.createQuery(s, Query.XPATH);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node entry = it.nextNode();
                String path = entry.getPath();
                String name = "";
                if (entry.hasProperty("jcr:xmlcharacters")) {
                    name = entry.getProperty("jcr:xmlcharacters").getString();
                    // 简单的输出，后面会有输出详细内容的方法
                    System.out.println(entry.toString());
                    System.out.println("name2: " + name);
                    System.out.println("name: " + entry.getName());
                    System.out.println("path: " + entry.getPath());
                    System.out.println(".......................分割线..............................");
                }

//                System.out.println("property: " + entry.getProperty("jcr:xmlcharacters").getString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查找某个标签下的文本内容
     */
    @Test
    public void xpath3(){
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> query >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Workspace ws = session.getWorkspace();
            // 查找
            QueryManager qm = ws.getQueryManager();
            String s = "GTCT0001/(3.xml)//标题/jcr:xmltext";
            Query q = qm.createQuery(s, Query.XPATH);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node node = it.nextNode();
                // 简单的输出，后面会有输出详细内容的方法
                System.out.println(node.toString());
                System.out.println("name: " + node.getName());
                System.out.println("path: " + node.getPath());
                System.out.println(".......................分割线..............................");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void sql2(){
        try {
            System.out.println("query ********************************************************");
            Workspace ws = session.getWorkspace();
            Node rn = session.getRootNode();
            // 查找
            QueryManager qm = ws.getQueryManager();
            // and [gendoc:pref]='root.xml'   /GTCT0001/1463896804309.xml
//            String s = "SELECT * FROM [nt:unstructured] as a WHERE [gendoc:pref]='1463896780069.xml' ";
            String s = "SELECT * FROM [nt:unstructured] as a WHERE  [gendoc:pref]='root.xml' ";
//            String s = "SELECT * FROM [nt:base] as a WHERE localname(a) = '[gendoc:基本元数据]/标题' ";
//            String s = "SELECT * FROM [nt:unstructured] where [gendoc:pref]='root.xml' ";
            Query q = qm.createQuery(s, Query.JCR_SQL2);// deprecated
//            q.setOffset(1);
//            q.setLimit(4);
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            Node gtctNode = rn.getNode("GTCT0001");

            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node entry = it.nextNode();
                if(entry.getPath().contains(gtctNode.getPath())){
                    System.out.println(entry.toString());
                    System.out.println(entry.getName());
                    System.out.println(".......................分割线..............................");
                }
                // 简单的输出，后面会有输出详细内容的方法
//                System.out.println("name: " + entry.getName());
//                System.out.println("path: " + entry.getPath());

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void title (){
        String pref = "root.xml";
        String rootNode = "GTCT0001";
        List<Map<String, String>> refPathList = getHrefs(pref, rootNode);
        List<Map<String, String>> titleList = getTitle(pref, rootNode);
    }


    public List<Map<String, String>> getHrefs(String pref, String rootNode){
        List<Map<String, String>> refPathList = new LinkedList<Map<String, String>>();
        try {
            System.out.println("************* getHrefs ********************************************************");
            Workspace ws = session.getWorkspace();
            Node rn = session.getRootNode();
            // 查找
            QueryManager qm = ws.getQueryManager();
            String s = "SELECT * FROM [nt:unstructured] as a WHERE  [gendoc:pref]='" + pref + "' ";
            Query q = qm.createQuery(s, Query.JCR_SQL2);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node entry = it.nextNode();
                Node pNode = entry.getParent();
                String path = entry.getPath();
                if(path.contains(rootNode)){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put(path, pNode.getName());
                    System.out.println(map.toString());
                    refPathList.add(map);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return refPathList;
    }

    public List<Map<String, String>> getTitle(String pref, String rootNode){
        List<Map<String, String>> titleList = new LinkedList<Map<String, String>>();
        try {
            System.out.println("********** getTitle ********************************************************");
            Workspace ws = session.getWorkspace();
            QueryManager qm = ws.getQueryManager();
            String s = "/jcr:root/" + rootNode + "//element(标题, nt:unstructured)/jcr:xmltext";
            Query q = qm.createQuery(s, Query.XPATH);
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            while (it.hasNext()) {
                Node entry = it.nextNode();
                if (entry.hasProperty("jcr:xmlcharacters")) {
                    Map<String, String> map = new HashMap<String, String>();
                    String name = entry.getProperty("jcr:xmlcharacters").getString();
                    map.put(entry.getPath(), name);
                    System.out.println(map.toString());
                    titleList.add(map);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return titleList;
    }

    @Test
    public void getHrefs(){
        String pref = "root.xml";
        String rootNode = "GTCT0001";
        try {
            Workspace ws = session.getWorkspace();
            Node rn = session.getRootNode();
            QueryManager qm = ws.getQueryManager();
            String s = "SELECT * FROM [nt:unstructured] as a WHERE  [gendoc:pref]='" + pref + "' ";
            Query q = qm.createQuery(s, Query.JCR_SQL2);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();

            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node entry = it.nextNode();
                Node pNode = entry.getParent();
                String path = entry.getPath();
                if(path.contains(rootNode)){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put(path, pNode.getName());
                    System.out.println(pNode.getName());
                    String titleStrin = path.substring(1, path.length()) + "/gendoc:基本元数据/标题/jcr:xmltext";
//                    System.out.println("titleStrin=" + titleStrin);
                    Node titleNode = rn.getNode(titleStrin);
                    if (titleNode.hasProperty("jcr:xmlcharacters")) {
                        System.out.println("title=" + titleNode.getProperty("jcr:xmlcharacters").getString());
                    }

                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
