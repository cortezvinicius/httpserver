package com.vcid.http;



import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.vcid.config.Config;
import com.vcid.config.ConfigManager;
import com.vcid.util.FileRead;
import sun.net.httpserver.HttpServerImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class Server
{
    public static void main(String[] args)
    {
        ConfigManager configManager = new ConfigManager();
        configManager.json();
        configManager.setConfig();
        Config config = configManager.getConfig();



        try
        {
            HttpServer httpServer = HttpServerImpl.create(new InetSocketAddress(config.getPort()),0);
            HttpContext context = httpServer.createContext(config.getWebroot());
            context.setHandler(new HttpHandler()
            {
                @Override
                public void handle(HttpExchange httpExchange) throws IOException
                {
                    URI uri = httpExchange.getRequestURI();

                    FileRead fileRead = new FileRead(uri.toString(), config.getFileExtension());

                    String file = fileRead.readFile();

                    byte[] bits = file.getBytes();
                    httpExchange.sendResponseHeaders(200,bits.length);
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(bits);
                    os.close();
                }
            });
            httpServer.start();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}