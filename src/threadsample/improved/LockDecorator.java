package threadsample.improved;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDecorator implements AutoCloseable {

    private Lock lock;

    public LockDecorator(){
        lock = new ReentrantLock();
    }

    public LockDecorator lock(){
        lock.lock();
        return this;
    }

    @Override
    public void close() throws Exception {
        lock.unlock();
    }
}
