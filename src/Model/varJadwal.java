//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package Model;

public class varJadwal {
    private int idJadwal;
    private String ta;
    private String semester;
    private String nip;
    private String kodeRuang;
    private String hari;
    private int kodeSesi;
    private int kodeSesiSelesai;
    private String kodeMtk;
    private String kelompok;
    
    // For display only
    private String namaDosen;
    private String namaMtk;
    private String waktu;
    private String waktuSelesai;
    private int sks;

    public int getIdJadwal() { return idJadwal; }
    public void setIdJadwal(int idJadwal) { this.idJadwal = idJadwal; }

    public String getTa() { return ta; }
    public void setTa(String ta) { this.ta = ta; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getNip() { return nip; }
    public void setNip(String nip) { this.nip = nip; }

    public String getKodeRuang() { return kodeRuang; }
    public void setKodeRuang(String kodeRuang) { this.kodeRuang = kodeRuang; }

    public String getHari() { return hari; }
    public void setHari(String hari) { this.hari = hari; }

    public int getKodeSesi() { return kodeSesi; }
    public void setKodeSesi(int kodeSesi) { this.kodeSesi = kodeSesi; }
    
    public int getKodeSesiSelesai() { return kodeSesiSelesai; }
    public void setKodeSesiSelesai(int kodeSesiSelesai) { this.kodeSesiSelesai = kodeSesiSelesai; }

    public String getKodeMtk() { return kodeMtk; }
    public void setKodeMtk(String kodeMtk) { this.kodeMtk = kodeMtk; }

    public String getKelompok() { return kelompok; }
    public void setKelompok(String kelompok) { this.kelompok = kelompok; }

    public String getNamaDosen() { return namaDosen; }
    public void setNamaDosen(String namaDosen) { this.namaDosen = namaDosen; }

    public String getNamaMtk() { return namaMtk; }
    public void setNamaMtk(String namaMtk) { this.namaMtk = namaMtk; }

    public String getWaktu() { return waktu; }
    public void setWaktu(String waktu) { this.waktu = waktu; }
    
    public String getWaktuSelesai() { return waktuSelesai; }
    public void setWaktuSelesai(String waktuSelesai) { this.waktuSelesai = waktuSelesai; }

    public int getSks() { return sks; }
    public void setSks(int sks) { this.sks = sks; }
}
