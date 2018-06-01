package com.pknu.bbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pknu.bbs.util.MediaUtils;
import com.pknu.bbs.util.UploadFileUtils;


@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="saveDir")
	String saveDir;		 

	@ResponseBody
	@RequestMapping(value ="/uploadAjax", method=RequestMethod.POST)
	public List<String> uploadAjax(MultipartHttpServletRequest mRequest) throws Exception{
		List<String> fileList= new ArrayList<>();
		List<MultipartFile> mfile=mRequest.getFiles("multiFile");
		for(MultipartFile file : mfile){			
			fileList.add(UploadFileUtils.uploadFile(saveDir,file.getOriginalFilename(),
					file.getBytes()));
		}
		return fileList;
	}
	
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]>  displayFile(String fileName)
			throws Exception{
		InputStream in = null; 
		ResponseEntity<byte[]> entity = null;

		try{      
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);			
			if(mType != null){				      
				in = new FileInputStream(saveDir+fileName);				
			}			
			entity = new ResponseEntity<byte[]>(					
					IOUtils.toByteArray(in),				
					HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(
					HttpStatus.BAD_REQUEST);
		}finally{
			in.close();
		}
		return entity;    
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteFile.bbs", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName){    
		logger.info("delete file: "+ fileName);    
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);    
		MediaType mType = MediaUtils.getMediaType(formatName);

		if(mType != null){      
			String front = fileName.substring(0,12);
			String end = fileName.substring(14);
			new File(saveDir+(front+end).replace('/', File.separatorChar)).delete();
		}

		new File(saveDir + fileName.replace('/', File.separatorChar)).delete();    
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}  
	
	@ResponseBody
	@RequestMapping(value="/deleteAllFiles.bbs", method=RequestMethod.POST)
//	직렬화 안했을때
//	public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files){	
//	직렬화 했을때
	public ResponseEntity<String> deleteFile(@RequestParam("files") String[] files){		
		logger.info("delete all files: "+ files);    
		if(files == null || files.length == 0) {
			return new ResponseEntity<String>
			("deleted", HttpStatus.OK);
		}
		for (String fileName : files) {
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);      
			MediaType mType = MediaUtils.getMediaType(formatName);      
			if(mType != null){        
				String front = fileName.substring(0,12);
				String end = fileName.substring(14);
				new File(saveDir + (front+end).replace('/', File.separatorChar)).delete();
			}
			new File(saveDir + fileName.replace('/', File.separatorChar)).delete();      
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}  
}
