package BOOKDATA;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;

public class Main {
    static Main m = new Main();
    static HeThong heThong = new HeThong();
    static Scanner sc = new Scanner(System.in);
    static String space = ("\n");
    static int i, number, choice;
    static boolean flag = true;

    public void work() throws IOException {
        do {
            out.println(space + "Hệ thống quản lý sách : ");
            out.println("1.Nhập sách vào file");
            out.println("2.In ra thông tin sách theo tên tác giả");
            out.println("3.Xóa sách");
            out.println("4.Sắp xếp dữ liệu theo số lượng ");
            out.println("5.Sắp xếp dữ liệu theo ngày ");
            out.println("6.Thoát chương trình ");
            out.println("_____________________________________________");
            out.print("Nhập : ");
            i = sc.nextInt();
            if (i != 0) {
                switch (i) {
                    case 1:
                        out.println(space + "Bạn đã chọn lựa chọn số 1 ");
                        heThong.input();
                        heThong.sortByWriter();
                        break;
                    case 2:
                        out.println(space + "Bạn đã chọn lựa chọn số 2 ");
                        heThong.outputWriter();
                        break;
                    case 3:
                        out.println(space + "Bạn đã chọn lựa chọn số 3 ");
                        heThong.deleteBook();
                        break;
                    case 4:
                        out.println(space + "Bạn đã chọn lựa chọn số 4 ");
                        heThong.sortWithLength();
                        break;
                    case 5:
                        out.println(space + "Bạn đã chọn lựa chọn số 5 ");
                        heThong.sortWithDay();
                        break;
                    case 6:
                        out.println(space + "Bạn chắc chắn muốn thoát chứ ? ");
                        out.println(space + "1.Thoát và lưu      2.Tiếp tục chương trình");
                        choice = sc.nextInt();
                        if (choice == 1) {
                            out.println(space + "Chương trình kết thúc");
                            System.exit(1);
                        } else if (choice == 2) {
                            continue;
                        }
                    default:
                        out.println(space + "Không có lựa chọn số " + i + " , yêu cầu nhập lại ");
                        continue;
                }
            }
        } while (true);
    }

    public static void main(String[] args) throws IOException {
        m.work();
    }
}

