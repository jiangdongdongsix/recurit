package com.advantech.recruit.dao;

import com.advantech.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitRespositry extends JpaRepository<Recruit,Long> {
}
