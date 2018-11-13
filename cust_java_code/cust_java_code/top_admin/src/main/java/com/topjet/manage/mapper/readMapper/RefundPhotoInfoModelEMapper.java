package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.RefundPhotoInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liyj on 2017/9/25.
 */
@Mapper
@Repository
public interface RefundPhotoInfoModelEMapper {

    //查询refundPhotoInfo表 根据refundType = 0 和refundId 查询
    @Select("SELECT r.id,r.refundId,r.refundType,r.pictureUrl,r.deleted from orderDB.refundPhotoInfo r where r.deleted = 0 and r.refundType = 0 and r.refundId = #{refundId}")
    public List<RefundPhotoInfoModel> getRefundPhontList0(@Param("refundId") Integer refundId);

    //查询refundPhotoInfo表 根据refundType=1和refundId 查询
    @Select("SELECT r.id,r.refundId,r.refundType,r.pictureUrl,r.deleted from orderDB.refundPhotoInfo r where r.deleted = 0 and r.refundType = 1 and r.refundId = #{refundId}")
    public List<RefundPhotoInfoModel> getRefundPhontList1(@Param("refundId") Integer refundId);

}
