package com.topjet.common;

import com.topjet.manage.constants.WebConstants;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.model.SysUserModel;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.TruckLengthDictionaryModel;
import com.topjet.manage.domain.model.TruckTypeDictionaryModel;

import java.util.List;


public class SessionUtils {
	
	/**
	  * 设置session的值
	  * @param key
	  * @param value
	  */
	 public static void setAttr(String key,Object value){
		 BaseController.getSession().setAttribute(key, value);
	 }
	 
	 
	 /**
	  * 获取session的值
	  * @param key
	  */
	 public static Object getAttr(String key){
		 return BaseController.getSession().getAttribute(key);
	 }
	 
	 /**
	  * 删除Session值
	  * @param key
	  */
	 public static void removeAttr(String key){
		 BaseController.getSession().removeAttribute(key);
	 }
	
	/**
	 * 获取当前用户session对象
	 * 
	 * @return
	 */
	public static SysUserModel getSysUserSession() {
		return (SysUserModel) BaseController.getSession().getAttribute(WebConstants.SESSION_USER);
	}
	
	/**
	 * 设置当前用户session对象
	 * 
	 */
	public static void setSysUserSession(SysUserModel sysUserModel) {
		setAttr(WebConstants.SESSION_USER, sysUserModel);
	}
	
	/**
	 * 删除当前用户session对象
	 * 
	 */
	public static void removeSysUserSession() {
		removeAttr(WebConstants.SESSION_USER);
		removeAttr(WebConstants.TASK_SESSION_USER);
	}

	/**
	 * 如果当前用户为分配任务设置当前用户session对象
	 *
	 */
	public static void setAssignSysUserSession(List<TaskBucketInfoModel> tbim) {
		setAttr(WebConstants.TASK_SESSION_USER, tbim);
	}
	/**
	 * 获取当前用户session对象
	 *
	 * @return
	 */
	public static List<TaskBucketInfoModel> getAssignSysUserSession() {
		return (List<TaskBucketInfoModel>) BaseController.getSession().getAttribute(WebConstants.TASK_SESSION_USER);
	}

	/**
	 * 设置车型参数
	 *
	 */
	public static void setTruckType(List<TruckTypeDictionaryModel> truckType) {
		setAttr(WebConstants.TRUCK_TYPE_SESSION,truckType);
	}

	/**
	 * 获取车型参数
	 */
	public static List<TruckTypeDictionaryModel> getTruckType(){
		return (List<TruckTypeDictionaryModel>)BaseController.getSession().getAttribute(WebConstants.TRUCK_TYPE_SESSION);
	}

	public static void removeTypeSession(){
		removeAttr(WebConstants.TRUCK_TYPE_SESSION);
	}

	public static void removeLengthSession(){
		removeAttr(WebConstants.TRUCK_LENGTH_SESSION);
	}

	/**
	 * 设置车长参数
	 *
	 */
	public static void setTruckLength(List<TruckLengthDictionaryModel> truckLength) {
		setAttr(WebConstants.TRUCK_LENGTH_SESSION, truckLength);
	}

	/**
	 * 获取车长参数
	 */
	public static List<TruckLengthDictionaryModel> getTruckLength() {
		return (List<TruckLengthDictionaryModel>) BaseController.getSession().getAttribute(WebConstants.TRUCK_LENGTH_SESSION);
	}

}
