package com.dwi.saas.activiti.controller.activiti;

import com.dwi.basic.base.R;
import com.dwi.basic.base.entity.SuperEntity;
import com.dwi.saas.activiti.domain.dto.activiti.InstantSelectSaveDTO;
import com.dwi.saas.activiti.service.activiti.MyProcessInstantService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.dwi.basic.base.R.success;

/**
 * 流程实例管理
 *
 * @author wz
 * @date 2020-08-07
 */
@Slf4j
@RestController
@RequestMapping("instance")
@RequiredArgsConstructor
public class InstanceBizController {
    private final MyProcessInstantService myProcessInstantService;

    /**
     * 新增流程实例
     *
     */
    @PostMapping(value = "/save")
    public R<Boolean> save(@RequestBody InstantSelectSaveDTO dto) {
        SuperEntity entity = new SuperEntity();
        entity.setId(dto.getBussKey());
        myProcessInstantService.add(entity, dto.getKey(), dto.getVariables());
        return success(true);
    }

    /**
     * 修改流程实例状态
     *
     * @param instId       实例ID
     * @param suspendState 修改状态
     */
    @GetMapping(value = "/updateSuspendStateInst")
    public R<Boolean> updateSuspendStateInst(@RequestParam(value = "instId") String instId, @RequestParam(value = "suspendState") String suspendState) {
        myProcessInstantService.suspendOrActiveApply(instId, suspendState);
        return success(true);
    }
}
