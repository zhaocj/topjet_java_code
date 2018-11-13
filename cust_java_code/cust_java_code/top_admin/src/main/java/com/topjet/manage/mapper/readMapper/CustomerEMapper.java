package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.CustomerBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerEMapper {
	//客服绩效
	public List<CustomerBean> getCustomerPerformance(CustomerBean customerBean);

	public Integer  getCustomerCount(CustomerBean customerBean);
}