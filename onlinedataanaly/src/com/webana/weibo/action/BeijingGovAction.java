package com.webana.weibo.action;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.webana.weibo.action.service.BeijingGovService;
import com.webana.weibo.excel.ExcelWriter;
import com.webana.weibo.excel.FileUtil;


/**
 *
 * @author
 */
public class BeijingGovAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    
    private BeijingGovService service;
    
    private int dayOfstat;

    private int progress;
    
    Map<String, String> filenames;

	private String newFileName;
    
    private String newFileCreatedTime;

	/**ws.generateStatExcel(rootPath);
     * @return forward to build status
     */
    @Override
	public String execute() {
    	String rootPath = ServletActionContext.getServletContext().getRealPath("/");
		String resourcePath = rootPath + File.separator + ExcelWriter.EXCEL_DEST_DIR;
		filenames = FileUtil.listFilesWithTime(resourcePath, "xlsx");
        return INPUT;
    }

	public String analysis() {
		service = super.createBeijingGovService();
		if (service == null) {
			this.addActionError("请先使用微博账号登录才能查询使用查询");
		} else {
			String rootPath = ServletActionContext.getServletContext().getRealPath("/");
			service.setDayOfstat(dayOfstat);
			service.generateStatExcel(rootPath);

		}
		return "ajax";
	}

    public String progress() {
    	progress = service.getProgress();
    	if(progress >= 100) {
    		newFileName = new File(service.getNewFileName()).getName();
    		newFileCreatedTime = FileUtil.getLocalDateFormat().format(new Date());
    	}
    	return "ajax";
    }
    
    public int getProgress() {
		return progress;
	}

	public Map<String, String> getFilenames() {
		return filenames;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public String getNewFileCreatedTime() {
		return newFileCreatedTime;
	}

	public void setDayOfstat(int dayOfstat) {
		this.dayOfstat = dayOfstat;
	}
}
