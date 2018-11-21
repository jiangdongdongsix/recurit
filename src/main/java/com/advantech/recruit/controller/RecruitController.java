package com.advantech.recruit.controller;

import com.advantech.recruit.entity.Recruit;
import com.advantech.recruit.entity.RecruitDto;
import com.advantech.recruit.servcie.ExcelSheetService;
import com.advantech.recruit.servcie.PictureService;
import com.advantech.recruit.servcie.RecruitService;
import com.advantech.recruit.utils.PositionUtils;
import com.advantech.recruit.utils.StringUtils;
import com.advantech.recruit.utils.ZipUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author dongdong.jiang
 * recruit
 */
@Controller
public class RecruitController {

    @Autowired
    private RecruitService recruitService;
    @Autowired
    private ExcelSheetService  excelSheetService;

    @Value("${openstack.recruit.path}")
    private String recruitUrl;

    /**
     * 返回主页面
     * @return
     */
    @GetMapping("/index")
    public String back(){
        return "index";
    }
    @GetMapping("/result")
    public String hello(){
        return "result";
    }
    @GetMapping("/report")
    public String report(){
        return "report";
    }

    @RequestMapping(value = "/save",method =RequestMethod.POST )
    public String save(
                     @RequestParam(value = "name") String name,
                     @RequestParam(value = "phone") String phone,
                     @RequestParam(value = "credit") String credit,
                     @RequestParam(value = "email") String email,
                     @RequestParam(value = "school") String school,
                     @RequestParam(value = "major") String major,
                     @RequestParam(value = "gender") String gender,
                     @RequestParam(value = "background", required = false) String background,
                     @RequestParam(value = "des", required = false)String des,
                     @RequestParam(value = "file", required = false) MultipartFile file,
                     @RequestParam(value = "position", required = false) String position,
                     HttpServletRequest request){
        System.out.println(file.getName()+"开始上传");
        System.out.println(file.getOriginalFilename()+"开始上传");
        Recruit recruit = new Recruit();
        recruit.setName(name);
        recruit.setPhone(phone);
        recruit.setCredit(credit);
        recruit.setBackground(background);
        recruit.setGender(gender);
        recruit.setEmail(email);
        recruit.setSchool(school);
        recruit.setMajor(major);
        recruit.setDescription(des);
        if(StringUtils.isBlank(position)){
            recruit.setPosition(PositionUtils.switchPosition("9"));
        }else{
            recruit.setPosition(PositionUtils.switchPosition(position));
        }
        System.out.println(recruit.toString());
        try {
             recruitService.save(recruit,file,request);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:result";
    }

    /**
     * 导出
     */
    @GetMapping("/excel")
    @ResponseBody
    public void doExport(HttpServletRequest request,
                         HttpServletResponse response){
        try{
            File file = excelSheetService.createExcel();
            exportFile(file,request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 按岗位返回下载的简历bao
     * 0：前端开发  web app
     * 1：java开发  java development
     * 2：云平台开发工程师 cloud development
     * 3：软件测试   software test
     * 4：软件产品经理 pm
     * 5: 云平台应用工程师 cloud application
     * @param position  投递岗位
     * @param request
     * @param response
     */
    @GetMapping("/upload")
    @ResponseBody
    public void upload(@RequestParam(value = "position", defaultValue = "") String position,
                         HttpServletRequest request,
                         HttpServletResponse response){
        String localPath;
        try{
            String s = PositionUtils.switchPosition(position);
            if("".equals(position)){
                localPath = recruitUrl;
            }else {
                localPath = recruitUrl +"/"+s;
            }
            File file = ZipUtils.Copy2Demo(localPath,s);
            exportFile(file,request,response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @GetMapping("/upload/all")
    @ResponseBody
    public void upload(HttpServletRequest request,
                       HttpServletResponse response){
        try{
            File file = ZipUtils.Copy2Demo(recruitUrl,"resume");
            exportFile(file,request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 导出文件并通知浏览器下载
     * @param file
     * @param request
     * @param response
     * @throws Exception
     */
    private void exportFile(File file, HttpServletRequest request,HttpServletResponse response) throws Exception{
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            String aFileName = file.getName();
            request.setCharacterEncoding("UTF-8");
            String agent = request.getHeader("User-Agent").toUpperCase();
            if ((agent.indexOf("MSIE") > 0) || ((agent.indexOf("RV") != -1) && (agent.indexOf("FIREFOX") == -1))) {
                aFileName = URLEncoder.encode(aFileName, "UTF-8");
            } else {
                aFileName = new String(aFileName.getBytes("UTF-8"), "ISO8859-1");
            }
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + aFileName);
            response.setHeader("Content-Length", String.valueOf(file.length()));
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            System.out.println("success");
            bos.flush();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("导出文件关闭流出错");
            }
        }
    }
}
