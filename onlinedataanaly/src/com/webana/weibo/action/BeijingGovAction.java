package com.webana.weibo.action;

import java.io.File;
import java.util.Date;
import java.util.Map;

import com.webana.weibo.action.service.BeijingGovService;
import com.webana.weibo.excel.FileUtil;
import com.webana.weibo.util.Constants;


/**
 *
 * @author
 */
public class BeijingGovAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    
    private static final String  REPORT_RUNNING = "reportIsrunning";
    
    private BeijingGovService service;
    
    private int dayOfstat;

    private int progress;
    
    Map<String, String> filenames;

	private String newFileName;
    
    private String newFileCreatedTime;
    
    private String errorMsg;

	/**ws.generateStatExcel(rootPath);
     * @return forward to build status
     */
    @Override
	public String execute() {
		String resourcePath = getResourceRootPath() + Constants.EXCEL_DEST_DIR;
		filenames = FileUtil.listFilesWithTime(resourcePath, "xlsx");
        return INPUT;
    }

	public String analysis() {
		errorMsg = null;
		service = super.createBeijingGovService();
		if (service == null) {
			errorMsg = "请先使用微博账号登录才能生成报表";
		} else {
			if(progress > 0 && progress < 100) {
				errorMsg = "正在生成报表，请勿重复提交";
				return "ajax";
			}
			if(super.getApplicationAttibute(REPORT_RUNNING) != null && (Boolean)super.getApplicationAttibute(REPORT_RUNNING) == true) {
				errorMsg = "正在生成报表，请稍后刷新页面查看";
				return "ajax";
			}
			super.setApplicationAttibute(REPORT_RUNNING, true);			
			service.setDayOfstat(dayOfstat);
			service.generateStatExcel(getResourceRootPath());

		}
		return "ajax";
	}

    public String progress() {
    	progress = service.getProgress();
    	if(progress >= 100) {
    		newFileName = new File(service.getNewFileName()).getName();
    		newFileCreatedTime = FileUtil.getLocalDateFormat().format(new Date());
    		super.setApplicationAttibute("reportIsrunning", false);
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

	public String getErrorMsg() {
		return errorMsg;
	}

}
