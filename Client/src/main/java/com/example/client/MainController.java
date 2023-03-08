package com.example.client;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import com.example.client.DBHelper;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainController {
    @FXML
    private VBox vB;

    @FXML
    private void initialize() {
        DBHelper.getSv();

        DBHelper.updateMap();
        vB.getChildren().add(tab());
        for (int i = 0; i < DBHelper.list.size(); i++) {
            vB.getChildren().add(item(i, DBHelper.list.get(i).getName(), DBHelper.list.get(i).getMASV()));
        }
        Label lb = new Label("Chú ý: Để xác nhận điểm danh, bạn phải nhấn nút xác nhận");
        lb.setFont(new Font(26));
        Button button = new Button("Xác nhận");
        button.setFont(new Font(26));
        button.setPrefSize(200, 50);
        button.setStyle("-fx-background-color: #000000");
        button.setTextFill(Color.WHITE);
        button.setOnAction(e ->{
            DBHelper.ghi();
            lb.setText("Đã xác nhận điểm danh");
        });
        Region region = new Region();
        region.setPrefHeight(30);
        Region region1 = new Region();
        region1.setPrefHeight(30);
        vB.getChildren().add(region1);
        vB.getChildren().add(lb);
        vB.getChildren().add(region);
        vB.getChildren().add(button);



    }
    private HBox tab(){
        HBox hb = new HBox();
        Label idLb = new Label("STT");
        idLb.setFont(new Font(26));
        idLb.setPrefSize(60,20);
        hb.getChildren().add(idLb);
        Region region = new Region();
        region.setPrefWidth(30);
        region.setPrefHeight(20);
        hb.getChildren().add(region);
        Label lb = new Label("Họ và tên");
        lb.setFont(new Font(26));
        lb.setPrefSize(300,20);
        hb.getChildren().add(lb);
        Region region1 = new Region();
        region1.setPrefWidth(410);
        region1.setPrefHeight(20);
        hb.getChildren().add(region1);
        Label lb1 = new Label("Trạng thái");
        lb1.setFont(new Font(26));
        lb1.setPrefSize(120,20);
        hb.getChildren().add(lb1);
        Region region2 = new Region();
        region2.setPrefWidth(100);
        region2.setPrefHeight(20);
        hb.getChildren().add(region2);
        Label time = new Label("Lần nghỉ");
        time.setFont(new Font(26));
        hb.getChildren().add(time);
        VBox.setVgrow(hb, javafx.scene.layout.Priority.ALWAYS);

        return hb;
    }
    private HBox item(int id,String name,String masv){
        Region region = new Region();
        region.setPrefWidth(30);
        region.setPrefHeight(20);

        HBox hb = new HBox();
        Label idLb = new Label(String.valueOf(id));
        idLb.setFont(new Font(26));
        idLb.setPrefSize(30,20);
        hb.getChildren().add(idLb);
        hb.getChildren().add(region);
        Label lb = new Label(name);
        lb.setFont(new Font(26));
        lb.setPrefSize(300,20);
        hb.getChildren().add(lb);

        Region region1 = new Region();
        region1.setPrefWidth(400);
        region1.setPrefHeight(20);
        hb.getChildren().add(region1);
        Button checkBtn = new Button("Có mặt");
        checkBtn.setStyle("-fx-background-color: #00ff00");
        checkBtn.setFont(new Font(26));
        checkBtn.setPrefSize(130,20);
        checkBtn.setOnAction(event -> {
           DBHelper.iDmap.put(masv, (DBHelper.iDmap.get(masv) + 1)%3);
            switch (DBHelper.iDmap.get(masv)) {
                case 2 -> {
                    checkBtn.setText("Có mặt");
                    checkBtn.setStyle("-fx-background-color: rgb(0,255,0)");
                    checkBtn.setTextFill(Color.BLACK);
                }
                case 0 -> {
                    checkBtn.setText("Vắng");
                    checkBtn.setStyle("-fx-background-color: #ff0000");
                    checkBtn.setTextFill(Color.WHITE);
                }
                case 1 -> {
                    checkBtn.setText("Có phép");
                    checkBtn.setStyle("-fx-background-color: #ffff00");
                    checkBtn.setTextFill(Color.BLACK);
                }
            }
            //System.out.println(DBHelper.iDmap.get(masv));
        });
        hb.getChildren().add(checkBtn);

        Region region2 = new Region();
        region2.setPrefWidth(100);
        region2.setPrefHeight(20);
        hb.getChildren().add(region2);
        int[] a = DBHelper.getTime(masv);
        Label time = new Label(a[1] + " (Phép: " + a[0] +")");
        time.setFont(new Font(26));
        hb.getChildren().add(time);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setStyle("-fx-border-color: black");


        hb.setOnMouseEntered(event -> {
            hb.setStyle("-fx-border-color: #0000ff");

        });
        hb.setOnMouseExited(event -> {
            hb.setStyle("-fx-border-color: black");
        });
        HBox.setMargin( idLb, new javafx.geometry.Insets(20, 10, 20, 20));
        VBox.setVgrow(hb, javafx.scene.layout.Priority.ALWAYS);
        return hb;
    }
}