package com.topjet.manage.controller;

import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.CheckCodeModel;
import com.topjet.manage.service.CheckCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-23 10:58
 */

@Controller
@Scope("prototype")
@RequestMapping("/checkCode")
public class CheckCodeController {

    @Autowired
    private CheckCodeService checkCodeService;

    @ResponseBody
    @RequestMapping("/list")
    public Object list(CheckCodeModel checkCodeModel, HttpServletRequest request) {
        PaginationBean pb = new PaginationBean();
        if(StringUtils.isBlank(checkCodeModel.getMobileNo())){
            pb.setTotal(0);
            return pb;
        }
        List<CheckCodeModel> checkCodeModelList = checkCodeService.listCheckCode(checkCodeModel);
        int total = checkCodeService.getCountByEntity(checkCodeModel);

        pb.setRows(checkCodeModelList);
        pb.setTotal(total);
        return pb;
    }

}
