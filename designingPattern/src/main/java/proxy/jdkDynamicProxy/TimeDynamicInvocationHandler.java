package proxy.jdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TimeDynamicInvocationHandler implements InvocationHandler {
    private Object target;
    private final Map<String, Method> methods = new HashMap<>();
    public TimeDynamicInvocationHandler(Object target){
        this.target = target;
        for(Method method: target.getClass().getDeclaredMethods()){
            methods.put(method.getName(), method);
        }
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke(target, args);
        long end = System.nanoTime();
        return result;
    }
}
