package com.csdl.cabexam.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ForTag extends TagSupport {
	
	// ��ǩͷ���ԣ�ѭ������
	private int num;
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * ִ�п�ʼ��ǩ
	 * 1<c:forEach num="����"  >��ǩͷ
	 * 2
	 * 3    ��ǩ��
	 * 4
	 * 5</c:forEach>// ��ǩβ
	 * 6---
	 */
	public int doStartTag() throws JspException {
		System.out.println("�����ǩͷ.");
		
		return TagSupport.EVAL_BODY_INCLUDE;// ִ�б�ǩ��
		//return TagSupport.SKIP_BODY;// ��ִ�б�ǩ�壬������ǩβ
	}
	
	/**
	 * ִ�����ǰ��֮����ʲô
	 */
	public int doAfterBody() throws JspException {
		System.out.println("ִ�б�ǩ��.");
		// �ݼ�ѭ������
		num--;
		if(num>0){
		    return TagSupport.EVAL_BODY_AGAIN;//����ִ��һ�α�ǩ��
		}else{
			return TagSupport.SKIP_BODY;// ������ǩ������ǩβ
		}
		
		// return TagSupport.EVAL_BODY_AGAIN;//����ִ��һ�α�ǩ��
		//return TagSupport.SKIP_BODY;// ������ǩ������ǩβ
	}

	/**
	 * ִ�б�ǩβ
	 */
	public int doEndTag() throws JspException {
		System.out.println("��ǩβ.");
		// return TagSupport.SKIP_PAGE;//������ǩ���ҳ������
		return TagSupport.EVAL_PAGE; //����ִ�б�ǩ���ҳ������
	}

	

}
