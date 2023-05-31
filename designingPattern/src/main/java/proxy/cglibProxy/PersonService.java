package proxy.cglibProxy;

public class PersonService {
    public String sayHello(String name){
        System.out.println("Hello " + name);
        return "Hello " + name;
    }
    public Integer lenthOfName(String name){
        return name.length();
    }
}
