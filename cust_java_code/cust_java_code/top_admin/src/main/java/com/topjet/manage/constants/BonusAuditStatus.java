package com.topjet.manage.constants;

public class BonusAuditStatus {

	/******************************推荐费补贴审核状态*******************************************/
	/*
	 * 未审核
	 */
	public static final Integer BONUAUDITSTATUS_FIRST_NOTAUDIT = 0;

	/*
	 * 审核通过
	 */
	public static final Integer BONUSAUDITSTATUS_FIRST_PASS = 1;
	/*
	 * 一审假一罚三
	 */
	public static final Integer BONUSAUDITSTATUS_FIRST_JYFS = 2;
	/*
	 * 一审审核驳回
	 */
	public static final Integer BONUSAUDITSTATUS_FIRST_REFUSE = 3;
	/*
	 * 二审通过
	 */
	public static final Integer BONUSSAUDITSTATUS_SECOND_PASS = 4;
	/*
	 * 二审待定
	 */
	public static final Integer BONUSAUDITSTATUS_SECOND_NOT_SURE = 5;
	/*
	 * 三审通过
	 */
	public static final Integer BONUSAUDITSTATUS_THIRD_PASS = 6;
	/*
	 * 三审驳回
	 */
	public static final Integer BONUSAUDITSTATUS_THIRD_REFUSE = 7;
	/*
	 * 一审假一罚三
	 */
	public static final Integer BONUSAUDITSTATUS_THIRD_JYFS = 8;



	/******************************运费补贴审核状态*******************************************/
	/*
	 * 未审核
	 */
	public static final Integer FREIGHT_BONUS_NOT_AUDIT = 0;
	/*
	 * 一审通过
	 */
	public static final Integer FREIGHT_BONUS_FIRST_AUDIT_PASS = 1;
	/*
	 * 一审待定
	 */
	public static final Integer FREIGHT_BONUS_FIRST_AUDIT_UNCERTAIN = 2;
	/*
	 * 一审驳回
	 */
	public static final Integer FREIGHT_BONUS_FIRST_AUDIT_FAIL = 3;
	/*
	 * 二审通过
	 */
	public static final Integer FREIGHT_BONUS_SECOND_AUDIT_PASS = 4;
	/*
	 * 二审驳回
	 */
	public static final Integer FREIGHT_BONUS_SECOND_AUDIT_FAIL = 5;
	/*
	 * 三审通过
	 */
	public static final Integer FREIGHT_BONUS_THIRD_AUDIT_PASS = 6;
	/*
	 * 三审其他
	 */
	public static final Integer FREIGHT_BONUS_THIRD_AUDIT_FAIL = 7;






}
