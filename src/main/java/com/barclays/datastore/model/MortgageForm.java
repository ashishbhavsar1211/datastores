package com.barclays.datastore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.Objects;

public class MortgageForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mortgageId;

    private Integer version;

    private String offerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate offerDate;

    private LocalDate CreatedDate;

    private Boolean offerExpired;

    @Override
    public String toString() {
        return "MortgageForm{" +
                "mortgageId='" + mortgageId + '\'' +
                ", version='" + version + '\'' +
                ", offerId='" + offerId + '\'' +
                ", offerDate=" + offerDate +
                ", CreatedDate=" + CreatedDate +
                ", offerExpired=" + offerExpired +
                '}';
    }

    public String getMortgageId() {
        return mortgageId;
    }

    public void setMortgageId(String mortgageId) {
        this.mortgageId = mortgageId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public LocalDate getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public LocalDate getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        CreatedDate = createdDate;
    }

    public Boolean getOfferExpired() {
        return offerExpired;
    }

    public void setOfferExpired(Boolean offerExpired) {
        this.offerExpired = offerExpired;
    }

    public static final Comparator<MortgageForm> offerDateComparator = new Comparator<MortgageForm>() {
        @Override
        public int compare(MortgageForm o1, MortgageForm o2) {
            return o1.offerDate.compareTo(o2.offerDate);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MortgageForm that = (MortgageForm) o;
        return mortgageId.equals(that.mortgageId) &&
                version.equals(that.version) &&
                offerId.equals(that.offerId) &&
                offerDate.equals(that.offerDate) &&
                Objects.equals(CreatedDate, that.CreatedDate) &&
                Objects.equals(offerExpired, that.offerExpired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mortgageId, version, offerId, offerDate, CreatedDate, offerExpired);
    }
}
