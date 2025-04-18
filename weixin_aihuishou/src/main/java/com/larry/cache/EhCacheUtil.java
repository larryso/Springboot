package com.larry.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

public class EhCacheUtil {
	public static final String CACHE_KEY_PROD="productList";
	public static final String CACHE_KEY_SHOP_PROD_CAT="shopProductCategory";
	public static final String CACHE_KEY_SHOP_PROD = "shopProduct";
	public static final String CACHE_KEY_SHOP_PROD_MAP = "shopProductMap";
	public static final String CACHE_KEY_HASHPROD = "hashProducts";
	private static CacheManager cacheManager = null;
	private static Cache cache = null;
	static {
		initCacheManager();
		initCache("wonddream_ehcache");
	}

	public static CacheManager initCacheManager() {
		try {
			if (cacheManager == null)
				cacheManager = CacheManager.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cacheManager;
	}

	public static CacheManager initCacheManager(String path) {
		try {
			if (cacheManager == null) {
				cacheManager = CacheManager.getInstance().create(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cacheManager;

	}

	public static Cache initCache(String cacheName) {
		checkCacheManager();
		if (null == cacheManager.getCache(cacheName)) {
			cacheManager.addCache(cacheName);
		}
		cache = cacheManager.getCache(cacheName);
		return cache;

	}

	public static void put(Object key, Object value) {
		checkCache(); // 创建Element,然后放入Cache对象中
		Element element = new Element(key, value);
		cache.put(element);
	}

	public static Object get(Object key) {
		checkCache();
		Element element = cache.get(key);
		if (null == element) {
			return null;
		}
		return element.getObjectValue();
	}

	public static Cache initCache(String cacheName, int maxElementsInMemory, boolean overflowToDisk, boolean eternal,
			long timeToLiveSeconds, long timeToIdleSeconds) throws Exception {
		try {
			CacheManager singletonManager = CacheManager.create();
			Cache myCache = singletonManager.getCache(cacheName);
			if (myCache != null) {
				CacheConfiguration config = cache.getCacheConfiguration();
				config.setTimeToLiveSeconds(timeToLiveSeconds);
				config.setMaxEntriesLocalHeap(maxElementsInMemory);
				config.setOverflowToDisk(overflowToDisk);
				config.setEternal(eternal);
				config.setTimeToIdleSeconds(timeToIdleSeconds);
			}
			if (myCache == null) {
				Cache memoryOnlyCache = new Cache(cacheName, maxElementsInMemory, overflowToDisk, eternal,
						timeToLiveSeconds, timeToIdleSeconds);
				singletonManager.addCache(memoryOnlyCache);
				myCache = singletonManager.getCache(cacheName);
			}
			return myCache;

		} catch (Exception e) {

			throw new Exception("init cache " + cacheName + " failed!!!");

		}

	}

	public static boolean removeElment(String cacheName, String key) throws Exception {
		try {
			CacheManager myManager = CacheManager.create();
			Cache myCache = myManager.getCache(cacheName);
			myCache.remove(key);
			return true;
		} catch (Exception e) {
			throw new Exception("remove cache " + cacheName + " failed!!!");
		}
	}

	public static boolean removeAllEhcache(String cacheName) throws Exception {
		try {
			CacheManager myManager = CacheManager.create();
			myManager.removeAllCaches();
			return true;
		} catch (Exception e) {
			throw new Exception("remove cache " + cacheName + " failed!!!");
		}
	}

	public static boolean removeAllElment(String cacheName, String key) throws Exception {
		try {
			CacheManager myManager = CacheManager.create();
			Cache myCache = myManager.getCache(cacheName);
			myCache.removeAll();
			return true;
		} catch (Exception e) {
			throw new Exception("remove cache " + cacheName + " failed!!!");
		}
	}

	public static void shutdown() {

		cacheManager.shutdown();

	}

	private static void checkCacheManager() {
		if (null == cacheManager) {
			throw new IllegalArgumentException("调用前请先初始化CacheManager值：EhCacheUtil.initCacheManager");
		}
	}

	private static void checkCache() {
		if (null == cache) {
			throw new IllegalArgumentException("调用前请先初始化Cache值：EhCacheUtil.initCache(参数)");
		}
	}

}
