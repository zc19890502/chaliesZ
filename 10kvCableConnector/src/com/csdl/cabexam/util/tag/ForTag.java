package com.csdl.cabexam.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ForTag extends TagSupport {
	
	// 标签头属性，循环次数
	private int num;
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * 执行开始标签
	 * 1<c:forEach num="属性"  >标签头
	 * 2
	 * 3    标签体
	 * 4
	 * 5</c:forEach>// 标签尾
	 * 6---
	 */
	public int doStartTag() throws JspException {
		System.out.println("进入标签头.");
		
		return TagSupport.EVAL_BODY_INCLUDE;// 执行标签体
		//return TagSupport.SKIP_BODY;// 不执行标签体，跳到标签尾
	}
	
	/**
	 * 执行完表前体之后做什么
	 */
	public int doAfterBody() throws JspException {
		System.out.println("执行标签体.");
		// 递减循环次数
		num--;
		if(num>0){
		    return TagSupport.EVAL_BODY_AGAIN;//重新执行一次标签体
		}else{
			return TagSupport.SKIP_BODY;// 结束标签体进入标签尾
		}
		
		// return TagSupport.EVAL_BODY_AGAIN;//重新执行一次标签体
		//return TagSupport.SKIP_BODY;// 结束标签体进入标签尾
	}

	/**
	 * 执行标签尾
	 */
	public int doEndTag() throws JspException {
		System.out.println("标签尾.");
		// return TagSupport.SKIP_PAGE;//结束标签后的页面内容
		return TagSupport.EVAL_PAGE; //继续执行标签后的页面内容
	}

	

}
