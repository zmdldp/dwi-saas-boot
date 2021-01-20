package com.dwi.saas.tenant.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.dwi.basic.base.R;
import com.dwi.basic.exception.BizException;
import com.dwi.basic.mq.properties.MqProperties;
import com.dwi.saas.common.constant.BizConstant;
import com.dwi.saas.tenant.api.AuthorityDsApi;
import com.dwi.saas.tenant.api.BaseExecutorDsApi;
import com.dwi.saas.tenant.api.ExtendExecutorDsApi;
import com.dwi.saas.tenant.api.FileDsApi;
import com.dwi.saas.tenant.api.GatewayDsApi;
import com.dwi.saas.tenant.api.MsgDsApi;
import com.dwi.saas.tenant.api.OauthDsApi;
import com.dwi.saas.tenant.biz.service.InitDsService;
import com.dwi.saas.tenant.domain.dto.DataSourcePropertyDTO;
import com.dwi.saas.tenant.domain.entity.DatasourceConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 没有开启消息队列就只能轮训了
 *
 * @author dwi
 * @date 2020年04月05日16:27:03
 */
@Service
@Slf4j
@ConditionalOnProperty(prefix = MqProperties.PREFIX, name = "enabled", havingValue = "false", matchIfMissing = true)
@RequiredArgsConstructor
public class DefaultInitDsServiceImpl implements InitDsService {

    private final AuthorityDsApi authorityDsApi;
    private final OauthDsApi oauthDsApi;
    private final FileDsApi fileDsApi;
    private final MsgDsApi msgDsApi;
    private final GatewayDsApi gatewayDsApi;
    private final ExtendExecutorDsApi extendExecutorDsApi;
    private final BaseExecutorDsApi baseExecutorDsApi;

    @Override
    public boolean removeDataSource(String tenant) {
        // 权限服务
        R<Boolean> authority = authorityDsApi.remove(tenant);
        // SpringBoot项目就不需要调用以下方法了
        // oauth
        R<Boolean> oauth = oauthDsApi.remove(tenant);
        // file
        R<Boolean> file = fileDsApi.remove(tenant);
//         msg
        R<Boolean> msg = msgDsApi.remove(tenant);

        R<Boolean> gateway = gatewayDsApi.remove(tenant);

        R<Boolean> extendExecutor = extendExecutorDsApi.remove(tenant);
        R<Boolean> baseExecutor = baseExecutorDsApi.remove(tenant);
        return true;
    }

    /**
     * 注意，集群部署时，请勿使用Feign调用触发初始化数据源时，否则会导致只有一个实例被初始化成功 ！！！
     *
     * @param typeMap 租户
     * @return
     */
    @Override
    public boolean initConnect(Map<String, DatasourceConfig> typeMap) {
        // 权限服务
        DataSourcePropertyDTO authorityDsp = BeanUtil.toBean(typeMap.get(BizConstant.AUTHORITY), DataSourcePropertyDTO.class);
        R<Boolean> authority = authorityDsApi.initConnect(authorityDsp);

        // SpringBoot项目就不需要调用以下方法了
        // oauth
        DataSourcePropertyDTO oauthDsp = BeanUtil.toBean(typeMap.get(BizConstant.OAUTH), DataSourcePropertyDTO.class);
        R<Boolean> oauth = oauthDsApi.initConnect(oauthDsp);
        // file
        DataSourcePropertyDTO fileDsp = BeanUtil.toBean(typeMap.get(BizConstant.FILE), DataSourcePropertyDTO.class);
        R<Boolean> file = fileDsApi.initConnect(fileDsp);
        // msg
        DataSourcePropertyDTO msgDsp = BeanUtil.toBean(typeMap.get(BizConstant.MSG), DataSourcePropertyDTO.class);
        R<Boolean> msg = msgDsApi.initConnect(msgDsp);
        // 网关
        DataSourcePropertyDTO gateDsp = BeanUtil.toBean(typeMap.get(BizConstant.GATE), DataSourcePropertyDTO.class);
        R<Boolean> gate = gatewayDsApi.initConnect(gateDsp);

        DataSourcePropertyDTO baseExecutorDsp = BeanUtil.toBean(typeMap.get(BizConstant.BASE_EXECUTOR), DataSourcePropertyDTO.class);
        R<Boolean> baseExecutor = baseExecutorDsApi.initConnect(baseExecutorDsp);

        DataSourcePropertyDTO executorDsp = BeanUtil.toBean(typeMap.get(BizConstant.EXTEND_EXECUTOR), DataSourcePropertyDTO.class);
        R<Boolean> executor = extendExecutorDsApi.initConnect(executorDsp);
        // 其他业务
        if (!gate.getIsSuccess() || gate.getData() == null || !gate.getData()) {
            throw new BizException("初始化网关服务数据源异常:" + gate.getMsg());
        }
        if (!oauth.getIsSuccess() || oauth.getData() == null || !oauth.getData()) {
            throw new BizException("初始化认证服务数据源异常:" + oauth.getMsg());
        }
        // 需要将全部服务的数据源连接成功，才叫成功，任意一个服务连接失败，都需要重新连接。开发环境，仅仅启动几个服务时，自行将未启动的服务注释掉，保证代码正确执行
        if (!authority.getIsSuccess() || authority.getData() == null || !authority.getData()) {
            throw new BizException("初始化权限服务数据源异常:" + authority.getMsg());
        }
        if (!file.getIsSuccess() || file.getData() == null || !file.getData()) {
            throw new BizException("初始化文件服务数据源异常:" + file.getMsg());
        }
        if (!msg.getIsSuccess() || msg.getData() == null || !msg.getData()) {
            throw new BizException("初始化消息服务数据源异常:" + msg.getMsg());
        }
        if (!baseExecutor.getIsSuccess() || baseExecutor.getData() == null || !baseExecutor.getData()) {
            throw new BizException("初始化定时执行服务数据源异常:" + baseExecutor.getMsg());
        }
        if (!executor.getIsSuccess() || executor.getData() == null || !executor.getData()) {
            throw new BizException("初始化定时执行服务数据源异常:" + executor.getMsg());
        }
        return true;
    }
}
