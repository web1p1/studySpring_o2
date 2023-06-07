package com.wind.web.dao;
import java.util.ArrayList;

import com.wind.web.dto.ContentDto;

public interface IDao {

	public ArrayList<ContentDto> listDao();
	public void writeDao(String bName, String bTitle, String bContent);
	public ContentDto viewDao(String bId);
	public void deleteDao(String bId);
	public void modifyDao(String bName, String bTitle, String bContent, String bId);
	public void replyDao(     String bName, String bTitle, String bContent, int bGroup, int bStep, int bIndent);
	public ContentDto reply_viewDao(String bId);
	public void replyShape(String bGroup, String bStep);
	public void upHit(String bId);
}
