package com.mock.playerintegrator.service;


import com.mock.playerintegrator.dto.Providers;
import com.mock.playerintegrator.dto.RequestDto;
public interface ProviderService {

	RequestDto getProvider(String providerId);
	
	void saveProvider(Providers providerId);
}
