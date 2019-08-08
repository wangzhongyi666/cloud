package com.cloud.framework.base.utils;

import java.util.List;

public class DataResult {

	// 操作对象
	private Object obj;
	// 是否成功
	private Boolean result;
	// 备注
	private String remark;
	// 记录总数
	private Long count;
	private Long total;
	// 查询结果
	private List results;
	private List rows;

	/**
	 * 数据操作结果
	 * 
	 * @param result
	 *            操作结果
	 */
	public DataResult(Boolean result) {
		this.result = result;
	}

	/**
	 * 数据操作结果
	 * 
	 * @param results
	 *            数据列表
	 */
	public DataResult(List results) {
		this.results = results;
	}

	/**
	 * 数据操作结果
	 * 
	 * @param count
	 *            数据数量
	 */
	public DataResult(Long count) {
		this.count = count;
	}

	/**
	 * 数据操作结果
	 * 
	 * @param count
	 *            数据数量
	 * @param results
	 *            数据列表
	 */
	public DataResult(Long count, List results) {
		this.count = count;
		this.results = results;
	}

	/**
	 * 数据操作结果
	 * 
	 * @param obj
	 *            操作实体
	 * @param result
	 *            操作结果
	 */
	public DataResult(Object obj, Boolean result) {
		this.obj = obj;
		this.result = result;
	}

	/**
	 * 数据操作结果
	 * 
	 * @param result
	 *            操作结果
	 * @param remark
	 *            结果描述
	 */
	public DataResult(Boolean result, String remark) {
		this.result = result;

	}

	/**
	 * 数据操作结果
	 * 
	 * @param obj
	 *            操作实体
	 * @param result
	 *            操作结果
	 * @param remark
	 *            结果描述
	 */
	public DataResult(Object obj, Boolean result, String remark) {
		this.obj = obj;
		this.result = result;
	}

	/**
	 * 数据操作结果
	 * 
	 * @param obj
	 *            操作实体
	 * @param result
	 *            操作结果
	 * @param remark
	 *            结果描述
	 * @param count
	 *            数据数量
	 * @param results
	 *            数据列表
	 */
	public DataResult(Object obj, Boolean result, String remark, Long count, List results) {
		this.obj = obj;
		this.result = result;
		this.count = count;
		this.results = results;
	}

	public DataResult() {
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
