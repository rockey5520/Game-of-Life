class CounterThread extends Thread {

    @Override
    public void run() {
        long counter = 0;

        while (!isInterrupted()) {
            counter++;
        }
        if(isInterrupted()){
            System.out.println("It was interrupted");
        }
    }
}