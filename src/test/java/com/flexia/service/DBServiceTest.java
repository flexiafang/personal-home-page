package com.flexia.service;

import com.flexia.bean.User;
import org.junit.Test;

/**
 * @author flexia
 * @date 2021/2/26
 */
public class DBServiceTest {

    @Test
    public void UserInsert() {
        User user = new User("1", 18, "1", "1", "1", "1", "1", "1", "1", "1", "1");
        DBService.insertUser(user);
    }

    @Test
    public void test() {
        User user = new User("方方", 18, "1", "1", "1", "1", "1", "1", "1", "1", "1");
        System.out.println(user);
    }
}