package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.AppSlideModel;

public interface AppSlideModelEMapper extends BaseEMapper<AppSlideModel>{

    AppSlideModel selectByPrimaryKey(Integer id);

}