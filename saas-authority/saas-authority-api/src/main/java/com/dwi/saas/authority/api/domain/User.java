package com.dwi.saas.authority.api.domain;


import com.dwi.basic.base.entity.Entity;
import com.dwi.basic.model.RemoteData;
import com.dwi.saas.authority.api.domain.enumeration.Sex;

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
 * 用户
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
@AllArgsConstructor
public class User extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 组织ID
     * #c_org
     *
     * @InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.dwi.saas.authority.entity.core.Org>
     */
    private RemoteData<Long, Org> org;

    /**
     * 岗位ID
     * #c_station
     *
     * @InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD) RemoteData<Long, String>
     */
    private RemoteData<Long, String> station;

    /**
     * 内置
     */
    private Boolean readonly;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    private Sex sex;

    /**
     * 状态
     */
    private Boolean state;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 民族
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.NATION) RemoteData<String, String>
     */
    private RemoteData<String, String> nation;

    /**
     * 学历
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.EDUCATION) RemoteData<String, String>
     */
    private RemoteData<String, String> education;

    /**
     * 职位状态
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.POSITION_STATUS) RemoteData<String, String>
     */
    private RemoteData<String, String> positionStatus;

    /**
     * 工作描述
     */
    private String workDescribe;

    /**
     * 最后一次输错密码时间
     */
    private LocalDateTime passwordErrorLastTime;

    /**
     * 密码错误次数
     */
    private Integer passwordErrorNum;

    /**
     * 密码过期时间
     */
    private LocalDateTime passwordExpireTime;

    /**
     * 密码
     */
    private String password;


    private String salt;


    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;


    @Builder
    public User(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                String account, String name, RemoteData<Long, Org> orgId, RemoteData<Long, String> stationId, Boolean readonly,
                String email, String mobile, Sex sex, Boolean state, String avatar, RemoteData<String, String> nation,
                RemoteData<String, String> education, RemoteData<String, String> positionStatus, String workDescribe, LocalDateTime passwordErrorLastTime, Integer passwordErrorNum, LocalDateTime passwordExpireTime,
                String password, String salt, LocalDateTime lastLoginTime) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.account = account;
        this.name = name;
        this.org = orgId;
        this.station = stationId;
        this.readonly = readonly;
        this.email = email;
        this.mobile = mobile;
        this.sex = sex;
        this.state = state;
        this.avatar = avatar;
        this.nation = nation;
        this.education = education;
        this.positionStatus = positionStatus;
        this.workDescribe = workDescribe;
        this.passwordErrorLastTime = passwordErrorLastTime;
        this.passwordErrorNum = passwordErrorNum;
        this.passwordExpireTime = passwordExpireTime;
        this.password = password;
        this.salt = salt;
        this.lastLoginTime = lastLoginTime;
    }

}
