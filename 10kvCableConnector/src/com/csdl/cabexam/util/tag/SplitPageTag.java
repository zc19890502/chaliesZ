package com.csdl.cabexam.util.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SplitPageTag extends TagSupport {

	// 数据源
	private List data;
	// 每页显示数
	private int pageSize = 0;
	//跳转到什么页面
	private String page;
	// 总数量
	private int accountSize = 0;
	// 总页数
	private int accountPage = 0;
	// 当前页
	private int currentPage = 1;
	// 开始标签 startIndex
	private int startIndex = 0;
	// 结束标签 endIndex
	private int endIndex = 0;
	// 递增类型
	private String step;// 0首页 -1上一页 +1下一页 -0尾页
	// 请求对象
	private HttpServletRequest request;
	// 页面输出对象
	private JspWriter out;
	

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 执行开始标签 1<c:forEach data="属性" pageSize="" >标签头 2 3 标签体 4 5</c:forEach>//
	 * 标签尾 6---
	 */
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		request = (HttpServletRequest) this.pageContext.getRequest();
		out = this.pageContext.getOut();
		accountSize = 0;
		accountPage = 0;
		if (null != data && data.size() > 0) {

			// 总数量
			accountSize = data.size();
			// 总页面
			accountPage = accountSize / pageSize;
			if (accountSize % pageSize != 0) {// 是否能被整除
				accountPage++;
			}

			// 当前页
			currentPage = 1;
			
			// 当前页的前一页
			String crtPage = request.getParameter("crtPage");
			if(null != crtPage && !"".equals(crtPage)){
				currentPage = Integer.parseInt(crtPage);
			}
			
			// 分页增长标示
			step = request.getParameter("step");
			// 判断是首页 0、上一页 -1、下一页 1、尾页 -0
			if (null != step && !"".equals(step)) {
				if ("0".equals(step)) {
					// 首页
					currentPage = 1;
				} else if ("-1".equals(step)) {
					// 上一页
					currentPage--;
				} else if ("1".equals(step)) {
					// 下一页
					currentPage++;
				} else if ("-0".equals(step)) {
					// 尾页
					currentPage = accountPage;
				}
			}

			// 开始标签
			startIndex = (currentPage - 1) * pageSize;

			// 结束标签
			if (currentPage >= accountPage) {
				endIndex = accountSize - 1;
			} else {
				endIndex = currentPage * pageSize - 1;
			}

			// 把数据绑定到request中
			request.setAttribute("splitData",
					data.get(startIndex));
			return TagSupport.EVAL_BODY_INCLUDE;
		} else {
			return TagSupport.SKIP_BODY;
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		// TODO Auto-generated method stub
		startIndex++;
		if (startIndex <= endIndex) {
			// 把数据绑定到request中
			request.setAttribute("splitData",
					data.get(startIndex));
			return TagSupport.EVAL_BODY_AGAIN;
		} else {
			return TagSupport.SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		try {
			String path = request.getContextPath();
			out.println("<tr align='right' ><td colspan='4'>");
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			out.println("共"+accountPage+"页"+" "+accountSize+" 条记录   当前"+currentPage+"/"+accountPage+"页 ");
			out.println("</td><td colspan='4'><a href='"+path+"/"+page+"&step=0'><i class='icon-step-backward'></i>首页</a>");
			// 判断当前页是否到了第一页，上一页按钮是否可用
			if(this.currentPage<=1){//disabled="disabled"
				out.println("<a href='javascript:void(0)' disabled='disabled'><font color='blue'>上一页</font></a>");
			}else{
				out.println("<a href='"+path+"/"+page+"&step=-1&crtPage="+this.currentPage+"'><i class='icon-chevron-left'></i>上一页</a>");
			}
			// 判断当前页是否到了最后一页，下一页按钮是否可用
			if(this.currentPage>=this.accountPage){//disabled="disabled"
				out.println("<a href='javascript:void(0)' disabled='disabled'><font color='blue'>下一页</font></a>");
			}else{
				out.println("<a href='"+path+"/"+page+"&step=1&crtPage="+this.currentPage+"'><i class='icon-chevron-right'></i>下一页</a>");
			}
			out.println("<a href='"+path+"/"+page+"&step=-0'><i class='icon-step-backward'></i>尾页</a>");
			//共5页 100 条记录 当前1/7页
			out.println("</td></tr>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doEndTag();
	}
}
