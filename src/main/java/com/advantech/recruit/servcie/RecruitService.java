package com.advantech.recruit.servcie;

import com.advantech.recruit.dao.RecruitRespositry;
import com.advantech.recruit.entity.Recruit;
import com.advantech.recruit.entity.RecruitDto;
import com.advantech.recruit.utils.StringUtils;
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
        //获取服务器下的绝对路径
        String localPath = request.getSession().getServletContext().getRealPath("/"+recruit.getPosition());
        File dir = new File(localPath);
        if(!dir.exists()) {
            // 如果文件夹不存在，就创建文件夹。
            dir.mkdir();
        }
        try {
            //判断文件是否为空
            if(!StringUtils.isBlank(file.getOriginalFilename())){
                //重构文件名 时间戳+原始名
                String fileName = TimeUtils.getCurrentTimeType2() + "_" + file.getOriginalFilename();
                //将文件下入新的文件并保存的指定的路径下
                file.transferTo(new File(localPath+"\\"+fileName));
                recruit.setResumeUrl(request.getServletContext().getContextPath()+"/"+recruit.getPosition()+"/"+fileName);
            }
            recruit.setCreateTime(TimeUtils.getCurrentTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recruitRespositry.save(recruit);

    }

    public List<Recruit> findAll() throws Exception{
       return recruitRespositry.findAllByIddesc();
    }



}
