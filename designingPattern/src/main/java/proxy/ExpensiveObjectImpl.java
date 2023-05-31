package proxy;

public class ExpensiveObjectImpl implements ExpensiveObj {
    public ExpensiveObjectImpl(){
        heavyInitialConfiguration();
    }
    @Override
    public void process() {
        System.out.println("Processing completed");
    }
    private void heavyInitialConfiguration(){
        // do initial ....
    }
}
