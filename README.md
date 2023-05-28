# ArraySortingSoftware
Phần mềm tạo mảng chữ cái ngẫu nhiên sau đó bạn có thể sắp xếp chúng theo thứ tự từ điển bằng các thuật toán có sẵn
## 1. Giới thiệu ứng dụng
### 1.1. Tính năng
- Tạo mảng chứa các phần tử là chuỗi ký tự ngẫu nhiên trong khoảng a→z và A→Z. Có thể điều chỉnh số phần tử của mảng từ 1 đến 100000, mặc định là 1000 phần tử.
- Hiển thị các phần tử của mảng, xoá các phần tử khỏi vùng hiển thị.
- Sắp xếp mảng theo thứ tự từ điển và hiển thị lại.
- Có thể lựa chọn thuật toán dùng để sắp xếp, mặc định là Quick Sort.
- Hiển thị thời gian thực hiện thuật toán.
- Khi người dùng nhấn sắp xếp trong khi mảng chưa được tạo hoặc nhấn xoá mảng trong khi vùng hiển thị mảng trống thì thông báo đến người dùng.
- Có thể xem thông tin của ứng dụng.
### 1.2. Công nghệ sử dụng
- Viết bằng ngôn ngữ Java
- Sử dụng JavaFX để tạo GUI
- Sử dụng IDE IntelliJ IDEA
- Dùng thư viện org.apache.commons.lang3 để tạo chuỗi ngẫu nhiên, so sánh chuỗi theo thứ tự từ điển (A→Z→a→z) và tính toán thời gian thực hiện thuật toán.
- Sử dụng Launch4j để tạo file .exe từ file .jar
### 1.4. Cài đặt và sử dụng
- Đảm bảo máy tính đã cài đặt JDK (Java Development Kit)
- Nếu chưa cài JDK, download tại đây.
- Chạy file "Ma Dinh Chien - Intern Test.exe" hoặc "Ma Dinh Chien - Intern Test.jar" trong thư mục "App".
## 2. Các thuật toán sắp xếp được sử dụng
### 2.1. Selection Sort (Sắp xếp chọn)
- Thuật toán Selection Sort là một trong những thuật toán sắp xếp đơn giản nhất. Trong trường hợp này ta sắp xếp mảng các chuỗi bằng cách lặp lại việc tìm kiếm phần tử có thứ tự từ điển nhỏ nhất từ phần chưa được sắp xếp trong mảng và đặt nó lên vị trí đầu tiên.
- Thuật toán được viết bằng ngôn ngữ Java trong ứng dụng như sau (với Mang[] được định nghĩa trước trong hàm taoMang(), size là kích thước của Mang[], swap() là hàm hoán đổi giá trị được viết trước đó, compareTo() là phương thức so sánh hai chuỗi theo thứ tự từ điển):

void SelectionSort() {
    for(int i = 0; i < size-1; i++) {
        int min_indx = i;
        for(int j = i+1; j < size; j++) {
            if(Mang[j].compareTo(Mang[min_indx]) < 0) {
                min_indx = j;
            }
        }
        swap(Mang, i, min_indx);
    }
}
- Độ phức tạp thời gian của thuật toán là O(N^2) với N là số phần tử của mảng.
### 2.2. Bubble Sort (Sắp xếp nổi bọt)
- Thuật toán Bubble Sort được thực hiện đơn giản bằng cách tráo đổi giá trị hai phần 
từ liền kề nhau nếu chúng chưa được sắp xếp, theo đó đầu tiên ta so sánh hai phần tử đầu, nếu phần tử đứng trước lớn hơn phần tử đứng sau theo thứ tự từ điển thì đổi chỗ chúng cho nhau, cứ thế phần tử thứ 2 và thứ 3… sau một lượt như vậy phần tử lớn nhất theo thứ tự từ điển đã được chuyển đến vị trí cuối cùng.
- Thực hiện thuật toán trong ứng dụng:

void BubbleSort() {
    for(int i = 0; i < size-1; i++) {
        for(int j = 0; j < size-i-1; j++) {
            if(Mang[j].compareTo(Mang[j+1]) > 0) {
                swap(Mang, j, j+1);
            }
        }
    }
}
- Độ phức tạp thời gian của thuật toán là O(N^2) với N là số phần tử của mảng.
### 2.3. Insertion Sort (Sắp xếp chèn)
- Inserttion Sort là thuật toán sắp xếp giống cách sắp xếp quân bài của những người chơi bài. Để thực hiện thuật toán này ta thực hiện các việc sau từ phần tử thứ nhất đến phần tử cuối: So sánh phần tử hiện tại (key) với các phần tử đứng trước nó, nếu nhỏ hơn di chuyển phần tử được so sánh ra sau và tiếp tục so sánh cho đến khi gặp phần tử lớn hơn hoặc bằng key: di chuyển phần từ này ra sau và chèn key vào vị trí đó.
- Thực hiện thuật toán trong ứng dụng: 
void InsertionSort() {
    for(int i = 1; i < size; i++) {
        String key = Mang[i];
        int j = i-1;
        while(j >= 0 && key.compareTo(Mang[j]) < 0) {
            Mang[j+1] = Mang[j];
            j--;
        }
        Mang[j+1] = key;
    }
}
- Độ phức tạp thời gian của thuật toán là O(N^2) với N là số phần tử của mảng.
### 2.3. Merge Sort (Sắp xếp trộn)
- Merge Sort được xây dựng theo mô hình chia để trị. Thuật toán chia dãy cần sắp xếp thành hai nửa. Sau đó gọi đệ qui cho mỗi nửa và hợp nhất lại các đoạn đã được sắp xếp. Thuật toán được tiến hành theo 4 bước dưới đây: 
• Tìm điểm giữa của dãy và chia dãy thành hai nửa.
• Thực hiện Merge Sort cho nửa thứ nhất.
• Thực hiện Merge Sort cho nửa thứ hai.
• Hợp nhất hai đoạn đã được sắp xếp thành một đoạn cũng được sắp xếp theo thứ tự từ điển.
- Hàm hợp nhất hai đoạn trong ứng dụng này:
void Merge(String[] Mang, int l, int m, int r) {
    int n1 = m-l+1;
    int n2 = r-m;
    String[] Mang1 = new String[n1], Mang2 = new String[n2];
    for(int i = 0; i < n1; i++) {
        Mang1[i] = Mang[l+i];
    }
    for(int i = 0; i < n2; i++) {
        Mang2[i] = Mang[m+i+1];
    }

    int i = 0, j = 0, k = l;
    while(i < n1 && j < n2) {
        if(Mang1[i].compareTo(Mang2[j]) <= 0) {
            Mang[k] = Mang1[i];
            i++;
        } else {
            Mang[k] = Mang2[j];
            j++;
        }
        k++;
    }

    while(i < n1) {
        Mang[k] = Mang1[i];
        k++; i++;
    }
    while(j < n2) {
        Mang[k] = Mang2[j];
        k++; j++;
    }
}
- Hàm MergeSort() trong ứng dụng này: 
void MergeSort(String[] Mang, int l, int r) {
    if(l < r) {
        int m = l + (r-l)/2;
        MergeSort(Mang, l, m);
        MergeSort(Mang, m+1, r);
        Merge(Mang, l, m, r);
    }
}
- Độ phức tạp thời gian của thuật toán là O(N.log⁡N) với N là số phần tử của mảng.
### 2.5. Quick Sort (Sắp Xếp Nhanh)
- Giống như Merge Sort, Quick Sort được thực hiện theo mô hình chia để trị. Thuật toán được thực hiện xung quanh một phần tử gọi là chốt (pivot). Mỗi cách lựa chọn vị trí phần tử chốt trong dãy sẽ cho ta một phiên bản khác nhau của thuật toán, ở đây ta chọn pivot là phần tử cuối trong dãy.
- Phần quan trọng của thuật toán này là thủ tục Partition (phân đoạn), thủ tục này thực hiện hai nhiệm vụ sau: 
• Định vị chính xác vị trí của pivot trong dãy nếu dãy được sắp xếp;
• Chuyển các phần tử nhỏ hơn pivot lên trước nó, các phần tử lớn hơn hoặc bằng pivot ra sau nó. Từ đó pivot chia dãy thành 2 dãy con.

- Thực hiện thuật toán trong ứng dụng như sau: 
• Hàm Partition():
int Partition(String[] Mang, int l, int h) {
    String pivot = Mang[h];
    int i = l-1;
    for(int j = l; j <= h-1; j++) {
        if(Mang[j].compareTo(pivot) < 0) {
            i++;
            swap(Mang, i, j);
        }
    }
    swap(Mang, i+1, h);
    return i+1;
}
• Hàm QuickSort() đầu tiên tìm pivot của mảng, sau đó gọi đệ quy mảng con trước pivot và sau pivot: 
void QuickSort(String[] Mang, int l, int h) {
    if(l < h) {
        int pivot = Partition(Mang, l, h);
        QuickSort(Mang, l, pivot-1);
        QuickSort(Mang, pivot+1, h);
    }
}
- Độ phức tạp thời gian của thuật toán là O(N.log⁡N) với N là số phần tử của mảng.
## 3. Đánh giá thời gian chạy của các thuật toán
- Như đã đề cập ở mục 2, độ phức tạp thời gian của 3 thuật toán Selection Sort, Bubble Sort và Insertion Sort là bằng nhau và bằng O(N^2), chậm hơn nhiều so với hai thuật toán Merge Sort và Quick Sort có độ phức tạp thời gian là O(N.log⁡N), đặc biệt là khi phải thực hiện trên mảng có số lượng phần tử (N) lớn (log⁡N≪N).
- Ta có thể đánh giá thời gian chạy của các thuật toán với dữ liệu lớn trên thực tế bằng cách sắp xếp các mảng ngẫu nhiên 100000 phần tử trong ứng dụng:
  
Thời gian chạy của thuật toán Bubble Sort			Thời gian chạy của thuật toán Merge Sort
-  Có thể thấy rằng thời gian chạy của thuật toán Merge Sort nhỏ hơn rất nhiều so với Bubble Sort (28ms so với 55s).
