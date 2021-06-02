//package com.example.demo.config;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.common.Erole;
//import com.example.demo.entity.Address;
//import com.example.demo.entity.Role;
//import com.example.demo.entity.Thanhtoan;
//import com.example.demo.entity.User;
//import com.example.demo.entity.tinhthanh.District;
//import com.example.demo.entity.tinhthanh.Province;
//import com.example.demo.entity.tinhthanh.Ward;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.ThanhtoanRepository;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.repository.tinhthanh.DistrictRepository;
//import com.example.demo.repository.tinhthanh.ProvinceRepository;
//import com.example.demo.repository.tinhthanh.WardRepository;
//
//@Component
//public class InsertData implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {
//
//	private static boolean eventFired = false;
//	private static final Logger logger = LoggerFactory.getLogger(InsertData.class);
//
//	@Autowired
//	private RoleRepository roleRepos;
//
//	@Autowired
//	private UserRepository userRepos;
//
//	@Autowired
//	private ProvinceRepository provinceRepos;
//
//	@Autowired
//	private DistrictRepository districtRepos;
//
//	@Autowired
//	private WardRepository wardRepos;
//	
//	@Autowired
//	private ThanhtoanRepository ttRepos;
//
//	@Autowired
//	private PasswordEncoder encoder;
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		if (eventFired) {
//			return;
//		}
//
//		logger.info("Application started.");
//
//		eventFired = true;
//
//		try {
//			createRoles();
//			createUser();
//			createProvince();
//			createDistrict();
//			createWard();
//			createHistoryThanhtoan();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void createRoles() {
//		List<Role> roleNames = new ArrayList<>();
//		roleNames.add(new Role(Erole.ROLE_ADMIN));
//		roleNames.add(new Role(Erole.ROLE_USER));
//
//		for (Role roleName : roleNames) {
//			if (roleRepos.existsByName(roleName.getName())) {
//				return;
//			}
//			roleName.setName(roleName.getName());
//			try {
//				roleRepos.save(roleName);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
//
//	private void createUser() {
////		String username, String cccd, String fullName, String dateOfBirth, String password, String phone,
////		String email, Address address, Integer typeUser, String coQuanBaoHiemThanhPho,
////		String coQuanBaoHiemQuanHuyen, Long salary, String maSoThue, String tenDonVi, String maDonVi
//
//		if (userRepos.existsByUsername("0000000001")) {
//			return;
//		} else if (userRepos.existsByCccd("000000000001")) {
//			return;
//		} else if (userRepos.existsByEmail("test@gmail.com")) {
//			return;
//		} else if (userRepos.existsByPhone("0999999999")) {
//			return;
//		} else {
//			Address address = new Address("Thành phố Hà Nội", "Quận Ba Đình", "Phường Phúc Xá",
//					"Số 62 ngõ 32 phố Phúc Xá");
//			User user = new User("0000000001", "000000000001", "Nguyễn Văn A", "12-14-1999",
//					encoder.encode("123456789"), "0999999999", "test@gmail.com", address, 1, "Thành phố Hà Nội",
//					"BHXH Quận Ba Đình", 20000000L, "aaa999", "Tập đoàn VinGroup", "vin999");
//		
//			address.setUser(user);
//			
//			
//			Set<Role> roles = new HashSet<>();
//			Role role = roleRepos.findByName(Erole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
//			roles.add(role);
//			user.setRoles(roles);
//			Set<User> setUser = new HashSet<User>();
//			setUser.add(user);
//			role.setUsers(setUser);
//			try {
//				userRepos.save(user);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	private void createProvince() {
//		List<Province> list = new ArrayList<Province>();
//		list.add(new Province("01TTT", "Thành phố Hà Nội"));
//		list.add(new Province("79TTT", "Thành phố Hồ Chí Minh"));
//		for (Province item : list) {
//			if (provinceRepos.existsByName(item.getName())) {
//				return;
//			}
//			item.setName(item.getName());
//			try {
//				provinceRepos.save(item);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
//
//	private void createDistrict() {
//		List<District> list = new ArrayList<>();
//		list.add(new District("001HH", "Quận Ba Đình", "01TTT"));
//		list.add(new District("002HH", "Quận Hoàn Kiếm", "01TTT"));
//		list.add(new District("762HH", "Quận Thủ Đức", "79TTT"));
//		list.add(new District("760HH", "Quận 1", "79TTT"));
//		list.add(new District("769HH", "Quận 2", "79TTT"));
//		for (District item : list) {
//			if (districtRepos.existsByName(item.getName())) {
//				return;
//			}
//			item.setName(item.getName());
//			try {
//				districtRepos.save(item);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
//
//	private void createWard() {
//		List<Ward> list = new ArrayList<>();
//		list.add(new Ward("00001", "Phường Phúc Xá", "001HH"));
//		list.add(new Ward("00004", "Phường Trúc Bạch", "001HH"));
//		list.add(new Ward("00010", "Phường Nguyễn Trung Trực", "760HH"));
//		list.add(new Ward("00011", "Phường Bến Thành", "760HH"));
//		for (Ward item : list) {
//			if (wardRepos.existsByName(item.getName())) {
//				return;
//			}
//			item.setName(item.getName());
//			try {
//				wardRepos.save(item);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
//	
//	private void createHistoryThanhtoan() {
//		List<Thanhtoan> list = new ArrayList<Thanhtoan>();
//		User user = userRepos.getOne(1L);
//		list.add(new Thanhtoan(user, "Đóng BHXH Tháng 5 - 2019", "2019-05-24 10:03:54.091", 1600000D, 
//				"Ngân hàng Nông nghiệp và Phát triển Nông thôn Việt Nam", "Chi nhánh Sở giao dịch - Phòng giao dịch số 2", "0000000001", 1));
//		list.add(new Thanhtoan(user, "Đóng BHXH Tháng 5 - 2020", "2020-05-20 10:03:54.091", 1600000D, 
//				"Ngân hàng Nông nghiệp và Phát triển Nông thôn Việt Nam", "Chi nhánh Sở giao dịch - Phòng giao dịch số 2", "0000000002", 1));
//		list.add(new Thanhtoan(user, "Đóng BHXH Tháng 5 - 2021", "2021-05-26 10:05:05.915", 1600000D, 
//				"Ngân hàng Nông nghiệp và Phát triển Nông thôn Việt Nam", "Chi nhánh Láng Hạ - Phòng giao dịch số 08", "0000000003", 1));
//	
//		for(Thanhtoan item : list) {
//			if (ttRepos.existsByCode(item.getCode())) {
//				return;
//			}
//			try {
//				ttRepos.save(item);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
//
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
