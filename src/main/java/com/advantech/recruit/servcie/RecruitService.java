package com.advantech.recruit.servcie;

import com.advantech.recruit.dao.RecruitRespositry;
import com.advantech.recruit.entity.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitService {

    @Autowired
    private RecruitRespositry recruitRespositry;


    public Recruit save(Recruit recruit) throws Exception{
        return recruitRespositry.save(recruit);
    }

    public List<Recruit> findAll() throws Exception{
       return recruitRespositry.findAll();
    }



}
