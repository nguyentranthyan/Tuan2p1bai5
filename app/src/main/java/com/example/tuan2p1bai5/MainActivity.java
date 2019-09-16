package com.example.tuan2p1bai5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edt_tenkh, edt_soluong, edt_tongkh, edt_tongkhvip, edt_tongdt;
    TextView tvw_lichsu, tvw_thanhtien;
    Button btn_tinh, btn_next, btn_thongke;
    ImageButton btn_thoat;
    CheckBox check_vip;
    String vip = "", kq = "";
    int demKH = 0, demVIP = 0;
    float tongDT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFindViewByID();
        eventClickThanhTien();
        eventClickNext();
        eventClickThongKe();
        evenClickClose();
    }

    private void evenClickClose() {
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void createFindViewByID() {
        //EditText
        edt_tenkh = (EditText) findViewById(R.id.editText_tenkh);
        edt_soluong = (EditText) findViewById(R.id.editText_soluong);
        edt_tongkh = (EditText) findViewById(R.id.editText_tongkh);
        edt_tongkhvip = (EditText) findViewById(R.id.editText_tongkhvip);
        edt_tongdt = (EditText) findViewById(R.id.editText_tongdt);
        //TextView
        tvw_lichsu = (TextView) findViewById(R.id.textView_lichsu);
        tvw_thanhtien = (TextView) findViewById(R.id.textView_thanhtien);
        //Button
        btn_tinh = (Button) findViewById(R.id.button_tinh);
        btn_next = (Button) findViewById(R.id.button_next);
        btn_thongke = (Button) findViewById(R.id.button_tk);
        btn_thoat = (ImageButton) findViewById(R.id.imageButton_thoat);
        //CheckBox
        check_vip = (CheckBox) findViewById(R.id.checkBox_vip);
    }
    public boolean checkEmpty(String tenkh, String sl){
        if(!tenkh.isEmpty() && !sl.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean checkSoluong(String sl){
        if(sl.matches("[0-9]{1,5}")){
            return true;
        }
        return false;
    }
    private void eventClickThanhTien(){
        btn_tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenkh = edt_tenkh.getText().toString();
                String sl = edt_soluong.getText().toString();
                if(checkEmpty(tenkh, sl)){
                    if(checkSoluong(sl)){
                        int soluong = Integer.parseInt(edt_soluong.getText().toString());
                        float tt = soluong * 20000;//1 cuốn sách giá 20000
                            if(check_vip.isChecked()){
                                double sale = (double) (tt * 0.9); // sale = tt - tt * 0.1 = tt(1 - 0.1) = tt * 0.9
                                tvw_thanhtien.setText("" + sale);
                                demVIP += 1;
                                tongDT += sale;
                            }
                            else{
                                tvw_thanhtien.setText("" + tt);
                                tongDT += tt;
                            }
                            demKH += 1;
                            eventEnableClickTinhTT();
                            saveCustomer();

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Hãy nhập tên khách hàng và số lượng sách.", Toast.LENGTH_SHORT).show();
                    edt_tenkh.setFocusable(true);
                }
            }
        });
    }

    private void eventClickThongKe(){
        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_tongkh.setText("" + demKH);
                edt_tongkhvip.setText("" + demVIP);
                edt_tongdt.setText("" + tongDT);
            }
        });
    }

    private void eventClickNext(){
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                eventEnableClickNext();
            }
        });
    }

    public void clear(){
        edt_tenkh.setText("");
        edt_soluong.setText("");
        check_vip.setChecked(false);
        tvw_thanhtien.setText("");
    }

    public void saveCustomer(){
        String tenkh = edt_tenkh.getText().toString();
        int soluong = Integer.parseInt(edt_soluong.getText().toString());
        String tt = tvw_thanhtien.getText().toString();
        if(check_vip.isChecked()){
            vip = "Có";
        }
        else{
            vip = "Không";
        }
        String lichsukh = "Tên khách hàng : " + tenkh + '\n' +
                "Số lượng sách : " + soluong + '\n' +
                "Khách hàng VIP : " + vip + '\n' +
                "Thành tiền : " + tt + '\n' + '\n';
        kq += lichsukh;
        tvw_lichsu.setText(kq);
    }

    public void eventEnableClickTinhTT(){
        btn_next.setEnabled(true);
        btn_thongke.setEnabled(true);
        btn_tinh.setEnabled(false);
        edt_tenkh.setEnabled(false);
        edt_soluong.setEnabled(false);
        check_vip.setEnabled(false);
        edt_tenkh.setFocusable(true);
    }

    public void eventEnableClickNext(){
        btn_next.setEnabled(false);
        btn_tinh.setEnabled(true);
        btn_thongke.setEnabled(true);
        edt_tenkh.setEnabled(true);
        edt_soluong.setEnabled(true);
        check_vip.setEnabled(true);
        edt_tenkh.setFocusable(true);
    }
}
