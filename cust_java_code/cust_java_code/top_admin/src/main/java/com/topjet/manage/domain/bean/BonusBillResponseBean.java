
package com.topjet.manage.domain.bean;

/**
 * @author        :  zhaocj
 * @Create Date   :  2017年9月03日
 * @function  补贴发放请求钱包response
 */
public class BonusBillResponseBean {
	private int flag;//标识
	private int succeed=0;//成功次数
	private int defeat=0;//失败次数
	private int response=0;//未响应次数
	private int err=0;//异常次数
	
	public int getSucceed() {
		return succeed;
	}
	public void setSucceed(int succeed) {
		this.succeed = succeed;
	}
	public int getDefeat() {
		return defeat;
	}
	public void setDefeat(int defeat) {
		this.defeat = defeat;
	}
	public int getResponse() {
		return response;
	}
	public void setResponse(int response) {
		this.response = response;
	}
	public int getErr() {
		return err;
	}
	public void setErr(int err) {
		this.err = err;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}

}

