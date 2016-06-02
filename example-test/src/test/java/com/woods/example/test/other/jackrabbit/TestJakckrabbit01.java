package com.woods.example.test.other.jackrabbit;

import com.woods.example.test.base.JackRabbiBaseTest;
import org.apache.jackrabbit.value.StringValue;
import org.junit.Test;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.jcr.version.VersionException;
import java.io.*;

/**
 * Created by lin on 2016/5/18.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-spring-context-web.xml"})
public class TestJakckrabbit01 extends JackRabbiBaseTest {

    /**
     * 保存内容到仓库
     */
    @Test
    public void save_01_encyclopedia(){
        System.out.println("***************************************** save_01_encyclopedia ");
        try {
            Node rn = null;
//            rn = session.getRootNode();
            Node encyclopedia = rn.addNode("wiki:encyclopedia");
            Node p = encyclopedia.addNode("wiki:entry");
            p.setProperty("wiki:title", new StringValue("rose"));
            p.setProperty("wiki:content", new StringValue("A rose is a flowering shrub."));
            p.setProperty("wiki:category",
                    new Value[]{
                            new StringValue("flower"),
                            new StringValue("plant"),
                            new StringValue("rose")});

            Node n = encyclopedia.addNode("wiki:entry");
            n.setProperty("wiki:title", new StringValue("Shakespeare"));
            n.setProperty("wiki:content", new
                    StringValue("A famous poet who likes roses."));
            n.setProperty("wiki:category", new StringValue("poet"));

            session.save();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    /**
     * 浏览内容仓库
     */
    @Test
    public void iterator_01_encyclopedia(){
        System.out.println("***************************************** query_01_encyclopedia ");
        try {
            Node rn = session.getRootNode();
            Node encyclopedia = rn.getNode("wiki:encyclopedia");
            String titile = rn.getProperty("wiki:encyclopedia/wiki:entry[1]/wiki:title").getString();
            System.out.println("title: " + titile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 浏览内容仓库
     */
    @Test
    public void iterator_02_encyclopedia(){
        try {
            Node rn = session.getRootNode();
            Node encyclopedia = rn.getNode("wiki:encyclopedia");
            NodeIterator entries = encyclopedia.getNodes("wiki:entry");
            while(entries.hasNext()){

                Node entry = entries.nextNode();

                System.out.println("name(): " + entry.getName());
                System.out.println("title: " + entry.getProperty("wiki:title").getString());
                System.out.println("content: " + entry.getProperty("wiki:content").getString());
                System.out.println("path: " + entry.getPath());

                Property property = entry.getProperty("wiki:category");

                try {
                    String category = property.getValue().getString();
                    System.out.println("category: " + category);
                } catch (ValueFormatException e){
                    Value[] categrorys = property.getValues();
                    for (Value v : categrorys){
                        System.out.println(v.getString());
                    }
                }
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }


    /**
     * 用 XPath 搜索内容
     */
    @Test
    public void query_01_encyclopedia(){
        Workspace ws = session.getWorkspace();
        try {
            QueryManager qm = ws.getQueryManager();
            Query q = qm.createQuery("//wiki:encyclopedia/wiki:entry[@wiki:title = 'rose']", Query.XPATH);
            Query q2 = qm.createQuery("//wiki:encyclopedia/wiki:entry[jcr:contains(@wiki:content, 'rose')]", Query.XPATH);
            QueryResult rs = q2.execute();
            NodeIterator it = rs.getNodes();
            while (it.hasNext()){
                Node entry = it.nextNode();
                System.out.println("name(): " + entry.getName());
                System.out.println("title: " + entry.getProperty("wiki:title").getString());
                System.out.println("content: " + entry.getProperty("wiki:content").getString());
                System.out.println("path: " + entry.getPath());
            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用 XPath 搜索内容
     */
    @Test
    public void query_01(){

        try {
            System.out.println("query...");
            Workspace ws = session.getWorkspace();
            // 查找
            QueryManager qm = ws.getQueryManager();
//            Query q = qm.createQuery("//wiki:encyclopedia/wiki:entry[@wiki:title = 'rose']", Query.XPATH);// deprecated
            //模糊搜索
            Query q = qm.createQuery("//wiki:encyclopedia/(*,nt:base)/[jcr:like(@wiki:title , '%ha%')]", Query.XPATH);// deprecated
//            Query q = qm.createQuery("//wiki:encyclopedia/(*,nt:base)[jcr:contains(@wiki:title , 'rose')]", Query.XPATH);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node entry = it.nextNode();
                // 简单的输出，后面会有输出详细内容的方法
                System.out.println("wiki:title: " + entry.getProperty("wiki:title").getString());
                System.out.println(entry.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 用 XPath 搜索内容
     */
    @Test
    public void query_01_root(){

        try {
            System.out.println("query...");
            Workspace ws = session.getWorkspace();
            // 查找
            QueryManager qm = ws.getQueryManager();
//            Query q = qm.createQuery("//wiki:encyclopedia/wiki:entry[@wiki:title = 'rose']", Query.XPATH);// deprecated
            //模糊搜索
//            Query q = qm.createQuery("根目录/root.xml/(*, *)/(*,nt:base)[jcr:like(@gendoc:href, '%xml%')]", Query.XPATH);// deprecated
            Query q = qm.createQuery("根目录/root.xml/*/(*,nt:base)[jcr:like(@gendoc:href , '%xml%')]", Query.XPATH);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node entry = it.nextNode();
                // 简单的输出，后面会有输出详细内容的方法
                System.out.println("gendoc:href: " + entry.getProperty("gendoc:href").getString());
                System.out.println(entry.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 浏览内容仓库
     */
    @Test
    public void query_02_ttc1(){
        try {
            System.out.println("query...");
            Workspace ws = session.getWorkspace();
            // 查找
            QueryManager qm = ws.getQueryManager();
//            Query q = qm.createQuery("//wiki:encyclopedia/wiki:entry[@wiki:title = 'rose']", Query.XPATH);// deprecated
            //模糊搜索
//            Query q = qm.createQuery("根目录/root.xml/(*, *)/(*,nt:base)[jcr:like(@gendoc:href, '%xml%')]", Query.XPATH);// deprecated
            Query q = qm.createQuery("select * from gendoc:一级章节 ", Query.SQL);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            // 然后就可以了遍历了
            while (it.hasNext()) {
                Node entry = it.nextNode();
                String name = entry.getName();

                System.out.println(name);
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    /**
     * 浏览内容仓库
     */
    @Test
    public void iterator_02_ttc1(){
        try {
            Node rn = session.getRootNode();

            Node hrefNode = rn.getNode("ttc1/1.xml/gendoc:一级章节");
            String name = "";

            if (hrefNode.hasProperty("jcr:xmlcharacters")) {
                name = hrefNode.getProperty("jcr:xmlcharacters").getString();
            }
            System.out.println(name);

        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    /**
     * 浏览内容仓库
     */
    @Test
    public void iterator_03_ttc1(){
        try {
            Node rn = session.getRootNode();

            String xpath = "GTCT0001/3.xml/gendoc:一级章节/gendoc:二级章节[1]/gendoc:内容/gendoc:术语/gendoc:中文/jcr:xmltext";

            if(rn.hasNode(xpath)){
                Node node = rn.getNode(xpath);
                String name = "默认";
                if (node.hasProperty("jcr:xmlcharacters")) {
                    name = node.getProperty("jcr:xmlcharacters").getString();
                }
                System.out.println("name1: " + name);
            }

            String s1 = "/jcr:root/GTCT0001//element(gendoc:中文, nt:unstructured)/jcr:xmltext";
            String s2 = "/jcr:root/GTCT0001//element(gendoc:英文, nt:unstructured)/jcr:xmltext";
            String s3 = "/jcr:root/GTCT0001//element(gendoc:解释, nt:unstructured)/jcr:xmltext";
            String ss1 = "/jcr:root/GTCT0001/(3.xml)//gendoc:中文/jcr:xmltext";
            String ss2 = "/jcr:root/GTCT0001/(3.xml)//gendoc:英文/jcr:xmltext";
            String ss3 = "/jcr:root/GTCT0001/(3.xml)//gendoc:解释/jcr:xmltext";
            String ss4 = "/jcr:root/GTCT0001/(1463896820072.xml)//标题/jcr:xmltext";
            Workspace ws = session.getWorkspace();
            QueryManager qm = ws.getQueryManager();
            Query q = qm.createQuery(s1, Query.XPATH);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();

            Query q2 = qm.createQuery(s2, Query.XPATH);// deprecated
            QueryResult result2 = q2.execute();// 执行查询
            NodeIterator it2 = result2.getNodes();

            Query q3 = qm.createQuery(ss4, Query.XPATH);// deprecated
            QueryResult result3 = q3.execute();// 执行查询
            NodeIterator it3 = result3.getNodes();
            StringBuffer sb = new StringBuffer();
            while (it.hasNext() || it2.hasNext() || it.hasNext()){
                Node ss1Node = it.nextNode();
                Node ss2Node = it2.nextNode();
                Node ss3Node = it3.nextNode();
                if (ss1Node.hasProperty("jcr:xmlcharacters")) {
                    sb.append(ss1Node.getProperty("jcr:xmlcharacters").getString()).append("\n");;
                }
                if (ss2Node.hasProperty("jcr:xmlcharacters")) {
                    sb.append(ss2Node.getProperty("jcr:xmlcharacters").getString()).append("\n");;
                }
                if (ss3Node.hasProperty("jcr:xmlcharacters")) {
                    sb.append(ss3Node.getProperty("jcr:xmlcharacters").getString()).append("\n");;
                }

            }
            System.out.println("sb: " + sb.toString());

        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    /**
     * 浏览内容仓库
     */
    @Test
    public void iterator_03_GTCT0001(){
        try {

            String ss = "/jcr:root/GTCT0001/(1463896820072.xml)//标题/jcr:xmltext";
            Workspace ws = session.getWorkspace();
            QueryManager qm = ws.getQueryManager();
            Query q = qm.createQuery(ss, Query.XPATH);// deprecated
            QueryResult result = q.execute();// 执行查询
            NodeIterator it = result.getNodes();
            StringBuffer sb = new StringBuffer();
            while (it.hasNext()){
                Node node = it.nextNode();
                if (node.hasProperty("jcr:xmlcharacters")) {
                    sb.append(node.getProperty("jcr:xmlcharacters").getString()).append("\n");;
                }

            }
            System.out.println("sb: " + sb.toString());

        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }


    /**
     * 导出到xml
     */
    @Test
    public void exportXml(){

        File file = new File("E:\\LearningMaterials\\jackrabbit\\jackrabbit_repository\\exportFile\\systemview3.xml");
        try {
            FileOutputStream out = new FileOutputStream(file);
            session.exportSystemView("/根目录", out, false, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isDirectory(){
        File f = new File("E:\\LearningMaterials\\jackrabbit\\jackrabbit_repository\\importFile");
        System.out.println(!f.isDirectory());
        System.out.println(f.getName());
        System.out.println(f);
    }

    /**
     * 导入xml
     */
    @Test
    public void importXml(){
        try {
            String path = "E:\\LearningMaterials\\jackrabbit\\jackrabbit_repository\\importFile\\";//路径
            String f = "NA0001";//文件名
            File filePath = new File(path + f);
            if (!filePath.isDirectory()) {
                System.out.println("你输入的不是文件夹，请检查路径是否有误!");
            } else {
                File[] files = filePath.listFiles();
                //第一层循环拿到010203标准类型节点名字
                System.out.println("Importing xml....");
                Node root = session.getRootNode();
                //导入XML文件,除非已经导入
                if (!root.hasNode(f)) {
                    //创建根节点(EX:010203)非结构化导入XML节点
                    Node node = root.addNode(f, "nt:unstructured");
                    //第二层循环拿到010203标准中的各个术语也就是xml文件的节点名字
                    for (int i = 0; i < files.length; i++) {
                        if (!node.hasNode(files[i].getName())) {
                            FileInputStream xml = new FileInputStream(files[i]);
                            Node temp = node.addNode(files[i].getName());
                            session.importXML(temp.getPath(), xml, ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
                            xml.close();
                            session.save();
                        } else {
                            System.out.println(files[i].getName() + "文件已经在仓库中");
                        }
                    }
                } else {
                    System.out.println(f + "文件已经在仓库中！");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void iterator_shuyu1(){
        try {
            Node rn = session.getRootNode();
            Node shuyu = rn.getNode("术语1");
            Node x3 = shuyu.getNode("3.xml");
            Node yiji = x3.getNode("gendoc:一级章节");
            NodeIterator it = yiji.getNodes();
            while (it.hasNext()){
                Node n = it.nextNode();
                System.out.println(n.getName());
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void query_shuyu1(){
        try {

            Workspace ws = session.getWorkspace();
            QueryManager qm = ws.getQueryManager();
//            Query q = qm.createQuery("//术语1/3.xml/gendoc:一级章节[jcr:contains(@gendoc:解释, '哈哈哈')]", Query.XPATH);
//            Query q = qm.createQuery("//术语1[jcr:contains(., '术语1解释，哈哈哈。')]", Query.XPATH);
            Query q = qm.createQuery("//element(*,nt:base)[jcr:contains(.,'哈哈哈。')]", Query.XPATH);
            QueryResult qr = q.execute();
            NodeIterator it = qr.getNodes();
            while (it.hasNext()){
                Node n = it.nextNode();
                System.out.println("name: " + n.getName());
                System.out.println("path: " + n.getPath());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
