package com.example.multiplecalculatemvp.handler;

import android.os.Handler;
import android.os.Message;
import com.example.multiplecalculatemvp.CalculateInterface;
import com.example.multiplecalculatemvp.presenter.CalculatePresenter;
import java.text.DecimalFormat;

import androidx.annotation.NonNull;

public class CalculateHandler extends Handler {

    private CalculateInterface.View view;

    public CalculateHandler(CalculateInterface.View view) {
        this.view = view;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if(CalculatePresenter.COMPLETED_THREAD_POOL == msg.what){
            DecimalFormat dF = new DecimalFormat("###,###.###");
            view.setResultTextView(dF.format(CalculatePresenter.result));
        }
    }
}
