package com.test;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class JOrderBank {
	private Long order_id;
	private String card_no;
	private String card_pwd;
	private BigDecimal card_value;
	private BigDecimal charge_value;
	private BigDecimal succ_value;
	private String error_code;
	private String error_msg;
	private Timestamp last_modifytime;
	private String remark;
	private String settlement_date;
	private String transaction_id;

	/**
	 * @return the order_id
	 */
	public Long getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id
	 *            the order_id to set
	 */
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	/**
	 * @return the card_no
	 */
	public String getCard_no() {
		return card_no;
	}

	/**
	 * @param card_no
	 *            the card_no to set
	 */
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	/**
	 * @return the card_pwd
	 */
	public String getCard_pwd() {
		return card_pwd;
	}

	/**
	 * @param card_pwd
	 *            the card_pwd to set
	 */
	public void setCard_pwd(String card_pwd) {
		this.card_pwd = card_pwd;
	}

	/**
	 * @return the card_value
	 */
	public BigDecimal getCard_value() {
		return card_value;
	}

	/**
	 * @param card_value
	 *            the card_value to set
	 */
	public void setCard_value(BigDecimal card_value) {
		this.card_value = card_value;
	}

	/**
	 * @return the charge_value
	 */
	public BigDecimal getCharge_value() {
		return charge_value;
	}

	/**
	 * @param charge_value
	 *            the charge_value to set
	 */
	public void setCharge_value(BigDecimal charge_value) {
		this.charge_value = charge_value;
	}

	/**
	 * @return the succ_value
	 */
	public BigDecimal getSucc_value() {
		return succ_value;
	}

	/**
	 * @param succ_value
	 *            the succ_value to set
	 */
	public void setSucc_value(BigDecimal succ_value) {
		this.succ_value = succ_value;
	}

	/**
	 * @return the error_code
	 */
	public String getError_code() {
		return error_code;
	}

	/**
	 * @param error_code
	 *            the error_code to set
	 */
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	/**
	 * @return the error_msg
	 */
	public String getError_msg() {
		return error_msg;
	}

	/**
	 * @param error_msg
	 *            the error_msg to set
	 */
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	/**
	 * @return the last_modifytime
	 */
	public Timestamp getLast_modifytime() {
		return last_modifytime;
	}

	/**
	 * @param last_modifytime
	 *            the last_modifytime to set
	 */
	public void setLast_modifytime(Timestamp last_modifytime) {
		this.last_modifytime = last_modifytime;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the settlement_date
	 */
	public String getSettlement_date() {
		return settlement_date;
	}

	/**
	 * @param settlement_date
	 *            the settlement_date to set
	 */
	public void setSettlement_date(String settlement_date) {
		this.settlement_date = settlement_date;
	}

	/**
	 * @return the transaction_id
	 */
	public String getTransaction_id() {
		return transaction_id;
	}

	/**
	 * @param transaction_id
	 *            the transaction_id to set
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

}
