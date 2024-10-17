package com.br.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.mvc.dto.NoticeDto;

@Service // Service 역할의 클래스에 부여하는 Component 어노테이션 (빈스캐닝에 의해서 자동으로 빈 등록됨)
public class NoticeServiceImpl implements NoticeService {

	@Override
	public List<NoticeDto> selectNoticeList() {
		return null;
	}

	@Override
	public NoticeDto selectNoticeByNo(int noticeNo) {
		return null;
	}

}
