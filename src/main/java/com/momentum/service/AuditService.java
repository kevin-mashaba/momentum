package com.momentum.service;

import java.util.List;

import com.momentum.entity.AuditTable;

public interface AuditService {

	AuditTable saveData(AuditTable auditTable);
	
	String getUser();
	String getTimeStamp();
	List<AuditTable> findByUserName(String username);
}
