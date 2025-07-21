package interface2.web.userinfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import interface2.domain.userinfo.*;
import interface2.domain.userinfo.dao.UserInfoDao;
import interface2.domain.userinfo.dao.mysql.UserInforMysqlDao;
import interface2.domain.userinfo.dao.oracle.UserInforOracleDao;

public class UserInfoClient {

	public static void main(String[] args) throws IOException {
		InputStream is = UserInfoClient.class.getClassLoader().getResourceAsStream("db.properties");
		
		if(is == null) {
			System.out.println("db.properties 파일을 찾을 수 없습니다.");
			return;
		}
		
		Properties prop = new Properties();
		prop.load(is);
		
		String dbType = prop.getProperty("DBTYPE");
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId("choongang");
		userInfo.setPasswd("!@#$%");
		userInfo.setUserName("이순신");
		
		UserInfoDao userInfoDao = null;
		
		if(dbType.equals("ORACLE")) {
			userInfoDao = new UserInforOracleDao();
		} else if(dbType.equals("MYSQL")) {
			userInfoDao = new UserInforMysqlDao();
		}
		
		userInfoDao.insertUserInfo(userInfo);
		userInfoDao.updateUserInfo(userInfo);
		userInfoDao.deleteUserInfo(userInfo);
	}
}


