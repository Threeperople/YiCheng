package com.example.administrator.yicheng.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jensen on 2016/8/10.
 */
public class StreamToByteUtils {
    public static  byte[] getByte(InputStream is){
        try {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte[] b=new byte[1024];
        int len=0;
            while((len=is.read(b))!=-1){
                bos.write(b,0,len);
                bos.flush();
            }
            is.close();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
