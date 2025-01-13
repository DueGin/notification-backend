package com.duegin.notification.config;

import com.duegin.notification.entity.User;


public class UserContext {
    private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置值
     *
     * @param user
     */
    public static void setUser(User user) {
        THREAD_LOCAL.set(user);
    }

    /**
     * 获取值
     *
     * @return
     */
    public static User getUser() {
        return THREAD_LOCAL.get();
    }

    public static Integer getUserId(){
        return THREAD_LOCAL.get().getId();
    }
}
