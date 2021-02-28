package com.mnsh.homework36kalkulyator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var hasNatija = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0.setOnClickListener(View.OnClickListener {
           if (txt_ekran.text != "0"){
               txt_ekran.text = "${txt_ekran.text}0"
           }

        })

        btn_1.setOnClickListener {
            raqamYozish(1)

        }

        btn_2.setOnClickListener {
            raqamYozish(2)

        }

        btn_3.setOnClickListener {
            raqamYozish(3)

        }

        btn_4.setOnClickListener {
            raqamYozish(4)
        }

        btn_5.setOnClickListener {
            raqamYozish(5)

        }

        btn_6.setOnClickListener {
            raqamYozish(6)

        }

        btn_7.setOnClickListener {
            raqamYozish(7)

        }

        btn_8.setOnClickListener {
            raqamYozish(8)

        }

        btn_9.setOnClickListener {
            raqamYozish(9)

        }

        btn_nuqata.setOnClickListener {
            val misol = txt_ekran.text.toString()
            var amalIndex = -1
            for (i in misol.indices) {
                if (misol[i] == '+' || misol[i] == '-' || misol[i] == '*' || misol[i] == '/'){
                    amalIndex = i
                }
            }
            if (amalIndex == -1){
                if (!txt_ekran.text.toString().contains('.')){
                    txt_ekran.text = "${txt_ekran.text}."
                }
            }else {
                val ekranLength = txt_ekran.text.length
                val matn = txt_ekran.text.toString().subSequence(amalIndex, ekranLength)
                if (!matn.contains('.')) {
                    txt_ekran.text = "${txt_ekran.text}."
                }
            }
        }

        btn_ac.setOnClickListener {
            txt_ekran.text = "0"
            hasNatija = false

        }

        btn_delete.setOnClickListener {
            val a = txt_ekran.text
            if (a.length == 1 || a == "-"){
                txt_ekran.text = "0"
            }else
            txt_ekran.text = a.subSequence(0, a.length-1)
        }


        btn_qoshish.setOnClickListener {
            amalYozish("+")
        }

        btn_ayirish.setOnClickListener {
            amalYozish("-")
        }

        btn_kopaytirish.setOnClickListener {
            amalYozish("*")
        }

        btn_bolish.setOnClickListener {
            amalYozish("/")
        }

        btn_teng.setOnClickListener {
            if (hasNatija == false) hisoblash()
        }
    }


    fun raqamYozish(raqam:Int){

        if (hasNatija){
            txt_ekran.text = "$raqam"
            hasNatija=false
        }else {

            val r = raqam.toString()
            if (txt_ekran.text == "0") {
                txt_ekran.text = r
            } else {
                txt_ekran.text = "${txt_ekran.text}$r"
            }
        }
        scrollView.scrollBy(100,100)
    }

    fun amalYozish(amalArg:String){
        if (hasNatija){
            val misol = txt_ekran.text.toString()
            for (i in misol.indices) {
                if (misol[i] == '='){
                    txt_ekran.text = "${misol.subSequence(i+1, misol.length)}$amalArg"
                    break
                }
            }
            hasNatija=false
        }else {
            txt_ekran.text = "${txt_ekran.text}$amalArg"
        }
        scrollView.scrollBy(100, 100)
    }


    fun hisoblash(){

        var sonlar = ArrayList<Double>()
        var amallar = ArrayList<Int>()

        val misol = txt_ekran.text.toString()

        var index1 = 0
        for (i in misol.indices) {
            if (amallar.isEmpty()) {
                when (misol[i]) {
                    '+' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(0)
                        index1 = i
                    }
                    '-' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(1)
                        index1 = i
                    }
                    '*' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(2)
                        index1 = i
                    }
                    '/' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(3)
                        index1 = i
                    }
                }
            }else{
                when (misol[i]) {
                    '+' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(0)
                        index1 = i
                    }
                    '-' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(1)
                        index1 = i
                    }
                    '*' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(2)
                        index1 = i
                    }
                    '/' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(3)
                        index1 = i
                    }
                }
            }
        }

        sonlar.add(misol.subSequence(index1+1, misol.length).toString().toDouble())

        var count = 0
        var natija = sonlar.first()
        while (count < amallar.size) {

            when (amallar[count]) {
                0 -> {
                    natija += sonlar[count + 1]
                }
                1 -> {
                    natija -= sonlar[count + 1]
                }
                2 -> {
                    natija *= sonlar[count + 1]
                }
                3 -> {
                    natija /= sonlar[count + 1]
                }
            }

            count++
        }

        val intNatija:Int = natija.toInt()
        if (natija / intNatija == 1.0){

            txt_ekran.text = "${txt_ekran.text}= $intNatija"
        }else {
            txt_ekran.text = "${txt_ekran.text}= $natija"
        }
        hasNatija = true
        val maxScrollAmount = scrollView.maxScrollAmount
        scrollView.scrollBy(maxScrollAmount, maxScrollAmount)
        scrollView.scrollBy(maxScrollAmount, maxScrollAmount)
        println(maxScrollAmount)
    }
}