package com.advantech.recruit.servcie;

import com.advantech.recruit.dao.RecruitRespositry;
import com.advantech.recruit.entity.Recruit;
import com.advantech.recruit.entity.RecruitDto;
import com.advantech.recruit.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service
public class RecruitService {

    @Autowired
    private RecruitRespositry recruitRespositry;

    public Recruit save(Recruit recruit, MultipartFile file, HttpServletRequest request) throws Exception{
        String localPath = request.getSession().getServletContext().getRealPath("/"+recruit.getPosition());
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
                    request.getServletContext().getContextPath()+"/"+recruit.getPosition()+"/"+fileName
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        recruit.setResumeUrl(request.getServletContext().getContextPath()+"/"+recruit.getPosition()+"/"+fileName);
        recruit.setCreateTime(TimeUtils.getCurrentTime());
        return recruitRespositry.save(recruit);

    }

    public List<Recruit> findAll() throws Exception{
       return recruitRespositry.findAll();
    }



}
