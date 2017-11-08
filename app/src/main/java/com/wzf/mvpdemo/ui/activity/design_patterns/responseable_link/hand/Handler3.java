package com.wzf.mvpdemo.ui.activity.design_patterns.responseable_link.hand;

public class Handler3   extends Handler {

	@Override
	public void handle(AbstractRequest abstractRequest) {
		System.out.println("----handle3  处理请求: "+abstractRequest.getRequestLevel());
	}

	@Override
	public int getHandleLevel() {
		return 3;
	}
}