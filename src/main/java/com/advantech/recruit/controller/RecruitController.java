package com.advantech.recruit.controller;

import com.advantech.recruit.entity.Recruit;
import com.advantech.recruit.entity.RecruitDto;
import com.advantech.recruit.servcie.ExcelSheetService;
import com.advantech.recruit.servcie.PictureService;
import com.advantech.recruit.servcie.RecruitService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RecruitController {

    @Autowired
    private RecruitService recruitService;


    @Autowired
    private ExcelSheetService  excelSheetService;

    @Autowired
    private PictureService pictureService;


    @GetMapping("/page")
    public String back(){
        return "index";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/report")
    public String report(){
        return "report";
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/save",method =RequestMethod.POST )
    public void save(RecruitDto recruit, HttpServletRequest request){
        Recruit save = null;
        try {
            save = recruitService.save(recruit,request);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/find")
    @ResponseBody
    public ResponseEntity<?> find(){
        List<Recruit> all= null;
        try {
           all = recruitService.findAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        if(all == null) all = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    /**
     * 导出
     */
    @GetMapping("/excel")
    @ResponseBody
    public void doExport(HttpServletRequest request,
                         HttpServletResponse response){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            File file = excelSheetService.createExcel();
            String aFileName = file.getName();
            request.setCharacterEncoding("UTF-8");
            String agent = request.getHeader("User-Agent").toUpperCase();
            if ((agent.indexOf("MSIE") > 0) || ((agent.indexOf("RV") != -1) && (agent.indexOf("FIREFOX") == -1)))
                aFileName = URLEncoder.encode(aFileName, "UTF-8");
            else {
                aFileName = new String(aFileName.getBytes("UTF-8"), "ISO8859-1");
            }
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + aFileName);
            response.setHeader("Content-Length", String.valueOf(file.length()));
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
                bos.write(buff, 0, bytesRead);
            System.out.println("success");
            bos.flush();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("导出文件失败！");
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
//                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("导出文件关闭流出错");
//               LOGGER.error("导出文件关闭流出错！", e);
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
        System.out.println("门店图片上传！！！");
        JSONObject jsonObject=new JSONObject();
        try{
            pictureService.saveOne(file,request);
//            restaurantPhotoService.saveOne(file,request,displayArea);
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "0");
            jsonObject.put("ErrorMessage", "");
        }catch (Exception e) {
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }

        return jsonObject.toJSONString();
    }




}
