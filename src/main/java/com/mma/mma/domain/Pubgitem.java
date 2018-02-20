package com.mma.mma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Pubgitem.
 */
@Entity
@Table(name = "pubg_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "pubgitem")
public class Pubgitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "uns")
    private Boolean uns;

    @Column(name = "unsr")
    private String unsr;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "sp")
    private Double sp;

    @Column(name = "meanp")
    private Double meanp;

    @Column(name = "maxp")
    private Double maxp;

    @Column(name = "avgp")
    private Double avgp;

    @Column(name = "minp")
    private Double minp;

    @Column(name = "lp")
    private Double lp;

    @Column(name = "savgd")
    private Integer savgd;

    @Column(name = "s_24_h")
    private Integer s24h;

    @Column(name = "s_7_d")
    private Integer s7d;

    @Column(name = "s_30_d")
    private Integer s30d;

    @Column(name = "s_90_d")
    private Integer s90d;

    @Column(name = "nid")
    private String nid;

    @Column(name = "uat")
    private Instant uat;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Pubgitem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isUns() {
        return uns;
    }

    public Pubgitem uns(Boolean uns) {
        this.uns = uns;
        return this;
    }

    public void setUns(Boolean uns) {
        this.uns = uns;
    }

    public String getUnsr() {
        return unsr;
    }

    public Pubgitem unsr(String unsr) {
        this.unsr = unsr;
        return this;
    }

    public void setUnsr(String unsr) {
        this.unsr = unsr;
    }

    public Integer getRank() {
        return rank;
    }

    public Pubgitem rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getSp() {
        return sp;
    }

    public Pubgitem sp(Double sp) {
        this.sp = sp;
        return this;
    }

    public void setSp(Double sp) {
        this.sp = sp;
    }

    public Double getMeanp() {
        return meanp;
    }

    public Pubgitem meanp(Double meanp) {
        this.meanp = meanp;
        return this;
    }

    public void setMeanp(Double meanp) {
        this.meanp = meanp;
    }

    public Double getMaxp() {
        return maxp;
    }

    public Pubgitem maxp(Double maxp) {
        this.maxp = maxp;
        return this;
    }

    public void setMaxp(Double maxp) {
        this.maxp = maxp;
    }

    public Double getAvgp() {
        return avgp;
    }

    public Pubgitem avgp(Double avgp) {
        this.avgp = avgp;
        return this;
    }

    public void setAvgp(Double avgp) {
        this.avgp = avgp;
    }

    public Double getMinp() {
        return minp;
    }

    public Pubgitem minp(Double minp) {
        this.minp = minp;
        return this;
    }

    public void setMinp(Double minp) {
        this.minp = minp;
    }

    public Double getLp() {
        return lp;
    }

    public Pubgitem lp(Double lp) {
        this.lp = lp;
        return this;
    }

    public void setLp(Double lp) {
        this.lp = lp;
    }

    public Integer getSavgd() {
        return savgd;
    }

    public Pubgitem savgd(Integer savgd) {
        this.savgd = savgd;
        return this;
    }

    public void setSavgd(Integer savgd) {
        this.savgd = savgd;
    }

    public Integer gets24h() {
        return s24h;
    }

    public Pubgitem s24h(Integer s24h) {
        this.s24h = s24h;
        return this;
    }

    public void sets24h(Integer s24h) {
        this.s24h = s24h;
    }

    public Integer gets7d() {
        return s7d;
    }

    public Pubgitem s7d(Integer s7d) {
        this.s7d = s7d;
        return this;
    }

    public void sets7d(Integer s7d) {
        this.s7d = s7d;
    }

    public Integer gets30d() {
        return s30d;
    }

    public Pubgitem s30d(Integer s30d) {
        this.s30d = s30d;
        return this;
    }

    public void sets30d(Integer s30d) {
        this.s30d = s30d;
    }

    public Integer gets90d() {
        return s90d;
    }

    public Pubgitem s90d(Integer s90d) {
        this.s90d = s90d;
        return this;
    }

    public void sets90d(Integer s90d) {
        this.s90d = s90d;
    }

    public String getNid() {
        return nid;
    }

    public Pubgitem nid(String nid) {
        this.nid = nid;
        return this;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public Instant getUat() {
        return uat;
    }

    public Pubgitem uat(Instant uat) {
        this.uat = uat;
        return this;
    }

    public void setUat(Instant uat) {
        this.uat = uat;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pubgitem pubgitem = (Pubgitem) o;
        if (pubgitem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pubgitem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pubgitem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", uns='" + isUns() + "'" +
            ", unsr='" + getUnsr() + "'" +
            ", rank='" + getRank() + "'" +
            ", sp='" + getSp() + "'" +
            ", meanp='" + getMeanp() + "'" +
            ", maxp='" + getMaxp() + "'" +
            ", avgp='" + getAvgp() + "'" +
            ", minp='" + getMinp() + "'" +
            ", lp='" + getLp() + "'" +
            ", savgd='" + getSavgd() + "'" +
            ", s24h='" + gets24h() + "'" +
            ", s7d='" + gets7d() + "'" +
            ", s30d='" + gets30d() + "'" +
            ", s90d='" + gets90d() + "'" +
            ", nid='" + getNid() + "'" +
            ", uat='" + getUat() + "'" +
            "}";
    }
}
