package com.cgr.lesson.utils;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 12:11 2020-04-30
 * @ Description：编码工具类
 * @ Modified By：
 */
public class CodeUtil {

    private static final String DEPT_TPYE="HOS";
    private static final String PERMISSION_TPYE="YXP";
    private static final String PEOPLE_TPYE="PE";
    /**
     * 右补位，左对齐
     * @pparam oriStr  原字符串
     * @param len  目标字符串长度
     * @param alexin  补位字符
     * @return  目标字符串
     * 以alexin 做为补位
     */
    public  static String padRight(String oriStr,int len,String alexin){
        String str = "";
        int strlen = oriStr.length();
        if(strlen < len){
            for(int i=0;i<len-strlen;i++){
                str=str+alexin;
            }
        }
        str=str+oriStr;
        return str;
    }
    /**
     * 获取机构编码 YXD0001
     * @Author:
     * @CreateDate:  2019/9/19 12:06
     * @UpdateUser:
     * @UpdateDate:  2019/9/19 12:06
     * @Version:     0.0.1
     * @param oriStr 初始值
     * @param len 位数
     * @param alexin 不足 以什么补充
     * @return       java.lang.String
     * @throws
     */
    public static String deptCode(String oriStr,int len,String alexin){
        return DEPT_TPYE+padRight(oriStr, len, alexin);
    }


    /**
    * @Description: 个人编码
    * @Param:
    * @return:
    * @Author:cgr
    * @Date: 2020-05-26 people
    */
    public static String peopleCode(String oriStr,int len,String alexin){
        return PEOPLE_TPYE+padRight(oriStr, len, alexin);
    }

}
