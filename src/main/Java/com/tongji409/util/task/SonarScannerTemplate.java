package com.tongji409.util.task;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;


/**
 * @author lijiechu
 * @create on 2018/9/4
 * @description
 */
public class SonarScannerTemplate {

    private String projectVersion;

    private String projectName;

    private static String propertiesContent;


    static {
        try {
            File tmplFile = ResourceUtils.getFile("classpath:sonar-project.properties.tmpl");
            propertiesContent= FileUtils.readFileToString(tmplFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SonarScannerTemplate sonarScannerTemplate = new SonarScannerTemplate("1.0", "hahah");
        System.out.println(sonarScannerTemplate.constructProperties());
    }

    public SonarScannerTemplate(String projectVersion, String projectName) {
        this.projectVersion = projectVersion;
        this.projectName = projectName;
    }

    public String constructProperties() {
        if(StringUtils.isEmpty(this.projectVersion) || StringUtils.isEmpty(this.projectName)) {
            throw new IllegalArgumentException("parameters not right");
        } else {
            Date date = new Date();
            String hashSalt = date.getTime() + this.projectVersion + this.projectName;
            String projectKey = DigestUtils.md5DigestAsHex(hashSalt.getBytes());
            String properties = MessageFormat.format(propertiesContent, projectKey, this.projectName, this.projectVersion, "." );
            return properties;
        }
    }



    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
