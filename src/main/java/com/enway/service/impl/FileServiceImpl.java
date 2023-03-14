package com.enway.service.impl;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.enway.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	public void createFile(String fileName, String content) {
		String path = "C:/Users/g.colameo/Desktop/" + fileName;

		Path filePath = Paths.get(path);
		try {
			// Crea il file
			Files.write(filePath, content.getBytes());

		} catch (IOException e) {
			logger.error("Errore nella creazione del file");
		}
	}

	@Override
	public String readFile(String fileName) {
		String path = "C:/Users/g.colameo/Desktop/" + fileName;

		Path filePath = Paths.get(path);
		List<String> fileContent = new ArrayList<>();

		try {
			fileContent = Files.readAllLines(filePath, StandardCharsets.UTF_8);
			logger.info("Lettura file avvenuta con successo");

			return fileContent.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Errore durante la lettura del file");
			return null;
		}

	}

	@Override
	public void updateFile(String fileName, String content) {

		String path = "C:/Users/g.colameo/Desktop/" + fileName;

		Path filePath = Paths.get(path);

		try {
			Files.write(filePath, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

			logger.info("File modificato correttamente");

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Errore nella modifica del file");
		}
	}

	@Override
	public String deleteFile(String fileName) {
		String path = "C:/Users/g.colameo/Desktop/" + fileName;

		Path filePath = Paths.get(path);
		
		String message = null;

		if (Files.exists(filePath)) {
			try {
				Files.delete(filePath);
				message = "File eliminato correttamente";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("Errore nella cancellazione del file");
			}
		} else {
			logger.error("Il file non esiste");
			message = "File non trovato";
		}
		return message;
	}

	@Override
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName) {
		String path = "C:/Users/g.colameo/Desktop/" + fileName;

		Path filePath = Paths.get(path);

		if (Files.exists(filePath)) {
			response.setContentType("text/plain");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			try {
				// copia i file all'interno dell'oputput stream
				Files.copy(filePath, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException ex) {
				ex.printStackTrace();
				logger.error("Errore nel download del file.");
			}
		}
	}
}
