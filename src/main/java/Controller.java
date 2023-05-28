import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.stage.Stage;
import org.apache.commons.lang3.RandomStringUtils; // Ho tro tao chuoi ngau nhien tu thu vien lang3
import org.apache.commons.lang3.time.StopWatch;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Spinner<Integer> spnSoPhanTu;

    int size = 1000;

    //Tao ChoiceBox de lua chon 1 trong 5 giai thuat:
    @FXML
    private ChoiceBox<String> cbxGiaiThuat;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbxGiaiThuat.getItems().addAll(SS, BS, IS, MS, QS); // Them 5 giai thuat vao ChoiceBox.
        cbxGiaiThuat.setValue(QS);  // Quick Sort la giai thuat mac dinh.
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100000);
        valueFactory.setValue(1000);
        spnSoPhanTu.setValueFactory(valueFactory);
        spnSoPhanTu.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                size = spnSoPhanTu.getValue();
            }
        });
    }

    String[] Mang;   // Tao mang gom 1000 phan tu la cac String.
    String chuoiChuaMang;  // Chuoi nay se chua cac phan tu cua mang, phuc vu viec hien thi.

    // Ham ben duoi dung de tao ra cac chuoi ngau nhien cho cac phan tu cua mang:
    public void taoMang() {
        Mang = new String[size];
        for(int i = 0; i < size; i++) {
            double randomDouble = Math.random()*100;
            int doDai = (int) (randomDouble) % 5 + 1;   // Day la do dai ngau nhien cua chuoi duoc tao.
            Mang[i] = RandomStringUtils.randomAlphabetic(doDai);    // Tao chuoi ngau nhien.
        }
    }

    public void hienThiMang() {
        chuoiChuaMang = "";
        for(int i = 0; i < size; i++) {
            chuoiChuaMang += Mang[i];
            if(i < size-1) chuoiChuaMang += "; ";
        }
        txaHienThiMang.setText(chuoiChuaMang);
    }

    @FXML
    private TextArea txaHienThiMang;

    @FXML
    void btnTaoMang(ActionEvent event) {
        taoMang();
        hienThiMang();
    }

    void swap(String[] Mang, int i, int j) {
        String temp = Mang[i];
        Mang[i] = Mang[j];
        Mang[j] = temp;
    }

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

    void BubbleSort() {
        for(int i = 0; i < size-1; i++) {
            for(int j = 0; j < size-i-1; j++) {
                if(Mang[j].compareTo(Mang[j+1]) > 0) {
                    swap(Mang, j, j+1);
                }
            }
        }
    }

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

    // Ham hop nhat 2 mang da sap xep thanh 1 mang duoc sap xep, dung cho MergeSort:
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

    void MergeSort(String[] Mang, int l, int r) {
        if(l < r) {
            int m = l + (r-l)/2;
            MergeSort(Mang, l, m);
            MergeSort(Mang, m+1, r);
            Merge(Mang, l, m, r);
        }
    }

    // Tim vi tri cua pivot trong day sau khi day duoc sap xep, chuyen cac phan tu nho hon pivot len truoc no:
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

    void QuickSort(String[] Mang, int l, int h) {
        if(l < h) {
            int pivot = Partition(Mang, l, h);
            QuickSort(Mang, l, pivot-1);
            QuickSort(Mang, pivot+1, h);
        }
    }

    // Cac hang String chua ten cac giai thuat sap xep:
    final String SS = "Selection Sort", BS = "Bubble Sort", IS = "Insertion Sort", MS = "Merge Sort", QS = "Quick Sort";

    @FXML
    private Label lbThoiGian;
    StopWatch stopWatch = new StopWatch();
    // Khi nhan nut "Sap xep lai mang":
    @FXML
    void btnSapXep(ActionEvent event) {
        if(txaHienThiMang.getText() == "") {
            thongBao("Bạn chưa tạo mảng!");
            return;
        }
        stopWatch.reset();
        stopWatch.start();
        switch (cbxGiaiThuat.getSelectionModel().getSelectedItem()) {
            case SS:
                SelectionSort();
                break;
            case BS:
                BubbleSort();
                break;
            case IS:
                InsertionSort();
                break;
            case MS:
                MergeSort(Mang, 0, size-1);
                break;
            case QS:
                QuickSort(Mang, 0, size-1);
                break;
            default:
        }
        stopWatch.stop();
        lbThoiGian.setText("Thời gian thực hiện thuật toán: " + stopWatch.getTime() + " ms");
        hienThiMang();
    }

    // Khi nhan nut "Xoa mang":
    @FXML
    void btnXoaMang(ActionEvent event) {
        if(txaHienThiMang.getText() == ""){
            thongBao("Mảng trống!");
            return;
        }
        txaHienThiMang.setText("");
        lbThoiGian.setText("");
    }

    void thongBao(String ndThongBao) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(ndThongBao);
        alert.showAndWait();
    }

    @FXML
    void about(ActionEvent event) {
        thongBao("Ứng dụng này được viết cho bài test DTS Super Intern \n Tác giả: Ma Đình Chiến \n Tháng 6, 2022");
    }

    @FXML
    void closeApp(ActionEvent event) {
        Stage stage = (Stage) lbThoiGian.getScene().getWindow();
        stage.close();
    }

}
