package com.kyanite.ft.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A VFtUserSignInfo.
 */
@Entity
@Table(name = "v_ft_user_sign_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VFtUserSignInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_code")
    private String phoneCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "seat")
    private String seat;

    @Column(name = "group_ids")
    private String groupIds;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "name_cn")
    private String nameCn;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "company_cn")
    private String companyCn;

    @Column(name = "company_en")
    private String companyEn;

    @Column(name = "title_cn")
    private String titleCn;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "remark")
    private String remark;

    @Column(name = "ddid")
    private String ddid;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "sign_time")
    private Instant signTime;

    @Column(name = "meet_id")
    private Long meetId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public VFtUserSignInfo phoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
        return this;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhone() {
        return phone;
    }

    public VFtUserSignInfo phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public VFtUserSignInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeat() {
        return seat;
    }

    public VFtUserSignInfo seat(String seat) {
        this.seat = seat;
        return this;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public VFtUserSignInfo groupIds(String groupIds) {
        this.groupIds = groupIds;
        return this;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public VFtUserSignInfo startTime(Instant startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public VFtUserSignInfo endTime(Instant endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public String getNameCn() {
        return nameCn;
    }

    public VFtUserSignInfo nameCn(String nameCn) {
        this.nameCn = nameCn;
        return this;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public VFtUserSignInfo nameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getCompanyCn() {
        return companyCn;
    }

    public VFtUserSignInfo companyCn(String companyCn) {
        this.companyCn = companyCn;
        return this;
    }

    public void setCompanyCn(String companyCn) {
        this.companyCn = companyCn;
    }

    public String getCompanyEn() {
        return companyEn;
    }

    public VFtUserSignInfo companyEn(String companyEn) {
        this.companyEn = companyEn;
        return this;
    }

    public void setCompanyEn(String companyEn) {
        this.companyEn = companyEn;
    }

    public String getTitleCn() {
        return titleCn;
    }

    public VFtUserSignInfo titleCn(String titleCn) {
        this.titleCn = titleCn;
        return this;
    }

    public void setTitleCn(String titleCn) {
        this.titleCn = titleCn;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public VFtUserSignInfo titleEn(String titleEn) {
        this.titleEn = titleEn;
        return this;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getRemark() {
        return remark;
    }

    public VFtUserSignInfo remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDdid() {
        return ddid;
    }

    public VFtUserSignInfo ddid(String ddid) {
        this.ddid = ddid;
        return this;
    }

    public void setDdid(String ddid) {
        this.ddid = ddid;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public VFtUserSignInfo updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public VFtUserSignInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getSignTime() {
        return signTime;
    }

    public VFtUserSignInfo signTime(Instant signTime) {
        this.signTime = signTime;
        return this;
    }

    public void setSignTime(Instant signTime) {
        this.signTime = signTime;
    }

    public Long getMeetId() {
        return meetId;
    }

    public VFtUserSignInfo meetId(Long meetId) {
        this.meetId = meetId;
        return this;
    }

    public void setMeetId(Long meetId) {
        this.meetId = meetId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VFtUserSignInfo)) {
            return false;
        }
        return id != null && id.equals(((VFtUserSignInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VFtUserSignInfo{" +
            "id=" + getId() +
            ", phoneCode='" + getPhoneCode() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", seat='" + getSeat() + "'" +
            ", groupIds='" + getGroupIds() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", nameCn='" + getNameCn() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", companyCn='" + getCompanyCn() + "'" +
            ", companyEn='" + getCompanyEn() + "'" +
            ", titleCn='" + getTitleCn() + "'" +
            ", titleEn='" + getTitleEn() + "'" +
            ", remark='" + getRemark() + "'" +
            ", ddid='" + getDdid() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", signTime='" + getSignTime() + "'" +
            ", meetId=" + getMeetId() +
            "}";
    }
}
