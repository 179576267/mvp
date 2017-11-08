package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.export_file;

public abstract class ExportOperate {

	/**
	 * 实例化ExportFileApi
	 * @return
	 */
	public abstract ExportFileApi newFileApi();
	
	/**
	 * 导出数据
	 * @param data
	 */
	public void export(String data){
		ExportFileApi file = newFileApi();
		file.export(data);
	}
	
}
