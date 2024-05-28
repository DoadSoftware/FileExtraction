package com.fileextraction.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fileextraction.model.Configurations;
import com.fileextraction.service.FileExtractionService;
import com.fileextraction.util.FileExtractionFunctions;
import com.fileextraction.util.FileExtractionUtil;

import net.sf.json.JSONObject;

@Controller
public class IndexController 
{
	@Autowired
	FileExtractionService fileExtractionService;
	public static Configurations session_Configurations;
	String session_broadcaster;
	String username;
	String password;
	int port_number,time_duration,count,match_number;
	
	@RequestMapping(value = {"/","/initialise"}, method={RequestMethod.GET,RequestMethod.POST}) 
	public String initialisePage(ModelMap model) throws JAXBException  
	{
		if(new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.CONFIGURATIONS_DIRECTORY + FileExtractionUtil.FILEEXTRACTION_XML).exists()) {
			session_Configurations = (Configurations)JAXBContext.newInstance(Configurations.class).createUnmarshaller().unmarshal(
					new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.CONFIGURATIONS_DIRECTORY  + FileExtractionUtil.FILEEXTRACTION_XML));
		} else {
			session_Configurations = new Configurations();
			JAXBContext.newInstance(Configurations.class).createMarshaller().marshal(session_Configurations, 
					new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.CONFIGURATIONS_DIRECTORY + FileExtractionUtil.FILEEXTRACTION_XML));
		}
		
//		System.out.println(session_Configurations.getBroadcaster()); 
//		System.out.println(session_Configurations.getUpdateDuration()); 
//		System.out.println(session_Configurations.getPortNumber()); 
//		System.out.println(session_Configurations.getUsername()); 
//		System.out.println(session_Configurations.getPassword());
		model.addAttribute("session_Configurations",session_Configurations);
		
		return "initialise";
	}

	@RequestMapping(value = {"/fileextraction"}, method={RequestMethod.GET,RequestMethod.POST}) 
	public String fileExtractionPage(ModelMap model, MultipartHttpServletRequest request,
			@RequestParam(value = "selectedBroadcaster", required = false, defaultValue = "") String selectedBroadcaster,
			@RequestParam(value = "matchNumber", required = false, defaultValue = "") int matchNumber,
			@RequestParam(value = "ftpUsername", required = false, defaultValue = "") String ftpUsername,
			@RequestParam(value = "ftpPassword", required = false, defaultValue = "") String ftpPassword,
			@RequestParam(value = "updateDuration", required = false, defaultValue = "") int updateDuration,
			@RequestParam(value = "ftpPortNumber", required = false, defaultValue = "") int ftpPortNumber)
			throws UnknownHostException,JAXBException, IOException,IllegalAccessException,InvocationTargetException
	{
		session_broadcaster = selectedBroadcaster;
		match_number = matchNumber;
		username = ftpUsername;
		password = ftpPassword;
		time_duration = updateDuration;
		port_number = ftpPortNumber;
		
		session_Configurations = new Configurations(session_broadcaster,match_number, username, password, port_number,time_duration);
		
		JAXBContext.newInstance(Configurations.class).createMarshaller().marshal(session_Configurations, 
				new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.CONFIGURATIONS_DIRECTORY + FileExtractionUtil.FILEEXTRACTION_XML));
		
		model.addAttribute("session_broadcaster", session_broadcaster);
		model.addAttribute("match_number", match_number);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		model.addAttribute("time_duration", time_duration);
		model.addAttribute("port_number", port_number);
		return "fileextraction";
	}

	@RequestMapping(value = {"/processFileExtractionProcedures"}, method={RequestMethod.GET,RequestMethod.POST})    
	public @ResponseBody String processFileExtractionProcedures(
			@RequestParam(value = "whatToProcess", required = false, defaultValue = "") String whatToProcess,
			@RequestParam(value = "valueToProcess", required = false, defaultValue = "") String valueToProcess) 
					throws IOException, IllegalAccessException, InvocationTargetException, JAXBException, InterruptedException
	{	
		switch (whatToProcess.toUpperCase()) {
		case "START_FILE_EXTRACTION":
			FileExtractionFunctions.FTPDownload(port_number, match_number, username, password);
			FileExtractionFunctions.UnzipDownloadFile(match_number);
			return JSONObject.fromObject(null).toString();
		
		default:
			return JSONObject.fromObject(null).toString();
		}
	}
	
}