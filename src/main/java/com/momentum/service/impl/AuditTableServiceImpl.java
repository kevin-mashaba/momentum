package com.momentum.service.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.momentum.entity.AuditTable;
import com.momentum.repository.AuditRepository;
import com.momentum.service.AuditService;

@Service
public class AuditTableServiceImpl implements AuditService {

	@Autowired
	private AuditRepository auditRepository;

	@Override
	public AuditTable saveData(AuditTable auditTable) {
		// TODO Auto-generated method stub

		return auditRepository.save(auditTable);
	}

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = null;

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();

		} else {
			username = principal.toString();
		}

		return username;
	}

	@Override
	public String getTimeStamp() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		;
		// String time = String.valueOf(calendar.getTime());
		return sdf.format(calendar.getTime());
	}

	@Override
	public List<AuditTable> findByUserName(String username) {
		// TODO Auto-generated method stub

		return auditRepository.findByUsername(username);
	}

}
