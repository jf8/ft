package com.kyanite.ft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A RankingData.
 */
@Entity
@Table(name = "ranking_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RankingData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_people")
    private Long totalPeople;

    @Column(name = "signd_people")
    private Long signdPeople;

    @Column(name = "attendance", precision = 21, scale = 2)
    private BigDecimal attendance;

    @Column(name = "order_num")
    private Long orderNum;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "day")
    private Instant day;

    @Column(name = "is_leaf")
    private Boolean isLeaf;

    @ManyToOne
    @JsonIgnoreProperties(value = "rankingData", allowSetters = true)
    private LiveSharing liveSharing;

    @ManyToOne
    @JsonIgnoreProperties(value = "rankingData", allowSetters = true)
    private DdBookDept ddBookDept;

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

    public RankingData name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalPeople() {
        return totalPeople;
    }

    public RankingData totalPeople(Long totalPeople) {
        this.totalPeople = totalPeople;
        return this;
    }

    public void setTotalPeople(Long totalPeople) {
        this.totalPeople = totalPeople;
    }

    public Long getSigndPeople() {
        return signdPeople;
    }

    public RankingData signdPeople(Long signdPeople) {
        this.signdPeople = signdPeople;
        return this;
    }

    public void setSigndPeople(Long signdPeople) {
        this.signdPeople = signdPeople;
    }

    public BigDecimal getAttendance() {
        return attendance;
    }

    public RankingData attendance(BigDecimal attendance) {
        this.attendance = attendance;
        return this;
    }

    public void setAttendance(BigDecimal attendance) {
        this.attendance = attendance;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public RankingData orderNum(Long orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Long getParentId() {
        return parentId;
    }

    public RankingData parentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Instant getDay() {
        return day;
    }

    public RankingData day(Instant day) {
        this.day = day;
        return this;
    }

    public void setDay(Instant day) {
        this.day = day;
    }

    public Boolean isIsLeaf() {
        return isLeaf;
    }

    public RankingData isLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
        return this;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public LiveSharing getLiveSharing() {
        return liveSharing;
    }

    public RankingData liveSharing(LiveSharing liveSharing) {
        this.liveSharing = liveSharing;
        return this;
    }

    public void setLiveSharing(LiveSharing liveSharing) {
        this.liveSharing = liveSharing;
    }

    public DdBookDept getDdBookDept() {
        return ddBookDept;
    }

    public RankingData ddBookDept(DdBookDept ddBookDept) {
        this.ddBookDept = ddBookDept;
        return this;
    }

    public void setDdBookDept(DdBookDept ddBookDept) {
        this.ddBookDept = ddBookDept;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RankingData)) {
            return false;
        }
        return id != null && id.equals(((RankingData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RankingData{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", totalPeople=" + getTotalPeople() +
            ", signdPeople=" + getSigndPeople() +
            ", attendance=" + getAttendance() +
            ", orderNum=" + getOrderNum() +
            ", parentId=" + getParentId() +
            ", day='" + getDay() + "'" +
            ", isLeaf='" + isIsLeaf() + "'" +
            "}";
    }
}
