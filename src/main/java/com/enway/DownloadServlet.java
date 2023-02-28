package com.enway;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/download")
public class DownloadServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DownloadServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String filePath = "C:/Users/g.colameo/Desktop/REGISTRO FAD CORSO 2 VIDIMATO_.pdf";
		
		response.setContentType("application/pdf");

		String fileName = "Registro.pdf";
		
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        
        try (InputStream inputStream = new FileInputStream(filePath);
                OutputStream outputStream = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Errore nel download del file.");
		}
	}
}
