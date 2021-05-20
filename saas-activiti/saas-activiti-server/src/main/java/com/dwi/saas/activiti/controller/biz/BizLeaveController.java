package com.dwi.saas.activiti.controller.biz;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.base.R;
import com.dwi.basic.base.controller.SuperController;
import com.dwi.basic.base.request.PageParams;
import com.dwi.basic.context.ContextUtil;
import com.dwi.basic.database.mybatis.conditions.Wraps;
import com.dwi.basic.echo.core.EchoService;
import com.dwi.basic.model.RemoteData;
import com.dwi.basic.utils.BeanPlusUtil;
import com.dwi.saas.activiti.domain.dto.activiti.InstantSelectReqDTO;
import com.dwi.saas.activiti.domain.dto.biz.BizLeavePageDTO;
import com.dwi.saas.activiti.domain.dto.biz.BizLeaveSaveDTO;
import com.dwi.saas.activiti.domain.dto.biz.BizLeaveUpdateDTO;
import com.dwi.saas.activiti.domain.dto.biz.TaskHiLeaveResDTO;
import com.dwi.saas.activiti.domain.dto.biz.TaskLeaveResDTO;
import com.dwi.saas.activiti.domain.entity.biz.BizLeave;
import com.dwi.saas.activiti.domain.entity.core.UpdateCollEntity;
import com.dwi.saas.activiti.service.activiti.MyTaskService;
import com.dwi.saas.activiti.service.biz.BizLeaveService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * 请假流程
 * </p>
 *
 * @author wz
 * @date 2020-08-12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/bizLeave")
@Api(value = "BizLeave", tags = "请假流程")
@PreAuth(replace = "bizLeave:")
@RequiredArgsConstructor
public class BizLeaveController extends SuperController<BizLeaveService, Long, BizLeave, BizLeavePageDTO, BizLeaveSaveDTO, BizLeaveUpdateDTO> {

    private final EchoService echoService;
    private final MyTaskService myTaskService;

    @PostMapping("save")
    public R<Boolean> save(@RequestBody BizLeave bizLeave) {
        boolean tag = baseService.saveBiz(bizLeave);
        return R.success(tag);
    }

    /**
     * 删除流程实例及模型
     *
     * @param entity 集合修改实体
     */
    @PostMapping(value = "/delete")
    public R<Boolean> delete(@RequestBody UpdateCollEntity<String> entity) {
        if (CollUtil.isEmpty(entity.getIds())) {
            return R.fail("删除列表为空!");
        }
        Boolean tag = baseService.deleteBiz(entity);
        return R.success(tag);
    }

    /**
     * 请假实例查询
     *
     */
    @PostMapping(value = "/pageBiz")
    public R<IPage<BizLeave>> pageBiz(@RequestBody PageParams<BizLeavePageDTO> params) {
        if (params.getModel().getIsMine()) {
            params.getModel().setCreatedBy(ContextUtil.getUserId());
        }
        IPage<BizLeave> page = params.buildPage();
        BizLeave model = BeanPlusUtil.toBean(params.getModel(), BizLeave.class);
        baseService.page(page, Wraps.lbQ(model).orderByDesc(BizLeave::getCreateTime));
        echoService.action(page);
        return R.success(page);
    }

    /**
     * 根据业务id获取实例详情
     *
     * @param id 主键id
     */
    @Override
    @GetMapping(value = "/get")
    public R<BizLeave> get(@RequestParam(value = "id") Long id) {
        BizLeave entity = baseService.getById(id);
        return R.success(entity);
    }

    /**
     * 当前待办任务
     *
     */
    @PostMapping("pageRunTask")
    public R<IPage<TaskLeaveResDTO>> pageRunTask(@RequestBody PageParams<InstantSelectReqDTO> dto) {
        dto.getModel().setUserId(ContextUtil.getUserIdStr());
        dto.getModel().setKey(baseService.getKey());
        IPage<TaskLeaveResDTO> page = BeanPlusUtil.toBeanPage(myTaskService.pageDealtWithRunTasks(dto), TaskLeaveResDTO.class);
        page.getRecords().forEach(obj -> obj.setBiz(new RemoteData(obj.getInst().getKey())));
        echoService.action(page);
        return R.success(page);
    }

    /**
     * 当前待办任务
     *
     */
    @PostMapping("pageHiTask")
    public R<IPage<TaskHiLeaveResDTO>> pageHiTask(@RequestBody PageParams<InstantSelectReqDTO> dto) {
        dto.getModel().setUserId(ContextUtil.getUserIdStr());
        dto.getModel().setKey(baseService.getKey());
        IPage<TaskHiLeaveResDTO> page = BeanPlusUtil.toBeanPage(myTaskService.pageDealtWithHiTasks(dto), TaskHiLeaveResDTO.class);
        page.getRecords().forEach(obj -> obj.setBiz(new RemoteData(obj.getInst().getKey())));
        echoService.action(page);
        return R.success(page);
    }
}
