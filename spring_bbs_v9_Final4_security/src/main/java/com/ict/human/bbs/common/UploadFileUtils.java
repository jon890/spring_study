package com.ict.human.bbs.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(String saveDir, String originalName, byte[] fileData) throws Exception {
		String storedFname = UUID.randomUUID().toString() + "_" + originalName;
		String storedPath = calcPath(saveDir);
		File target = new File(saveDir + storedPath, storedFname);
		//						       부모 디렉터리            , 자식 파일
		FileCopyUtils.copy(fileData, target);
		String formatName = originalName.substring(originalName.lastIndexOf(".") +1);
		String uploadedFileName = null;
		
		if(MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumnail(saveDir, storedPath, storedFname);
		} else {
			uploadedFileName = makeIcon(storedPath, storedFname);
		}
		return uploadedFileName;
	}
	
	private static String calcPath(String saveDir) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(saveDir, yearPath, monthPath, datePath);
		logger.info(datePath);
		
		return datePath;
	}
	
	private static String makeThumnail(String saveDir, String storedPath, String storedFname) throws Exception {
		BufferedImage sourceImg = ImageIO.read(new File(saveDir + storedPath, storedFname));
		BufferedImage thumImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		String thumnailName = saveDir + storedPath + File.separator + "s_" + storedFname;
		
		File newFile = new File(thumnailName);
		String formatName = storedFname.substring(storedFname.lastIndexOf(".")+1);
		ImageIO.write(thumImg,  formatName.toUpperCase(), newFile);
		return thumnailName.substring(saveDir.length()).replace(File.separatorChar, '/');
	}
	
	private static String makeIcon(String storedPath, String storedFname) throws Exception {
		String iconName = storedPath + File.separator + storedFname;
		return iconName.replace(File.separatorChar, '/');
	}
	
	
	private static void makeDir(String saveDir, String ... paths) {
		if( new File(saveDir + paths[paths.length-1]).exists() ) {
			return;
			// break와 비슷한 역할
		}
		
		for(String path : paths) {
			File dirPath = new File(saveDir + path);
			if( !dirPath.exists() ) {
				dirPath.mkdir();
			}
		}
	}
	
	
}