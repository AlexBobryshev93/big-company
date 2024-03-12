package org.swiss.re.config;

public class AppConfig {

    private Integer maxIntermediaries = 4;
    private Integer salarySurplusLowerBondPercent = 20;
    private Integer salarySurplusUpperBondPercent = 50;

    public Integer getMaxIntermediaries() {
        return maxIntermediaries;
    }

    public void setMaxIntermediaries(Integer maxIntermediaries) {
        this.maxIntermediaries = maxIntermediaries;
    }

    public Integer getSalarySurplusLowerBondPercent() {
        return salarySurplusLowerBondPercent;
    }

    public void setSalarySurplusLowerBondPercent(Integer salarySurplusLowerBondPercent) {
        this.salarySurplusLowerBondPercent = salarySurplusLowerBondPercent;
    }

    public Integer getSalarySurplusUpperBondPercent() {
        return salarySurplusUpperBondPercent;
    }

    public void setSalarySurplusUpperBondPercent(Integer salarySurplusUpperBondPercent) {
        this.salarySurplusUpperBondPercent = salarySurplusUpperBondPercent;
    }
}
