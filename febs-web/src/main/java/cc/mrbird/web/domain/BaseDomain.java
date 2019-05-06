package cc.mrbird.web.domain;

import javax.persistence.Transient;

public class BaseDomain {
    @Transient
   protected String oderCloumn;
    @Transient
   protected Boolean asc;

    public String getOderCloumn() {
        return oderCloumn;
    }

    public void setOderCloumn(String oderCloumn) {
        this.oderCloumn = oderCloumn;
    }

    public Boolean isAsc() {
        return asc;
    }

    public void setAsc(Boolean asc) {
        this.asc = asc;
    }
}
