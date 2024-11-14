package com.nt.Service;

import java.util.List;

import com.nt.Bind.AppBind;

public interface AppService {

	
	public String appReg(AppBind appBind);
	
	public List<AppBind> getAppBaseOnRole(Integer id);
	
	
	
}
