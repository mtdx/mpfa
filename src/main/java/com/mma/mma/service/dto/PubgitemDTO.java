package com.mma.mma.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Pubgitem entity.
 */
public class PubgitemDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Boolean uns;

    private String unsr;

    private Integer rank;

    private Double sp;

    private Double maxp;

    private Double avgp;

    private Double minp;

    private Integer savgd;

    private Integer s24h;

    private Integer s7d;

    private Integer s30d;

    private Integer s90d;

    private Double cfp;

    private Double iop;

    private Double dcx;

    private Double dopx;

    private Double oplp;

    private Integer opq;

    private String nid;

    private Instant uat;

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

    public Boolean isUns() {
        return uns;
    }

    public void setUns(Boolean uns) {
        this.uns = uns;
    }

    public String getUnsr() {
        return unsr;
    }

    public void setUnsr(String unsr) {
        this.unsr = unsr;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getSp() {
        return sp;
    }

    public void setSp(Double sp) {
        this.sp = sp;
    }

    public Double getMaxp() {
        return maxp;
    }

    public void setMaxp(Double maxp) {
        this.maxp = maxp;
    }

    public Double getAvgp() {
        return avgp;
    }

    public void setAvgp(Double avgp) {
        this.avgp = avgp;
    }

    public Double getMinp() {
        return minp;
    }

    public void setMinp(Double minp) {
        this.minp = minp;
    }

    public Integer getSavgd() {
        return savgd;
    }

    public void setSavgd(Integer savgd) {
        this.savgd = savgd;
    }

    public Integer gets24h() {
        return s24h;
    }

    public void sets24h(Integer s24h) {
        this.s24h = s24h;
    }

    public Integer gets7d() {
        return s7d;
    }

    public void sets7d(Integer s7d) {
        this.s7d = s7d;
    }

    public Integer gets30d() {
        return s30d;
    }

    public void sets30d(Integer s30d) {
        this.s30d = s30d;
    }

    public Integer gets90d() {
        return s90d;
    }

    public void sets90d(Integer s90d) {
        this.s90d = s90d;
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

    public Double getDopx() {
        return dopx;
    }

    public void setDopx(Double dopx) {
        this.dopx = dopx;
    }

    public Double getOplp() {
        return oplp;
    }

    public void setOplp(Double oplp) {
        this.oplp = oplp;
    }

    public Integer getOpq() {
        return opq;
    }

    public void setOpq(Integer opq) {
        this.opq = opq;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public Instant getUat() {
        return uat;
    }

    public void setUat(Instant uat) {
        this.uat = uat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PubgitemDTO pubgitemDTO = (PubgitemDTO) o;
        if(pubgitemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pubgitemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PubgitemDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", uns='" + isUns() + "'" +
            ", unsr='" + getUnsr() + "'" +
            ", rank='" + getRank() + "'" +
            ", sp='" + getSp() + "'" +
            ", maxp='" + getMaxp() + "'" +
            ", avgp='" + getAvgp() + "'" +
            ", minp='" + getMinp() + "'" +
            ", savgd='" + getSavgd() + "'" +
            ", s24h='" + gets24h() + "'" +
            ", s7d='" + gets7d() + "'" +
            ", s30d='" + gets30d() + "'" +
            ", s90d='" + gets90d() + "'" +
            ", cfp='" + getCfp() + "'" +
            ", iop='" + getIop() + "'" +
            ", dcx='" + getDcx() + "'" +
            ", dopx='" + getDopx() + "'" +
            ", oplp='" + getOplp() + "'" +
            ", opq='" + getOpq() + "'" +
            ", nid='" + getNid() + "'" +
            ", uat='" + getUat() + "'" +
            "}";
    }
}
