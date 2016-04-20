package com.woods.example.test.exportexcel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lin on 2016/4/14.
 */
public class TestExport2 {

    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    @Test
    public void export(){

        String[] titleFields = { "日期", "KJ实际交易金额","交易笔数", "KJ账面金额（应收渠道）","交易笔数","会计日期","对账日期","对账金额"
                ,                "对账笔数", "应收－待结算", "应收-待结算-笔数", "KJ实际入金金额" };
        String[] valueFields = new String[]{"date1", "amount", "count", "kj", "bs", "ms", "ysjs", "ysdjs", "kjsjje"};
        String[] subValueFields = new String[]{"kjrq", "dzrq", "dzje", "dzbs"};

        String excelName = "测试";
        ExportExcel2.export2(excelName, list, valueFields, subValueFields, titleFields);

    }

    @Before
    public void data(){
        /** 子对象数组  1 **/
        List<Map<String, Object>> ms1 = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("kjrq", "20160407");
        m1.put("dzrq", "20160408");
        m1.put("dzje", "84402864.01");
        m1.put("dzbs", "24801");
        Map<String, Object> m2 = new HashMap<String, Object>();
        m2.put("kjrq", "20160407");
        m2.put("dzrq", "20160408");
        m2.put("dzje", "84402864.02");
        m2.put("dzbs", "24802");
        Map<String, Object> m3 = new HashMap<String, Object>();
        m3.put("kjrq", "20160407");
        m3.put("dzrq", "20160408");
        m3.put("dzje", "84402864.03");
        m3.put("dzbs", "24803");
        ms1.add(m1);
        ms1.add(m2);
        ms1.add(m3);

        /** 子对象数组  2 **/
        List<Map<String, Object>> ms2 = new ArrayList<Map<String, Object>>();
        Map<String, Object> m4 = new HashMap<String, Object>();
        m4.put("kjrq", "20160408");
        m4.put("dzrq", "20160409");
        m4.put("dzje", "84402864.04");
        m4.put("dzbs", "24804");
        Map<String, Object> m5 = new HashMap<String, Object>();
        m5.put("kjrq", "20160408");
        m5.put("dzrq", "20160409");
        m5.put("dzje", "84402864.05");
        m5.put("dzbs", "24805");
        ms2.add(m4);
        ms2.add(m5);

        /** 子对象数组  3 **/
        List<Map<String, Object>> ms3 = new ArrayList<Map<String, Object>>();
        Map<String, Object> m6 = new HashMap<String, Object>();
        m6.put("kjrq", "20160408");
        m6.put("dzrq", "20160409");
        m6.put("dzje", "84402864.06");
        m6.put("dzbs", "24806");
        Map<String, Object> m7 = new HashMap<String, Object>();
        m7.put("kjrq", "20160408");
        m7.put("dzrq", "20160409");
        m7.put("dzje", "84402864.06");
        m7.put("dzbs", "24806");
        ms3.add(m6);
        ms3.add(m7);


        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("date1","4.71111111");
        map1.put("amount","86692605.42");
        map1.put("count","25644");
        map1.put("kj","");
        map1.put("bs","");
        map1.put("ms", ms1);//一对多关系
        map1.put("ysjs", "1111111111");
        map1.put("ysdjs", "1");
        map1.put("kjsjje", "234234");
        //"date1", "amount", "count", "kj", "bs", "ms", "ysjs", "ysdjs", "kjsjje"

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("date1","4.8");
        map2.put("amount","86692605.42");
        map2.put("count","25644");
        map2.put("kj","123123123");
        map2.put("bs","");
        map2.put("ms", ms2);//一对多关系
        map2.put("ysjs", "22222222222222");
        map2.put("ysdjs", "1");
        map2.put("kjsjje", "234234");

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("date1","4.9");
        map3.put("amount","86692605.42");
        map3.put("count","25644");
        map3.put("kj","123123123");
        map3.put("bs","");
        map3.put("ms", ms3);//一对多关系
        map3.put("ysjs", "333333333333333");
        map3.put("ysdjs", "");
        map3.put("kjsjje", "234234");

        list.add(map3);
        list.add(map2);
        list.add(map1);
    }

}
