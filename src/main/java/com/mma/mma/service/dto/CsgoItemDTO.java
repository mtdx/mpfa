package com.mma.mma.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the CsgoItem entity.
 */
public class CsgoItemDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private BigDecimal sp;

    private Boolean opm;

    private Integer vol;

    private BigDecimal mp7;

    private BigDecimal avg7;

    private BigDecimal lp7;

    private BigDecimal hp7;

    private BigDecimal mad7;

    private BigDecimal dp7;

    private BigDecimal trend7;

    private Integer vol7;

    private BigDecimal mp30;

    private BigDecimal avg30;

    private BigDecimal lp30;

    private BigDecimal hp30;

    private BigDecimal mad30;

    private BigDecimal dp30;

    private BigDecimal trend30;

    private Integer vol30;

    private BigDecimal mpAll;

    private BigDecimal avgAll;

    private BigDecimal lpAll;

    private BigDecimal hpAll;

    private BigDecimal madAll;

    private BigDecimal dpAll;

    private BigDecimal trendAll;

    private Integer volAll;

    private Double cfp;

    private Double iop;

    private Double dcx;

    private Instant added;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSp() {
        return sp;
    }

    public void setSp(BigDecimal sp) {
        this.sp = sp;
    }

    public Boolean isOpm() {
        return opm;
    }

    public void setOpm(Boolean opm) {
        this.opm = opm;
    }

    public Integer getVol() {
        return vol;
    }

    public void setVol(Integer vol) {
        this.vol = vol;
    }

    public BigDecimal getMp7() {
        return mp7;
    }

    public void setMp7(BigDecimal mp7) {
        this.mp7 = mp7;
    }

    public BigDecimal getAvg7() {
        return avg7;
    }

    public void setAvg7(BigDecimal avg7) {
        this.avg7 = avg7;
    }

    public BigDecimal getLp7() {
        return lp7;
    }

    public void setLp7(BigDecimal lp7) {
        this.lp7 = lp7;
    }

    public BigDecimal getHp7() {
        return hp7;
    }

    public void setHp7(BigDecimal hp7) {
        this.hp7 = hp7;
    }

    public BigDecimal getMad7() {
        return mad7;
    }

    public void setMad7(BigDecimal mad7) {
        this.mad7 = mad7;
    }

    public BigDecimal getDp7() {
        return dp7;
    }

    public void setDp7(BigDecimal dp7) {
        this.dp7 = dp7;
    }

    public BigDecimal getTrend7() {
        return trend7;
    }

    public void setTrend7(BigDecimal trend7) {
        this.trend7 = trend7;
    }

    public Integer getVol7() {
        return vol7;
    }

    public void setVol7(Integer vol7) {
        this.vol7 = vol7;
    }

    public BigDecimal getMp30() {
        return mp30;
    }

    public void setMp30(BigDecimal mp30) {
        this.mp30 = mp30;
    }

    public BigDecimal getAvg30() {
        return avg30;
    }

    public void setAvg30(BigDecimal avg30) {
        this.avg30 = avg30;
    }

    public BigDecimal getLp30() {
        return lp30;
    }

    public void setLp30(BigDecimal lp30) {
        this.lp30 = lp30;
    }

    public BigDecimal getHp30() {
        return hp30;
    }

    public void setHp30(BigDecimal hp30) {
        this.hp30 = hp30;
    }

    public BigDecimal getMad30() {
        return mad30;
    }

    public void setMad30(BigDecimal mad30) {
        this.mad30 = mad30;
    }

    public BigDecimal getDp30() {
        return dp30;
    }

    public void setDp30(BigDecimal dp30) {
        this.dp30 = dp30;
    }

    public BigDecimal getTrend30() {
        return trend30;
    }

    public void setTrend30(BigDecimal trend30) {
        this.trend30 = trend30;
    }

    public Integer getVol30() {
        return vol30;
    }

    public void setVol30(Integer vol30) {
        this.vol30 = vol30;
    }

    public BigDecimal getMpAll() {
        return mpAll;
    }

    public void setMpAll(BigDecimal mpAll) {
        this.mpAll = mpAll;
    }

    public BigDecimal getAvgAll() {
        return avgAll;
    }

    public void setAvgAll(BigDecimal avgAll) {
        this.avgAll = avgAll;
    }

    public BigDecimal getLpAll() {
        return lpAll;
    }

    public void setLpAll(BigDecimal lpAll) {
        this.lpAll = lpAll;
    }

    public BigDecimal getHpAll() {
        return hpAll;
    }

    public void setHpAll(BigDecimal hpAll) {
        this.hpAll = hpAll;
    }

    public BigDecimal getMadAll() {
        return madAll;
    }

    public void setMadAll(BigDecimal madAll) {
        this.madAll = madAll;
    }

    public BigDecimal getDpAll() {
        return dpAll;
    }

    public void setDpAll(BigDecimal dpAll) {
        this.dpAll = dpAll;
    }

    public BigDecimal getTrendAll() {
        return trendAll;
    }

    public void setTrendAll(BigDecimal trendAll) {
        this.trendAll = trendAll;
    }

    public Integer getVolAll() {
        return volAll;
    }

    public void setVolAll(Integer volAll) {
        this.volAll = volAll;
    }

    public Double getCfp() {
        return cfp;
    }

    public void setCfp(Double cfp) {
        this.cfp = cfp;
    }

    public Double getIop() {
        return iop;
    }

    public void setIop(Double iop) {
        this.iop = iop;
    }

    public Double getDcx() {
        return dcx;
    }

    public void setDcx(Double dcx) {
        this.dcx = dcx;
    }

    public Instant getAdded() {
        return added;
    }

    public void setAdded(Instant added) {
        this.added = added;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CsgoItemDTO csgoItemDTO = (CsgoItemDTO) o;
        if(csgoItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), csgoItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CsgoItemDTO{" +
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
            ", added='" + getAdded() + "'" +
            "}";
    }
}
