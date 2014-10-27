package com.cx.bank.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


//��ȡ���ݿ�Ļ�����Ϣ
public class ReadDBConfig {

	private String name;
	private String driver;
	private String url;
	private String user;
	private String password;
	private String maxconns;
	private Properties prop = null;

	public ReadDBConfig() {
		prop = new Properties();
		try {
			//ͨ�����������ȡ�ļ�
			prop.load(new FileReader(ReadDBConfig.class.getClassLoader()
					.getResource("config.properties").getPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getName(){
		this.name=prop.getProperty("name");
		return name;
	}

	public String getDriver() {
		this.driver = prop.getProperty("driver");
		return driver;
	}

	public String getUrl() {
		this.url = prop.getProperty("url");
		return url;
	}

	public String getUser() {
		this.user = prop.getProperty("user");
		return user;
	}

	public String getPassword() {
		this.password = prop.getProperty("password");
		return password;
	}

	public String getMaxconns() {
		this.maxconns = prop.getProperty("maxconns");
		return maxconns;
	}

}
