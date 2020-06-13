class Info {

    public static void printCurrentThreadInfo() {
        Thread thread = Thread.currentThread();
        System.out.println("name: "+thread.getName());
        System.out.println("priority: "+thread.getPriority());
    }
}