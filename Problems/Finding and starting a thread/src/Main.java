import java.lang.Thread.State;

class ThreadProcessor {

  public static void findAndStartThread(Thread... threads) throws InterruptedException {
    for (Thread thread : threads
    ) {
      if (thread.getState() == State.NEW) {
        thread.start();
        thread.join();
      }
    }
  }
}