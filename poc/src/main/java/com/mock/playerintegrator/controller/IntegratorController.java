package com.mock.playerintegrator.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.playerintegrator.dto.InformationDTO;
import com.mock.playerintegrator.dto.MetaDataDto;
import com.mock.playerintegrator.dto.ProviderDTO;
import com.mock.playerintegrator.dto.Providers;
import com.mock.playerintegrator.dto.RequestDto;
import com.mock.playerintegrator.service.ProviderService;
import com.mock.playerintegrator.utility.ConnectionUtility;

@RestController
@RequestMapping("/fetchassessmentdetails")
public class IntegratorController {

	private static final Logger logger=Logger.getLogger(IntegratorController.class);
	
	@Autowired
	private ProviderService prodService;
	
	@PostMapping(path="/test")
	public InformationDTO getAssessmentDetails(@RequestBody ProviderDTO providerDTO) {
		
		logger.error("testSession :"+providerDTO.getTestSessionId()+" assessment : "+providerDTO.getAssessmentName()
				+"  providerId :"+providerDTO.getProviderName());
		RequestDto dto= prodService.getProvider(providerDTO.getProviderName());
		dto.setAssessment(providerDTO.getAssessmentName());
		String urlDTOJson=ConnectionUtility.post(dto.getUrl(), dto);
		ObjectMapper mapper = new ObjectMapper();
		 
		//Convert JSON to POJO
		InformationDTO infDTO;
		try {
			infDTO = mapper.readValue(urlDTOJson, InformationDTO.class);
			infDTO.setTestSessionId(providerDTO.getTestSessionId());
			return infDTO;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			logger.error("JsonParseException :", e);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			logger.error("JsonMappingException :", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("IOException :", e);
		}
		return null;
		
	}
	
	@PostMapping(path="/updatemetadata")
	public String updateMetaData(@RequestBody MetaDataDto providerDTO) {
		try {
		Providers provider=new Providers();
		provider.setProviderName(providerDTO.getProviderName());
		provider.setSecretKey(providerDTO.getSecretKey());
		provider.setAccountId(providerDTO.getAccountId());
		provider.setRedirectUrl(providerDTO.getUrl());
		prodService.saveProvider(provider);
		logger.error("providerName : "+providerDTO.getProviderName()
				+" secretKey:"+providerDTO.getSecretKey()+"  accountId : "+providerDTO.getAccountId()
				+"  url : "+providerDTO.getAccountId());
		return "provider has been updated";
		}catch(Exception e) {
			
			logger.error("providerName : "+providerDTO.getProviderName()
			+" secretKey:"+providerDTO.getSecretKey()+"  accountId : "+providerDTO.getAccountId()
			+"  url : "+providerDTO.getAccountId());
			return "Error updating provider details";
		}
	}
	
	
}
