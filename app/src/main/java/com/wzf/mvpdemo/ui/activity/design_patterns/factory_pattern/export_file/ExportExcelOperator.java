package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.export_file;

public class ExportExcelOperator extends ExportOperate {

	@Override
	public ExportFileApi newFileApi() {
		return new ExportExcelFile();
	}

}
