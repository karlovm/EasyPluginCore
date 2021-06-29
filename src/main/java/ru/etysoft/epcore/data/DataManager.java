package ru.etysoft.epcore.data;

import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Set;

public class DataManager {

      public static void saveString(String data, String path) throws Exception
      {
              PrintWriter out = new PrintWriter(path);
              out.println(data);
      }


    public static String loadString(String path) throws Exception
    {
            StringBuffer fileData = new StringBuffer();
            BufferedReader reader = new BufferedReader(
                    new FileReader(path));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
            return fileData.toString();
    }
}
