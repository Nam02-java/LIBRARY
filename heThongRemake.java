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
    static int giaTien, soLuong, number, tempInt, j, choice;
    static String space = ("\n");
    static Scanner sc = new Scanner(System.in);
    static Scanner sc1 = new Scanner(System.in);
    static Scanner sc2 = new Scanner(System.in);
    static Scanner sc3 = new Scanner(System.in);
    static Scanner sc4 = new Scanner(System.in);
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
        updateListWithDataFile();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getTenSach().equalsIgnoreCase(tenSach)) {
                arrayList.get(i).setSoLuong(arrayList.get(i).getSoLuong() + soLuong);
                writerToFile();
                pickUp();
            }
        }
        arrayList.add(new CauTruc(tenSach, tacGia, giaTien, soLuong));
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
            out.println(cauTruc.getTenSach() + "     " + cauTruc.getTacGia() + "     " + cauTruc.getGiaTien() + "     " + cauTruc.getSoLuong());
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
            bufferedWriter.write(arrayList.get(i).getTenSach() + "     " + arrayList.get(i).getTacGia() + "     " + arrayList.get(i).getGiaTien() + "     " + arrayList.get(i).getSoLuong() + space);
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
            arrayList.add(new CauTruc(s.next(), s.next(), s.nextInt(), s.nextInt()));
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
        arrayList.clear();
        if (file.length() == 0) {
            out.println(space + "File ko có dữ liệu , bạn cần phải tạo dữ liệu cho file trước");
        } else if (file.length() > 0) {
            out.println(space + "Nhập tên tác giả : ");
            tempString = sc1.nextLine();
            updateListWithDataFile();
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).getTacGia().contains(tempString)) {
                    out.println(arrayList.get(i).getTenSach() + "  " + arrayList.get(i).getTacGia() + "  " + arrayList.get(i).getGiaTien() + "  " + arrayList.get(i).getSoLuong() + space);
                } else if (arrayList.get(i).getTacGia() != tempString) {
                    out.println("Không có dữ liệu ");
                }
            }
        }
    }

    void deleteBook() throws IOException {
        if (file.length() == 0) {
            out.println(space + "File ko có dữ liệu , bạn cần phải tạo dữ liệu cho file trước");
        } else if (file.length() > 0) {
            out.println(space + "1.Xóa sách theo tên     2.Xóa tất cả     3.Quay về menu");
            number = sc.nextInt();
            if (number == 1) {
                arrayList.clear();
                updateListWithDataFile();
                out.println(space + "Nhập tên sách mà bạn muốn xóa : ");
                tempString = sc1.nextLine();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getTenSach().contains(tempString)) {
                        out.println(space + "Có sách ! ");
                        out.println(space + "Nhập số lượng sách mà bạn muốn xóa : ");
                        tempInt = sc4.nextInt();
                        if (tempInt > arrayList.get(i).getSoLuong()) {
                            out.println("Vượt quá số lượng cho phép ");
                        } else if (tempInt <= arrayList.get(i).getSoLuong()) {
                            arrayList.get(i).setSoLuong(arrayList.get(i).getSoLuong() - tempInt);
                            writerToFile();
                        } else if (arrayList.get(i).getTenSach() != tempString) {

                        }
                    }
                }
            }
            if (number == 2) {
                out.println("Bạn chắc chắn muốn xóa hết chứ ? ");
                out.println("1.Xóa tất cả    2.Không");
                int choice = sc4.nextInt();
                if (choice == 1) {
                    out.println("Xóa thành công ! ");
                    arrayList.clear();
                    updateListWithDataFile();
                    arrayList.clear();
                    writerToFile();
                } else if (choice == 2) {
                    deleteBook();
                }
            }
            if (number == 3) {
            }
        }
    }

    void demo() throws IOException {
        file.createNewFile();
    }

}

