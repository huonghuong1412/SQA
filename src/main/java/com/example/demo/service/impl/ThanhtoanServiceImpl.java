package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ThanhtoanDto;
import com.example.demo.entity.Thanhtoan;
import com.example.demo.entity.User;
import com.example.demo.repository.ThanhtoanRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ThanhtoanService;

@Service
public class ThanhtoanServiceImpl implements ThanhtoanService {

	@Autowired
	private ThanhtoanRepository repos;

	@Autowired
	private UserRepository userRepos;

	@Override
	public List<ThanhtoanDto> getHistoryThanhtoan(String username) {
		User user = userRepos.findOneByUsername(username);
		List<ThanhtoanDto> dtos = new ArrayList<ThanhtoanDto>();
		List<Thanhtoan> entities = repos.getAllByUser(user);

		for (Thanhtoan item : entities) {
			ThanhtoanDto dto = new ThanhtoanDto(item);
			dtos.add(dto);
		}

		return dtos;
	}

}
