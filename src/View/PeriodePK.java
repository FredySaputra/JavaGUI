//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package View;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Freds
 */
@Embeddable
public class PeriodePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TA")
    private String ta;
    @Basic(optional = false)
    @Column(name = "Semester")
    private String semester;

    public PeriodePK() {
    }

    public PeriodePK(String ta, String semester) {
        this.ta = ta;
        this.semester = semester;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ta != null ? ta.hashCode() : 0);
        hash += (semester != null ? semester.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodePK)) {
            return false;
        }
        PeriodePK other = (PeriodePK) object;
        if ((this.ta == null && other.ta != null) || (this.ta != null && !this.ta.equals(other.ta))) {
            return false;
        }
        if ((this.semester == null && other.semester != null) || (this.semester != null && !this.semester.equals(other.semester))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "View.PeriodePK[ ta=" + ta + ", semester=" + semester + " ]";
    }
    
}
