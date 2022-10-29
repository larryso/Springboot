package producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * bounded buffer that will be read by Producer and consumer
 */
public class DataQueue<T> {
    private final Queue<Message> queue = new LinkedList<>();
    private Integer maxSize;
    private final Object FULL_QUEUE = new Object();
    private final Object EMPTY_QUEUE = new Object();

    public DataQueue(Integer maxSize){
        this.maxSize = maxSize;
    }
    public boolean isFull(){
        return queue.size() == maxSize;
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    public void add(Message<T> message){
        synchronized (queue){
            queue.add(message);
        }
    }
    public Message<T> consume(){
        synchronized (queue){
            return queue.poll();
        }
    }
    public void waitOnFull() throws InterruptedException {
        synchronized (FULL_QUEUE){
            FULL_QUEUE.wait();
        }
    }
    public void waitOnEmpty() throws InterruptedException {
        synchronized (EMPTY_QUEUE){
            EMPTY_QUEUE.wait();
        }
    }
    public void notifyAllForFull(){
        synchronized (FULL_QUEUE){
            FULL_QUEUE.notifyAll();
        }
    }
    public void notifyAllForEmpty(){
        synchronized (EMPTY_QUEUE){
            EMPTY_QUEUE.notifyAll();
        }
    }

}
