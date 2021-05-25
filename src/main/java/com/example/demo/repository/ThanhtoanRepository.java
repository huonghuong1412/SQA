package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Thanhtoan;
import com.example.demo.entity.User;

@Repository
public interface ThanhtoanRepository extends JpaRepository<Thanhtoan, Long> {

	public List<Thanhtoan> getAllByUser(User user);
		

}
