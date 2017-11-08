package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.export_file;

public class ExportExcelFile implements ExportFileApi {

	@Override
	public void export(String data) {
		System.out.println("Excel表格");
	}

}
