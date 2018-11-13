package com.topjet.constants;

public class BonusAuditStatus {

	/**********************审核进度********************************/

	public static final Integer BONUS_AUDIT_FIRST_PROCESS = 1;

	public static final Integer BONUS_AUDIT_SECOND_PROCESS = 2;

	public static final Integer BONUS_AUDIT_THIRD_PROCESS = 3;


	/**********************审核状态********************************/
	/*
	 * 未审核
	 */
	public static final Integer BONUS_NOT＿AUDIT = 0;

	/*
	 * 一审审核通过
	 */
	public static final Integer BONUS_AUDIT_FIRST_PASS = 1;

	/*
	 * 一审待定
	 */
	public static final Integer BONUS_AUDIT_FIRST_UN_CERTAIN = 2;
	/*
	 * 一审驳回
	 */
	public static final Integer BONUS_AUDIT_FIRST_FAIL = 3;

	/*
	 * 二审通过
	 */
	public static final Integer BONUS_AUDIT_SECOND_PASS = 4;
	/*
	 * 二审驳回
	 */
	public static final Integer BONUS_AUDIT_SECOND_FAIL = 5;

	/*
	 * 三审通过
	 */
	public static final Integer BONUS_AUDIT_THIRD_PASS = 6;

	/*
	 * 三审驳回
	 */
	public static final Integer BONUS_AUDIT_THIRD_FAIL = 7;

	/*
	 * 三审其它
	 */
	public static final Integer BONUS_AUDIT_THIRD_OTHER = 8;



}
