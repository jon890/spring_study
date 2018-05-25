package com.ict.human.bbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ict.human.bbs.common.MediaUtils;
import com.ict.human.bbs.common.UploadFileUtils;
import com.ict.human.bbs.service.BBSService;


@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name = "saveDir")
	private String saveDir;
	
	@Autowired
	private BBSService bbsService;

	// 비동기 업로드 - 글쓰기시 동작
	@ResponseBody
	@RequestMapping(value = "/uploadAjax.bbs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<String>> uploadAjax(@RequestPart("multiFile") List<MultipartFile> mfile)
			throws Exception {
		List<String> fileList = new ArrayList<>();

		for (MultipartFile file : mfile) {
			fileList.add(UploadFileUtils.uploadFile(saveDir, file.getOriginalFilename(), file.getBytes()));
		}
		return new ResponseEntity<>(fileList, HttpStatus.CREATED);
	}

	// 비동기 업로드 - 파일 이름과 썸네일 이미지 보여주기

	@ResponseBody
	@RequestMapping(value = "/displayFile.bbs")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(saveDir + fileName);

			if (mType != null) {
				headers.setContentType(mType);
			} else {
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			}
			fileName = fileName.substring(fileName.indexOf("_") + 1);
			headers.add("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(fileName, "utf-8").replace("+", "%20") + "\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	@RequestMapping(value = "/download.bbs")
	@ResponseBody
	public FileSystemResource download(HttpServletResponse resp, @RequestParam String storedFname) {
		return bbsService.download(resp, storedFname);

	}

	@ResponseBody
	@RequestMapping(value = "/deleteFile.bbs", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		logger.info("delete file: " + fileName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		MediaType mType = MediaUtils.getMediaType(formatName);

		if (mType != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(saveDir + (front + end).replace('/', File.separatorChar)).delete();
		}

		new File(saveDir + fileName.replace('/', File.separatorChar)).delete();
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAllFiles.bbs", method = RequestMethod.POST)
	// ajax() 함수가 배열을 직렬화 하지 않고 보낼때는..아래 코드처럼 해도 처리됨
	// public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[]
	// files){
	public ResponseEntity<String> deleteFile(@RequestParam("files") String[] files) {
		logger.info("delete all files: " + files);
		if (files == null || files.length == 0) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
		for (String fileName : files) {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			if (mType != null) {
				String front = fileName.substring(0, 12);
				String end = fileName.substring(14);
				new File(saveDir + (front + end).replace('/', File.separatorChar)).delete();
			}
			new File(saveDir + fileName.replace('/', File.separatorChar)).delete();
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}


}