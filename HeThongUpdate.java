package BOOKDATA;

import com.sun.tools.javac.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class HeThong extends BOOKDATA.Main {
    static Main m = new Main();
    static HeThong heThong = new HeThong();
    static String tenSach, tacGia, tempString, demo;
    static int giaTien, soLuong, ngayNhap, number, tempInt, j, choice, maSach;
    static String space = ("\n");
    static Scanner sc = new Scanner(System.in);
    static Scanner sc1 = new Scanner(System.in);
    static Scanner sc2 = new Scanner(System.in);
    static Scanner sc3 = new Scanner(System.in);
    static Scanner sc4 = new Scanner(System.in);
    static Scanner sc5 = new Scanner(System.in);
    static ArrayList<CauTruc> arrayList = new ArrayList<>();
    static File file = new File("BookData.txt");
    static BufferedWriter bufferedWriter;
    static BufferedReader bufferedReader;

    void input() throws IOException {
        arrayList.clear();
        out.println(space + "Nhập tên sách : ");
        tenSach = sc1.nextLine();
        out.println(space + "Nhập tên tác giả : ");
        tacGia = sc2.nextLine();
        out.println(space + "Nhập giá tiền : ");
        giaTien = sc3.nextInt();
        out.println(space + "Nhập số lượng : ");
        soLuong = sc4.nextInt();
        out.println(space + "Nhập ngày nhập : ");
        ngayNhap = sc5.nextInt();
        updateListWithDataFile();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getTenSach().equalsIgnoreCase(tenSach)) {
                arrayList.get(i).setSoLuong(arrayList.get(i).getSoLuong() + soLuong);
                writerToFile();
                pickUp();
            }
        }
        arrayList.add(new CauTruc(tenSach, tacGia, giaTien, soLuong, ngayNhap));
        writerToFile();
        pickUp();
    }

    void pickUp() throws IOException {
        out.println(space + "1.Tiếp tục     2.Quay về menu");
        choice = sc.nextInt();
        if (choice == 1) {
            heThong.input();
        } else if (choice == 2) {
            work();
        } else if (choice != 1 && choice != 2) {
            out.println("Nhập sai lựa chọn , yêu cầu nhập lại ");
            pickUp();
        }
    }

    void output() {
        for (CauTruc cauTruc : arrayList) {
            out.println(cauTruc.getTenSach() + "     " + cauTruc.getTacGia() + "     " + cauTruc.getGiaTien() + "     " + cauTruc.getSoLuong() + "     " + cauTruc.getNgayNhap());
        }
    }

    void outputCount() {
        for (int i = 0; i < arrayList.size(); i++) {
            out.println(space + i + ".   " + arrayList.get(i).getTenSach() + "     " + arrayList.get(i).getTacGia() + "     " + arrayList.get(i).getGiaTien() + "     " + arrayList.get(i).getSoLuong() + "     " + arrayList.get(i).getNgayNhap());
        }
    }

    void secert() {
        arrayList.clear();
    }

    void creatFile() throws IOException {
        file.createNewFile();
    }

    void writerToFile() throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < arrayList.size(); i++) {
            bufferedWriter.write(arrayList.get(i).getTenSach() + "     " + arrayList.get(i).getTacGia() + "     " + arrayList.get(i).getGiaTien() + "     " + arrayList.get(i).getSoLuong() + "     " + arrayList.get(i).getNgayNhap() + space);
        }
        bufferedWriter.close();
    }

    void readFile() throws IOException {
        bufferedReader = new BufferedReader(new FileReader(file));
        for (int i = 0; i <= arrayList.size(); i++) {
            out.println(bufferedReader.readLine());
        }
        bufferedReader.close();
    }

    void updateListWithDataFile() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            arrayList.add(new CauTruc(s.next(), s.next(), s.nextInt(), s.nextInt(), s.nextInt()));
        }
        s.close();
    }

    void sortByWriter() throws IOException {
        updateListWithDataFile();
        Collections.sort(arrayList, new Comparator<CauTruc>() {
            @Override
            public int compare(CauTruc o1, CauTruc o2) {
                return o1.getTacGia().compareToIgnoreCase(o2.getTacGia());
            }
        });
        writerToFile();
        secert();
    }

    void outputWriter() throws IOException {
        Scanner scanner = new Scanner(System.in);
        arrayList.clear();
        if (file.length() == 0) {
            out.println(space + "File ko có dữ liệu , bạn cần phải tạo dữ liệu cho file trước");
        } else if (file.length() > 0) {
            out.println(space + "1.Xuất theo tên     2.Xuất tất cả     3.Quay về menu");
            choice = sc.nextInt();
            if (choice == 1) {
                arrayList.clear();
                updateListWithDataFile();
                out.println(space + "Nhập tên tác giả : ");
                tempString = scanner.nextLine();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getTacGia().equalsIgnoreCase(tempString)) {
                        out.println(arrayList.get(i).getTenSach() + "  " + arrayList.get(i).getTacGia() + "  " + arrayList.get(i).getGiaTien() + "  " + arrayList.get(i).getSoLuong() + space);
                    } else if (arrayList.get(i).getTacGia() != tempString) {
                    }
                }
            } else if (choice == 2) {
                arrayList.clear();
                updateListWithDataFile();
                outputCount();
            } else if (choice == 3) {
                arrayList.clear();
                work();
            }
        }
    }

    void deleteBook() throws IOException {
        if (file.length() == 0) {
            out.println(space + "File ko có dữ liệu , bạn cần phải tạo dữ liệu cho file trước");
        } else if (file.length() > 0) {
            arrayList.clear();
            out.println(space + "Toàn bộ dữ liệu sách trong file : ");
            updateListWithDataFile();
            outputCount();
            arrayList.clear();
            out.println(space + "1.Xóa sách theo tên     2.Xóa tất cả     3.Quay về menu");
            number = sc.nextInt();
            if (number == 1) {
                arrayList.clear();
                updateListWithDataFile();
                out.println(space + "Nhập mã sách mà bạn muốn xóa : ");
                maSach = sc1.nextInt();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getTenSach().equalsIgnoreCase(arrayList.get(maSach).getTenSach())) {
                        out.println(space + "Có sách ! ");
                        out.println(space + "Nhập số lượng sách mà bạn muốn xóa : ");
                        tempInt = sc4.nextInt();
                        if (tempInt > arrayList.get(maSach).getSoLuong()) {
                            out.println(space + "Vượt quá số lượng cho phép ");
                        } else if (tempInt <= arrayList.get(maSach).getSoLuong()) {
                            arrayList.get(maSach).setSoLuong(arrayList.get(maSach).getSoLuong() - tempInt);
                            writerToFile();
                            out.println(space + "Xóa số lượng thành công ! ");
                            if (arrayList.get(maSach).getSoLuong() <= 0) {
                                arrayList.remove(arrayList.get(maSach));
                                writerToFile();
                            }
                            deleteBook();
                        } else if (arrayList.get(maSach).getSoLuong() != tempInt) {
                            out.println(space + "Không có dữ liệu ! ");
                            deleteBook();
                        }
                    }
                }
            } else if (number == 2) {
                out.println(space + "Bạn chắc chắn muốn xóa hết chứ ? ");
                out.println(space + "1.Xóa tất cả    2.Không");
                int choice = sc4.nextInt();
                if (choice == 1) {
                    out.println(space + "Xóa thành công ! ");
                    arrayList.clear();
                    updateListWithDataFile();
                    arrayList.clear();
                    writerToFile();
                } else if (choice == 2) {
                    deleteBook();
                }
            } else if (number == 3) {
                arrayList.clear();
                work();
            }
        }
    }

    void demo() throws IOException {
        file.createNewFile();
    }

    void sortWithLength() throws FileNotFoundException {
        if (file.length() == 0) {
            out.println(space + "File ko có dữ liệu , bạn cần phải tạo dữ liệu cho file trước");
        } else if (file.length() > 0) {
            arrayList.clear();
            updateListWithDataFile();
            Collections.sort(arrayList, (a, b) -> (int) (a.getSoLuong() - b.getSoLuong()));
            out.println(space + "Sau khi sắp xếp theo số lượng : ");
            output();
        }
    }

    void sortWithDay() throws FileNotFoundException {
        if (file.length() == 0) {
            out.println(space + "File ko có dữ liệu , bạn cần phải tạo dữ liệu cho file trước");
        } else if (file.length() > 0) {
            arrayList.clear();
            updateListWithDataFile();
            Collections.sort(arrayList, (a, b) -> (int) (a.getNgayNhap() - b.getNgayNhap()));
            out.println(space + "Sau khi sắp xếp theo ngày : ");
            output();
        }
    }
}


