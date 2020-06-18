package com.cgr.lesson.vo.resp;

import lombok.Data;

import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 18:39 2020-05-29
 * @ Description：个人信息登记返回类
 * @ Modified By：
 */
@Data
public class PeopleInfoRespVO {
    private String id;
    private String name;
    private String people_id;
    private String sex;
    private Date birthday;
    private String id_card;
    private String work_place;
    private String phone;
    private String contacts_name;
    private String contacts_phone;
    private String residence_name;
    private String nation;
    private String blood_name;
    private String blood_rh_name;
    private String education_name;
    private String profession_name;
    private String marital_status_name;
    private String pay_name;
    private String pay_text;
    private String allergic_history_name;
    private String exposure_history_name;
    private String medical_card;
    private String people_pasthistory_id;
    private String disease_history_name;
    private String operation_history_name;
    private String trauma_history_name;
    private String bunko_history_name;
    private String father_disease_name;
    private String monther_disease_name;
    private String brother_disease_name;
    private String children_disease_name;
    private String heredity_disease_name;
    private String disability_name;
    private String disability_text;
    private String disability_num;
    private String disability_grade_name;
    private String people_living_id;
    private String kitchen_exhaust_name;
    private String fuel_type_name;
    private String water_name;
    private String toilet_name;
    private String livestock_farm_name;

}
