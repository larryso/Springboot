package singleTon;

public class Test {
	public static void main(String[] args) {
//		System.out.println(SingletonEager.sc);
//		System.out.println(SingletonEager.sc);
//		System.out.println(SingletonEager.sc);
		//System.out.println(new SingletonEager());
		DataSource ds = SingletonEnum.INSTANCE.getDataSource();
	}

}
