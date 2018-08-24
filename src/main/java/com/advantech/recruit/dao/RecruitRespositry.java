package com.advantech.recruit.dao;

import com.advantech.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitRespositry extends JpaRepository<Recruit,Long> {


    @Query(value = "select * from recruit order by id DESC",nativeQuery = true)
    List<Recruit> findAllByIddesc();
}
