package proxy;

public class ExpensiveObjectProxy implements ExpensiveObj {
    private static ExpensiveObj obj;
    @Override
    public void process() {
        if(obj== null){
            obj = new ExpensiveObjectImpl();
        }
        obj.process();
    }
}
