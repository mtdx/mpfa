package com.mma.mma.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the Pubgitem entity. This class is used in PubgitemResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /pubgitems?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PubgitemCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private BooleanFilter uns;

    private StringFilter unsr;

    private IntegerFilter rank;

    private DoubleFilter sp;

    private DoubleFilter meanp;

    private DoubleFilter maxp;

    private DoubleFilter avgp;

    private DoubleFilter minp;

    private DoubleFilter lp;

    private IntegerFilter savgd;

    private IntegerFilter s24h;

    private IntegerFilter s7d;

    private IntegerFilter s30d;

    private IntegerFilter s90d;

    private StringFilter nid;

    private InstantFilter uat;

    public PubgitemCriteria() {
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

    public BooleanFilter getUns() {
        return uns;
    }

    public void setUns(BooleanFilter uns) {
        this.uns = uns;
    }

    public StringFilter getUnsr() {
        return unsr;
    }

    public void setUnsr(StringFilter unsr) {
        this.unsr = unsr;
    }

    public IntegerFilter getRank() {
        return rank;
    }

    public void setRank(IntegerFilter rank) {
        this.rank = rank;
    }

    public DoubleFilter getSp() {
        return sp;
    }

    public void setSp(DoubleFilter sp) {
        this.sp = sp;
    }

    public DoubleFilter getMeanp() {
        return meanp;
    }

    public void setMeanp(DoubleFilter meanp) {
        this.meanp = meanp;
    }

    public DoubleFilter getMaxp() {
        return maxp;
    }

    public void setMaxp(DoubleFilter maxp) {
        this.maxp = maxp;
    }

    public DoubleFilter getAvgp() {
        return avgp;
    }

    public void setAvgp(DoubleFilter avgp) {
        this.avgp = avgp;
    }

    public DoubleFilter getMinp() {
        return minp;
    }

    public void setMinp(DoubleFilter minp) {
        this.minp = minp;
    }

    public DoubleFilter getLp() {
        return lp;
    }

    public void setLp(DoubleFilter lp) {
        this.lp = lp;
    }

    public IntegerFilter getSavgd() {
        return savgd;
    }

    public void setSavgd(IntegerFilter savgd) {
        this.savgd = savgd;
    }

    public IntegerFilter gets24h() {
        return s24h;
    }

    public void sets24h(IntegerFilter s24h) {
        this.s24h = s24h;
    }

    public IntegerFilter gets7d() {
        return s7d;
    }

    public void sets7d(IntegerFilter s7d) {
        this.s7d = s7d;
    }

    public IntegerFilter gets30d() {
        return s30d;
    }

    public void sets30d(IntegerFilter s30d) {
        this.s30d = s30d;
    }

    public IntegerFilter gets90d() {
        return s90d;
    }

    public void sets90d(IntegerFilter s90d) {
        this.s90d = s90d;
    }

    public StringFilter getNid() {
        return nid;
    }

    public void setNid(StringFilter nid) {
        this.nid = nid;
    }

    public InstantFilter getUat() {
        return uat;
    }

    public void setUat(InstantFilter uat) {
        this.uat = uat;
    }

    @Override
    public String toString() {
        return "PubgitemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (uns != null ? "uns=" + uns + ", " : "") +
                (unsr != null ? "unsr=" + unsr + ", " : "") +
                (rank != null ? "rank=" + rank + ", " : "") +
                (sp != null ? "sp=" + sp + ", " : "") +
                (meanp != null ? "meanp=" + meanp + ", " : "") +
                (maxp != null ? "maxp=" + maxp + ", " : "") +
                (avgp != null ? "avgp=" + avgp + ", " : "") +
                (minp != null ? "minp=" + minp + ", " : "") +
                (lp != null ? "lp=" + lp + ", " : "") +
                (savgd != null ? "savgd=" + savgd + ", " : "") +
                (s24h != null ? "s24h=" + s24h + ", " : "") +
                (s7d != null ? "s7d=" + s7d + ", " : "") +
                (s30d != null ? "s30d=" + s30d + ", " : "") +
                (s90d != null ? "s90d=" + s90d + ", " : "") +
                (nid != null ? "nid=" + nid + ", " : "") +
                (uat != null ? "uat=" + uat + ", " : "") +
            "}";
    }

}
