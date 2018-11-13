package com.topjet.util;


import com.topjet.user.constant.CityConstant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Filename : FileExportUtil.java
 * @Package : com.topjet.common.util
 * @version : 1.0
 * @Description : 后台管理框架1.0版本
 * @Copyright : Copyright (c)2015-2015
 * @Company : 上海拓景信息科技有限公司 http://www.566560.com
 * @author : 唐永梦（silence）
 * @Create Date : 2015年10月26日
 * @Modified By : 唐永梦（silence）
 * @Modified Date : 2015年10月26日
 * @DescriptionClass : 文件导出工具类 (说明类的作用)
 */
public class FileExportUtil {

	/**
	 * 导出文件到指定路径
	 * 
	 * @param path
	 *            路径
	 * @param fileName
	 *            文件名
	 * @param content
	 *            文件内容
	 */
	public static void fileExport(String path, String fileName, String content) {
		if (isBlank(path) || isBlank(fileName) || isBlank(content)) {
			return;
		}
		try {
			File fpath = new File(path);
			if(!fileName.equals(CityConstant.CITY_EXPORT_JSON_FILENAME_BACK_PROJECT)){
				if (!fpath.exists()) {
					fpath.mkdirs();
				}
			}
			
			FileWriter fos = new FileWriter(path + "/" + fileName);
			BufferedWriter bw = new BufferedWriter(fos);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

/*	*//**
	 * Windows生成文件到指定地址
	 * @param path
	 * @param fileName
	 * @param content
	 * @throws IOException
	 *//*
	public static void fileExportWin(String path, String fileName,
			String content) throws IOException {
		FileWriter fos = new FileWriter(path+fileName);
		BufferedWriter bw = new BufferedWriter(fos);
		bw.write(content);
		bw.close();
	}*/

	/**
	 * 判断字符串是否为null或者空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (null == str)
			return true;
		if ("".equals(str.replaceAll("　", " ").trim()))
			return true;
		return false;
	}

}
