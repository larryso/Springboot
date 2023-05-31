package proxy.jdkDynamicProxy;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;

public class DynamicaProxyTest {
    public static void main(String[] args) {
        /**
        Map proxyInstance = (Map) Proxy.newProxyInstance(DynamicaProxyTest.class.getClassLoader(),
                new Class[] {Map.class},
                new DynamicInvocationHandler());
        proxyInstance.put("key", "Dynamic Proxy");
        System.out.println(proxyInstance.get("key"));
         **/
        /**
        Map proxyInstance =  (Map) Proxy.newProxyInstance(DynamicaProxyTest.class.getClassLoader(),
                new Class[] {Map.class},
                ((proxy, method, args1) -> {
                    if(method.getName().equalsIgnoreCase("isEmpty")){
                        return true;
                    }else{
                        return 54;
                    }
                }));
        System.out.println(proxyInstance.isEmpty());
         */


    }
}
