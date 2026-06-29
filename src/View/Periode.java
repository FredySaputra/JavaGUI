//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package View;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Freds
 */
@Entity
@Table(name = "periode", catalog = "db_akademik_2311500140", schema = "")
@NamedQueries({
    @NamedQuery(name = "Periode.findAll", query = "SELECT p FROM Periode p")
    , @NamedQuery(name = "Periode.findByTa", query = "SELECT p FROM Periode p WHERE p.periodePK.ta = :ta")
    , @NamedQuery(name = "Periode.findBySemester", query = "SELECT p FROM Periode p WHERE p.periodePK.semester = :semester")})
public class Periode implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeriodePK periodePK;

    public Periode() {
    }

    public Periode(PeriodePK periodePK) {
        this.periodePK = periodePK;
    }

    public Periode(String ta, String semester) {
        this.periodePK = new PeriodePK(ta, semester);
    }

    public PeriodePK getPeriodePK() {
        return periodePK;
    }

    public void setPeriodePK(PeriodePK periodePK) {
        this.periodePK = periodePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodePK != null ? periodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periode)) {
            return false;
        }
        Periode other = (Periode) object;
        if ((this.periodePK == null && other.periodePK != null) || (this.periodePK != null && !this.periodePK.equals(other.periodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "View.Periode[ periodePK=" + periodePK + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
