## Sorting Algorithms Visualisation
Phần mềm **Sorting Algorithms Visualisation** _v0.0.1_ được phát triển bởi 5 sinh viên của Đại học Bách khoa Hà Nội. Chúng tôi gồm có:
- _Phạm Huy Hà Thái - Project Manager - IT2 K65_
- _Nguyễn Xuân Bách - Developer - IT2 K65_
- _Trịnh Hữu Tùng - Developer - IT2 K65_
- _Nguyễn Tuấn Anh - Tester - IT2 K65_
- _Hoàng Thị Minh Thu - Designer - IT2 K65_

**Sorting Algorithms Visualisation** ra đời với mục đích hỗ trợ việc học tập và nghiên cứu một số thuật toán sắp xếp thông dụng. Với chức năng đầy đủ, giao diện thân thiện, trực quan cùng các bước thao tác đơn giản, chúng tôi hi vọng **Sorting Algorithms Visualisation** có thể hỗ trợ tối đa cho người dùng trong việc tìm hiểu bài toán này.

Đây là dự án mã nguồn mở dưới sự cấp phép của [MIT License](https://opensource.org/licenses/MIT), nên việc đóng góp và phát triển sản phẩm đều rất khuyến khích dưới các yêu cầu và quy định của giấy phép để đem trải nghiệm sản phẩm tốt hơn. 

Mọi đóng góp và thắc mắc xin liên hệ:
- _Email: thai.phh204784@sis.hust.edu.vn_
- _Điện thoại: 0904951358_

## Ngôn ngữ
Phần mềm được lập trình bằng ngôn ngữ ***Java***, xây dựng giao diện với ***JavaFX***, ngôn ngữ ***XML*** và ***CSS***. Một số tính năng khác có yêu cầu các thư viện từ **[GluonHQ](https://gluonhq.com/)** để mang trải nghiệm người dùng tốt hơn. Vì vậy việc cài đặt các thư viện này là cần thiết để phần mềm có thể chạy đúng tính năng của nó.

Chức năng chính của phần mềm được phát triển bằng ***[Eclipse](https://www.eclipse.org/)*** và giao diện được xây dựng bằng ***[JavaFX](https://gluonhq.com/products/javafx/)***.

## Cài đặt
Dưới đây là các bước cài đặt cơ bản của **Graph Path Finder**:

### Cài đặt IDE (Eclipse)
Tải về và cài đặt theo hướng dẫn tại trang chủ của [Eclipse](https://www.eclipse.org/downloads/).

### Cài đặt JavaFX và thiết lập trong IDE
- Tải và cài đặt **JavaFX** tại [GluonHQ](https://gluonhq.com/products/javafx/).
- Thêm ***Environment Variables***:
  - **Windows**: ```set PATH_TO_FX="path\to\javafx-sdk-15.0.1\lib"```
  - **MacOS/Linux**: ```export PATH_TO_FX=path/to/javafx-sdk-15.0.1/lib```

    Để JavaFX tự động chạy khi khởi động Linux, hãy thêm câu lệnh trên vào ```~/.bashrc```.
  - Thêm vào **configuration**:
    
    Mở IDE (Eclipse/Inntellij), vào **Run**, chọn **Edit Configuration**, chọn **Add VM variables** và thêm:
      
      ```--module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml``` 

## Tính năng chính
### Mô hình hóa thuật toán sắp xếp
Hỗ trợ 3 chế độ sắp xếp ***Selection Sort***, ***Bubble Sort***, ***Merge Sort***
### Hiển thị lịch sử sắp xếp
Có ghi lại lịch sử duyệt và sắp xếp của thuật toán để người dùng hình dung và hiểu thuật toán dễ hơn
### Sắp xếp theo tốc độ tùy chọn
Bạn có thể chọn tốc độ theo ý muốn để có thể tiện cho việc nghiên cứu và quan sát thuật toán sắp xếp.
## Về dự án
Đây là một dự án mã nguồn mở cấp phép dưới ***MIT License***, với mục đích giáo dục. Mọi hành vi thương mại hóa, close-source đều bị nghiêm cấms.
