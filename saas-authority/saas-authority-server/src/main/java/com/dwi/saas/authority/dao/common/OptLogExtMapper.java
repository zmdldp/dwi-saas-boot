package com.dwi.saas.authority.dao.common;

import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.saas.authority.domain.entity.common.OptLogExt;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * 系统日志
 * </p>
 *
 * @author dwi
 * @date 2020-07-02
 */
@Repository
public interface OptLogExtMapper extends SuperMapper<OptLogExt> {
    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    Long clearLog(@Param("clearBeforeTime") LocalDateTime clearBeforeTime, @Param("clearBeforeNum") Integer clearBeforeNum);
}
