package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	@Query(value = "SELECT max(id) + 1 as max FROM User", nativeQuery = true)
//	@Query(value = "SELECT seq_name.nextval FROM dual", nativeQuery = true)
//	@Query(value = "SELECT nextval('yourSeqName')", nativeQuery =true)
//	@Query(value = "SELECT yourSeqName.nextval FROM dual", nativeQuery = true)
	@Query(value = "SELECT nextval('item_id_seq')", nativeQuery = true)
	Long getNextSeriesId();

	Optional<User> findByUsername(String username);

	public User findOneByUsername(String username);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);

	public Boolean existsByPhone(String phone);

	public Boolean existsByCccd(String cccd);

}
