package com.larry.utils;

import com.larry.consts.FormNumConstants;

public enum FormNumTypeEnum {
	/**
	 * 应付单单号： 固定前缀：YF 时间格式：yyyyMMdd 流水号长度：7(当单日单据较多时可根据业务适当增加流水号长度) 随机数长度：3 总长度：20
	 */
	REC_ORDER("REC", FormNumConstants.SERIAL_YYYYMMDD_PREFIX, 7, 3, 20),

	/**
	 * 付款单单号： 固定前缀：FK 时间格式：yyyyMMdd 流水号长度：7 随机数长度：3 总长度：20
	 */
	FK_ORDER("FK", FormNumConstants.SERIAL_YYYYMMDD_PREFIX, 7, 3, 20),

	/**
	 * 测试单单号： 固定前缀："" 时间格式：yyyyMMdd 流水号长度：10 随机数长度：0 总长度：20
	 */
	TEST_ORDER("te", FormNumConstants.SERIAL_YYYYMMDD_PREFIX, 10, 0, 20),;

	/**
	 * 单号前缀 为空时填""
	 */
	private String prefix;

	/**
	 * 时间格式表达式 例如：yyyyMMdd
	 */
	private String datePattern;

	/**
	 * 流水号长度
	 */
	private Integer serialLength;
	/**
	 * 随机数长度
	 */
	private Integer randomLength;

	/**
	 * 总长度
	 */
	private Integer totalLength;

	FormNumTypeEnum(String prefix, String datePattern, Integer serialLength, Integer randomLength,
			Integer totalLength) {
		this.prefix = prefix;
		this.datePattern = datePattern;
		this.serialLength = serialLength;
		this.randomLength = randomLength;
		this.totalLength = totalLength;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	public Integer getSerialLength() {
		return serialLength;
	}

	public void setSerialLength(Integer serialLength) {
		this.serialLength = serialLength;
	}

	public Integer getRandomLength() {
		return randomLength;
	}

	public void setRandomLength(Integer randomLength) {
		this.randomLength = randomLength;
	}

	public Integer getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(Integer totalLength) {
		this.totalLength = totalLength;
	}

}
