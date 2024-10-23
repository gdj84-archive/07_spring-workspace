package com.br.file.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.br.file.dto.BoardDto;
import com.br.file.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	@PostMapping("/insert1.do")
	public String insertOneFileBoard(BoardDto board, MultipartFile uploadFile) {
		// 라이브러리 추가 후 빈 등록을 해야만 값이 잘 담김!!
		log.debug("board: {}", board);
		log.debug("attach: {}", uploadFile);
		
		if(uploadFile != null && !uploadFile.isEmpty()) { // 첨부파일이 존재할 경우 => 업로드
			// 전달된 파일 업로드 처리 
			// 1) 업로드할 폴더 (/upload/yyyyMMdd)
			String filePath = "/upload/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
			
			File filePathDir = new File(filePath);
			if(!filePathDir.exists()) { // 해당 경로의 폴더가 존재하지 않을 경우
				filePathDir.mkdirs();	// 해당 폴더 만들기
			}
			
			// 2) 파일명 수정 작업
			String originalFilename = uploadFile.getOriginalFilename(); // "xxxxx.jpg" | "xxxx.tar.gz"
			String originalExt = originalFilename.endsWith(".tar.gz") ? ".tar.gz" 
																	  : originalFilename.substring(originalFilename.lastIndexOf("."));
			
			// UUID.randomUUID().toString() : 랜덤값 발생 (32자리 + -4개)
			String filesystemName = UUID.randomUUID().toString().replace("-", "") + originalExt;
			
			// 3) 업로드 (폴더에 파일 저장)
			try {
				uploadFile.transferTo(new File(filePathDir, filesystemName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		return "redirect:/";
	}

	
	
	
	
	
	
	
}
