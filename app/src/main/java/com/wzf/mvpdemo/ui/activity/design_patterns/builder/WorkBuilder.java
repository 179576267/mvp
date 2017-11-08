package com.wzf.mvpdemo.ui.activity.design_patterns.builder;

public class WorkBuilder  implements Build{
	private Room room=new Room();
	@Override
	public void makeWindow() {
		room.setFloor("地板  ");
	}

	@Override
	public void makeFloor() {
		room.setWindow("窗户");
	}

	@Override
	public Room getRoom() {
		return room;
	}

}
