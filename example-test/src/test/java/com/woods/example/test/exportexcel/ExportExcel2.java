package com.woods.example.test.exportexcel;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//import com.sun.java_cup.internal.internal_error;

public class ExportExcel2 {

    public static void export2(String excelName, List<Map<String, Object>> list, String[] valueFields, String[] subValueFields, String[] titleFields) {

        HSSFWorkbook bookWorkbook = new HSSFWorkbook();// 创建excel文件
        HSSFSheet sheet = bookWorkbook.createSheet();

        HSSFCell cell;
        HSSFRow row;

        //表头样式
        HSSFCellStyle titleStyle = bookWorkbook.createCellStyle();// 创建一个单元的样式
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置水平居中
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        // 设置字体
        HSSFFont font = bookWorkbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        titleStyle.setFont(font);
        //内容样式
        HSSFCellStyle contentStyle = bookWorkbook.createCellStyle();// 创建一个单元的样式
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置水平居中
        contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        //创建表头行
        row = sheet.createRow(0);
        //填充表头
        for (int i = 0; i < titleFields.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(titleFields[i]);
            cell.setCellStyle(titleStyle);
            // 设置每列的宽度自适应
            sheet.autoSizeColumn(i);
        }

        //放入信息，一般为list
        //遍历list
        for(int i = 0; i < list.size(); i++){
            Map<String, Object> map = list.get(i);
            List<Map<String, Object>> ms = (List<Map<String, Object>>)map.get("ms");

            for (int j = 0; j < ms.size(); j++){
                row=sheet.createRow(sheet.getPhysicalNumberOfRows());
                sheet.autoSizeColumn(sheet.getPhysicalNumberOfRows());

                Map<String, Object> m = ms.get(j);

                int y = 0;//控制集合循环
                for (int k = 0; k < valueFields.length; k++){

                    cell=row.createCell(k + y);
                    cell.setCellStyle(contentStyle);
                    sheet.autoSizeColumn(k+y);
                    if(map.get(valueFields[k]) instanceof List){
                        for (y = 0; y < subValueFields.length; y++){

                            cell=row.createCell(k+y);
                            cell.setCellStyle(contentStyle);
                            sheet.autoSizeColumn(k+y);
                            Object cellVale = m.get(subValueFields[y]);
                            if(null != cellVale && StringUtils.isNotEmpty(cellVale.toString())){
                                cell.setCellValue(Double.valueOf(cellVale.toString()));
                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            }
                        }
                        y = subValueFields.length-1;
                    } else {
                        Object cellVale = map.get(valueFields[k]);
                        if(null != cellVale && StringUtils.isNotEmpty(cellVale.toString())){
                            row = sheet.getRow(sheet.getLastRowNum()-j);
                            cell = row.getCell(k+y);
                            cell.setCellValue(Double.valueOf(cellVale.toString()));
                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            row = sheet.getRow(sheet.getLastRowNum());
                        }
                        //控制跨行
                        if(j == (ms.size() - 1) ){
                            CellRangeAddress cra = new CellRangeAddress((sheet.getPhysicalNumberOfRows() - ms.size()), sheet.getPhysicalNumberOfRows()-1, k+y, k+y);
                            sheet.addMergedRegion(cra);
                        }
                        if(k == (valueFields.length - 1) ){
                            y = 0;
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