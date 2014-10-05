package configuration;

import java.io.*;
import java.util.Properties;

public class PropertiesFile {

    private String accessToken = "";
    private String loginVK = "";
    private String passwordVK = "";
    private String patchToXML = "";

    //Getters
    public String getAccessToken() {
        return accessToken;
    }

    public String getLoginVK() {
        return loginVK;
    }

    public String getPasswordVK() {
        return passwordVK;
    }

    public String getPatchToXML() {
        return patchToXML;
    }

    //Setters
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setLoginVK(String loginVK) {
        this.loginVK = loginVK;
    }

    public void setPasswordVK(String passwordVK) {
        this.passwordVK = passwordVK;
    }

    public void setPatchToXML(String patchToXML) {
        this.patchToXML = patchToXML;
    }


    public PropertiesFile() {


        String filePath = new File("").getAbsolutePath();
        String path = filePath + "config.properties";

        if ((new File(path).exists())) {
            readProperties(path);
        }
        else {
            writeAllProperties(path);
        }
    }

    void debugMethod () {

        /*String path = System.getProperty("user.dir")+"\\src\\configuration\\config.properties";
        readProperties(path);*/
    }

    private void readProperties (String path) {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(path);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            accessToken = prop.getProperty("accessToken");
            loginVK = prop.getProperty("loginVK");
            passwordVK = prop.getProperty("passwordVK");
            patchToXML = prop.getProperty("patchToXML");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeAllProperties (String path) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream(path);

            // set the properties value
            prop.setProperty("accessToken", accessToken);
            prop.setProperty("loginVK", loginVK);
            prop.setProperty("passwordVK", passwordVK);
            prop.setProperty("patchToXML", patchToXML);

            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

