package utilities;

import apiClasses.UserPosts;

import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class BaseSteps {
    private Properties prop;
    private Logger log;
    private String baseURI;
    public String getBaseURI(){
        log=LogManager.getLogger(this.getClass().getName());
        prop=new  Properties();
        try {
            prop.load(BaseSteps.class.getClassLoader().getResourceAsStream("config.properties"));
            baseURI = prop.getProperty("baseURI");
            log.info("Base URI:" + baseURI);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baseURI;
    }
}
