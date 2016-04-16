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
public class TestExport {

    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    @Test
    public void export(){

        String[] titleFields = { "日期", "KJ实际交易金额","交易笔数", "KJ账面金额（应收渠道）","交易笔数","会计日期","对账日期","对账金额"
                ,                "对账笔数", "应收－待结算", "应收-待结算-笔数", "KJ实际入金金额" };
        String[] valueFields = new String[]{"date1", "amount", "count", "kj", "bs", "kjrq", "dzrq", "dzje", "dzbs",
                                "ysjs", "ysdjs", "kjsjje"};
        String excelName = "测试";
        ExportExcel.export(excelName, list, valueFields, titleFields);

    }

    @Before
    public void data(){
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("date1","4.7");
        map1.put("amount","86692605.42");
        map1.put("count","25644");
        map1.put("kj","");
        map1.put("bs","");
        String map1Arr1[] = {"20160407", "20160407", "20160407"};
        String map1Arr2[] = {"20160408", "20160408", "20160408"};
        String map1Arr3[] = {"84402864.52", "81418.75", "2208322.15"};
        String map1Arr4[] = {"24801", "20", "823"};
        map1.put("kjrq", map1Arr1);
        map1.put("dzrq", map1Arr2);
        map1.put("dzje", map1Arr3);
        map1.put("dzbs", map1Arr4);
        map1.put("ysjs", "87321786.82");
        map1.put("ysdjs", "1");
        map1.put("kjsjje", "");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("date1","4.8");
        map2.put("amount","86692605.42");
        map2.put("count","25644");
        map2.put("kj","");
        map2.put("bs","");
        String map2Arr1[] = {"20160407"};
        String map2Arr2[] = {"20160408"};
        String map2Arr3[] = {"84402864.52"};
        String map2Arr4[] = {"24801"};
        map2.put("kjrq", map2Arr1);
        map2.put("dzrq", map2Arr2);
        map2.put("dzje", map2Arr3);
        map2.put("dzbs", map2Arr4);
        map2.put("ysjs", "87321786.82");
        map2.put("ysdjs", "1");
        map2.put("kjsjje", "");

        Map<String, Object> map3 = new HashMap<String, Object>();

        map3.put("date1","4.9");
        map3.put("amount","86692605.42");
        map3.put("count","25644");
        map3.put("kj","");
        map3.put("bs","");
        String map3Arr1[] = {"20160407", "20160407"};
        String map3Arr2[] = {"20160408", "20160408"};
        String map3Arr3[] = {"84402864.52", "81418.75"};
        String map3Arr4[] = {"24801", "20"};
        map3.put("kjrq", map3Arr1);
        map3.put("dzrq", map3Arr2);
        map3.put("dzje", map3Arr3);
        map3.put("dzbs", map3Arr4);
        map3.put("ysjs", "87321786.82");
        map3.put("ysdjs", "1");
        map3.put("kjsjje", "");

        list.add(map1);
        list.add(map2);
        list.add(map3);
    }

}
