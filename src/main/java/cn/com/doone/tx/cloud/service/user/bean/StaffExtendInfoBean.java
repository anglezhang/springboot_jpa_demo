package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *用户组表
 * yecz
 */
@Entity
@Table(name = "ts_staff_extend_info")
@SequenceGenerator(name="common_generator",sequenceName="s_ts_staff_extend_info", // oracle 用到的序列名 s_config_code
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class StaffExtendInfoBean extends PlatBaseEntity implements Serializable {


    private static final long serialVersionUID = -3387790638243268348L;
    /** 用户ID */
    @Column(name="STAFF_ID",nullable = false)
    private Long staffId;
    /** 扩展字段 1~30 */
    @Column(name="FIELD_1",nullable = true)
    private String field1;
    @Column(name="FIELD_2",nullable = true)
    private String field2;
    @Column(name="FIELD_3",nullable = true)
    private String field3;
    @Column(name="FIELD_4",nullable = true)
    private String field4;
    @Column(name="FIELD_5",nullable = true)
    private String field5;
    @Column(name="FIELD_6",nullable = true)
    private String field6;
    @Column(name="FIELD_7",nullable = true)
    private String field7;
    @Column(name="FIELD_8",nullable = true)
    private String field8;
    @Column(name="FIELD_9",nullable = true)
    private String field9;
    @Column(name="FIELD_10",nullable = true)
    private String field10;
    @Column(name="FIELD_11",nullable = true)
    private String field11;
    @Column(name="FIELD_12",nullable = true)
    private String field12;
    @Column(name="FIELD_13",nullable = true)
    private String field13;
    @Column(name="FIELD_14",nullable = true)
    private String field14;
    @Column(name="FIELD_15",nullable = true)
    private String field15;
    @Column(name="FIELD_16",nullable = true)
    private String field16;
    @Column(name="FIELD_17",nullable = true)
    private String field17;
    @Column(name="FIELD_18",nullable = true)
    private String field18;
    @Column(name="FIELD_19",nullable = true)
    private String field19;
    @Column(name="FIELD_20",nullable = true)
    private String field20;
    @Column(name="FIELD_21",nullable = true)
    private String field21;
    @Column(name="FIELD_22",nullable = true)
    private String field22;
    @Column(name="FIELD_23",nullable = true)
    private String field23;
    @Column(name="FIELD_24",nullable = true)
    private String field24;
    @Column(name="FIELD_25",nullable = true)
    private String field25;
    @Column(name="FIELD_26",nullable = true)
    private String field26;
    @Column(name="FIELD_27",nullable = true)
    private String field27;
    @Column(name="FIELD_28",nullable = true)
    private String field28;
    @Column(name="FIELD_29",nullable = true)
    private String field29;
    @Column(name="FIELD_30",nullable = true)
    private String field30;


	public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    public String getField6() {
        return field6;
    }

    public void setField6(String field6) {
        this.field6 = field6;
    }

    public String getField7() {
        return field7;
    }

    public void setField7(String field7) {
        this.field7 = field7;
    }

    public String getField8() {
        return field8;
    }

    public void setField8(String field8) {
        this.field8 = field8;
    }

    public String getField9() {
        return field9;
    }

    public void setField9(String field9) {
        this.field9 = field9;
    }

    public String getField10() {
        return field10;
    }

    public void setField10(String field10) {
        this.field10 = field10;
    }

    public String getField11() {
        return field11;
    }

    public void setField11(String field11) {
        this.field11 = field11;
    }

    public String getField12() {
        return field12;
    }

    public void setField12(String field12) {
        this.field12 = field12;
    }

    public String getField13() {
        return field13;
    }

    public void setField13(String field13) {
        this.field13 = field13;
    }

    public String getField14() {
        return field14;
    }

    public void setField14(String field14) {
        this.field14 = field14;
    }

    public String getField15() {
        return field15;
    }

    public void setField15(String field15) {
        this.field15 = field15;
    }

    public String getField16() {
        return field16;
    }

    public void setField16(String field16) {
        this.field16 = field16;
    }

    public String getField17() {
        return field17;
    }

    public void setField17(String field17) {
        this.field17 = field17;
    }

    public String getField18() {
        return field18;
    }

    public void setField18(String field18) {
        this.field18 = field18;
    }

    public String getField19() {
        return field19;
    }

    public void setField19(String field19) {
        this.field19 = field19;
    }

    public String getField20() {
        return field20;
    }

    public void setField20(String field20) {
        this.field20 = field20;
    }

    public String getField21() {
        return field21;
    }

    public void setField21(String field21) {
        this.field21 = field21;
    }

    public String getField22() {
        return field22;
    }

    public void setField22(String field22) {
        this.field22 = field22;
    }

    public String getField23() {
        return field23;
    }

    public void setField23(String field23) {
        this.field23 = field23;
    }

    public String getField24() {
        return field24;
    }

    public void setField24(String field24) {
        this.field24 = field24;
    }

    public String getField25() {
        return field25;
    }

    public void setField25(String field25) {
        this.field25 = field25;
    }

    public String getField26() {
        return field26;
    }

    public void setField26(String field26) {
        this.field26 = field26;
    }

    public String getField27() {
        return field27;
    }

    public void setField27(String field27) {
        this.field27 = field27;
    }

    public String getField28() {
        return field28;
    }

    public void setField28(String field28) {
        this.field28 = field28;
    }

    public String getField29() {
        return field29;
    }

    public void setField29(String field29) {
        this.field29 = field29;
    }

    public String getField30() {
        return field30;
    }

    public void setField30(String field30) {
        this.field30 = field30;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "StaffExtendInfoBean{" +
                "staffId=" + staffId +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                ", field4='" + field4 + '\'' +
                ", field5='" + field5 + '\'' +
                ", field6='" + field6 + '\'' +
                ", field7='" + field7 + '\'' +
                ", field8='" + field8 + '\'' +
                ", field9='" + field9 + '\'' +
                ", field10='" + field10 + '\'' +
                ", field11='" + field11 + '\'' +
                ", field12='" + field12 + '\'' +
                ", field13='" + field13 + '\'' +
                ", field14='" + field14 + '\'' +
                ", field15='" + field15 + '\'' +
                ", field16='" + field16 + '\'' +
                ", field17='" + field17 + '\'' +
                ", field18='" + field18 + '\'' +
                ", field19='" + field19 + '\'' +
                ", field20='" + field20 + '\'' +
                ", field21='" + field21 + '\'' +
                ", field22='" + field22 + '\'' +
                ", field23='" + field23 + '\'' +
                ", field24='" + field24 + '\'' +
                ", field25='" + field25 + '\'' +
                ", field26='" + field26 + '\'' +
                ", field27='" + field27 + '\'' +
                ", field28='" + field28 + '\'' +
                ", field29='" + field29 + '\'' +
                ", field30='" + field30 + '\'' +
                '}';
    }
}
