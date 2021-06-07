package BOOKDATA;

public class CauTruc {
    String tenSach, tacGia;
    int giaTien, soLuong, ngayNhap;

    public CauTruc(String tenSach, String tacGia, int giaTien, int soLuong, int ngayNhap) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(int ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}

