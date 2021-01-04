package com.dwi.saas.gateway.dao;

import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.saas.gateway.entity.RateLimiter;
import org.springframework.stereotype.Repository;

/**
 * 限流
 *
 * @author dwi
 * @date 2020/8/5 上午10:31
 */
@Repository
public interface RateLimiterMapper extends SuperMapper<RateLimiter> {
}
