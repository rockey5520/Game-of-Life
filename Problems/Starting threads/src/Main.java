public class Main {

    public static void main(String[] args) {

        new Thread(new RunnableWorker(), "worker-X").start();
        new Thread(new RunnableWorker(), "worker-X").start();
        new Thread(new RunnableWorker(), "worker-X").start();
    }
}

// Don't change the code below       
class RunnableWorker implements Runnable {

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();

        if (name.startsWith("worker-")) {
            System.out.println("too hard calculations...");
        } else {
            return;
        }
    }
}