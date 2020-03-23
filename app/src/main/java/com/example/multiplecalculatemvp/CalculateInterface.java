package com.example.multiplecalculatemvp;

import com.example.multiplecalculatemvp.model.Number;

public interface CalculateInterface {
    interface View{
        void setResultTextView(String result);
    }
    interface Presenter{
        void calculateMultiple(long numberMultiplied,long multiplier);
    }
}
