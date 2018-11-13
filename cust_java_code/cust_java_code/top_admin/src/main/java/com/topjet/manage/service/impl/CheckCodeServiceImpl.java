package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.CheckCodeModel;
import com.topjet.manage.mapper.readMapper.CheckCodeModelEMapper;
import com.topjet.manage.service.CheckCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-23 11:02
 */

@Service
@Transactional
public class CheckCodeServiceImpl implements CheckCodeService{
    @Autowired
    private CheckCodeModelEMapper checkCodeModelEMapper;

    /**
     * 分页获取验证码数据
     * @param checkCodeModel
     * @return
     */
    @Override
    public List<CheckCodeModel> listCheckCode(CheckCodeModel checkCodeModel) {
        return checkCodeModelEMapper.selectPageListByEntity(checkCodeModel);
    }

    /**
     * 根据实体类查询验证码数量
     * @param checkCodeModel
     * @return
     */
    @Override
    public Integer getCountByEntity(CheckCodeModel checkCodeModel) {
        return checkCodeModelEMapper.getCountByEntity(checkCodeModel);
    }

}
