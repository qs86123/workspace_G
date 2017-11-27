package com.chaonghong.data.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:44 2017/11/21
 * @Email:tao8.wang@changhong.com
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyIntercept.intercept() invoked");
        return null;
    }

    @Override
    public Object plugin(Object o) {
//        Executor e = (Executor) o;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>:" + o.getClass());
        if (o instanceof RoutingStatementHandler) {
            RoutingStatementHandler c = (RoutingStatementHandler) o;
            BoundSql boundSql = c.getBoundSql();
            try {
                Field sql = boundSql.getClass().getDeclaredField("sql");
                sql.setAccessible(true);
                sql.set(boundSql, "select * from person limit 10");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return c;
        }
        return o;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
