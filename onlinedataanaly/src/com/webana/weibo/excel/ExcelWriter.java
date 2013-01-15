package com.webana.weibo.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webana.weibo.action.model.UserEntry;



/**
 * 
 * @author Andrew
 *
 */
public class ExcelWriter {
	
	public static final String EXCEL_PREFIX = "weibodailyreport";
	Workbook wb;
	String rootPath = "";
	int dayofstat = 1;
	private static final Logger logger = LoggerFactory.getLogger(ExcelWriter.class);
	public static final String EXCEL_DEST_DIR = "resources";
	public static final String GOV_LIST_DIR = "resources";

	public void initExcel(String rootPath, int dayofstat){
		wb = new XSSFWorkbook();
		this.rootPath = rootPath;
		this.dayofstat = dayofstat;
	}
	public void writeMostHotTopics(List<UserEntry> ues){
		Collections.sort(ues, new Comparators());
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(0);		
		String[] titles = {"微博名称", "热门信息", "转发", "评论"};
		for (int cell = 0; cell < 4; cell ++){
			row.createCell(cell).setCellValue(titles[cell]);
		}
				
		for (int rowNo = 1; rowNo <= ues.size(); rowNo++){
			Row valuerow = sheet.createRow(rowNo);
			for (int cell = 0; cell < 4; cell ++){
				UserEntry ue = ues.get(rowNo-1);
				Object[] values = ue.toHotTopicArray();
				if (values[cell]!=null){
					if (isInteger(values[cell].toString())){
						valuerow.createCell(cell).setCellValue(Integer.parseInt(values[cell].toString()));
					}else{
						valuerow.createCell(cell).setCellValue(values[cell].toString());
					}
				}
			}
			
		}		
	}
	
	public void writeUserEntry(List<UserEntry> ues, int sheetno) {
		logger.info("writeUserEntry........");
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(0);		
		String[] titles = {"单位名称", "微博名称", "发布总数", "当日发布量", "原创量", "转发量", "粉丝总量", 
				"粉丝总量", "热门信息", "转发", "评论", "网友", "提问内容", "网友", "提问", "官方回复"};
		for (int cell = 0; cell < 16; cell ++){
			row.createCell(cell).setCellValue(titles[cell]);
		}
		
		setYesterdayFollowers(ues, sheetno);// set yesterday followers by look up yesterdays excel.
		addTotalToList(ues);// add total entry.
				
		for (int rowNo = 1; rowNo <= ues.size(); rowNo++){
			Row valuerow = sheet.createRow(rowNo);
			for (int cell = 0; cell < 16; cell ++){
				UserEntry ue = ues.get(rowNo-1);
				Object[] values = ue.toArray();
				if (values[cell]!=null){
					if (isInteger(values[cell].toString())){
						valuerow.createCell(cell).setCellValue(Integer.parseInt(values[cell].toString()));
					}else{
						valuerow.createCell(cell).setCellValue(values[cell].toString());
					}
				}
			}
			
		}		
	}
	
	private void addTotalToList(List<UserEntry> ues){
		UserEntry total = new UserEntry();		
		for (UserEntry ue:ues){
			if(ue==null)
				continue;
			total.setDailyStatusCount(ue.getDailyStatusCount()+total.getDailyStatusCount());
			total.setFollowers(ue.getFollowers()+total.getFollowers());
			total.setFollowersYesterday(ue.getFollowersYesterday()+total.getFollowersYesterday());
			total.setStatusCount(ue.getStatusCount()+total.getStatusCount());
		}
		ues.add(total);
	}
	
	private void setYesterdayFollowers(List<UserEntry> ues, int sheetno){
		Map<String, Integer> yesterdayFollowersMap = this.getYesterdayFollowersCount(sheetno);
		for (UserEntry ue:ues){
			if(ue==null)
				continue;
			Integer yesterFollowers = yesterdayFollowersMap.get(ue.getScreenNames());
			if (yesterFollowers!=null) {						
				ue.setFollowersYesterday(yesterFollowers);
			}
		}
	}
	
	public String writeToFile() throws IOException {
		Calendar cal = Calendar.getInstance();
		String fileName = mkFileName(cal.getTime());
		FileOutputStream out = new FileOutputStream(fileName);
		wb.write(out);
		out.close();
		return fileName;
	}
	
	 public boolean isInteger(String str) {
         str = str.trim();
         try {
             Integer.parseInt(str);
             return true;
         }
         catch (NumberFormatException ex) {
             return false;
         }
     }
	 
	private String mkFileName(Date date) {
        String DATE_FORMAT = "MM.dd";        
        TimeZone timeZoneNY = TimeZone.getTimeZone("GMT+8");
		 SimpleDateFormat outputFormat = new SimpleDateFormat(DATE_FORMAT, Locale.CHINA);
		 outputFormat.setTimeZone(timeZoneNY);
        String filename = rootPath + File.separator + EXCEL_DEST_DIR + File.separator + EXCEL_PREFIX + outputFormat.format(date) + ".xlsx";
        return filename;
	}
	
	public Map<String, Integer> getYesterdayFollowersCount(int sheetno){
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE,   -dayofstat);
			Date yesterday = cal.getTime();
			File file = new File(this.mkFileName(yesterday));
			if (!file.exists()){
				return map;
			}
			XSSFWorkbook xwb = new XSSFWorkbook(this.mkFileName(yesterday));
			Sheet sheet = xwb.getSheetAt(sheetno);
			for (Row row: sheet){
				if (row==null || row.getCell(1)==null || row.getCell(6)==null)
					continue;
				String screenName = row.getCell(1).toString();
				String followers = row.getCell(6).toString();
				try{
					float   floatNum=Float.parseFloat(followers);
					map.put(screenName, (int)floatNum);
				}catch(NumberFormatException e){
					continue;
				}
				
			}
		    } catch (IOException e) {
		      e.printStackTrace();
		    }			    
		return map;
	}
	
	class Comparators implements Comparator<UserEntry> {
		public int compare(UserEntry ue1, UserEntry ue2) {
			return (ue2.getTopic().getHotTopicRepostCount() + ue2.getTopic()
					.getHotTopicRepostCount())
					- (ue1.getTopic().getHotTopicRepostCount() + ue1.getTopic()
							.getHotTopicRepostCount());
		}

	}

}
