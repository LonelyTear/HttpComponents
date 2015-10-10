package kingtool.pager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Pager implements java.io.Serializable {
	public static final String SKIP_RESULT = "skipResult";
	public static final String MAX_RESULT = "maxResult";
	private static final long serialVersionUID = -153703641828965368L;
	
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	/**
	 * 每页的记录数
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 当前页码
	 */
	private int currentPageNo = 1;
	
	/**
	 * 当前页包含的记录数，currentPageSize <= pageSize
	 */
	private int currentPageSize;
	
	/**
	 * 总记录数
	 */
	private int totalCount;

	/**
	 * 总页数
	 */
	private int totalPageSize = 1;
	
	/**
	 * 当前页第一条数据在数据库中的位置
	 */
	private int start = 1;
	
	/**
	 * 当前页最后一条数据在数据库中的位置
	 */
	private int end = 1;
	
	private String requestURI ="";
	private String queryString ="";
	private List<?> result;

	public Pager() {	}

	//原来是public Pager(Integer currentPageSize, int pageSize) {XXX
	public Pager(Integer currentPageNo, int pageSize) {//$$currentPageNo和pageSize还可以为<0的-2等,忽略此情况
		if(currentPageNo == null || currentPageNo <= 0){
			currentPageNo = 1;
		}
		this.currentPageNo = currentPageNo;
		this.pageSize = pageSize;
//		resetPager(currentPageNo, pageSize);
	}

	public Pager(Integer currentPageNo, int pageSize,HttpServletRequest request) {//$$currentPageNo和pageSize还可以为<0的-2等,忽略此情况
		if(currentPageNo == null || currentPageNo <= 0){
			currentPageNo = 1;
		}
		this.currentPageNo = currentPageNo;
		this.pageSize = pageSize;
		requestURI = request.getRequestURI();
		queryString = request.getQueryString();
		queryString = Pagination.filterQueryString(queryString);
		System.out.println("您现在访问的网址是"+requestURI+"?"+queryString);
	}
	
	/**
	 * 在set时防止当前页数超出最大页数
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
//		计算总页数
		this.totalPageSize = (totalCount + pageSize - 1) / pageSize;
//		@@这里还要判断  当前第几页 是否已超出 最大页数,超出置设置当前第几页为最后一页的页数,这样的话要先统计页数再去进行查询List
		if(this.currentPageNo > this.totalPageSize) 
			this.currentPageNo = this.totalPageSize;
//		当前第几页 < 最大页数,则当前页共有pageSize,不然最后一页  由总记录数- (当前页 * 每页数)
		this.currentPageSize = this.currentPageNo < this.totalPageSize ?  pageSize : this.totalCount - ( (this.currentPageNo-1) * this.pageSize);
		setStart();
		setEnd();
	}
	
	/**
	 * 获取任一页第一条数据在#数据库#中的位置
	 */
	private void setStart() {
		start =( currentPageNo - 1 ) * pageSize;
	}

	/**
	 * 获取任一页最后一条数据在#数据库#中的位置 oracle用
	 */
	private void setEnd() {
//		int temp = (currentPageNo < pageSize);
		end = (currentPageNo - 1) * pageSize + currentPageSize ;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCurrentPageSize() {
		return currentPageSize;
	}

	public void setCurrentPageSize(int currentPageSize) {
		this.currentPageSize = currentPageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	public int getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(int totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	
	
	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}
}
