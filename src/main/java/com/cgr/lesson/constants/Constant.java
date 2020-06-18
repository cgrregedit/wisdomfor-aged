package com.cgr.lesson.constants;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 23:30 2020-04-30
 * @ Description：  创建一个专门存放常量的类
 * @ Modified By：
 */
public class Constant {
    /**
     * 用户名称 key
     */
    public static final String JWT_USER_NAME="jwt-user-name-key";

    /**
     * 权限key
     */
    public static final String JWT_PERMISSIONS_KEY="jwt-permissions-key_";


    /**
     * 角色key
     */
    public static final String JWT_ROLES_KEY="jwt-roles-key_";

    /**
     * refresh_token 主动退出后加入黑名单 key
     */
    public static final String JWT_REFRESH_TOKEN_BLACKLIST="jwt-refresh-token-blacklist_";

    /**
     * access_token 主动退出后加入黑名单 key
     */
    public static final String JWT_ACCESS_TOKEN_BLACKLIST="jwt-access-token-blacklist_";

    /**
     * 正常token
     */
    public static final String ACCESS_TOKEN="authorization";
    /**
     * 刷新token
     */
    public static final String REFRESH_TOKEN="refresh_token";

    /**
     * 标记用户是否已经被锁定
     */
    public static final String ACCOUNT_LOCK_KEY="account-lock-key_";

    /**
     * 标记用户是否已经删除
     */
    public static final String DELETED_USER_KEY="deleted-user-key_";

    /**
     * 主动去刷新 token key(适用场景 比如修改了用户的角色/权限去刷新token)
     */
    public static final String JWT_REFRESH_KEY="jwt-refresh-key_";
    /**
     * 标记新的access_token
     */
    public static final String JWT_REFRESH_IDENTIFICATION="jwt-refresh-identification_";

    /**
     * 部门编码key
     */
    public static final String DEPT_CODE_KEY="dept-code-key_";

    /**
     * 个人编码key
     */
    public static final String PEOPELE_CODE_KEY="people-code-key_";

    /**
     * 用户权鉴缓存 key
     */
    public static final String IDENTIFY_CACHE_KEY="shiro-cache:com.xh.lesson.shiro.CustomRealm.authorizationCache:";

}

