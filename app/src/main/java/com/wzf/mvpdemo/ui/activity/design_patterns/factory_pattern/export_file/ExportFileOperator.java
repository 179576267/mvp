package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.export_file;

public class ExportFileOperator extends ExportOperate{

	@Override
	public ExportFileApi newFileApi() {
		return new ExportTextFile();
	}
	
}
