package com.kyanite.ft.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A LiveSharing.
 */
@Entity
@Table(name = "live_sharing")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LiveSharing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "day")
    private Instant day;

    @Column(name = "conf_number")
    private String confNumber;

    @OneToMany(mappedBy = "liveSharing")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RankingData> rankingData = new HashSet<>();

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

    public LiveSharing name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDay() {
        return day;
    }

    public LiveSharing day(Instant day) {
        this.day = day;
        return this;
    }

    public void setDay(Instant day) {
        this.day = day;
    }

    public String getConfNumber() {
        return confNumber;
    }

    public LiveSharing confNumber(String confNumber) {
        this.confNumber = confNumber;
        return this;
    }

    public void setConfNumber(String confNumber) {
        this.confNumber = confNumber;
    }

    public Set<RankingData> getRankingData() {
        return rankingData;
    }

    public LiveSharing rankingData(Set<RankingData> rankingData) {
        this.rankingData = rankingData;
        return this;
    }

    public LiveSharing addRankingData(RankingData rankingData) {
        this.rankingData.add(rankingData);
        rankingData.setLiveSharing(this);
        return this;
    }

    public LiveSharing removeRankingData(RankingData rankingData) {
        this.rankingData.remove(rankingData);
        rankingData.setLiveSharing(null);
        return this;
    }

    public void setRankingData(Set<RankingData> rankingData) {
        this.rankingData = rankingData;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LiveSharing)) {
            return false;
        }
        return id != null && id.equals(((LiveSharing) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LiveSharing{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", day='" + getDay() + "'" +
            ", confNumber='" + getConfNumber() + "'" +
            "}";
    }
}
