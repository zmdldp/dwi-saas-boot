package com.dwi.saas.authority.api.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dwi.basic.base.entity.TreeEntity;
import com.dwi.basic.model.RemoteData;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 组织
 * </p>
 *
 * @author dwi
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_org")
@ApiModel(value = "Org", description = "组织")
@AllArgsConstructor
public class Org extends TreeEntity<Org, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.ORG_TYPE) RemoteData<String, String>
     */
    private RemoteData<String, String> type;

    /**
     * 简称
     */
    private String abbreviation;

    /**
     * 树结构
     */
    private String treePath;

    /**
     * 状态
     */
    private Boolean state;

    /**
     * 描述
     */
    private String describe;


    @Builder
    public Org(Long id, String label, Long parentId, Integer sortValue, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
               RemoteData<String, String> type, String abbreviation, String treePath, Boolean state, String describe) {
        this.id = id;
        this.label = label;
        this.parentId = parentId;
        this.sortValue = sortValue;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.type = type;
        this.abbreviation = abbreviation;
        this.treePath = treePath;
        this.state = state;
        this.describe = describe;
    }

}
