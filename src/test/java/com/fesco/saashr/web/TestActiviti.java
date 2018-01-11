package com.fesco.saashr.web;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author WangXingYu
 * @date 2018-01-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestActiviti {
    @Autowired
    RuntimeService runtimeService;

    @Test
    public void contextLoads() {
        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
       BpmnModel bpmnModel= repositoryService.getBpmnModel("demo");
        System.out.println(bpmnModel);
//        String bpmnFileName = "demo.bpmn";
//        repositoryService.createDeployment().addInputStream("demo.bpmn", this.getClass().getClassLoader().getResourceAsStream(bpmnFileName)).deploy();
//
//        ProcessDefinition processDefinition = repositoryService
//                .createProcessDefinitionQuery().singleResult();
//        assertEquals("demo", processDefinition.getKey());
//
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//
//        Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put("applyUser", "employee1");
//        variables.put("days", 3);
//
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("SayHelloToLeave", variables);
//        assertNotNull(processInstance);
//        System.out.println("pid=" + processInstance.getId() + ", pdid="
//                + processInstance.getProcessDefinitionId());
//
//        TaskService taskService = processEngine.getTaskService();
//        Task taskOfDeptLeader = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
//        assertNotNull(taskOfDeptLeader);
//        assertEquals("领导审批", taskOfDeptLeader.getName());
//
//        taskService.claim(taskOfDeptLeader.getId(), "leaderUser");
//        variables = new HashMap<String, Object>();
//        variables.put("approved", true);
//        taskService.complete(taskOfDeptLeader.getId(), variables);
//
//        taskOfDeptLeader = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
//        assertNull(taskOfDeptLeader);
//
//        HistoryService historyService = processEngine.getHistoryService();
//        long count = historyService.createHistoricProcessInstanceQuery().finished().count();
//        assertEquals(1, count);
    }
}