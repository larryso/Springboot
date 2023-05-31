package proxy.cglibProxy;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CglibProxyTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /**
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue) ()->
            "Hello Larry"
        );
        PersonService proxy = (PersonService) enhancer.create();
        System.out.println(proxy.sayHello(null));
         **/

        /**
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args2, proxy)->{
            if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
                return "Dynamic Proxy";
            }else {
                return proxy.invokeSuper(obj, args2);
            }
        });
        Object proxy = (Object) enhancer.create();
        System.out.println(proxy.getClass() + "---"+ proxy.equals("true"));
        PersonService proxy2 = (PersonService) enhancer.create();
        System.out.println(proxy2.sayHello(null));
         **/

        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.setSuperclass(PersonService.class);
        beanGenerator.addProperty("name", String.class);
        PersonService proxyPersonService = (PersonService) beanGenerator.create();
        Method setter = proxyPersonService.getClass().getMethod("setName", String.class);
        setter.invoke(proxyPersonService, "string value set by cglib");
        System.out.println(proxyPersonService.getClass().getMethod("getName").invoke(proxyPersonService));
        proxyPersonService.sayHello("Larry Song");

    }
}
