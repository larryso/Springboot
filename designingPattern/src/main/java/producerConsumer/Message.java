package producerConsumer;

public class Message<T> {
    private long id;
    private T object;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
