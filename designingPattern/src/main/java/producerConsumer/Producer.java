package producerConsumer;

public class Producer implements Runnable {
    private DataQueue dataQueue;
    private volatile Boolean runFlag;

    public Producer(DataQueue dataQueue, Boolean runFlag){
        this.dataQueue = dataQueue;
        this.runFlag = runFlag;
    }
    @Override
    public void run() {

    }
    public void produce() {
        while (runFlag) {
           //Message message = generateMessage();
            while (dataQueue.isFull()) {
                try {
                    dataQueue.waitOnFull();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runFlag) {
                break;
            }
            //dataQueue.add(message);
            dataQueue.notifyAllForEmpty();
        }
    }

//    private Message generateMessage() {
//    }

    public void stop(){
        this.runFlag = false;
    }
}
