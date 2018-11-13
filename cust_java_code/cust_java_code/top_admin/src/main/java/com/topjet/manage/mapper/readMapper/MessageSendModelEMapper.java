package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.MessageSendModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-15 9:40
 */

@Mapper
public interface MessageSendModelEMapper {

    @Select("SELECT * FROM messageSend WHERE deleted = 0 ORDER BY id DESC LIMIT #{offset}, #{limit}")
    List<MessageSendModel> listMessageSend(MessageSendModel model);

    @Select("SELECT COUNT(id) FROM messageSend WHERE deleted = #{deleted}")
    int countMessageSend(@Param("deleted") Integer deleted);

    @Select("SELECT * FROM messageSend WHERE id = #{id} AND deleted = 0")
    MessageSendModel getMessageSendDetail(@Param("id") Integer id);

    @Select("SELECT * FROM messageSend WHERE id = #{id} AND deleted = 0 AND version = #{version}")
    MessageSendModel selectMessageSend(@Param("id") Integer id, @Param("version") Integer version);

    @Select("SELECT * FROM messageSend WHERE id = #{id} AND deleted = 0")
    MessageSendModel selectMessageSendModelByPrimarykey(@Param("id") Integer id);

}
