package com.cgr.lesson.exception.code;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 12:11 2020-04-30
 * @ Description： 创建 BaseResponseCode 枚举类
 * @ Modified By：
 */
public enum BaseResponseCode implements ResponseCodeInterface{
    /**
     * 这个要和前端约定好
     *code=0：服务器已成功处理了请求。 通常，这表示服务器提供了请求的网页。
     *code=4010001：（授权异常） 请求要求身份验证。 客户端需要跳转到登录页面重新登录
     *code=4010002：(凭证过期) 客户端请求刷新凭证接口
     *code=4030001：没有权限禁止访问
     *code=400xxxx：系统主动抛出的业务异常
     *code=5000001：系统异常
     *
     */
    SUCCESS(0,"操作成功"),
    SYSTEM_ERROR(5000001,"系统异常请稍后再试"),
    DATA_ERROR(4000001,"传入数据异常"),
    METHOD_IDENTITY_ERROR(4000002,"数据校验异常"),
    ACCOUNT_ERROR(4000003,"该账号不存在"),
    ACCOUNT_LOCK(4010001,"该账号被锁定"),
    ACCOUNT_PASSWORD_ERROR(4000004,"用户名密码不匹配"),
    TOKEN_ERROR(4010001,"用户未登录，请重新登录"),
    TOKEN_NOT_NULL(4010001,"token 不能为空"),
    SHIRO_AUTHENTICATION_ERROR(4010001,"用户认证异常"),
    ACCOUNT_HAS_DELETED_ERROR(4010001,"该账号已被删除，请联系系统管理员"),
    TOKEN_PAST_DUE(4010002,"token 异常,请刷新token"),
    NOT_PERMISSION(4030001,"没有权限访问该资源"),
    OPERATION_ERROR(4000005,"操作失败"),
    OPERATION_MENU_PERMISSION_CATALOG_ERROR(4000006,"操作后的菜单类型是目录，所属菜单必须为默认顶级菜单或者目录"),
    OPERATION_MENU_PERMISSION_MENU_ERROR(4000007,"操作后的菜单类型是菜单，所属菜单必须为目录类型"),
    OPERATION_MENU_PERMISSION_BTN_ERROR(4000008,"操作后的菜单类型是按钮，所属菜单必须为菜单类型"),
    OPERATION_MENU_PERMISSION_URL_NOT_NULL(4000009,"菜单权限的url不能为空"),
    OPERATION_MENU_PERMISSION_URL_PERMS_NULL(4000010,"菜单权限的标识符不能为空"),
    OPERATION_MENU_PERMISSION_URL_METHOD_NULL(4000011,"菜单权限的请求方式不能为空"),
    ACCOUNT_LOCK_TIP(4010012,"该账号被锁定,请联系系统管理员"),
    OPERATION_MENU_PERMISSION_UPDATE(4010013,"操作的菜单权限存在子集关联不允许变更"),
    ROLE_PERMISSION_RELATION(4010014, "该菜单权限存在子集关联，不允许删除"),
    NOT_PERMISSION_DELETED_DEPT(4010015,"该组织机构下还关联着用户，不允许删除"),
    OLD_PASSWORD_ERROR(4010016,"旧密码不匹配"),
    OPERATION_MENU_PERMISSION_URL_CODE_NULL(4000011,"菜单权限的按钮标识不能为空"),
    ;

    /**
     *  响应码
     */
    private int code;

    /**
     * 提示
     */
    private String msg;

    BaseResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
