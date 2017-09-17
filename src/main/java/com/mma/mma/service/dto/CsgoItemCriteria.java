package com.mma.mma.service.dto;

import io.github.jhipster.service.filter.*;

import java.io.Serializable;




/**
 * Criteria class for the CsgoItem entity. This class is used in CsgoItemResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /csgo-items?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CsgoItemCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private BigDecimalFilter sp;

    private BooleanFilter opm;

    private IntegerFilter vol;

    private BigDecimalFilter mp7;

    private BigDecimalFilter avg7;

    private BigDecimalFilter lp7;

    private BigDecimalFilter hp7;

    private BigDecimalFilter mad7;

    private BigDecimalFilter dp7;

    private BigDecimalFilter trend7;

    private IntegerFilter vol7;

    private BigDecimalFilter mp30;

    private BigDecimalFilter avg30;

    private BigDecimalFilter lp30;

    private BigDecimalFilter hp30;

    private BigDecimalFilter mad30;

    private BigDecimalFilter dp30;

    private BigDecimalFilter trend30;

    private IntegerFilter vol30;

    private BigDecimalFilter mpAll;

    private BigDecimalFilter avgAll;

    private BigDecimalFilter lpAll;

    private BigDecimalFilter hpAll;

    private BigDecimalFilter madAll;

    private BigDecimalFilter dpAll;

    private BigDecimalFilter trendAll;

    private IntegerFilter volAll;

    private DoubleFilter cfp;

    private DoubleFilter iop;

    private DoubleFilter dcx;

    private InstantFilter added;

    public CsgoItemCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public BigDecimalFilter getSp() {
        return sp;
    }

    public void setSp(BigDecimalFilter sp) {
        this.sp = sp;
    }

    public BooleanFilter getOpm() {
        return opm;
    }

    public void setOpm(BooleanFilter opm) {
        this.opm = opm;
    }

    public IntegerFilter getVol() {
        return vol;
    }

    public void setVol(IntegerFilter vol) {
        this.vol = vol;
    }

    public BigDecimalFilter getMp7() {
        return mp7;
    }

    public void setMp7(BigDecimalFilter mp7) {
        this.mp7 = mp7;
    }

    public BigDecimalFilter getAvg7() {
        return avg7;
    }

    public void setAvg7(BigDecimalFilter avg7) {
        this.avg7 = avg7;
    }

    public BigDecimalFilter getLp7() {
        return lp7;
    }

    public void setLp7(BigDecimalFilter lp7) {
        this.lp7 = lp7;
    }

    public BigDecimalFilter getHp7() {
        return hp7;
    }

    public void setHp7(BigDecimalFilter hp7) {
        this.hp7 = hp7;
    }

    public BigDecimalFilter getMad7() {
        return mad7;
    }

    public void setMad7(BigDecimalFilter mad7) {
        this.mad7 = mad7;
    }

    public BigDecimalFilter getDp7() {
        return dp7;
    }

    public void setDp7(BigDecimalFilter dp7) {
        this.dp7 = dp7;
    }

    public BigDecimalFilter getTrend7() {
        return trend7;
    }

    public void setTrend7(BigDecimalFilter trend7) {
        this.trend7 = trend7;
    }

    public IntegerFilter getVol7() {
        return vol7;
    }

    public void setVol7(IntegerFilter vol7) {
        this.vol7 = vol7;
    }

    public BigDecimalFilter getMp30() {
        return mp30;
    }

    public void setMp30(BigDecimalFilter mp30) {
        this.mp30 = mp30;
    }

    public BigDecimalFilter getAvg30() {
        return avg30;
    }

    public void setAvg30(BigDecimalFilter avg30) {
        this.avg30 = avg30;
    }

    public BigDecimalFilter getLp30() {
        return lp30;
    }

    public void setLp30(BigDecimalFilter lp30) {
        this.lp30 = lp30;
    }

    public BigDecimalFilter getHp30() {
        return hp30;
    }

    public void setHp30(BigDecimalFilter hp30) {
        this.hp30 = hp30;
    }

    public BigDecimalFilter getMad30() {
        return mad30;
    }

    public void setMad30(BigDecimalFilter mad30) {
        this.mad30 = mad30;
    }

    public BigDecimalFilter getDp30() {
        return dp30;
    }

    public void setDp30(BigDecimalFilter dp30) {
        this.dp30 = dp30;
    }

    public BigDecimalFilter getTrend30() {
        return trend30;
    }

    public void setTrend30(BigDecimalFilter trend30) {
        this.trend30 = trend30;
    }

    public IntegerFilter getVol30() {
        return vol30;
    }

    public void setVol30(IntegerFilter vol30) {
        this.vol30 = vol30;
    }

    public BigDecimalFilter getMpAll() {
        return mpAll;
    }

    public void setMpAll(BigDecimalFilter mpAll) {
        this.mpAll = mpAll;
    }

    public BigDecimalFilter getAvgAll() {
        return avgAll;
    }

    public void setAvgAll(BigDecimalFilter avgAll) {
        this.avgAll = avgAll;
    }

    public BigDecimalFilter getLpAll() {
        return lpAll;
    }

    public void setLpAll(BigDecimalFilter lpAll) {
        this.lpAll = lpAll;
    }

    public BigDecimalFilter getHpAll() {
        return hpAll;
    }

    public void setHpAll(BigDecimalFilter hpAll) {
        this.hpAll = hpAll;
    }

    public BigDecimalFilter getMadAll() {
        return madAll;
    }

    public void setMadAll(BigDecimalFilter madAll) {
        this.madAll = madAll;
    }

    public BigDecimalFilter getDpAll() {
        return dpAll;
    }

    public void setDpAll(BigDecimalFilter dpAll) {
        this.dpAll = dpAll;
    }

    public BigDecimalFilter getTrendAll() {
        return trendAll;
    }

    public void setTrendAll(BigDecimalFilter trendAll) {
        this.trendAll = trendAll;
    }

    public IntegerFilter getVolAll() {
        return volAll;
    }

    public void setVolAll(IntegerFilter volAll) {
        this.volAll = volAll;
    }

    public DoubleFilter getCfp() {
        return cfp;
    }

    public void setCfp(DoubleFilter cfp) {
        this.cfp = cfp;
    }

    public DoubleFilter getIop() {
        return iop;
    }

    public void setIop(DoubleFilter iop) {
        this.iop = iop;
    }

    public DoubleFilter getDcx() {
        return dcx;
    }

    public void setDcx(DoubleFilter dcx) {
        this.dcx = dcx;
    }

    public InstantFilter getAdded() {
        return added;
    }

    public void setAdded(InstantFilter added) {
        this.added = added;
    }

    @Override
    public String toString() {
        return "CsgoItemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (sp != null ? "sp=" + sp + ", " : "") +
                (opm != null ? "opm=" + opm + ", " : "") +
                (vol != null ? "vol=" + vol + ", " : "") +
                (mp7 != null ? "mp7=" + mp7 + ", " : "") +
                (avg7 != null ? "avg7=" + avg7 + ", " : "") +
                (lp7 != null ? "lp7=" + lp7 + ", " : "") +
                (hp7 != null ? "hp7=" + hp7 + ", " : "") +
                (mad7 != null ? "mad7=" + mad7 + ", " : "") +
                (dp7 != null ? "dp7=" + dp7 + ", " : "") +
                (trend7 != null ? "trend7=" + trend7 + ", " : "") +
                (vol7 != null ? "vol7=" + vol7 + ", " : "") +
                (mp30 != null ? "mp30=" + mp30 + ", " : "") +
                (avg30 != null ? "avg30=" + avg30 + ", " : "") +
                (lp30 != null ? "lp30=" + lp30 + ", " : "") +
                (hp30 != null ? "hp30=" + hp30 + ", " : "") +
                (mad30 != null ? "mad30=" + mad30 + ", " : "") +
                (dp30 != null ? "dp30=" + dp30 + ", " : "") +
                (trend30 != null ? "trend30=" + trend30 + ", " : "") +
                (vol30 != null ? "vol30=" + vol30 + ", " : "") +
                (mpAll != null ? "mpAll=" + mpAll + ", " : "") +
                (avgAll != null ? "avgAll=" + avgAll + ", " : "") +
                (lpAll != null ? "lpAll=" + lpAll + ", " : "") +
                (hpAll != null ? "hpAll=" + hpAll + ", " : "") +
                (madAll != null ? "madAll=" + madAll + ", " : "") +
                (dpAll != null ? "dpAll=" + dpAll + ", " : "") +
                (trendAll != null ? "trendAll=" + trendAll + ", " : "") +
                (volAll != null ? "volAll=" + volAll + ", " : "") +
                (cfp != null ? "cfp=" + cfp + ", " : "") +
                (iop != null ? "iop=" + iop + ", " : "") +
                (dcx != null ? "dcx=" + dcx + ", " : "") +
                (added != null ? "added=" + added + ", " : "") +
            "}";
    }

}
