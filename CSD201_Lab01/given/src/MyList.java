
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;
    int size;

    MyList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            f.writeBytes(p.getInfo() + " "); // write data in the node p to the file f
            p = p.next;
        }

        f.writeBytes("\r\n");
    }

    void loadData(int k) {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;

        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);// insert the new Node(a[i], b[i], c[i]) into the list
        }
    }

    void addLast(String xDistrict, int xPrice, int xArea) {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        Apartment apartment = new Apartment(xDistrict, xPrice, xArea);

        if (isEmpty()) {
            head = tail = new Node(apartment, null);
            size++;
        } else {
            Node q = new Node(apartment, null);
            tail.next = q;
            tail = q;
            size++;
        }

        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }

    // f1: ham nay se goi ham addLast nhieu lan
    void f1() throws Exception {
        clear();
        loadData(0);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

    // f2: ham addFirst ==> du lieu nhap tu ban phim
    void f2(String xDistrict, int xPrice, int xArea) throws Exception {
        clear();
        loadData(0);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        Apartment apartment = new Apartment(xDistrict, xPrice, xArea);
        Node newNode = new Node(apartment, head);
        head = newNode;

        // Nếu danh sách trống, cập nhật tail
        if (tail == null) {
            tail = newNode;
        }

        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    // f3: ham addPos ==> them node vao vi tri thu k, trong do node moi va chi so k duoc nhap tu ban phim
    void f3(int position) throws Exception {
        clear();
        loadData(0);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        Apartment obj = new Apartment("Kha Van Can", 1000, 50);
        if (position <= 0) {
            throw new ArithmeticException("Position is not valid!");
        } else if (position > size) {
            addLast("Kha VAn Can", 1540, 50);
            size++;
        } else if (position == 1) {
            f2("Kha VAn Can", 1540, 50);
            size++;
        } else {
            int index = 1;
            Node previous = null;
            Node current = head;
            while (current != null) {
                if (index == position) {
                    Node p = new Node(obj, current);
                    previous.next = p;
                    size++;
                    break;
                }
                previous = current;
                current = current.next;
                index++;
            }
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    // f4: removeFirst
    void f4() throws Exception {
        clear();
        loadData(0);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (head == tail) {
            head = tail = null;
        } else if (head != null) {
            head = head.next;
        }

        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    // f5: removeLast
    void f5() throws Exception {
        clear();
        loadData(0);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------

        // Kiểm tra xem danh sách rỗng
        if (isEmpty()) {
            System.out.println("Danh sách rỗng, không thể xóa.");
            return;
        }

        // Trường hợp đặc biệt: Nếu danh sách chỉ có một phần tử, xóa nút và cập nhật head và tail
        if (head == tail) {
            head = tail = null;
            return;
        }

        // Tìm nút trước nút cuối
        Node prev = head;
        while (prev.next != tail) {
            prev = prev.next;
        }

        // Xóa nút cuối và cập nhật tail
        prev.next = null;
        tail = prev;

        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    // f6: filter ==> chi giu lai cac node theo mot yeu cau cho truoc (yeu cau tu chon)
    void f6() throws Exception {
        clear();
        loadData(0);
        String fname = "f6.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        Node q = head, pre = q;
        while (q != null) {
            if (q.getInfo().price < 5) {
                pre.next = q.next;
            }
            pre = q;
            q = q.next;
        }
        q = head;
        pre = q;
        while (q != null) {
            if (q.getInfo().price < 5 && q.next == null) {
                pre.next = null;
                tail = pre;
            }
            pre = q;
            q = q.next;
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    // f7: dem so node theo dieu kien (tu thiet ke dieu kien)
    void f7() throws Exception {
        clear();
        loadData(0);
        String fname = "f7.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (isEmpty()) {
            System.out.println("List is empty!");
        } else {
            int count = 0;
            Node p = head;
            while (p != null) {
                if (p.getInfo().district.equals("Q1")) {
                    System.out.println(p.getInfo());
                    count++;
                }
                p = p.next;
            }
            System.out.println("Quantity: " + count);
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    // f8: dao nguoc list
    void f8() throws Exception {
        clear();
        loadData(0);
        String fname = "f8.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        Node current = head;
        Node next = null;
        Node prev = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        tail = head;
        head = prev;

        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }


    // f9: update thong tin theo yeu cau (tu thiet ke yeu cau)
    void f9() throws Exception {
        clear();
        loadData(0);
        String fname = "f9.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        String district;
        int price, area, position, count = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("List before Update:");
        do {
            System.out.println("Input the position in the apartment list to update: ");

            position = Integer.parseInt(sc.nextLine().trim());
            if (position >= size) {
                System.out.println("Out of range of the list!!!");

            }
        } while (position >= size);

        Node q = head;
        if (isEmpty()) {
            System.out.println("Empty List! Cannot update the apartment!!!");
        } else {
            while (q != null) {
                if (count == position) {
                    System.out.println("Input new district:");
                    district = sc.nextLine().trim();
                    System.out.println("Input new price:");
                    price = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Input new area:");
                    area = Integer.parseInt(sc.nextLine().trim());
                    q.getInfo().setDistrict(district);
                    q.getInfo().setPrice(price);
                    q.getInfo().setArea(area);

                }
                count++;
                q = q.next;
            }
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    // f10: search ==> tu thiet ke dieu kien search
    void f10(String x) throws Exception {
        clear();
        loadData(0);
        String fname = "f10.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (isEmpty()) {
            System.out.println("List is empty!");
        } else {
            Node p = head;
            while (p != null) {
                if (p.getInfo().district.equals("TD")) {
                    System.out.println(p.getInfo());
                }
                p = p.next;
            }
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

}
