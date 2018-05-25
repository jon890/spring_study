package com.ict.human.bbs.dto;

import java.util.List;

public class BBSDto {
	
	private int articleNum;
	private String id;
	private String title;
	private String content;
	private int depth;
	private int hit;
	private int groupId;
	private String writeDate;
	private int fileStatus;
	private List<String> files;
	private int commentCount;
	
	
	public int getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(int fileStatus) {
		this.fileStatus = fileStatus;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}
	
	@Override
	public String toString() {
		return "BBSDto [articleNum=" + articleNum + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", depth=" + depth + ", hit=" + hit + ", groupId=" + groupId + ", writeDate=" + writeDate
				+ ", fileStatus=" + fileStatus + ", files=" + files + ", commentCount=" + commentCount + "]";
	}

}