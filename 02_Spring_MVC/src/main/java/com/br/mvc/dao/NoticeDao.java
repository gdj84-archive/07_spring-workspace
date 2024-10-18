package com.br.mvc.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.br.mvc.dto.NoticeDto;

@Repository // dao 역할을 하는 클래스에 부여하는 component 어노테이션 (빈 스캐닝에 의해 빈등록됨)
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
	
	public int updateNotice(NoticeDto n) {
		for(NoticeDto notice : dbList) {
			if(notice.getNo() == n.getNo()) {
				notice.setTitle(n.getTitle());
				notice.setContent(n.getContent());
				return 1;
			}
		}
		
		return 0;
	}

}
