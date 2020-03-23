package com.example.multiplecalculatemvp.presenter;

import android.os.Message;
import com.example.multiplecalculatemvp.CalculateInterface;
import com.example.multiplecalculatemvp.handler.CalculateHandler;
import com.example.multiplecalculatemvp.model.Number;
import com.example.multiplecalculatemvp.thread.ThreadToCalculate;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CalculatePresenter implements CalculateInterface.Presenter {

    public final static int COMPLETED_THREAD_POOL = 100;
    private final static int COREPOOLSIZE = 1;
    private final static int MAXPOOLSIZE = Runtime.getRuntime().availableProcessors();
    private final static int QUENE = 1;

    private CalculateInterface.View view;

    public static long result;
    private int checkFinishTaskCount;
    private CalculateHandler calculateHandler;

    public CalculatePresenter(CalculateInterface.View view) {
        this.view = view;
    }

    @Override
    public void calculateMultiple(long numberMultiplied, long multiplier){
        calculateHandler = new CalculateHandler(view);
        ThreadPoolExecutor threadPoolExecutor;
        ArrayList<Long> arrLong;
        Number[] numbers;
        arrLong = new ArrayList<>(MAXPOOLSIZE);
        numbers = new Number[MAXPOOLSIZE];
        result = 0;
        checkFinishTaskCount = 0;
        arrLong.clear();
        for(int i=0;i<MAXPOOLSIZE-1;i++){
            arrLong.add(multiplier/MAXPOOLSIZE);
        }
        arrLong.add(multiplier - ((MAXPOOLSIZE-1)*(multiplier/MAXPOOLSIZE)));

        for(int i=0;i<MAXPOOLSIZE;i++){
            numbers[i] = new Number(numberMultiplied,arrLong.get(i));
        }
        threadPoolExecutor = new ThreadPoolExecutor(COREPOOLSIZE,MAXPOOLSIZE,1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(QUENE));
        for(int i=0;i<MAXPOOLSIZE;i++){
            threadPoolExecutor.execute(new ThreadToCalculate(numbers[i], 0, new ThreadToCalculate.Listenner() {
                @Override
                public void getResult(long tResult) {
                    if(tResult!=-1){
                        result += tResult;
                        checkFinishTaskCount++;
                        Message message = new Message();
                        if(checkFinishTaskCount == MAXPOOLSIZE){
                            message.what = COMPLETED_THREAD_POOL;
                            calculateHandler.sendMessage(message);
                        }
                    }
                }
            }));
        }
    }
}
