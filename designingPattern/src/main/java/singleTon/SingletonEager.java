package singleTon;

public class SingletonEager {
	private static volatile SingletonEager sc = null;
	private SingletonEager() {
		
	}
	public static SingletonEager getInstance() {
		if(sc == null) {
			synchronized(SingletonEager.class) {
				sc = new SingletonEager();
			}
			
		}
		return sc;
	}

}
