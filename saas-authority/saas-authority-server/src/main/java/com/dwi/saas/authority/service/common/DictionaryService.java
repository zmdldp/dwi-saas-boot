package com.dwi.saas.authority.service.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.base.service.SuperService;
import com.dwi.basic.model.LoadService;
import com.dwi.saas.authority.domain.dto.common.DictionaryPageQuery;
import com.dwi.saas.authority.domain.dto.common.DictionaryTypeSaveDTO;
import com.dwi.saas.authority.domain.entity.common.Dictionary;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 业务接口
 * 字典类型
 * </p>
 *
 * @author dwi
 * @date 2020-07-02
 */
public interface DictionaryService extends SuperService<Dictionary>, LoadService {
	   /**
     * 修改字典类型
     *
     * @param dictType 字典类型
     * @return 是否成功
     */
    boolean updateType(DictionaryTypeSaveDTO dictType);

    /**
     * 保存字典类型
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    Dictionary saveType(DictionaryTypeSaveDTO dictType);

    /**
     * 删除字典类型
     *
     * @param types 字典类型
     * @return 是否成功
     */
    Boolean deleteType(List<String> types);

    /**
     * 分页查询字典类型
     *
     * @param page  分页参数
     * @param query 查询条件
     * @return 分页数据
     */
    IPage<Dictionary> pageType(IPage<Dictionary> page, DictionaryPageQuery query);

    /**
     * 根据类型查询字典
     *
     * @param types 类型
     * @return 集合
     */
    Map<String, List<Dictionary>> listByTypes(String[] types);

    /**
     * 根据类型查询字典
     *
     * @param types 类型
     * @return 集合
     */
    Map<String, Map<Dictionary, String>> mapInverse(String[] types);

//    /**
//     * 根据类型编码查询字典项
//     *
//     * @param typeCodes 字典类型#编码
//     * @return 名称
//     */
//    Map<Serializable, Object> findDictionaryItem(Set<Serializable> typeCodes);

}
