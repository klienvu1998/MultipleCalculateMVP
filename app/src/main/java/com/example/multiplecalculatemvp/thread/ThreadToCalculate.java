package com.example.multiplecalculatemvp.thread;

import com.example.multiplecalculatemvp.model.Number;

public class ThreadToCalculate implements Runnable {
    private Number number;
    private long result;
    private Listenner listenner;

    public ThreadToCalculate(Number number, long result, Listenner listenner) {
        this.number = number;
        this.result = result;
        this.listenner = listenner;
    }

    @Override
    public void run() {
        for(long i=0;i<number.getMultiplier();i++){
            result += number.getNumberMultiplied();
        }
        synchronized (this) {
            listenner.getResult(result);
        }
    }
    public interface Listenner{
        void getResult(long result);
    }
}
