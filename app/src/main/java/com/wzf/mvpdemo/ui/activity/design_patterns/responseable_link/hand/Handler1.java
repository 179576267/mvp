package com.wzf.mvpdemo.ui.activity.design_patterns.responseable_link.hand;

public class Handler1 extends Handler{

	@Override
	public void handle(AbstractRequest abstractRequest) {
		System.out.println("----handle1  处理请求: "+abstractRequest.getRequestLevel());
	}

	@Override
	public int getHandleLevel() {
		return 1;
	}
  
}
