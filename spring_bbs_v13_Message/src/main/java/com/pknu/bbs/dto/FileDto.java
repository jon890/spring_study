package com.pknu.bbs.dto;

public class FileDto {
	private int fileNum;	
	private String storedFname;
	private long fileLength;
	private int articleNum;
	
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}	
	public String getStoredFname() {
		return storedFname;
	}
	public void setStoredFname(String storedFname) {
		this.storedFname = storedFname;
	}
	public long getFileLength() {
		return fileLength;
	}
	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}
	public int getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}
	@Override
	public String toString() {
		return "FileDto [fileNum=" + fileNum + ", storedFname=" + storedFname
				+ ", fileLength=" + fileLength + ", articleNum=" + articleNum + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fileNum;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDto other = (FileDto) obj;
		if (fileNum != other.fileNum)
			return false;
		return true;
	}	
	
}
