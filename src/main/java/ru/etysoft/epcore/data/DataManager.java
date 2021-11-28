package ru.etysoft.epcore.data;

import org.bukkit.Bukkit;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class DataManager {

    public static boolean saveSerializable(Serializable data, String path) throws Exception
    {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(path)));
            out.writeObject(data);
            out.close();
            return true;
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
    }

    public static Serializable loadSerializable(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Serializable data = (Serializable) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveString(String data, String path) throws Exception
    {
            PrintWriter out = new PrintWriter(path);
            out.println(data);
            out.close();
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
