package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.export_file;

/**
 * 导出数据库文件
 * @author Jason
 * QQ: 1476949583
 * @date 2016年12月16日
 * @version 1.0
 */
public class ExportDBOperator extends ExportOperate{

	@Override
	public ExportFileApi newFileApi() {
		return new ExportDBFile();
	}
	
	

}
