package com.woods.example.test.exportexcel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;

//import com.sun.java_cup.internal.internal_error;

public class ExportExcel {

    public static void export(String excelName, List<Map<String, Object>> list, String[] valueFields, String[] titleFields) {

        HSSFWorkbook bookWorkbook = new HSSFWorkbook();// 创建excel文件
        HSSFSheet sheet = bookWorkbook.createSheet();

        HSSFCell cell;
        HSSFRow row;

        HSSFCellStyle style = bookWorkbook.createCellStyle();// 创建一个单元的样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置水平居中
        style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);// 上下居中
        // 设置字体
        HSSFFont font = bookWorkbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 22);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        int rowint = 0;
        int titlerow1 = rowint++;
        row = sheet.createRow(titlerow1);

        //创建表格头
        for (int i = 0; i < titleFields.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(titleFields[i]);
            cell.setCellStyle(style);
            // 设置每列的宽度
            sheet.setColumnWidth(i, 4200);
        }

        //放入信息，一般为list
        //遍历list
        for(int i = 0; i < list.size(); i++){
            Map<String, Object> map = list.get(i);
            String[] kjrq = (String[])map.get("kjrq");

            for (int j = 0; j < kjrq.length; j++){
                System.out.println("rowSize2: " + sheet.getPhysicalNumberOfRows());
                row=sheet.createRow( sheet.getPhysicalNumberOfRows());

                for (int k = 0; k < valueFields.length; k++){
                    cell=row.createCell(k);
                    cell.setCellStyle(style);
                    if(map.get(valueFields[k]) instanceof String[]){
                        String[] strs = (String[])map.get(valueFields[k]);
                        Object cellVale = strs[j];
                        if(null != cellVale){
                            cell.setCellValue(cellVale.toString());
                        }else {
                            cell.setCellValue("");
                        }
                    } else {
                        Object cellVale = map.get(valueFields[k]);
                        if(null != cellVale){
                            cell.setCellValue(cellVale.toString());
                        } else {
                            cell.setCellValue("");
                        }
                        if(j == (kjrq.length-1) ){
                            CellRangeAddress cra=new CellRangeAddress((sheet.getPhysicalNumberOfRows()-kjrq.length), sheet.getPhysicalNumberOfRows()-1, k, k);
                            sheet.addMergedRegion(cra);
                        }
                    }
                }
            }
        }

        try {
            FileOutputStream outputStream;

            /**  输出信息，导出excel
             *
             *  response.setContentType("application/vnd.ms-excel;charset=UTF-8");
             response.setHeader("Content-Type", "application/octet-stream");
             *  OutputStream out = response.getOutputStream();
             wb.write(out);
             out.close();
             */
            try {
                outputStream = new FileOutputStream("e://"+ excelName +".xls");
                bookWorkbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (FileNotFoundException e) {
                System.err.println("获取不到位置");
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {

        }
    }
}