package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.RecommendRelationshipInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecommendRelationshipInfoModelEMapper {

    @Select("SELECT * FROM userDB.recommendRelationship WHERE id = #{id}")
    RecommendRelationshipInfoModel selectByPrimarykey(Integer id);


    @Select("SELECT * FROM userDB.recommendRelationship WHERE recommendedUserId = #{recommendedUserId}")
    List<RecommendRelationshipInfoModel> selectRecomRelByRecomedId(Integer recommendedUserId);


}