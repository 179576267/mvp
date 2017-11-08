package com.wzf.mvpdemo.ui.activity.design_patterns.builder.designer;

public class Client {
	  public static void main(String[] args) {    
		     (new WorkBuilder()).makeWindow("蓝色玻璃").makeFloor("黄色地板").makeChat("").show();; //获取工人对象
		     System.out.println("");;   //工人交房
		  }  
}
