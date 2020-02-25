package threadsample.improved;

public class Resource {
    private LockDecorator lock = new LockDecorator();

    public void execute(Runnable source){
        try(LockDecorator l = lock.lock()) {
            source.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
