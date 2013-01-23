package com.webana.weibo.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

public class FileUtil {
	 public static List<String> readFileByLines(String fileName) {
	        File file = new File(fileName);
	        List<String> lines = new ArrayList<String>();
	        BufferedReader reader = null;
	        try {
	        	 InputStreamReader read = new InputStreamReader (new FileInputStream(file),"UTF-8");
	            reader = new BufferedReader(read);
	            String tempString = null;
	            int line = 1;
	            while ((tempString = reader.readLine()) != null) {
	            	lines.add(tempString);
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	        return lines;
	    }
	 

	 public static List<File> listFiles(String path, String suffix, String startWith) {
	     List<File> files = new ArrayList<File>();   
		 File file = new File(path);
	        File[] fs = file.listFiles();
	        for (File f:fs){
	        	if (f.getName().endsWith(suffix) && (f.getName().startsWith(startWith))){
	        		files.add(f);
	        	}
	        }
	        return files;
	    }
	 
	 public static Map<String, String> listFilesWithTime(String path, String suffix) {
		 TreeMap<String, String> files = new TreeMap<String, String>();
		 File file = new File(path);
	        File[] fs = file.listFiles();
	        List<File> list = Arrays.asList(fs);
	        for (File f:list){
	        	if (f.getName().endsWith(suffix)){
	        		long time = f.lastModified();
	        		Date date = new Date(time);
	        		String lastModifiedDate = getLocalDateFormat().format(date);
	        		files.put(f.getName(),lastModifiedDate);
	        	}
	        }
	        return files;
	    }
	 
	public static DateFormat getLocalDateFormat() {
		TimeZone timeZoneNY = TimeZone.getTimeZone("GMT+8");
		 SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		 outputFormat.setTimeZone(timeZoneNY);
		return outputFormat;
	}

	public static String readTxtFile(File filename) {
		String read;
		FileReader fileread;
		String readStr = "";
		try {
			fileread = new FileReader(filename);
			BufferedReader bufread = new BufferedReader(fileread);
			while ((read = bufread.readLine()) != null) {
				readStr = readStr + read + "\r\n";
			}
			bufread.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readStr;
	}

	public static void writeTxtFile(File filename, String text) throws IOException{
        String filein = text;
        RandomAccessFile mm = null;
        try {
            mm = new RandomAccessFile(filename, "rw");
            mm.writeBytes(filein);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (mm != null) {
                try {
                    mm.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
	}
}
