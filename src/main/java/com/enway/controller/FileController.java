package com.enway.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enway.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileService fileService;
	
	@PostMapping("/create")
	public String createFile(@RequestParam String path, @RequestBody String content){
		fileService.createFile(path, content);
		return fileService.readFile(path);
	}
	
	@GetMapping("/read")
	public String readFile(@RequestParam String path) {
		return fileService.readFile(path);
	}
	
	@PutMapping("/append")
	public String updateFile(@RequestParam String path, @RequestBody String content){
		fileService.updateFile(path, content);
		return fileService.readFile(path);
	}
	
	@DeleteMapping("/delete")
	public String deleteFile(@RequestParam String path){
		return fileService.deleteFile(path);
	}
	
	@GetMapping("/download")
	public String downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam String path){
		fileService.downloadFile(request, response, path);
		return fileService.readFile(path);
	}
}
