package com.wzf.mvpdemo.ui.activity.design_patterns.responseable_link.hand;

public class Request2  extends AbstractRequest{

	public Request2(Object object) {
		super(object);
	}

	@Override
	public int getRequestLevel() {
		return 2;
	}

}
