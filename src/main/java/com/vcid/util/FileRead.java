package com.vcid.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;

public class FileRead
{
    private String file;
    private String extension;

    public FileRead(String file, String extension)
    {
        this.file = file;
        this.extension = extension;
    }

    public String readFile()
    {

        if(file.equals("/"))
        {
            file = "/index" + extension;

            System.out.println(file);

        }

        String file_path = "www" + file;
        File arquivo = new File(file_path);

        if (!arquivo.exists())
        {
            return getFileResponse("404.html");
        }else
        {


            String[] split = file.split("/");

            String fileExtenson = FilenameUtils.getExtension(split[1]);

            if (fileExtenson.equals("html"))
            {
                return getFileResponse(file_path);
            }else if (fileExtenson.equals("pl"))
            {
                try
                {
                    Process process = Runtime.getRuntime().exec("perl " +file_path);

                    StringBuilder output = new StringBuilder();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;

                    while ((line = reader.readLine()) != null)
                    {
                        output.append(line + "\n");
                    }

                    int exitval = process.waitFor();

                    System.out.println(exitval);

                    if(exitval == 0)
                    {
                        System.out.println(output);
                        return output.toString();
                    }


                }catch (IOException e)
                {
                    getFileResponse(file_path);
                }catch (InterruptedException e)
                {
                    getFileResponse(file_path);
                }
            }else if (fileExtenson.equals("php"))
            {
                try
                {
                    Process process = Runtime.getRuntime().exec("php " + file_path);

                    StringBuilder output = new StringBuilder();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;

                    while ((line = reader.readLine()) != null)
                    {
                        output.append(line + "\n");
                    }

                    int exitval = process.waitFor();

                    System.out.println(exitval);

                    if(exitval == 0)
                    {
                        System.out.println(output);
                        return output.toString();
                    }


                }catch (IOException e)
                {
                    getFileResponse(file_path);
                }catch (InterruptedException e)
                {
                    getFileResponse(file_path);
                }
            }
        }
        return "";

    }

    public static String render(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public String getFileResponse(String path)
    {
        try
        {
            String response = render(path, StandardCharsets.UTF_8);

            return response;

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }


}
