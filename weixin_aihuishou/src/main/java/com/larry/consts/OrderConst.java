package com.larry.consts;

public class OrderConst {
	enum OrderStatus{
		NEW_SUB(0),PROCESSING(1),CLOSED(2),CANCLED(3);
		private int status;
		OrderStatus(int status){
			this.status = status;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		
	}
	public static final int NEW_ORDER=OrderStatus.NEW_SUB.getStatus();
	public static final int PROCESSING_ORDER= OrderStatus.PROCESSING.getStatus();
	public static final int CLOSED_ORDER= OrderStatus.CLOSED.getStatus();
	public static final int CANCLED_ORDER=OrderStatus.CANCLED.getStatus();

}