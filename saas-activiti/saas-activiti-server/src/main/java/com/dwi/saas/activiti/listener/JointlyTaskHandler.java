package com.dwi.saas.activiti.listener;

import com.dwi.basic.utils.SpringUtils;
import com.dwi.saas.activiti.domain.constant.ReimbursementVarConstant;
import com.dwi.saas.activiti.domain.constant.ResultConstant;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 任务监听器,监听报销流程对于会签任务处理环节
 *
 * @author wz
 * @date 2020-08-07
 */
@Component
public class JointlyTaskHandler implements TaskListener {

    private TaskService taskService;

    /**
     * 监听
     *
     * @param delegateTask 任务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void notify(DelegateTask delegateTask) {
        if (taskService == null) {
            taskService = SpringUtils.getBean(TaskService.class);
        }

        DelegateExecution delegateExecution = delegateTask.getExecution();
        Map<String, Object> variables = delegateTask.getVariables();
        if (ResultConstant.PASS.equals(variables.get(ReimbursementVarConstant.RESULT))) {
            delegateExecution.setVariable(ReimbursementVarConstant.UP, ((Integer) delegateExecution.getVariable(ReimbursementVarConstant.UP)) + 1);
        } else {
            delegateExecution.setVariable(ReimbursementVarConstant.DOWN, ((Integer) delegateExecution.getVariable(ReimbursementVarConstant.DOWN)) + 1);
        }
    }

}

