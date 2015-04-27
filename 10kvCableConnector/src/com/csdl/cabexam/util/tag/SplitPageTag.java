package com.csdl.cabexam.util.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SplitPageTag extends TagSupport {

	// ����Դ
	private List data;
	// ÿҳ��ʾ��
	private int pageSize = 0;
	//��ת��ʲôҳ��
	private String page;
	// ������
	private int accountSize = 0;
	// ��ҳ��
	private int accountPage = 0;
	// ��ǰҳ
	private int currentPage = 1;
	// ��ʼ��ǩ startIndex
	private int startIndex = 0;
	// ������ǩ endIndex
	private int endIndex = 0;
	// ��������
	private String step;// 0��ҳ -1��һҳ +1��һҳ -0βҳ
	// �������
	private HttpServletRequest request;
	// ҳ���������
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
	 * ִ�п�ʼ��ǩ 1<c:forEach data="����" pageSize="" >��ǩͷ 2 3 ��ǩ�� 4 5</c:forEach>//
	 * ��ǩβ 6---
	 */
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		request = (HttpServletRequest) this.pageContext.getRequest();
		out = this.pageContext.getOut();
		accountSize = 0;
		accountPage = 0;
		if (null != data && data.size() > 0) {

			// ������
			accountSize = data.size();
			// ��ҳ��
			accountPage = accountSize / pageSize;
			if (accountSize % pageSize != 0) {// �Ƿ��ܱ�����
				accountPage++;
			}

			// ��ǰҳ
			currentPage = 1;
			
			// ��ǰҳ��ǰһҳ
			String crtPage = request.getParameter("crtPage");
			if(null != crtPage && !"".equals(crtPage)){
				currentPage = Integer.parseInt(crtPage);
			}
			
			// ��ҳ������ʾ
			step = request.getParameter("step");
			// �ж�����ҳ 0����һҳ -1����һҳ 1��βҳ -0
			if (null != step && !"".equals(step)) {
				if ("0".equals(step)) {
					// ��ҳ
					currentPage = 1;
				} else if ("-1".equals(step)) {
					// ��һҳ
					currentPage--;
				} else if ("1".equals(step)) {
					// ��һҳ
					currentPage++;
				} else if ("-0".equals(step)) {
					// βҳ
					currentPage = accountPage;
				}
			}

			// ��ʼ��ǩ
			startIndex = (currentPage - 1) * pageSize;

			// ������ǩ
			if (currentPage >= accountPage) {
				endIndex = accountSize - 1;
			} else {
				endIndex = currentPage * pageSize - 1;
			}

			// �����ݰ󶨵�request��
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
			// �����ݰ󶨵�request��
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
			out.println("��"+accountPage+"ҳ"+" "+accountSize+" ����¼   ��ǰ"+currentPage+"/"+accountPage+"ҳ ");
			out.println("</td><td colspan='4'><a href='"+path+"/"+page+"&step=0'><i class='icon-step-backward'></i>��ҳ</a>");
			// �жϵ�ǰҳ�Ƿ��˵�һҳ����һҳ��ť�Ƿ����
			if(this.currentPage<=1){//disabled="disabled"
				out.println("<a href='javascript:void(0)' disabled='disabled'><font color='blue'>��һҳ</font></a>");
			}else{
				out.println("<a href='"+path+"/"+page+"&step=-1&crtPage="+this.currentPage+"'><i class='icon-chevron-left'></i>��һҳ</a>");
			}
			// �жϵ�ǰҳ�Ƿ������һҳ����һҳ��ť�Ƿ����
			if(this.currentPage>=this.accountPage){//disabled="disabled"
				out.println("<a href='javascript:void(0)' disabled='disabled'><font color='blue'>��һҳ</font></a>");
			}else{
				out.println("<a href='"+path+"/"+page+"&step=1&crtPage="+this.currentPage+"'><i class='icon-chevron-right'></i>��һҳ</a>");
			}
			out.println("<a href='"+path+"/"+page+"&step=-0'><i class='icon-step-backward'></i>βҳ</a>");
			//��5ҳ 100 ����¼ ��ǰ1/7ҳ
			out.println("</td></tr>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doEndTag();
	}
}
