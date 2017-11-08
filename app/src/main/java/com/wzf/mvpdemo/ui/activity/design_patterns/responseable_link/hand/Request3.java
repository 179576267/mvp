package com.wzf.mvpdemo.ui.activity.design_patterns.responseable_link.hand;

public class Request3  extends AbstractRequest{

	public Request3(Object object) {
		super(object);
	}

	@Override
	public int getRequestLevel() {
		return 3;
	}

}
