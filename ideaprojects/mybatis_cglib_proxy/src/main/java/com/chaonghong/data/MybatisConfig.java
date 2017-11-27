package com.chaonghong.data;

import java.util.Properties;

import javax.sql.DataSource;

import com.chaonghong.data.interceptor.MyInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.github.pagehelper.PageInterceptor;

//@Configuration
//@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer
{

    @Autowired
    DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception
    {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.chaonghong.data.entity");
        // 支持下划线到驼峰
        org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration();
        conf.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(conf);

        // 分页插件
        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "pageNum=page;pageSize=rows;orderBy=orderBy");
        pageHelper.setProperties(properties);

        // 添加插件
        bean.setPlugins(new Interceptor[] {new MyInterceptor()});
        return bean.getObject();
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
