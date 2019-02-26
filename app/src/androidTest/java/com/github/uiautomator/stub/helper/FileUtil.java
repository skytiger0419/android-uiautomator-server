package com.github.uiautomator.stub.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
    public static void writeFile(String filePath,String message) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(message);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static String readFile(String path){
        File f = new File(path);
        if(f.exists()){
            try{
                InputStream stream = new FileInputStream(f);
                return readStream(stream);
            }catch (Exception e){

            }
        }
        return null;
    }

    /**
     * 以utf-8的形式进行文件流读取，并返回字符串
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String readStream(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        reader.close();
        return (sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "");
    }
}
