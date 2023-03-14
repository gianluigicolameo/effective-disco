package com.enway.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {
	public void createFile(String fileName, String content);
	public String readFile(String fileName);
	public void updateFile(String fileName, String content);
	public String deleteFile(String fileName);
	void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName);
}
