package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.MessageSendModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-15 9:43
 */

@Mapper
public interface MessageSendModelMapper {

    int insert(MessageSendModel model);

    @Update("UPDATE messageSend SET deleted = 1 WHERE id = #{id}")
    int deleteMessageSend(@Param("id") Integer id);

    int update(MessageSendModel messageSendModel);
}
