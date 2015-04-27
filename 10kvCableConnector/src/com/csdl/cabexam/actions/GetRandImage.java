package com.csdl.cabexam.actions;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.util.GenerateRandImg;
import com.opensymphony.xwork2.ActionSupport;


public class GetRandImage extends ActionSupport
{
	public void getRandImage()
	{	
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			OutputStream out = response.getOutputStream();	
			GenerateRandImg randImage = new GenerateRandImg(out);
			String str = randImage.getRandImg();
			request.getSession().setAttribute("randstr", str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
