package com.thinkcms.freemark.corelibs.subject;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.freemark.corelibs.notify.AbstractNotify;
import com.thinkcms.freemark.corelibs.observer.Observer;
import com.thinkcms.freemark.corelibs.observer.ObserverAction;
import com.thinkcms.freemark.corelibs.observer.ObserverData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.*;

public class SubjectAdapt implements Subject {

    @Autowired
    SimpMessagingTemplate SMT;

    // 定义观察者
    private List<Observer> observers;

    private ObserverData observerData;

    /**
     * 回调通知
     * @param observerData
     */
    public void callBack(ObserverData observerData){
        if(Checker.BeNotNull(observerData)){
            ObserverAction action=observerData.getObserverAction();
            if(Checker.BeNotNull(action)){
                Map<String,Object> notify=new HashMap<>();
                String actDesc=action.getAction();
                notify.put("action",actDesc);
                notify.put("code",action.getCode());
                notify.put("gmt_create",new Date());
                SMT.convertAndSend("/topic/executeJob", notify);
            }
        }
    };

    @Override
    public void registerObserver(Observer o) {
        if(Checker.BeEmpty(observers)){
            observers = new ArrayList<>(16);
        }
        observers.add(o);
    }

    @Override
    public void registerObserver(List<Observer> os) {
        if(Checker.BeEmpty(observers)){
            observers = new ArrayList<>(16);
        }
        observers.addAll(os);
    }

    @Override
    public void removeObserver(Observer o) {
        if(Checker.BeNotEmpty(observers)){
            observers.remove(o);
        }
    }

    @Override
    public void removeAllObserver() {
        if(Checker.BeNotEmpty(observers)){
            observers.clear();
        }
    }

    @Override
    public void notifyObserver() {
        if(Checker.BeNotEmpty(observers)){
            observers.forEach(observer->{
                observer.update(observerData);
            });
        }
    }

    @Override
    public void notifyObserver(AbstractNotify notifyRes) {
        if(Checker.BeNotEmpty(observers)){
            observers.forEach(observer->{
                observer.update(observerData,notifyRes);
            });
        }
    }

    @Override
    public void setObserverData(ObserverData observerData) {
        this.observerData=observerData;
    }
}
