package com.dwi.saas.oauth.event.model;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dwi.basic.context.ContextUtil;
import com.dwi.basic.log.util.AddressUtil;
import com.dwi.saas.oauth.domain.Online;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 登录状态DTO
 *
 * @author dwi
 * @date 2020年03月18日17:25:44
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class LoginStatusDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;
    /***
     * 用户id
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 租户编码
     */
    private String tenant;
    /**
     * 登录类型
     */
    private Type type;
    /**
     * 登录描述
     */
    private String description;

    /**
     * 登录浏览器
     */
    private String ua;
    /**
     * 登录IP
     */
    private String ip;
    
    /**
     * 浏览器
     */
    private String browser;
    
    /**
     * 浏览器版本
     */
    private String browserVersion;
    
    /**
     * 操作系统
     */
    private String operatingSystem;
    /**
     * 登录地址
     */
    private String location;

    private Online online;

    public static LoginStatusDTO success(Long id, Online online) {
        LoginStatusDTO loginStatus = LoginStatusDTO.builder()
                .id(id).tenant(ContextUtil.getTenant())
                .type(Type.SUCCESS).description("登录成功")
                .build().setInfo();
        online.setLoginIp(loginStatus.getIp());
        online.setLocation(loginStatus.getLocation());
        loginStatus.setOnline(online);
        return loginStatus;
    }

    public static LoginStatusDTO fail(Long id, String description) {
        return LoginStatusDTO.builder()
                .id(id).tenant(ContextUtil.getTenant())
                .type(Type.FAIL).description(description)
                .build().setInfo();
    }

    public static LoginStatusDTO fail(String account, String description) {
        return LoginStatusDTO.builder()
                .account(account).tenant(ContextUtil.getTenant())
                .type(Type.FAIL).description(description)
                .build().setInfo();
    }

    public static LoginStatusDTO pwdError(Long id, String description) {
        return LoginStatusDTO.builder()
                .id(id).tenant(ContextUtil.getTenant())
                .type(Type.PWD_ERROR).description(description)
                .build().setInfo();
    }

    private LoginStatusDTO setInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return this;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        if (request == null) {
            return this;
        }
        String tempUa = request.getHeader("user-agent");
        String tempIp = ServletUtil.getClientIP(request);
        String tempLocation = AddressUtil.getRegion(tempIp);
        this.ua = tempUa;
        UserAgent userAgent = UserAgentUtil.parse(tempUa);
        this.browser = userAgent.getBrowser().toString();
        this.browserVersion = userAgent.getVersion();
        this.operatingSystem = userAgent.getOs().toString();
        this.ip = tempIp;
        this.location = tempLocation;
        return this;
    }

    @Getter
    public enum Type {
        /**
         * 成功
         */
        SUCCESS,
        /**
         * 密码错误
         */
        PWD_ERROR,
        /**
         * 失败
         */
        FAIL
    }

}
