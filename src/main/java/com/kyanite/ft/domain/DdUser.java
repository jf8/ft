package com.kyanite.ft.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A DdUser.
 */
@Entity
@Table(name = "dd_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DdUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unionid")
    private String unionid;

    @Column(name = "remark")
    private String remark;

    @Column(name = "userid")
    private String userid;

    @Column(name = "is_leader_in_depts")
    private String isLeaderInDepts;

    @Column(name = "is_boss")
    private Boolean isBoss;

    @Column(name = "hired_date", precision = 21, scale = 2)
    private BigDecimal hiredDate;

    @Column(name = "is_senior")
    private Boolean isSenior;

    @Column(name = "tel")
    private String tel;

    @Column(name = "department")
    private String department;

    @Column(name = "work_place")
    private String workPlace;

    @Column(name = "order_in_depts")
    private String orderInDepts;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "errmsg")
    private String errmsg;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "is_hide")
    private Boolean isHide;

    @Column(name = "jobnumber")
    private String jobnumber;

    @Column(name = "name")
    private String name;

    @Column(name = "extattr")
    private String extattr;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "position")
    private String position;

    @Column(name = "roles")
    private String roles;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "dd_user_dd_book_dept",
               joinColumns = @JoinColumn(name = "dd_user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "dd_book_dept_id", referencedColumnName = "id"))
    private Set<DdBookDept> ddBookDepts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnionid() {
        return unionid;
    }

    public DdUser unionid(String unionid) {
        this.unionid = unionid;
        return this;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public DdUser remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserid() {
        return userid;
    }

    public DdUser userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getIsLeaderInDepts() {
        return isLeaderInDepts;
    }

    public DdUser isLeaderInDepts(String isLeaderInDepts) {
        this.isLeaderInDepts = isLeaderInDepts;
        return this;
    }

    public void setIsLeaderInDepts(String isLeaderInDepts) {
        this.isLeaderInDepts = isLeaderInDepts;
    }

    public Boolean isIsBoss() {
        return isBoss;
    }

    public DdUser isBoss(Boolean isBoss) {
        this.isBoss = isBoss;
        return this;
    }

    public void setIsBoss(Boolean isBoss) {
        this.isBoss = isBoss;
    }

    public BigDecimal getHiredDate() {
        return hiredDate;
    }

    public DdUser hiredDate(BigDecimal hiredDate) {
        this.hiredDate = hiredDate;
        return this;
    }

    public void setHiredDate(BigDecimal hiredDate) {
        this.hiredDate = hiredDate;
    }

    public Boolean isIsSenior() {
        return isSenior;
    }

    public DdUser isSenior(Boolean isSenior) {
        this.isSenior = isSenior;
        return this;
    }

    public void setIsSenior(Boolean isSenior) {
        this.isSenior = isSenior;
    }

    public String getTel() {
        return tel;
    }

    public DdUser tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDepartment() {
        return department;
    }

    public DdUser department(String department) {
        this.department = department;
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public DdUser workPlace(String workPlace) {
        this.workPlace = workPlace;
        return this;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getOrderInDepts() {
        return orderInDepts;
    }

    public DdUser orderInDepts(String orderInDepts) {
        this.orderInDepts = orderInDepts;
        return this;
    }

    public void setOrderInDepts(String orderInDepts) {
        this.orderInDepts = orderInDepts;
    }

    public String getMobile() {
        return mobile;
    }

    public DdUser mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public DdUser errmsg(String errmsg) {
        this.errmsg = errmsg;
        return this;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Boolean isActive() {
        return active;
    }

    public DdUser active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAvatar() {
        return avatar;
    }

    public DdUser avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean isIsAdmin() {
        return isAdmin;
    }

    public DdUser isAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean isIsHide() {
        return isHide;
    }

    public DdUser isHide(Boolean isHide) {
        this.isHide = isHide;
        return this;
    }

    public void setIsHide(Boolean isHide) {
        this.isHide = isHide;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public DdUser jobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
        return this;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getName() {
        return name;
    }

    public DdUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtattr() {
        return extattr;
    }

    public DdUser extattr(String extattr) {
        this.extattr = extattr;
        return this;
    }

    public void setExtattr(String extattr) {
        this.extattr = extattr;
    }

    public String getStateCode() {
        return stateCode;
    }

    public DdUser stateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPosition() {
        return position;
    }

    public DdUser position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRoles() {
        return roles;
    }

    public DdUser roles(String roles) {
        this.roles = roles;
        return this;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Set<DdBookDept> getDdBookDepts() {
        return ddBookDepts;
    }

    public DdUser ddBookDepts(Set<DdBookDept> ddBookDepts) {
        this.ddBookDepts = ddBookDepts;
        return this;
    }

    public DdUser addDdBookDept(DdBookDept ddBookDept) {
        this.ddBookDepts.add(ddBookDept);
        ddBookDept.getDdUsers().add(this);
        return this;
    }

    public DdUser removeDdBookDept(DdBookDept ddBookDept) {
        this.ddBookDepts.remove(ddBookDept);
        ddBookDept.getDdUsers().remove(this);
        return this;
    }

    public void setDdBookDepts(Set<DdBookDept> ddBookDepts) {
        this.ddBookDepts = ddBookDepts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DdUser)) {
            return false;
        }
        return id != null && id.equals(((DdUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DdUser{" +
            "id=" + getId() +
            ", unionid='" + getUnionid() + "'" +
            ", remark='" + getRemark() + "'" +
            ", userid='" + getUserid() + "'" +
            ", isLeaderInDepts='" + getIsLeaderInDepts() + "'" +
            ", isBoss='" + isIsBoss() + "'" +
            ", hiredDate=" + getHiredDate() +
            ", isSenior='" + isIsSenior() + "'" +
            ", tel='" + getTel() + "'" +
            ", department='" + getDepartment() + "'" +
            ", workPlace='" + getWorkPlace() + "'" +
            ", orderInDepts='" + getOrderInDepts() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", errmsg='" + getErrmsg() + "'" +
            ", active='" + isActive() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", isAdmin='" + isIsAdmin() + "'" +
            ", isHide='" + isIsHide() + "'" +
            ", jobnumber='" + getJobnumber() + "'" +
            ", name='" + getName() + "'" +
            ", extattr='" + getExtattr() + "'" +
            ", stateCode='" + getStateCode() + "'" +
            ", position='" + getPosition() + "'" +
            ", roles='" + getRoles() + "'" +
            "}";
    }
}
