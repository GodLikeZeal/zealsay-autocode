package com.zeal.shiyulin.common.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 查看上传的图片
 * @author zeal
 * @version 2014-06-25
 */
public class UserfilesDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void fileOutputStream(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//TODO
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		fileOutputStream(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		fileOutputStream(req, resp);
	}
}
