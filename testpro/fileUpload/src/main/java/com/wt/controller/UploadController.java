package com.wt.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UploadController {

	@RequestMapping("/upload")
	public String indexPage() {
		return "upload";
	}

	// 演示文件上传原理
	@RequestMapping("/upload/file")
	public void fileUpload(HttpServletRequest request, HttpServletResponse response) {
		try {
			String contentType = request.getHeader("Content-Type");
			System.out.println("contentType:"+contentType);
			System.out.println("over---------------------------over");
			InputStream is = request.getInputStream();
			int len = -1;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1) {
				System.out.println(new String(buf, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/upload/file/easy")
	public void fileUpload1(HttpServletRequest request, HttpServletResponse response) {
		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
			// 3 解析request ,List存放 FileItem （表单元素的封装对象，一个<input>对应一个对象）
			List<FileItem> fileItems = servletFileUpload.parseRequest(request);

			for (FileItem fileItem : fileItems) {
				if (fileItem.isFormField()) {
					// 是否为表单字段（普通表单元素）
					// 表单字段名称
					String fieldName = fileItem.getFieldName();
					System.out.println(fieldName);
					// 表单字段值
					String fieldValue = fileItem.getString();
					System.out.println(fieldValue);
				} else {
					// 上传字段（上传表单元素）
					// 表单字段名称 fileItem.getFieldName();
					// 上传文件名
					String fileName = fileItem.getName();
					// * 兼容浏览器， IE ： C:\Users\liangtong\Desktop\abc.txt ; 其他浏览器
					// ： abc.txt
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					// 上传内容
					InputStream is = request.getInputStream();
					String parentDir = request.getRealPath("/WEB-INF/upload");
					File file = new File(parentDir, fileName);
					if (!file.getParentFile().exists())// 父路径不存在，创建父路径
						file.getParentFile().mkdirs();
					FileOutputStream out = new FileOutputStream(file);
					int len = -1;
					byte[] buf = new byte[1024];
					while ((len = is.read(buf)) != -1) {
						out.write(buf, 0, len);
						System.out.println(new String(buf, 0, len));
					}
					out.close();
					is.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
