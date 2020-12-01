package com.ylzinfo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {

    /**
     * 
     * @param inputStream
     *            已经把流关闭，不需要在关闭流
     * @return
     * @throws IOException
     */
    public static String readInputStream(InputStream inputStream) throws IOException {
        return readInputStream(new InputStreamReader(inputStream, "UTF-8"));
    }

    /**
     * 
     * @param inputStreamReader
     *            已经把流关闭，不需要在关闭流
     * @return
     * @throws IOException
     */
    public static String readInputStream(InputStreamReader inputStreamReader) throws IOException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(inputStreamReader);
            String str = "", sCurrentLine = "";
            while ((sCurrentLine = reader.readLine()) != null) {
                str = str + sCurrentLine;
            }

            return str;

        } finally {
            reader.close();
        }

    }
}
