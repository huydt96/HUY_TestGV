package com.gv.testgv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.gv.testgv.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.btn1.setOnClickListener { question1() }
        binding.btn2.setOnClickListener { question2() }
        binding.btn3.setOnClickListener { question3() }
    }

    private fun question1() {
        val input = binding.edInputQuestion1.text.toString()
        if (!checkValid(input)) {
            binding.tvOutput1.text = "Không hợp lệ"
            return
        }
        val number = input.toInt()
        var countSquareNumber = 0
        for (i in 1..number) {
            if (isSquareNumber(i)) {
                countSquareNumber += 1
            }
        }
        binding.tvOutput1.text = "từ 1 tới N có $countSquareNumber số chính phương"
    }

    private fun question2() {
        val input1 = binding.edInput1Question2.text.toString()
        val input2 = binding.edInput2Question2.text.toString()
        if (!checkValid(input1)) {
            binding.tvOutput2.text = "Không hợp lệ"
            return
        }
        if (!checkValid(input2)) {
            binding.tvOutput2.text = "Không hợp lệ"
            return
        }
        val number1 = input1.toInt()
        val number2 = input2.toInt()
        var result = "Result:"
        for (i in number1..number2)
            if (isPrime(i) && isSymmetricalNumber(i)) {
                result += " $i"
            }
        binding.tvOutput2.text = "Các số nguyên tố đối xứng từ $input1 đến $input2 là: $result"
    }

    private fun question3() {
        val input = binding.edInputQuestion3.text.toString()
        if (!checkValid(input)) {
            binding.tvOutput3.text = "Không hợp lệ"
            return
        }
        val number = input.toInt()
        var multiplicationNumber = 1
        var listDividerIsPrime = ""
        for (i in 1..number) {
            if (isDivider(i, number)) {
//                Log.i("HUY", "question3: isDivider +$i")
            }
            if (isDivider(i, number) && isPrime(i)) {
                listDividerIsPrime += " $i"
                multiplicationNumber *= i
            }
        }
        if (multiplicationNumber == number) {
            binding.tvOutput3.text =
                "Tích các ước là số nguyên tố của $input là $multiplicationNumber = $input" +
                        "\nDanh sách các ước là số nguyên tố: $listDividerIsPrime"
        } else if (multiplicationNumber < number) {
            binding.tvOutput3.text =
                "Tích các ước là số nguyên tố của $input là $multiplicationNumber < $input" +
                        "\nDanh sách các ước là số nguyên tố: $listDividerIsPrime"
        }
    }

    private fun isDivider(i: Int, number: Int): Boolean {
        if (number % i == 0) return true
        return false
    }

    private fun isSymmetricalNumber(number: Int): Boolean {
        val string = number.toString()
        val length = string.length
        for (i in 0..length / 2) {
            if (string[i] != string[length - 1 - i]) return false
        }
        return true
    }

    private fun isPrime(number: Int): Boolean {
        if (number <= 1) return false
        for (i in 2..sqrt(number.toDouble()).toInt()) {
            if (number % i == 0) return false
        }
        return true
    }

    private fun isSquareNumber(number: Int): Boolean {
        val sqrtNumber = sqrt(number.toDouble())
        if (sqrtNumber % 1.0 == 0.0) return true
        return false
    }

    private fun checkValid(number: String?): Boolean {
        if (number.isNullOrBlank()) return false
        if (number.isDigitsOnly() && number.toInt() >= 0) return true
        return false
    }
}