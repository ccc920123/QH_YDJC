package com.scxd.common;

  
/**
 * 文件描述：字符串工具类
 * 作者：齐遥遥
 * 时间：2017/9/1
 * 公司：数字灯塔
 * 部门：技术部
 * 修改人：
 * 修改时间：
 */
public class StringUtil {

	/**
	 * 给定字符串是否为空或空串
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && str.length() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 给定字符串是否为空或空串
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null && str.length() != 0) {
			return false;
		}
		return true;
	}
}
