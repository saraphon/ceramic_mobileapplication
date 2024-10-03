package com.example.ceramic;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ceramic.model.smarthome;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.widget.Button;

public class Product extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdptersmar mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        String roomType = getIntent().getStringExtra("roomType");

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<smarthome> smarthomes = getSmarthomesByRoomType(roomType);
        mAdapter = new MyAdptersmar(smarthomes, this);
        recyclerView.setAdapter(mAdapter);

        Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(v -> {
            List<smarthome> selectedItems = mAdapter.getSelectedItems(); // ดึงรายการที่เลือก
            Intent intent = new Intent(Product.this, chat.class);

            // ส่งชื่อของ smarthome ที่ถูกเลือกไปยัง Chat
            ArrayList<String> itemNames = new ArrayList<>();
            for (smarthome item : selectedItems) {
                itemNames.add(item.getSmarthome_name());
            }

            intent.putStringArrayListExtra("selectedItems", itemNames);
            startActivity(intent);
        });
    }

    private List<smarthome> getSmarthomesByRoomType(String roomType) {
        List<smarthome> smarthomes = new ArrayList<>();

        switch (roomType) {
            case "deskaccessories":
                smarthomes.add(createSmarthome("love", "https://i.pinimg.com/736x/0e/4a/cf/0e4acf91b70d1331ada0b877eb55db6a.jpg", "", "260 บาท"));
                smarthomes.add(createSmarthome("ที่ใส่ปากกา", "https://i.pinimg.com/564x/a8/a9/c0/a8a9c0fc731f32e029f3ee9b7a6e979f.jpg", "", "100 บาท"));
                smarthomes.add(createSmarthome("ที่กั้นหนังสือ", "https://i.pinimg.com/564x/6e/2f/2a/6e2f2ab4dffc70c28ce4200d95404087.jpg", "", "250 บาท"));
                smarthomes.add(createSmarthome("ที่พู่กัน", "https://i.pinimg.com/564x/7d/57/f0/7d57f031bc28d5a8166dd21261695bc0.jpg", "", "150 บาท"));
                break;

            case "homedecor":
                smarthomes.add(createSmarthome("บ้านเห็ดใส่ไฟ", "https://i.pinimg.com/564x/46/63/06/466306359d34c325b846f769bf66b671.jpg", "", "320 บาท"));
                smarthomes.add(createSmarthome("แจกัน", "https://i.pinimg.com/564x/98/3d/0c/983d0cbc66faa23297d051b6a2f0905c.jpg", "", "150 บาท"));
                smarthomes.add(createSmarthome("น้องผีในไฟ", "https://i.pinimg.com/736x/92/43/82/92438218e1a1e705f95102afe04c2e5b.jpg", "", "89 บาท"));
                smarthomes.add(createSmarthome("ที่ใส่เทียน", "https://i.pinimg.com/736x/cf/3e/02/cf3e021197344a8afd4878c73013b3a9.jpg", "", "85 บาท"));
                break;

            case "household":
                smarthomes.add(createSmarthome("แก้วน้ำ", "https://i.pinimg.com/736x/2f/f4/ab/2ff4abe686f1b02e965f6a0019338d58.jpg", "", "150 บาท"));
                smarthomes.add(createSmarthome("จาน", "https://i.pinimg.com/736x/de/97/c7/de97c797d2c019e30017cd0f8c10b3c5.jpg", "", "170 บาท"));
                smarthomes.add(createSmarthome("ช้อน", "https://i.pinimg.com/564x/fa/a7/23/faa7238a5fcb1d767d11c905ffb6bd9d.jpg", "Summer Cake", "59 บาท"));
                smarthomes.add(createSmarthome("แก้วชา", "https://i.pinimg.com/736x/03/79/45/0379452b7c10d1b12bcd985082dc54e1.jpg", "", "150 บาท"));
                break;

            case "jewelry":
                smarthomes.add(createSmarthome("ต่างหู", "https://i.pinimg.com/564x/f8/b4/6e/f8b46e9110277c6ca2c9f08a7edb0670.jpg", "", "45 บาท"));
                smarthomes.add(createSmarthome("กำไลข้อมือ", "https://i.pinimg.com/564x/c5/07/01/c507015caec2fb485d836663732cd0e2.jpg", "", "80 บาท"));
                smarthomes.add(createSmarthome("สร้อยคอ", "https://i.pinimg.com/736x/ba/44/b3/ba44b3e2a4200cd75056416530f6c2bb.jpg", "", "150 บาท"));
                smarthomes.add(createSmarthome("ต่างหู", "https://i.pinimg.com/564x/9d/b2/dc/9db2dc6815fa4108b0094bad1b744ec4.jpg", "", "70 บาท"));
                break;
        }

        return smarthomes;
    }

    private smarthome createSmarthome(String name, String image, String detail, String price) {
        smarthome item = new smarthome();
        item.setSmarthome_name(name);
        item.setSmarthome_image(image);
        item.setSmarthome_detail(detail);
        item.setSmarthome_price(price);
        item.setChecked(false); // ตั้งค่าเริ่มต้นเป็นไม่เลือก
        return item;
    }
}
