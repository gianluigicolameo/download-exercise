package com.enway.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {
	@GetMapping("/download")
	public String download(HttpServletRequest req, HttpServletResponse resp) {
		File file = new File("C:/Users/g.colameo/Desktop/DownloadFile.txt");

		try {
			FileInputStream fileIn = new FileInputStream(file);
			ServletOutputStream out = resp.getOutputStream();
			 resp.setHeader(
				        HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getName() + ".txt");
			resp.setContentType("text/plain");
			byte[] outputByte = new byte[4096];
			//copy binary contect to output stream
			while(fileIn.read(outputByte, 0, 4096) != -1)
			{
				out.write(outputByte, 0, 4096);
			}
			fileIn.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
