package ru.yarka.mcpeclient.utils;

import ru.yarka.mcpeclient.utils.async.ClientThread;

import java.util.ArrayList;

public class ThreadPool {

    private ArrayList<ClientThread> pool;

    public ThreadPool() {
        pool = new ArrayList<>();
    }

    public void push(ClientThread thread, boolean start) {
        if(start) thread.start();

        pool.add(thread);
    }

    public void push(ClientThread thread) {
        push(thread, true);
    }


    public void stopAll() {
        pool.forEach((thread) -> {
            System.out.println("Stopping thread " + thread.getName());
            thread.finish();
        });

        pool.clear();
    }
}
