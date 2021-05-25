package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.common.Erole;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

@Component
public class InsertData implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {

	private static boolean eventFired = false;
	private static final Logger logger = LoggerFactory.getLogger(InsertData.class);

	@Autowired
	private RoleRepository roleRepos;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if (eventFired) {
			return;
		}

		logger.info("Application started.");

		eventFired = true;

		try {
			createRoles();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createRoles() {
		List<Role> roleNames = new ArrayList<>();
		roleNames.add(new Role(Erole.ROLE_ADMIN));
		roleNames.add(new Role(Erole.ROLE_USER));

		for (Role roleName : roleNames) {
			if (roleRepos.existsByName(roleName.getName())) {
				return;
			}
			roleName.setName(roleName.getName());
			try {
				roleRepos.save(roleName);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub

	}

}
