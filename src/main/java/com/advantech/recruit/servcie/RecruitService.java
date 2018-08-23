package com.advantech.recruit.servcie;

import com.advantech.recruit.dao.RecruitRespositry;
import com.advantech.recruit.entity.Recruit;
import com.advantech.recruit.entity.RecruitDto;
import com.advantech.recruit.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service
public class RecruitService {

    @Autowired
    private RecruitRespositry recruitRespositry;


    public Recruit save(RecruitDto recruitDto, HttpServletRequest request) throws Exception{
        Recruit recruit1 = new Recruit();
        String localPath = request.getSession().getServletContext().getRealPath("/upload");
        String fileName = System.currentTimeMillis() + "_" + recruitDto.getFile().getOriginalFilename();
        File dir = new File(localPath);
        if(!dir.exists()) {
            // 如果文件夹不存在，就创建文件夹。
            dir.mkdir();
        }
        //保存
        try {
            recruitDto.getFile().transferTo(new File(localPath+"\\"+fileName));
            System.out.println(
                    request.getServletContext().getContextPath()+"/upload/"+fileName
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        recruit1.setPhone(recruitDto.getTel());
        recruit1.setSchool(recruitDto.getSchool());
        recruit1.setName(recruitDto.getName());
        recruit1.setEmail(recruitDto.getEmail());
        recruit1.setGrander(recruitDto.getEmail());
        recruit1.setUrl(request.getServletContext().getContextPath()+"/upload/"+fileName);
        recruit1.setCreateTime(TimeUtils.getCurrentTime());



        return recruitRespositry.save(recruit1);
    }

    public List<Recruit> findAll() throws Exception{
       return recruitRespositry.findAll();
    }



}
