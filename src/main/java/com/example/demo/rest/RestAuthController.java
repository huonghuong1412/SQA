package com.example.demo.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Erole;
import com.example.demo.common.JwtUtils;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.auth.JwtResponse;
import com.example.demo.dto.auth.LoginDto;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.auth.RegisterDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class RestAuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Validated @RequestBody LoginDto dto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), userDetails.getFullname(), roles));

	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Validated @RequestBody RegisterDto dto) {

		if (userRepository.existsByUsername(dto.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Mã số BHXH đã được đăng ký!"));
		}

		if (userRepository.existsByCccd(dto.getCccd())) {
			return ResponseEntity.badRequest().body(new MessageResponse("CMND/CCCD đã được đăng ký!"));
		}

		if (userRepository.existsByPhone(dto.getPhone())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Số điện thoại đã được đăng ký!"));
		}

		if (userRepository.existsByEmail(dto.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email đã được đăng ký!"));
		}

		if (dto.getUsername() == null || dto.getUsername().length() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Mã BHXH không được để trống"));
		}

		if (dto.getCccd() == null || dto.getCccd().length() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("CMND/CCCD không được để trống"));
		}

		if (dto.getFullName() == null || dto.getFullName().length() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Họ tên không được để trống"));
		}

		if (dto.getPassword() == null || dto.getPassword().length() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu không được để trống"));
		}

		if (dto.getEmail() == null || dto.getEmail().length() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email liên hệ không được để trống"));
		}

		if (dto.getPhone() == null || dto.getPhone().length() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Số điện thoại liên hệ không được để trống"));
		}

		if (dto.getCity() == null || dto.getCity().length() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Thành phố liên hệ không được để trống"));
		}

		if (dto.getDistrict() == null || dto.getDistrict().length() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Quận huyện liên hệ không được để trống"));
		}

		if (dto.getWard() == null || dto.getWard().length() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Xã phường liên hệ không được để trống"));
		}

		if (dto.getHouse() == null || dto.getHouse().length() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Số nhà liên hệ không được để trống"));
		}

		Address address = new Address(dto.getCity(), dto.getDistrict(), dto.getWard(), dto.getHouse());

		User user = new User();
		user.setUsername(dto.getUsername());
		user.setCccd(dto.getCccd());
		user.setFullName(dto.getFullName());
		user.setDateOfBirth(dto.getDateOfBirth());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setTypeUser(dto.getTypeUser());
		user.setPhone(dto.getPhone());
		user.setEmail(dto.getEmail());
		user.setAddress(address);
		address.setUser(user);

		Set<String> strRoles = dto.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(Erole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(adminRole);
					break;
				case "user":
					Role userRole1 = roleRepository.findByName(Erole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(userRole1);
					break;
				default:
					Role userRole = roleRepository.findByName(Erole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(userRole);
					break;
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công!"));

	}

	@GetMapping("/info")
//	@PreAuthorize("hasRole('USER') or hasRole('BUSINESS') or hasRole('ADMIN')")
	public ResponseEntity<UserDto> getNewById() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		UserDto result = userService.getCurrentUser(userDetails.getId());
		return new ResponseEntity<UserDto>(result, HttpStatus.OK);
	}

	@PutMapping("/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> update(@Validated @RequestBody UserDto dto, @PathVariable Long id) {
		User user = userRepository.getOne(id);
		Address address = addressRepository.findOneByUserId(id);

		if (userRepository.existsByMaSoThue(dto.getMaSoThue()) && dto.getMaSoThue().equals(user.getMaSoThue()) == false) {
			return ResponseEntity.badRequest().body(new MessageResponse("Mã số thuế đã được đăng ký!"));
		}

		switch (dto.getTypeUser()) {
		case 1:
			if (dto.getSalary() == null || dto.getSalary() <= 0) {
				return ResponseEntity.badRequest().body(new MessageResponse("Lương không thể nhỏ hơn hoặc bằng 0"));
			} else if (dto.getSalary() > 0 && dto.getSalary() < 4420000) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Lương phải lớn hơn hoặc bằng 4420000 mới có thể đóng bảo hiểm"));
			}
			break;
		case 2:
			if (dto.getSalary() == null || dto.getSalary() <= 0) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Số tiền đóng không thể nhỏ hơn hoặc bằng 0"));
			} else if (dto.getSalary() > 0 && dto.getSalary() < 700000) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Số tiền đóng phải lớn hơn hoặc bằng 700000"));
			} else if (dto.getSalary() > 29800000) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Số tiền đóng phải nhỏ hơn hoặc bằng 29800000"));
			}
			break;
		default:
			break;
		}

		address.setCity(dto.getCity());
		address.setDistrict(dto.getDistrict());
		address.setWard(dto.getWard());
		address.setHouse(dto.getHouse());

		user.setUsername(dto.getUsername());
		user.setCccd(dto.getCccd());
		user.setFullName(dto.getFullName());
		user.setDateOfBirth(dto.getDateOfBirth());
		user.setPhone(dto.getPhone());
		user.setEmail(dto.getEmail());
		user.setAddress(address);
		user.setTypeUser(dto.getTypeUser());
		user.setCoQuanBaoHiemThanhPho(dto.getCoQuanBaoHiemThanhPho());
		user.setCoQuanBaoHiemQuanHuyen(dto.getCoQuanBaoHiemQuanHuyen());
		user.setSalary(dto.getSalary());
		user.setMaSoThue(dto.getMaSoThue());
		user.setTenDonVi(dto.getTenDonVi());
		user.setMaDonVi(dto.getMaDonVi());

		address.setUser(user);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Khai báo thông tin thành công!"));

	}

}
