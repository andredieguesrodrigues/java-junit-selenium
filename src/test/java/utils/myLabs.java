package utils;

import java.io.IOException;
import java.util.Properties;

import static utils.Support.loadConfig;

public class myLabs {

    public static void main(String args[]) throws IOException {

        Properties prop = loadConfig();
        String url = prop.getProperty("base_url");

        System.out.println("Current working directory in Java : " + url);
    }

}