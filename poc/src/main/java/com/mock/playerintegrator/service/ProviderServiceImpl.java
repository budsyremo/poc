package com.mock.playerintegrator.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock.playerintegrator.controller.IntegratorController;
import com.mock.playerintegrator.dto.Providers;
import com.mock.playerintegrator.dto.RequestDto;
import com.mock.playerintegrator.repository.ProviderRepository;

@Service
public class ProviderServiceImpl implements ProviderService{

	@Autowired
	ProviderRepository providerRepository;
	
	private static final Logger logger=Logger.getLogger(ProviderServiceImpl.class);
	
	@Override
	public RequestDto getProvider(String providerId) {
		try {
		Providers providers= providerRepository.findByProviderName(providerId);
		RequestDto dto=new RequestDto();
		dto.setAccountId(providers.getAccountId());
		dto.setSecretKey(providers.getSecretKey());
		dto.setUrl(providers.getRedirectUrl());
		return dto;
		}catch(Exception e) {
			logger.error("Exception Occured", e);
		}
		return null;
		
	}

	@Override
	public void saveProvider(Providers provider) {
		try {
		Providers providerExisting=providerRepository.findByProviderName(provider.getProviderName());
		if(providerExisting!=null) {
			provider.setId(providerExisting.getId());
			providerRepository.save(provider);
		}else {
			int maxId=providerRepository.maxId();
			provider.setId(maxId+1);
			providerRepository.save(provider);
		}
		}catch(Exception e) {
			logger.error("Exception Occured", e);
		}
		
	}

}
