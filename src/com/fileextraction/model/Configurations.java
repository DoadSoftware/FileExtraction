package com.fileextraction.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Configurations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configurations {
	
	@XmlElement(name="broadcaster")
	private String broadcaster;
	
	@XmlElement(name="matchNumber")
	private int matchNumber;
	
	@XmlElement(name="ftpUsername")
	private String username;
	
	@XmlElement(name="ftpPassword")
	private String password;
	
	@XmlElement(name="ftpPortNumber")
	private int portNumber;
	
	@XmlElement(name="updateDuration")
	private int updateDuration;
	
	public Configurations(String broadcaster, int matchNumber, String username, String password, int portNumber,
			int updateDuration) {
		super();
		this.broadcaster = broadcaster;
		this.matchNumber = matchNumber;
		this.username = username;
		this.password = password;
		this.portNumber = portNumber;
		this.updateDuration = updateDuration;
	}

	public Configurations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBroadcaster() {
		return broadcaster;
	}

	public void setBroadcaster(String broadcaster) {
		this.broadcaster = broadcaster;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public int getUpdateDuration() {
		return updateDuration;
	}

	public void setUpdateDuration(int updateDuration) {
		this.updateDuration = updateDuration;
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}

}
