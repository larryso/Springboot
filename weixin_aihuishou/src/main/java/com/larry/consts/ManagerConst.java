package com.larry.consts;

public class ManagerConst {
	enum AccountStatus{
		ACTIVE(0),INACTIVE(1);
		private int status;
		AccountStatus(int status){
			this.status = status;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}
		
	}
	public final static int ACCOUNT_STATUS_ACTIVE = AccountStatus.ACTIVE.getStatus();
	public final static int ACCOUNT_STATUS_INACTIVE = AccountStatus.INACTIVE.getStatus();

}
