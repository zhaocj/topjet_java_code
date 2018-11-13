package com.topjet.manage.service;

import com.topjet.manage.domain.model.CheckCodeModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-23 11:00
 */

public interface CheckCodeService {

    List<CheckCodeModel> listCheckCode(CheckCodeModel checkCodeModel);

    Integer getCountByEntity(CheckCodeModel checkCodeModel);

}
