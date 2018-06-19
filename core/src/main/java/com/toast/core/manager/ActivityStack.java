package com.toast.core.manager;

import com.toast.core.base.WAbsActivity;

import java.util.ArrayList;
import java.util.Stack;

public abstract class ActivityStack {

    private static Stack<WAbsActivity> sActivitys = new Stack<>();
    private static int stubIdx = Integer.MAX_VALUE;

    private ActivityStack(){}

    /**
     * 关闭activity,并从堆栈中移除
     * @param activity
     */
    public static void finish(WAbsActivity activity) {
        if (activity != null) {
            activity.finish();
            sActivitys.remove(activity);
            activity = null;
        }
    }

    /**
     * 获取栈顶的Activity,并从堆栈中移除
     * @return
     */
    public static WAbsActivity getTopActivity() {
        if (!sActivitys.isEmpty()) {
            return sActivitys.pop();
        }
        return null;
    }

    /**
     * 获取栈底的Activity
     * @return
     */
    public static WAbsActivity getLastActivity(){
        if (!sActivitys.isEmpty()) {
            return sActivitys.lastElement();
        }
        return null;
    }

    /**
     * 移除对应的activity
     * @param activity
     */
    public synchronized static void pop(WAbsActivity activity) {
        if (activity != null && !sActivitys.isEmpty()) {
            sActivitys.remove(activity);
        }
    }

    /**
     * 返回栈顶activity并且不移除堆栈
     * @return
     */
    public static WAbsActivity getTopActivityNoRemove() {
        if (!sActivitys.isEmpty()) {
            return sActivitys.elementAt(sActivitys.size() - 1);
        }
        return null;
    }

    /**
     * 往栈中压入activity
     * @param activity
     */
    public static void push(WAbsActivity activity) {
        if (activity != null && sActivitys != null) {
            sActivitys.push(activity);
        }
    }

    /**
     * 销毁栈中的activity,并清空栈
     */
    public static void finishAll() {
        while (!sActivitys.isEmpty()) {
            finish(getTopActivityNoRemove());
        }
        sActivitys.clear();
    }

    /**
     * 获取栈的大小
     * @return
     */
    public static int size(){
        if (sActivitys != null) {
            return sActivitys.size();
        }
        return 0;
    }

    /**
     * 返回一个activity的list集合
     * @return
     */
    public static ArrayList<WAbsActivity> getActivityList(){
        return new ArrayList<>(sActivitys);
    }
}
