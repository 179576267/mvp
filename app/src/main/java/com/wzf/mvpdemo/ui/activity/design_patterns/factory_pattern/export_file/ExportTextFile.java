package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.export_file;

/**
 * 文本文件
 * @author Jason
 * QQ: 1476949583
 * @date 2016年12月16日
 * @version 1.0
 */
public class ExportTextFile implements ExportFileApi {

	@Override
	public void export(String data) {
		System.out.println("导出数据到文本文件...");
	}

}
