package utilities;

import apiClasses.UserPosts;

import java.io.IOException;
import java.util.Properties;

public class BaseSteps {
    private Properties prop;
    private String baseURI;
    public String getBaseURI(){
        prop=new  Properties();
        try {
            prop.load(BaseSteps.class.getClassLoader().getResourceAsStream("config.properties"));
            baseURI = prop.getProperty("baseURI");
            System.out.println("Base URI:" + baseURI);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baseURI;
    }
}
