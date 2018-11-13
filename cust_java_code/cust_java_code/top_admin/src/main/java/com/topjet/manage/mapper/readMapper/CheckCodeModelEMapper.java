package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.CheckCodeModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CheckCodeModelEMapper extends BaseEMapper<CheckCodeModel> {

    List<CheckCodeModel> selectPageListByEntity(CheckCodeModel checkCodeModel);

    Integer getCountByEntity(CheckCodeModel checkCodeModel);
}