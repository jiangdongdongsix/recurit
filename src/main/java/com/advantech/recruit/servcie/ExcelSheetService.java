package com.advantech.recruit.servcie;

import com.advantech.recruit.entity.Recruit;
import com.advantech.recruit.utils.JsonbUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class ExcelSheetService {

    @Autowired
    private RecruitService recruitService;

    public File  createExcel() throws Exception{
        int idx=1;
        HSSFWorkbook book =new HSSFWorkbook();
        HSSFSheet sheet=book.createSheet("recruit");
        List<Recruit> all = recruitService.findAll();
        String[] filedName = getFiledName(new Recruit());
        int col = 10;
        //生成表单的第一行，表头
        HSSFRow row0 =sheet.createRow(0);
        for(int i=0;i<col;i++){
            HSSFCell cell = row0.createCell(i);
            cell.setCellValue(filedName[i]);
        }

        for(Recruit r: all){
            HSSFRow row =sheet.createRow(idx++);//一行创建完成，开始创建列
            Map<String, Object> map = JsonbUtils.objectToMap(r);
            for(int i =0 ;i<col;i++){
                HSSFCell cell =row.createCell(i);
                String str = (String)map.get(filedName[i]);
                cell.setCellValue(str);
            }
        }
        File file = new File("db.xls");
        FileOutputStream out =new FileOutputStream(file);
        book.write(out);//采用book来进行写</span>
        return file;
    }




    private String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            System.out.println(fields[i].getType());
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }
}
