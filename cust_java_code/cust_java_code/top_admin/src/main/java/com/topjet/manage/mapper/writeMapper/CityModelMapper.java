package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.CityModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface CityModelMapper extends BaseMapper<CityModel>{

}