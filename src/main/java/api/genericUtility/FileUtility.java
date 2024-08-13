package api.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Nadhiya
 */
public class FileUtility {
	public String getDataFromPropertiesFile(String key) throws IOException {
		FileInputStream fis= new FileInputStream("./config/config_env_data.properties");
		Properties prop= new Properties();
		prop.load(fis);
		String data=prop.getProperty(key);
		return data;
	}
}
