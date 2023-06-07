package com.wind.web.util;

import java.util.HashMap;

public class ListPaging {


	/*
	 * totalListNum : 게시판 글 목록 전체 갯수
	 * curNum : 현재 글 번호
	 * oneScreenListNum : 화면 하나에 나오는 목록 갯수
	 * curNowPageNum : 현재 ID 가 속한 화면페이지 번호
	 * totalPageNum : 전체 페이지 번호(갯수)
	 * 
	 */
	
	public static int totalListNum;
	public static int curNum = totalListNum;
	public static int oneScreenListNum = 10;
	public static int curNowPageNum = (totalListNum - curNum)/oneScreenListNum + 1;
	public static int totalPageNum = (totalListNum-1) / oneScreenListNum + 1;
	
	public static HashMap<String, Integer> myListPageInfo;


	public static void initListPaging(int tNum, int cNum) {

		myListPageInfo = new HashMap<String, Integer>();

		totalListNum = tNum;
		curNum = cNum;
		if (curNum==0) { curNum = totalListNum; }
		oneScreenListNum = 10;
		curNowPageNum = (totalListNum - curNum)/oneScreenListNum + 1;
		totalPageNum = (totalListNum-1) / oneScreenListNum + 1;
		
		myListPageInfo.put("totalListNum", totalListNum);
		myListPageInfo.put("curNum", curNum);
		myListPageInfo.put("curNowPageNum", curNowPageNum);
		myListPageInfo.put("totalPageNum", totalPageNum);
		
	}

	public static void viewPageInfo() {
		initListPaging(totalListNum, curNum);
		System.out.println("totalListNum = " + totalListNum);
		System.out.println("curNum = " + curNum);
		System.out.println("curNowPageNum = " + curNowPageNum);
		System.out.println("totalPageNum = " + totalPageNum);
	}
	
	public static void setTotalListNum(int totalListNum) {
		ListPaging.totalListNum = totalListNum;
		if (curNum==0) { curNum = totalListNum; }
	}

	// totalListNum : 전체 게시물 수
	public static int getTotalListNum() {
		return totalListNum;
	}

	public static int getCurNum() {
		return curNum;
	}

	public static int getOneScreenListNum() {
		return oneScreenListNum;
	}

	public static int getCurNowPageNum() {
		return curNowPageNum;
	}

	public static int getTotalPageNum() {
		return totalPageNum;
	}

	public static void setCurNum(int curNum) {
		ListPaging.curNum = curNum;
	}

	public static void setOneScreenListNum(int oneScreenListNum) {
		ListPaging.oneScreenListNum = oneScreenListNum;
	}

	public static void setCurNowPageNum(int curNowPageNum) {
		ListPaging.curNowPageNum = curNowPageNum;
	}

	public static void setTotalPageNum(int totalPageNum) {
		ListPaging.totalPageNum = totalPageNum;
	}
	
	



}
