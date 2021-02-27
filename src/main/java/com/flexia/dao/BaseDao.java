package com.flexia.dao;

/**
 * @author flexia
 * @date 2021/2/26
 */
public interface BaseDao<T> {
    /**
     * 用于规范操作数据库的新增数据规则
     *
     * @param t 新增的数据
     * @return 返回新增的结果，是一个数字，大于0表示新增成功
     */
    int insert(T t);

    /**
     * 用于规范操作数据库的获取数据规则
     *
     * @param userId 用户ID
     * @return 返回的是获取的数据
     */
    T findByUserId(int userId);
}
