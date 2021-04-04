package com.vcid.config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ConfigManager
{

    private String webroot;
    private int port;
    private String extension;
    private static Config config;

    public void setConfig()
    {
        config = new Config();
        config.setPort(port);
        config.setWebroot(webroot);
        config.setFileExtension(extension);
    }

    public Config getConfig()
    {
        return config;
    }

    public void json()
    {
        @SuppressWarnings("unchecked")
        JSONParser jsonParser = new JSONParser();
        try
        {
            Object obj = jsonParser.parse(new FileReader("config.json"));

            JSONArray list = (JSONArray) obj;

            JSONObject jsonObject = (JSONObject) list.get(0);

            webroot = (String) jsonObject.get("webroot");
            String portString = (String) jsonObject.get("port");
            port = Integer.parseInt(portString);
            extension = (String) jsonObject.get("extension");




        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
