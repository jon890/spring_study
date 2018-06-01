package com.pknu.bbs.util;

import java.io.File;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaveHelper {
	@Resource(name="saveDir")
	String saveDir;
	
	public String save(MultipartFile fname){		
		String storedFname=UUID.randomUUID().toString()+"_"+fname.getOriginalFilename();
		try{
			fname.transferTo(new File(saveDir+storedFname));			
		}catch(Exception e){}		
	    return storedFname;
	}
}
