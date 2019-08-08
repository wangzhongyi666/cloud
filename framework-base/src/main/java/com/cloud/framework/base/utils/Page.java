package com.cloud.framework.base.utils;

import java.util.List;


public class Page<T> {
	private List<T> list; // 要返回的某一页的记录列表
	private int allRow; // 总记录数
	private int totalPage; // 总页数
	private int currentPage; // 当前页
	private int pageSize; // 每页记录数

	private boolean isFirstPage; // 是否为第一页
	private boolean isLastPage; // 是否为最后一页
	private boolean hasPreviousPage; // 是否有前一页
	private boolean hasNextPage; // 是否有下一页

	public Page() {
		super();
	}

	public Page(List<T> list, int currentPage, int pageSize, int allRow) {
		super();
		this.list = list;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.allRow = allRow;
		this.totalPage = allRow / pageSize + ((allRow % pageSize >0) == true ? 1 : 0);
	}

	public List<T> getList() {
		return list;
	}
    
	public void setList(List<T> list) {
		this.list = list;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 初始化分页信息
	 */
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
	}

	/**
	 * 以下判断页的信息,只需getter方法(is方法)即可
	 * 
	 * @return
	 */

	public boolean isFirstPage() {
		return currentPage == 1; // 如是当前页是第1页
	}

	public boolean isLastPage() {
		return currentPage == totalPage; // 如果当前页是最后一页
	}

	public boolean isHasPreviousPage() {
		return currentPage != 1; // 只要当前页不是第1页
	}

	public boolean isHasNextPage() {
		return currentPage != totalPage; // 只要当前页不是最后1页
	}

	/**
	 * 计算总页数,静态方法,供外部直接通过类名调用
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param allRow
	 *            总记录数
	 * @return 总页数
	 */
	public static int countTotalPage(final int pageSize, final int allRow) {
		int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow / pageSize + 1;
		return totalPage;
	}

	/**
	 * 计算当前页开始记录
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param currentPage
	 *            当前第几页
	 * @return 当前页开始记录号
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		int curpage = (currentPage == 0 ? 1 : currentPage);
		final int offset = pageSize * (curpage - 1);
		return offset;
	}

	/**
	 * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
	 * 
	 * @parampage
	 *            传入的参数(可能为空,即0,则返回1)
	 * @return 当前页
	 */
	public static int countCurrentPage(int number) {
		final int curPage = (number == 0 ? 1 : number);
		return curPage;
	}

}