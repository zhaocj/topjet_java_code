package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.OwnerTruckInfoModel;
import com.topjet.manage.domain.vo.OwnerTruckListQuery;
import com.topjet.manage.domain.vo.OwnerTruckListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerTruckInfoModelEMapper extends BaseEMapper<OwnerTruckInfoModel>{

    List<OwnerTruckListVO> listOwnerTruck(OwnerTruckListQuery query);

    Integer countOwnerTruck(OwnerTruckListQuery query);

}