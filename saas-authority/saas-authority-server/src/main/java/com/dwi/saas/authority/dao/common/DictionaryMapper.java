package com.dwi.saas.authority.dao.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.saas.authority.domain.dto.common.DictionaryPageQuery;
import com.dwi.saas.authority.domain.entity.common.Dictionary;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 字典类型
 * </p>
 *
 * @author dwi
 * @date 2020-07-02
 */
@Repository
public interface DictionaryMapper extends SuperMapper<Dictionary> {
    /**
     * 分页查询字典类型
     *
     * @param page  分页参数
     * @param query 查询条件
     * @return 分页数据
     */
    IPage<Dictionary> pageType(IPage<Dictionary> page, @Param("query") DictionaryPageQuery query);
}
