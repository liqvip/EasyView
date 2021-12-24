package com.github.easyview.demo

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.easyview.EasyView

/**
 * @author: Little Bei
 * @Date: 2021/12/24
 */
class TestActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var easyView: EasyView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        initView()
    }

    private fun initView() {
        easyView = findViewById(R.id.ev)


        findViewById<Button>(R.id.bt_left).setOnClickListener(this)
        findViewById<Button>(R.id.bt_top).setOnClickListener(this)
        findViewById<Button>(R.id.bt_right).setOnClickListener(this)
        findViewById<Button>(R.id.bt_bottom).setOnClickListener(this)

        findViewById<Button>(R.id.bt_top_left).setOnClickListener(this)
        findViewById<Button>(R.id.bt_top_right).setOnClickListener(this)
        findViewById<Button>(R.id.bt_bottom_right).setOnClickListener(this)
        findViewById<Button>(R.id.bt_bottom_left).setOnClickListener(this)

        findViewById<Button>(R.id.bt_all_radius).setOnClickListener(this)
        findViewById<Button>(R.id.bt_stroke_width).setOnClickListener(this)
        findViewById<Button>(R.id.bt_stroke_color).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.bt_left -> {
                val left = findViewById<EditText>(R.id.et_left).text
                if(TextUtils.isEmpty(left) || !TextUtils.isDigitsOnly(left)){
                    Toast.makeText(this, "请输入正确的圆角半径！", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setLeftRadius(left.toString().toFloat())
                }
            }
            R.id.bt_top -> {
                val left = findViewById<EditText>(R.id.et_top).text
                if(TextUtils.isEmpty(left) || !TextUtils.isDigitsOnly(left)){
                    Toast.makeText(this, "请输入正确的圆角半径！", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setTopRadius(left.toString().toFloat())
                }
            }
            R.id.bt_right -> {
                val left = findViewById<EditText>(R.id.et_right).text
                if(TextUtils.isEmpty(left) || !TextUtils.isDigitsOnly(left)){
                    Toast.makeText(this, "请输入正确的圆角半径！", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setRightRadius(left.toString().toFloat())
                }
            }
            R.id.bt_bottom -> {
                val left = findViewById<EditText>(R.id.et_bottom).text
                if(TextUtils.isEmpty(left) || !TextUtils.isDigitsOnly(left)){
                    Toast.makeText(this, "请输入正确的圆角半径！", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setBottomRadius(left.toString().toFloat())
                }
            }


            R.id.bt_top_left -> {
                val left = findViewById<EditText>(R.id.et_top_left).text
                if(TextUtils.isEmpty(left) || !TextUtils.isDigitsOnly(left)){
                    Toast.makeText(this, "请输入正确的圆角半径！", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setTopLeftRadius(left.toString().toFloat())
                }
            }
            R.id.bt_top_right -> {
                val left = findViewById<EditText>(R.id.et_top_right).text
                if(TextUtils.isEmpty(left) || !TextUtils.isDigitsOnly(left)){
                    Toast.makeText(this, "请输入正确的圆角半径！", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setTopRightRadius(left.toString().toFloat())
                }
            }
            R.id.bt_bottom_right -> {
                val left = findViewById<EditText>(R.id.et_bottom_right).text
                if(TextUtils.isEmpty(left) || !TextUtils.isDigitsOnly(left)){
                    Toast.makeText(this, "请输入正确的圆角半径！", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setBottomRightRadius(left.toString().toFloat())
                }
            }
            R.id.bt_bottom_left -> {
                val left = findViewById<EditText>(R.id.et_bottom_left).text
                if(TextUtils.isEmpty(left) || !TextUtils.isDigitsOnly(left)){
                    Toast.makeText(this, "请输入正确的圆角半径！", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setBottomLeftRadius(left.toString().toFloat())
                }
            }


            R.id.bt_all_radius -> {
                val left = findViewById<EditText>(R.id.et_all_radius).text
                if(TextUtils.isEmpty(left) || !TextUtils.isDigitsOnly(left)){
                    Toast.makeText(this, "请输入正确的圆角半径！", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setRadius(left.toString().toFloat())
                }
            }
            R.id.bt_stroke_width -> {
                val left = findViewById<EditText>(R.id.et_stroke_width).text
                if(TextUtils.isEmpty(left) || !TextUtils.isDigitsOnly(left)){
                    Toast.makeText(this, "请输入正确的描边大小！", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setStrokeWidth(left.toString().toFloat())
                }
            }
            R.id.bt_stroke_color -> {
                val left = findViewById<EditText>(R.id.et_stroke_color).text
                if(TextUtils.isEmpty(left) || !left.startsWith('#') || left.length != 7){
                    Toast.makeText(this, "请输入正确的描边颜色值，如 '#FFFFFF'", Toast.LENGTH_SHORT).show()
                }else{
                    easyView.setStrokeColor(left.toString())
                }
            }

        }
    }

}