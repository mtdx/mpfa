package com.mma.mma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A CsgoItem.
 */
@Entity
@Table(name = "csgo_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "csgoitem")
public class CsgoItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sp", precision=10, scale=2)
    private BigDecimal sp;

    @Column(name = "opm")
    private Boolean opm;

    @Column(name = "vol")
    private Integer vol;

    @Column(name = "mp_7", precision=10, scale=2)
    private BigDecimal mp7;

    @Column(name = "avg_7", precision=10, scale=2)
    private BigDecimal avg7;

    @Column(name = "lp_7", precision=10, scale=2)
    private BigDecimal lp7;

    @Column(name = "hp_7", precision=10, scale=2)
    private BigDecimal hp7;

    @Column(name = "mad_7", precision=10, scale=2)
    private BigDecimal mad7;

    @Column(name = "dp_7", precision=10, scale=2)
    private BigDecimal dp7;

    @Column(name = "trend_7", precision=10, scale=2)
    private BigDecimal trend7;

    @Column(name = "vol_7")
    private Integer vol7;

    @Column(name = "mp_30", precision=10, scale=2)
    private BigDecimal mp30;

    @Column(name = "avg_30", precision=10, scale=2)
    private BigDecimal avg30;

    @Column(name = "lp_30", precision=10, scale=2)
    private BigDecimal lp30;

    @Column(name = "hp_30", precision=10, scale=2)
    private BigDecimal hp30;

    @Column(name = "mad_30", precision=10, scale=2)
    private BigDecimal mad30;

    @Column(name = "dp_30", precision=10, scale=2)
    private BigDecimal dp30;

    @Column(name = "trend_30", precision=10, scale=2)
    private BigDecimal trend30;

    @Column(name = "vol_30")
    private Integer vol30;

    @Column(name = "mp_all", precision=10, scale=2)
    private BigDecimal mpAll;

    @Column(name = "avg_all", precision=10, scale=2)
    private BigDecimal avgAll;

    @Column(name = "lp_all", precision=10, scale=2)
    private BigDecimal lpAll;

    @Column(name = "hp_all", precision=10, scale=2)
    private BigDecimal hpAll;

    @Column(name = "mad_all", precision=10, scale=2)
    private BigDecimal madAll;

    @Column(name = "dp_all", precision=10, scale=2)
    private BigDecimal dpAll;

    @Column(name = "trend_all", precision=10, scale=2)
    private BigDecimal trendAll;

    @Column(name = "vol_all")
    private Integer volAll;

    @Column(name = "cfp")
    private Double cfp;

    @Column(name = "iop")
    private Double iop;

    @Column(name = "dcx")
    private Double dcx;

    @Column(name = "oplp")
    private Double oplp;

    @Column(name = "opq")
    private Integer opq;

    @Column(name = "d")
    private Boolean d;

    @Column(name = "added")
    private Instant added;

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

    public CsgoItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSp() {
        return sp;
    }

    public CsgoItem sp(BigDecimal sp) {
        this.sp = sp;
        return this;
    }

    public void setSp(BigDecimal sp) {
        this.sp = sp;
    }

    public Boolean isOpm() {
        return opm;
    }

    public CsgoItem opm(Boolean opm) {
        this.opm = opm;
        return this;
    }

    public void setOpm(Boolean opm) {
        this.opm = opm;
    }

    public Integer getVol() {
        return vol;
    }

    public CsgoItem vol(Integer vol) {
        this.vol = vol;
        return this;
    }

    public void setVol(Integer vol) {
        this.vol = vol;
    }

    public BigDecimal getMp7() {
        return mp7;
    }

    public CsgoItem mp7(BigDecimal mp7) {
        this.mp7 = mp7;
        return this;
    }

    public void setMp7(BigDecimal mp7) {
        this.mp7 = mp7;
    }

    public BigDecimal getAvg7() {
        return avg7;
    }

    public CsgoItem avg7(BigDecimal avg7) {
        this.avg7 = avg7;
        return this;
    }

    public void setAvg7(BigDecimal avg7) {
        this.avg7 = avg7;
    }

    public BigDecimal getLp7() {
        return lp7;
    }

    public CsgoItem lp7(BigDecimal lp7) {
        this.lp7 = lp7;
        return this;
    }

    public void setLp7(BigDecimal lp7) {
        this.lp7 = lp7;
    }

    public BigDecimal getHp7() {
        return hp7;
    }

    public CsgoItem hp7(BigDecimal hp7) {
        this.hp7 = hp7;
        return this;
    }

    public void setHp7(BigDecimal hp7) {
        this.hp7 = hp7;
    }

    public BigDecimal getMad7() {
        return mad7;
    }

    public CsgoItem mad7(BigDecimal mad7) {
        this.mad7 = mad7;
        return this;
    }

    public void setMad7(BigDecimal mad7) {
        this.mad7 = mad7;
    }

    public BigDecimal getDp7() {
        return dp7;
    }

    public CsgoItem dp7(BigDecimal dp7) {
        this.dp7 = dp7;
        return this;
    }

    public void setDp7(BigDecimal dp7) {
        this.dp7 = dp7;
    }

    public BigDecimal getTrend7() {
        return trend7;
    }

    public CsgoItem trend7(BigDecimal trend7) {
        this.trend7 = trend7;
        return this;
    }

    public void setTrend7(BigDecimal trend7) {
        this.trend7 = trend7;
    }

    public Integer getVol7() {
        return vol7;
    }

    public CsgoItem vol7(Integer vol7) {
        this.vol7 = vol7;
        return this;
    }

    public void setVol7(Integer vol7) {
        this.vol7 = vol7;
    }

    public BigDecimal getMp30() {
        return mp30;
    }

    public CsgoItem mp30(BigDecimal mp30) {
        this.mp30 = mp30;
        return this;
    }

    public void setMp30(BigDecimal mp30) {
        this.mp30 = mp30;
    }

    public BigDecimal getAvg30() {
        return avg30;
    }

    public CsgoItem avg30(BigDecimal avg30) {
        this.avg30 = avg30;
        return this;
    }

    public void setAvg30(BigDecimal avg30) {
        this.avg30 = avg30;
    }

    public BigDecimal getLp30() {
        return lp30;
    }

    public CsgoItem lp30(BigDecimal lp30) {
        this.lp30 = lp30;
        return this;
    }

    public void setLp30(BigDecimal lp30) {
        this.lp30 = lp30;
    }

    public BigDecimal getHp30() {
        return hp30;
    }

    public CsgoItem hp30(BigDecimal hp30) {
        this.hp30 = hp30;
        return this;
    }

    public void setHp30(BigDecimal hp30) {
        this.hp30 = hp30;
    }

    public BigDecimal getMad30() {
        return mad30;
    }

    public CsgoItem mad30(BigDecimal mad30) {
        this.mad30 = mad30;
        return this;
    }

    public void setMad30(BigDecimal mad30) {
        this.mad30 = mad30;
    }

    public BigDecimal getDp30() {
        return dp30;
    }

    public CsgoItem dp30(BigDecimal dp30) {
        this.dp30 = dp30;
        return this;
    }

    public void setDp30(BigDecimal dp30) {
        this.dp30 = dp30;
    }

    public BigDecimal getTrend30() {
        return trend30;
    }

    public CsgoItem trend30(BigDecimal trend30) {
        this.trend30 = trend30;
        return this;
    }

    public void setTrend30(BigDecimal trend30) {
        this.trend30 = trend30;
    }

    public Integer getVol30() {
        return vol30;
    }

    public CsgoItem vol30(Integer vol30) {
        this.vol30 = vol30;
        return this;
    }

    public void setVol30(Integer vol30) {
        this.vol30 = vol30;
    }

    public BigDecimal getMpAll() {
        return mpAll;
    }

    public CsgoItem mpAll(BigDecimal mpAll) {
        this.mpAll = mpAll;
        return this;
    }

    public void setMpAll(BigDecimal mpAll) {
        this.mpAll = mpAll;
    }

    public BigDecimal getAvgAll() {
        return avgAll;
    }

    public CsgoItem avgAll(BigDecimal avgAll) {
        this.avgAll = avgAll;
        return this;
    }

    public void setAvgAll(BigDecimal avgAll) {
        this.avgAll = avgAll;
    }

    public BigDecimal getLpAll() {
        return lpAll;
    }

    public CsgoItem lpAll(BigDecimal lpAll) {
        this.lpAll = lpAll;
        return this;
    }

    public void setLpAll(BigDecimal lpAll) {
        this.lpAll = lpAll;
    }

    public BigDecimal getHpAll() {
        return hpAll;
    }

    public CsgoItem hpAll(BigDecimal hpAll) {
        this.hpAll = hpAll;
        return this;
    }

    public void setHpAll(BigDecimal hpAll) {
        this.hpAll = hpAll;
    }

    public BigDecimal getMadAll() {
        return madAll;
    }

    public CsgoItem madAll(BigDecimal madAll) {
        this.madAll = madAll;
        return this;
    }

    public void setMadAll(BigDecimal madAll) {
        this.madAll = madAll;
    }

    public BigDecimal getDpAll() {
        return dpAll;
    }

    public CsgoItem dpAll(BigDecimal dpAll) {
        this.dpAll = dpAll;
        return this;
    }

    public void setDpAll(BigDecimal dpAll) {
        this.dpAll = dpAll;
    }

    public BigDecimal getTrendAll() {
        return trendAll;
    }

    public CsgoItem trendAll(BigDecimal trendAll) {
        this.trendAll = trendAll;
        return this;
    }

    public void setTrendAll(BigDecimal trendAll) {
        this.trendAll = trendAll;
    }

    public Integer getVolAll() {
        return volAll;
    }

    public CsgoItem volAll(Integer volAll) {
        this.volAll = volAll;
        return this;
    }

    public void setVolAll(Integer volAll) {
        this.volAll = volAll;
    }

    public Double getCfp() {
        return cfp;
    }

    public CsgoItem cfp(Double cfp) {
        this.cfp = cfp;
        return this;
    }

    public void setCfp(Double cfp) {
        this.cfp = cfp;
    }

    public Double getIop() {
        return iop;
    }

    public CsgoItem iop(Double iop) {
        this.iop = iop;
        return this;
    }

    public void setIop(Double iop) {
        this.iop = iop;
    }

    public Double getDcx() {
        return dcx;
    }

    public CsgoItem dcx(Double dcx) {
        this.dcx = dcx;
        return this;
    }

    public void setDcx(Double dcx) {
        this.dcx = dcx;
    }

    public Double getOplp() {
        return oplp;
    }

    public CsgoItem oplp(Double oplp) {
        this.oplp = oplp;
        return this;
    }

    public void setOplp(Double oplp) {
        this.oplp = oplp;
    }

    public Integer getOpq() {
        return opq;
    }

    public CsgoItem opq(Integer opq) {
        this.opq = opq;
        return this;
    }

    public void setOpq(Integer opq) {
        this.opq = opq;
    }

    public Boolean isD() {
        return d;
    }

    public CsgoItem d(Boolean d) {
        this.d = d;
        return this;
    }

    public void setD(Boolean d) {
        this.d = d;
    }

    public Instant getAdded() {
        return added;
    }

    public CsgoItem added(Instant added) {
        this.added = added;
        return this;
    }

    public void setAdded(Instant added) {
        this.added = added;
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
        CsgoItem csgoItem = (CsgoItem) o;
        if (csgoItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), csgoItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CsgoItem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sp='" + getSp() + "'" +
            ", opm='" + isOpm() + "'" +
            ", vol='" + getVol() + "'" +
            ", mp7='" + getMp7() + "'" +
            ", avg7='" + getAvg7() + "'" +
            ", lp7='" + getLp7() + "'" +
            ", hp7='" + getHp7() + "'" +
            ", mad7='" + getMad7() + "'" +
            ", dp7='" + getDp7() + "'" +
            ", trend7='" + getTrend7() + "'" +
            ", vol7='" + getVol7() + "'" +
            ", mp30='" + getMp30() + "'" +
            ", avg30='" + getAvg30() + "'" +
            ", lp30='" + getLp30() + "'" +
            ", hp30='" + getHp30() + "'" +
            ", mad30='" + getMad30() + "'" +
            ", dp30='" + getDp30() + "'" +
            ", trend30='" + getTrend30() + "'" +
            ", vol30='" + getVol30() + "'" +
            ", mpAll='" + getMpAll() + "'" +
            ", avgAll='" + getAvgAll() + "'" +
            ", lpAll='" + getLpAll() + "'" +
            ", hpAll='" + getHpAll() + "'" +
            ", madAll='" + getMadAll() + "'" +
            ", dpAll='" + getDpAll() + "'" +
            ", trendAll='" + getTrendAll() + "'" +
            ", volAll='" + getVolAll() + "'" +
            ", cfp='" + getCfp() + "'" +
            ", iop='" + getIop() + "'" +
            ", dcx='" + getDcx() + "'" +
            ", oplp='" + getOplp() + "'" +
            ", opq='" + getOpq() + "'" +
            ", d='" + isD() + "'" +
            ", added='" + getAdded() + "'" +
            "}";
    }
}
