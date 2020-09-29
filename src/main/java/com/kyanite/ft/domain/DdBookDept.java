package com.kyanite.ft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DdBookDept.
 */
@Entity
@Table(name = "dd_book_dept")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DdBookDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "order_num")
    private Long orderNum;

    @Column(name = "parentid")
    private Long parentid;

    @Column(name = "create_dept_group")
    private Boolean createDeptGroup;

    @Column(name = "auto_add_user")
    private Boolean autoAddUser;

    @Column(name = "dept_hiding")
    private Boolean deptHiding;

    @Column(name = "dept_permits")
    private String deptPermits;

    @Column(name = "user_permits")
    private String userPermits;

    @Column(name = "outer_dept")
    private Boolean outerDept;

    @Column(name = "outer_permit_depts")
    private String outerPermitDepts;

    @Column(name = "outer_permit_users")
    private String outerPermitUsers;

    @Column(name = "org_dept_owner")
    private String orgDeptOwner;

    @Column(name = "dept_manager_userid_list")
    private String deptManagerUseridList;

    @Column(name = "source_identifier")
    private String sourceIdentifier;

    @Column(name = "ext")
    private String ext;

    @Column(name = "is_leaf")
    private Boolean isLeaf;

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DdBookDept> children = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "dd_book_dept_dd_book_person",
               joinColumns = @JoinColumn(name = "dd_book_dept_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "dd_book_person_id", referencedColumnName = "id"))
    private Set<DdBookPerson> ddBookPeople = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "children", allowSetters = true)
    private DdBookDept parent;

    @ManyToMany(mappedBy = "ddBookDepts")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<DdUser> ddUsers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DdBookDept name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public DdBookDept orderNum(Long orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Long getParentid() {
        return parentid;
    }

    public DdBookDept parentid(Long parentid) {
        this.parentid = parentid;
        return this;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Boolean isCreateDeptGroup() {
        return createDeptGroup;
    }

    public DdBookDept createDeptGroup(Boolean createDeptGroup) {
        this.createDeptGroup = createDeptGroup;
        return this;
    }

    public void setCreateDeptGroup(Boolean createDeptGroup) {
        this.createDeptGroup = createDeptGroup;
    }

    public Boolean isAutoAddUser() {
        return autoAddUser;
    }

    public DdBookDept autoAddUser(Boolean autoAddUser) {
        this.autoAddUser = autoAddUser;
        return this;
    }

    public void setAutoAddUser(Boolean autoAddUser) {
        this.autoAddUser = autoAddUser;
    }

    public Boolean isDeptHiding() {
        return deptHiding;
    }

    public DdBookDept deptHiding(Boolean deptHiding) {
        this.deptHiding = deptHiding;
        return this;
    }

    public void setDeptHiding(Boolean deptHiding) {
        this.deptHiding = deptHiding;
    }

    public String getDeptPermits() {
        return deptPermits;
    }

    public DdBookDept deptPermits(String deptPermits) {
        this.deptPermits = deptPermits;
        return this;
    }

    public void setDeptPermits(String deptPermits) {
        this.deptPermits = deptPermits;
    }

    public String getUserPermits() {
        return userPermits;
    }

    public DdBookDept userPermits(String userPermits) {
        this.userPermits = userPermits;
        return this;
    }

    public void setUserPermits(String userPermits) {
        this.userPermits = userPermits;
    }

    public Boolean isOuterDept() {
        return outerDept;
    }

    public DdBookDept outerDept(Boolean outerDept) {
        this.outerDept = outerDept;
        return this;
    }

    public void setOuterDept(Boolean outerDept) {
        this.outerDept = outerDept;
    }

    public String getOuterPermitDepts() {
        return outerPermitDepts;
    }

    public DdBookDept outerPermitDepts(String outerPermitDepts) {
        this.outerPermitDepts = outerPermitDepts;
        return this;
    }

    public void setOuterPermitDepts(String outerPermitDepts) {
        this.outerPermitDepts = outerPermitDepts;
    }

    public String getOuterPermitUsers() {
        return outerPermitUsers;
    }

    public DdBookDept outerPermitUsers(String outerPermitUsers) {
        this.outerPermitUsers = outerPermitUsers;
        return this;
    }

    public void setOuterPermitUsers(String outerPermitUsers) {
        this.outerPermitUsers = outerPermitUsers;
    }

    public String getOrgDeptOwner() {
        return orgDeptOwner;
    }

    public DdBookDept orgDeptOwner(String orgDeptOwner) {
        this.orgDeptOwner = orgDeptOwner;
        return this;
    }

    public void setOrgDeptOwner(String orgDeptOwner) {
        this.orgDeptOwner = orgDeptOwner;
    }

    public String getDeptManagerUseridList() {
        return deptManagerUseridList;
    }

    public DdBookDept deptManagerUseridList(String deptManagerUseridList) {
        this.deptManagerUseridList = deptManagerUseridList;
        return this;
    }

    public void setDeptManagerUseridList(String deptManagerUseridList) {
        this.deptManagerUseridList = deptManagerUseridList;
    }

    public String getSourceIdentifier() {
        return sourceIdentifier;
    }

    public DdBookDept sourceIdentifier(String sourceIdentifier) {
        this.sourceIdentifier = sourceIdentifier;
        return this;
    }

    public void setSourceIdentifier(String sourceIdentifier) {
        this.sourceIdentifier = sourceIdentifier;
    }

    public String getExt() {
        return ext;
    }

    public DdBookDept ext(String ext) {
        this.ext = ext;
        return this;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Boolean isIsLeaf() {
        return isLeaf;
    }

    public DdBookDept isLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
        return this;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Set<DdBookDept> getChildren() {
        return children;
    }

    public DdBookDept children(Set<DdBookDept> ddBookDepts) {
        this.children = ddBookDepts;
        return this;
    }

    public DdBookDept addChildren(DdBookDept ddBookDept) {
        this.children.add(ddBookDept);
        ddBookDept.setParent(this);
        return this;
    }

    public DdBookDept removeChildren(DdBookDept ddBookDept) {
        this.children.remove(ddBookDept);
        ddBookDept.setParent(null);
        return this;
    }

    public void setChildren(Set<DdBookDept> ddBookDepts) {
        this.children = ddBookDepts;
    }

    public Set<DdBookPerson> getDdBookPeople() {
        return ddBookPeople;
    }

    public DdBookDept ddBookPeople(Set<DdBookPerson> ddBookPeople) {
        this.ddBookPeople = ddBookPeople;
        return this;
    }

    public DdBookDept addDdBookPerson(DdBookPerson ddBookPerson) {
        this.ddBookPeople.add(ddBookPerson);
        ddBookPerson.getDdBookDepts().add(this);
        return this;
    }

    public DdBookDept removeDdBookPerson(DdBookPerson ddBookPerson) {
        this.ddBookPeople.remove(ddBookPerson);
        ddBookPerson.getDdBookDepts().remove(this);
        return this;
    }

    public void setDdBookPeople(Set<DdBookPerson> ddBookPeople) {
        this.ddBookPeople = ddBookPeople;
    }

    public DdBookDept getParent() {
        return parent;
    }

    public DdBookDept parent(DdBookDept ddBookDept) {
        this.parent = ddBookDept;
        return this;
    }

    public void setParent(DdBookDept ddBookDept) {
        this.parent = ddBookDept;
    }

    public Set<DdUser> getDdUsers() {
        return ddUsers;
    }

    public DdBookDept ddUsers(Set<DdUser> ddUsers) {
        this.ddUsers = ddUsers;
        return this;
    }

    public DdBookDept addDdUser(DdUser ddUser) {
        this.ddUsers.add(ddUser);
        ddUser.getDdBookDepts().add(this);
        return this;
    }

    public DdBookDept removeDdUser(DdUser ddUser) {
        this.ddUsers.remove(ddUser);
        ddUser.getDdBookDepts().remove(this);
        return this;
    }

    public void setDdUsers(Set<DdUser> ddUsers) {
        this.ddUsers = ddUsers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DdBookDept)) {
            return false;
        }
        return id != null && id.equals(((DdBookDept) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DdBookDept{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", orderNum=" + getOrderNum() +
            ", parentid=" + getParentid() +
            ", createDeptGroup='" + isCreateDeptGroup() + "'" +
            ", autoAddUser='" + isAutoAddUser() + "'" +
            ", deptHiding='" + isDeptHiding() + "'" +
            ", deptPermits='" + getDeptPermits() + "'" +
            ", userPermits='" + getUserPermits() + "'" +
            ", outerDept='" + isOuterDept() + "'" +
            ", outerPermitDepts='" + getOuterPermitDepts() + "'" +
            ", outerPermitUsers='" + getOuterPermitUsers() + "'" +
            ", orgDeptOwner='" + getOrgDeptOwner() + "'" +
            ", deptManagerUseridList='" + getDeptManagerUseridList() + "'" +
            ", sourceIdentifier='" + getSourceIdentifier() + "'" +
            ", ext='" + getExt() + "'" +
            ", isLeaf='" + isIsLeaf() + "'" +
            "}";
    }
}
