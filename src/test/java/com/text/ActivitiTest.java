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
