package com.wzf.mvpdemo.ui.activity.design_patterns.responseable_link.hand;

public class Handler2 extends Handler {

	@Override
	public void handle(AbstractRequest abstractRequest) {
		System.out.println("----handle2  处理请求: "+abstractRequest.getRequestLevel());
	}

	@Override
	public int getHandleLevel() {
		return 2;
	}

}
