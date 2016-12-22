package com.lyape.baseadapter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by xuqiang on 2016/12/22.
 */

public class PersonEntity extends BaseObservable{

    public String name;
    public int  age;

    public boolean isLeft;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.lyape.baseadapter.BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(com.lyape.baseadapter.BR.age);
    }

    public boolean isLeft(){
        return  isLeft;
    }

}
