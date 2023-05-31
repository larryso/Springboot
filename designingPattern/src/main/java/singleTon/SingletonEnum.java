package singleTon;

public enum SingletonEnum {
	INSTANCE;
	private DataSource ds = null;
	private SingletonEnum() {
		System.out.println("SingletonEnum cration..");
		ds = new DataSource();
		
	}
	public DataSource getDataSource() {
		return ds;
	}
	

}
final class DataSource{
	
}
