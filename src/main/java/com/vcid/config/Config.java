package com.vcid.config;

public class Config
{
    private int port;
    private String webroot;
    private String fileExtension;

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getWebroot()
    {
        return webroot;
    }

    public void setWebroot(String webroot)
    {
        this.webroot = webroot;
    }

    public String getFileExtension()
    {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension)
    {
        this.fileExtension = fileExtension;
    }
}
