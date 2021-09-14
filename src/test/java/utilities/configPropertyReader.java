package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configPropertyReader {
	
  /** reads the propert from the specified property file and returns it
 * @param propertyname
 * 								propertyname which is to be returned
 * @param propertiesfilename
 * 								filename from which property should be read
 * @return
 * @throws IOException
 */
public static String readAndGetProperty(String propertyname, String propertiesfilename) throws IOException
  {
	  Properties prop = new Properties();
	  String filename = System.getProperty("user.dir") + File.separator + propertiesfilename;
      FileInputStream fis = new FileInputStream(filename);
      prop.load(fis);
      String property = prop.getProperty(propertyname);
      return property;
  }
}
