package com.advantech.recruit.servcie;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
public class PictureService {


    public void saveOne(MultipartFile file, HttpServletRequest request){

        String localPath = request.getSession().getServletContext().getRealPath("/upload");
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        File dir = new File(localPath);
        if(!dir.exists()) {
            // 如果文件夹不存在，就创建文件夹。
            dir.mkdir();
        }
        //保存
        try {
            file.transferTo(new File(localPath+"\\"+fileName));
            System.out.println(
                request.getServletContext().getContextPath()+"/upload/"+fileName
            );
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
