package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.WhiteUserInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by tsj010 on 2018/4/25.
 */
@Mapper
@Repository
public interface WhiteUserInfoModelMapper {


    public void  insert(WhiteUserInfoModel whiteUserInfoModel);
    public void  update(int  id);


}
