package com.wzf.mvpdemo.ui.activity.design_patterns.responseable_link.hand;

public class Request1 extends AbstractRequest{

	public Request1(Object object) {
		super(object);
		
		
	}

	@Override
	public int getRequestLevel() {
		return 1;
	}

}
