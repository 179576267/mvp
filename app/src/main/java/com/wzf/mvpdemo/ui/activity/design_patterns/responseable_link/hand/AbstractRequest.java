package com.wzf.mvpdemo.ui.activity.design_patterns.responseable_link.hand;

public abstract class AbstractRequest {
	private Object object;
	
	public AbstractRequest(Object object)
	{
		this.object=object;
	}
	/**
	 * 具体的内容对象
	 * @return
	 */
	public Object getContent()
	{
		return object;
	}
	/**
	 * 获取请求级别
	 */
	public abstract int getRequestLevel();
}
