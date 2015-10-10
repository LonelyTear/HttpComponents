package kingtool.pager;


import javax.servlet.http.HttpServletRequest;

public class Pagination { 
	private StringBuilder sb = null;
	private int lastPageNum,pageNumber;
	private HttpServletRequest request = null;
	public Pagination(){ sb = new StringBuilder();}
	
	public Pagination(HttpServletRequest request){
		this.request = request;
		sb = new StringBuilder();
		Integer i,j = null;
		if((i = (Integer)request.getAttribute("lastPageNum"))!= null){
			setLastPageNum(i);
		}
		if((j = (Integer)request.getAttribute("pageNumber"))!= null){
			setPageNum(j);
		}
		this.request.setAttribute("pagination", this.toString());
	}
	
	public String toString(){
		return processPage();
	}

	public String processPage(){
		String requestURI = request.getRequestURI();
		String queryString = request.getQueryString();
		if(queryString == null) queryString="";
		queryString = filterQueryString(queryString);
System.out.println("你现在访问的网址是"+requestURI+"?"+queryString);
//System.out.println("当前页是 "+pageNumber);
		sb.append("<form action='"+requestURI+"?"+queryString+"&pageOperator=jump"+"' method='post' align='left'>");
			sb.append("<a  href='"+requestURI+"?"+queryString+"&pageOperator=first&pageNumber=1"
			+"'>首页</a>"+"&nbsp|&nbsp");
			sb.append("<a href='"+requestURI+"?"+queryString+"&pageOperator=previous&pageNumber="+pageNumber
			+"'>上一页</a>"+"&nbsp|&nbsp");
		if((pageNumber - 2) >= 1){
			sb.append("<a style='border:1px #ffffff solid;' href='"+requestURI+"?"+queryString+"&pageOperator=jump&pageNumber="+(pageNumber-2)
					+"'>"+(pageNumber-2)+"</a>"+"&nbsp|&nbsp");
		}
		if((pageNumber - 1) >= 1){
			sb.append("<a style='border:1px #ffffff solid;' href='"+requestURI+"?"+queryString+"&pageOperator=jump&pageNumber="+(pageNumber-1)
					+"'>"+(pageNumber-1)+"</a>"+"&nbsp|&nbsp");
		}
		sb.append("<a style='border:1px #ffffff solid;' href='"+requestURI+"?"+queryString+"&pageOperator=jump&pageNumber="+(pageNumber)
				+"'>"+(pageNumber)+"</a>"+"&nbsp|&nbsp");
		if((pageNumber + 1) <= lastPageNum){
			sb.append("<a style='border:1px #ffffff solid;' href='"+requestURI+"?"+queryString+"&pageOperator=jump&pageNumber="+(pageNumber+1)
					+"'>"+(pageNumber+1)+"</a>"+"&nbsp|&nbsp");
		}
		if((pageNumber +2)<= lastPageNum){
			sb.append("<a style='border:1px #ffffff solid;' href='"+requestURI+"?"+queryString+"&pageOperator=jump&pageNumber="+(pageNumber+2)
					+"'>"+(pageNumber+2)+"</a>"+"&nbsp|&nbsp");
		}
		sb.append("<a href='"+requestURI+"?"+queryString+"&pageOperator=next&pageNumber="+pageNumber
				+"'>下一页</a>"+"&nbsp|&nbsp");
		sb.append("<a href='"+requestURI+"?"+queryString+"&pageOperator=last&pageNumber=1"
				+"'>尾页</a>"+"&nbsp|&nbsp");
		sb.append("当前第"+pageNumber+"/"+lastPageNum+"页"+"&nbsp;&nbsp;");
		sb.append("<input name='pageNumber' type='text'  size=pageNumberberle='border-color:silver;'/>"+"&nbsp");	
		sb.append("<input type='submit' value='跳转'/>");
		sb.append("</form>");
		return sb.toString();
	}
	
	public static String filterQueryString(String queryString){//过滤串，把page
		queryString = queryString.replaceAll("pageNumber[^&]*", "");
		queryString = queryString.replaceAll("&{2,}", "&");
		queryString = queryString.replaceAll("^&", "");
		queryString = queryString.replaceAll("&$", "");
		return queryString;
	}
	
	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public int getPageNum() {
		return pageNumber;
	}

	public void setPageNum(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public static void main(String[] args) {
		String queryString = "mi=3&like=true&pageNumber=2&pageOperator=next&quality=03";
		queryString = queryString.replaceAll("pageNumber[^&]*", "");
//		queryString = queryString.replaceAll("pageOperator[^&]*", "");
//		queryString = queryString.replaceAll("&{2,}", "&");
//		queryString = queryString.replaceAll("^&", "");
//		queryString = queryString.replaceAll("&$", "");
		System.out.println(queryString);
		
	}
}
