package com.aloha.spring_di.service;

import org.springframework.stereotype.Service;

import com.aloha.spring_di.dao.BoardDAO;

@Service // 서비스 역할로 빈 등록
public class BoardServiceImpl implements BoardService {

	@Override
	public void test() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'test'");
	}

	@Override
	public void setDAO(BoardDAO boardDAO) {
        
	}

    
}
