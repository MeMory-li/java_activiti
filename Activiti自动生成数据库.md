# Activiti自动生成数据库

## 1、环境

```
Mysql：5.5
JDK:1.8
Maven:3.8.1
actibpm:3.E-8
IDEA:2019.3
OS:Win10 64
```

## 2、插件的安装

```
1、由于我用的是IDEA：2019.3的系统，所以说在搜索actibpm插件的时候就无法搜到，只能选择手动安装插件。
```

![image-20210708133417971](C:\Users\1025088\AppData\Roaming\Typora\typora-user-images\image-20210708133417971.png)

```
2、在 https://plugins.jetbrains.com/plugin/7429-actibpm/versions/stable/17789 网站进行下载程序，得到如下所示安装包。
```

![image-20210708133654900](C:\Users\1025088\AppData\Roaming\Typora\typora-user-images\image-20210708133654900.png)

```
3、通过如下操作进行安装下载的安装包。
```

![image-20210708133842904](C:\Users\1025088\AppData\Roaming\Typora\typora-user-images\image-20210708133842904.png)

## 3、搭建程序框架

### 1、创建数据库。数据库名为：utf8mb4

![image-20210708134418030](C:\Users\1025088\AppData\Roaming\Typora\typora-user-images\image-20210708134418030.png)

### 2、创建mven工程，并进行设名字地址的输入。

![image-20210708134645801](C:\Users\1025088\AppData\Roaming\Typora\typora-user-images\image-20210708134645801.png)

![image-20210708134937827](C:\Users\1025088\AppData\Roaming\Typora\typora-user-images\image-20210708134937827.png)

![image-20210708135047373](C:\Users\1025088\AppData\Roaming\Typora\typora-user-images\image-20210708135047373.png)

![image-20210708140959423](C:\Users\1025088\AppData\Roaming\Typora\typora-user-images\image-20210708140959423.png)

### 3、pom.xml的编写

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.newtouch.activiti</groupId>
    <artifactId>activiti01</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
        <slf4j.version>1.6.6</slf4j.version>
        <log4j.version>1.2.12</log4j.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-engine</artifactId>
            <version>7.0.0.Beta1</version>
        </dependency>

        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-spring</artifactId>
            <version>7.0.0.Beta1</version>
        </dependency>

        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-bpmn-model</artifactId>
            <version>7.0.0.Beta1</version>
        </dependency>

        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-bpmn-converter</artifactId>
            <version>7.0.0.Beta1</version>
        </dependency>

        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-json-converter</artifactId>
            <version>7.0.0.Beta1</version>
        </dependency>

        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-bpmn-layout</artifactId>
            <version>7.0.0.Beta1</version>
          </dependency>

        <dependency>
            <groupId>org.activiti.cloud</groupId>
            <artifactId>activiti-cloud-services-api</artifactId>
            <version>7.0.0.Beta1</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.40</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <!-- log start -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <!-- log end -->

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.5</version>
        </dependency>

        <dependency>
            <groupId>commons-dbcp</groupId>
            <artifactId>commons-dbcp</artifactId>
            <version>1.4</version>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>alfresco</id>
            <name>Activiti Releases</name>
            <url>https://artifacts.alfresco.com/nexus/content/repositories/activiti-releases</url>
            <releases>
                <enabled>true</enabled>
            </releases>
        </repository>
    </repositories>

</project>
```

### 4、log4j.properties日志文档的编写

#### *注：IDEA预编译时会有一些代码划红线的错误，并不会影响正常使用，不必理会。

```java
# Set root category priority to INFO and its only appender to CONSOLE.
#log4j.rootCategory=INFO, CONSOLE            debug   info   warn error fatal
log4j.rootCategory=debug, CONSOLE, LOGFILE

# Set the enterprise logger category to FATAL and its only appender to CONSOLE.
log4j.logger.org.apache.axis.enterprise=FATAL, CONSOLE

# CONSOLE is set to be a ConsoleAppender using a PatternLayout.
log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
log4j.appender.CONSOLE.layout.ConversionPattern=%d{ISO8601} %-6r [%15.15t] %-5p %30.30c %x - %m\n

# LOGFILE is set to be a File appender using a PatternLayout.
log4j.appender.LOGFILE=org.apache.log4j.FileAppender
log4j.appender.LOGFILE.File=d:\axis.log
log4j.appender.LOGFILE.Append=true
log4j.appender.LOGFILE.layout=org.apache.log4j.PatternLayout
log4j.appender.LOGFILE.layout.ConversionPattern=%d{ISO8601} %-6r [%15.15t] %-5p %30.30c %x - %m\n
```

### 5、activiti.cfg.xml文档的编写

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--数据源-->
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/activiti"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>

    <!--配置Activiti使用的processEngine对象   默认命名为processEngineConfiguration-->
    <bean id="processEngineConfiguration" class="org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration">
        <!--注入数据源-->
        <property name="dataSource" ref="dataSource"/>
        <!--配置数据源方式二：-->
        <!--<property name="jdbcDriver" value="com.mysql.jdbc.Driver"/>-->
        <!--指定数据库生成策略-->
        <property name="databaseSchemaUpdate" value="true"/>
    </bean>
</beans>
```

### 6、测试文档ActivitiTest的编写

```
package com.text;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * 测试类
 *      作用：测试activiti所需要的25张表的生成
 */
public class ActivitiTest {
    @Test
    public void testGenTable(){


        // 引擎配置
        ProcessEngineConfiguration pec=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine=pec.buildProcessEngine();

        //1、创建一个ProcessEngineConfiguration对象
       // ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //2、创建ProcesEngine对象
        // ProcessEngine processEngine = configuration.buildProcessEngine();

        //3.输出processEngine对象
        System.out.println(processEngine);
    }
}

```

### 7、运行测试即可

![image-20210708140405096](C:\Users\1025088\AppData\Roaming\Typora\typora-user-images\image-20210708140405096.png)

## 4、错误总结

```java
由于项目中的代码好多是直接cope前人的代码，所以说在项目启动的时候bug也出现了许多，如果出现以下bug，只需要修改activiti.cfg.xml中的bean id即可。
```

![image-20210708141340695](C:\Users\1025088\AppData\Roaming\Typora\typora-user-images\image-20210708141340695.png)

