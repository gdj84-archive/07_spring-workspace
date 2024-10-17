package com.br.mvc.dao;

import java.util.Arrays;
import java.util.List;

import com.br.mvc.dto.NoticeDto;

public class NoticeDao {
	
	private List<NoticeDto> dbList = Arrays.asList(
					new NoticeDto(1, "제목1", "내용1")
				  , new NoticeDto(2, "제목2", "내용2")
				  , new NoticeDto(3, "제목3", "내용3"));
	
	public List<NoticeDto> selectNoticeList(){
		return dbList;
	}
	
	public NoticeDto selectNoticeByNo(int noticeNo) {
		for(NoticeDto n : dbList) {
			if(n.getNo() == noticeNo) {
				return n;
			}
		}
		return null;
	}

}
