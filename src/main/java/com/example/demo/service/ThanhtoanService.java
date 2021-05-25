package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ThanhtoanDto;

@Service
public interface ThanhtoanService {

	public List<ThanhtoanDto> getHistoryThanhtoan(String username);
	
}
