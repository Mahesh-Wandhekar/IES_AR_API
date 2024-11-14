package com.nt.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Bind.AppBind;
import com.nt.Constant.AppConstant;
import com.nt.Entity.ApplicationEntity;
import com.nt.Entity.IESUsersEntity;
import com.nt.Repository.IESApplicationRepo;
import com.nt.Repository.IESUserRepo;

@Service
public class AppServiceImp implements AppService {

	@Autowired
	private IESApplicationRepo appRepo;

	@Autowired
	private IESUserRepo userRepo;

	
	
	@Override
	public String appReg(AppBind appBind) {
		if (appBind.getSsn().startsWith("700")) {
			IESUsersEntity entity = userRepo.findById(appBind.getUserId()).get();
			ApplicationEntity applicationEntity = new ApplicationEntity();
			BeanUtils.copyProperties(appBind, applicationEntity);
			applicationEntity.setStateName("RI");
			applicationEntity.setCreatedByApps(entity);
			applicationEntity = appRepo.save(applicationEntity);
			return AppConstant.APP_CRE_SUCC + applicationEntity.getCaseNum();
		}
		return AppConstant.APP_CRE_FAILED;
	}

	@Override
	public List<AppBind> getAppBaseOnRole(Integer id) {
		IESUsersEntity entity=userRepo.findById(id).get();
		List <ApplicationEntity> applicationEntity=null;
		if(entity.getRole().equals("User")) {
			applicationEntity=appRepo.findByCreatedByApps(entity);
		}else {	
			applicationEntity=appRepo.findAll();
		}
		List<AppBind> apps=new ArrayList<>();
		for(ApplicationEntity entity2:applicationEntity) {
			AppBind appBind=new AppBind();
			BeanUtils.copyProperties(entity2, appBind);
			apps.add(appBind);
		}
		return apps;
	}

}
