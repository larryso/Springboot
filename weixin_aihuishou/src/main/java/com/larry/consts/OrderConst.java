package com.larry.consts;

import java.util.Calendar;
import java.util.Date;

public class OrderConst {
	enum OrderStatus {
		NEW_SUB(0), PROCESSING(1), CLOSED(2), CANCLED(3),REJECT(4);
		private int status;

		OrderStatus(int status) {
			this.status = status;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	}

	public static final int NEW_ORDER = OrderStatus.NEW_SUB.getStatus();
	public static final int PROCESSING_ORDER = OrderStatus.PROCESSING.getStatus();
	public static final int CLOSED_ORDER = OrderStatus.CLOSED.getStatus();
	public static final int CANCLED_ORDER = OrderStatus.CANCLED.getStatus();
	public static final int REJECTED_ORDER = OrderStatus.REJECT.getStatus();
	public static final String ORDER_PREFIX = "REC" + getDate();

	private static String getDate() {
		Calendar calendar = Calendar.getInstance();
		return new Integer(calendar.get(Calendar.YEAR)).toString() + new Integer((calendar.get(Calendar.MONTH) + 1)).toString() + calendar.get(Calendar.DATE);
	}
	public static void main(String[] args) {
		System.out.println(OrderConst.ORDER_PREFIX);
	}

}
