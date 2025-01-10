package com.duegin.notification.config;

import com.duegin.notification.entity.User;


public class BaseContext {
    private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置值
     * @param user
     */
    public static void setCurrentId(User user){
        THREAD_LOCAL.set(user);
    }

    /**
     * 获取值
     * @return
     */
    public static User getCurrentId(){
        return THREAD_LOCAL.get();
    }
}
