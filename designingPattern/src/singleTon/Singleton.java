package singleTon;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Singleton implements Serializable {
	private static final long serialVersionUID = -1093810940935189395L;
	private volatile static Singleton instance = null;

	private Singleton() {
	}

	public Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	private Object readResolve() throws ObjectStreamException {
		return instance;
	}

	private Object writeReplace() throws ObjectStreamException {
		return instance;
	}

	public Object clone()throws CloneNotSupportedException{
		throw new CloneNotSupportedException("singleton cannot be cloned");
	}
	private static Class getClass(String classname) throws ClassNotFoundException{
		 ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		 if(classLoader == null) {
			 classLoader = Singleton.class.getClassLoader();
		 }
		 return classLoader.loadClass(classname);
	}

}
