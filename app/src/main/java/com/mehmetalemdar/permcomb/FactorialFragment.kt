package com.mehmetalemdar.permcomb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import java.math.BigInteger


class FactorialFragment : Fragment() {


    var factorialContext:Context? = null

    var number: Int? = null
    var factorialResult = BigInteger.ONE

    var edtxtNumber:EditText?=null
    var btnFactorial: Button? = null
    var txtResult:TextView? = null
    var imgBtnFactorialShare:ImageButton? = null



    override fun onAttach(context: Context) {
        super.onAttach(context)
        factorialContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_factorial, container, false)


        edtxtNumber = view.findViewById<EditText>(R.id.edtNumber)
        btnFactorial = view.findViewById<Button>(R.id.btnFactorial)
        txtResult = view.findViewById<TextView>(R.id.txtFactorialResult)
        imgBtnFactorialShare = view.findViewById(R.id.imgBtnShareFactorial)





        btnFactorial!!.setOnClickListener {
            if(edtxtNumber!!.text == null || edtxtNumber!!.text.toString().trim().equals("")){
                Toast.makeText(factorialContext,"Incorrect entered data",Toast.LENGTH_LONG).show()

            }else if(edtxtNumber!!.text.toString().toIntOrNull() == null){
                Toast.makeText(factorialContext,"Please enter a number / The highest number could be 220",Toast.LENGTH_LONG).show()
            }else if(edtxtNumber!!.text.toString().toInt()>220){
                Toast.makeText(factorialContext,"The highest number could be 220",Toast.LENGTH_SHORT).show()
            }
            else{
                factorial()

                it.hideKeyboard()
            }

        }

        imgBtnFactorialShare!!.setOnClickListener {
            shareFuctorialButtonClicked()
        }


        return view

    }
    fun View.hideKeyboard() {
        val inputManager = factorialContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    fun factorial(){
        number = edtxtNumber!!.text.toString().toIntOrNull()

        if(number == null){
            Toast.makeText(factorialContext,"Please enter a number / The highest number could be 220",Toast.LENGTH_LONG).show()
        }else{
            for (i in 1..number!!){
                factorialResult = factorialResult.multiply(BigInteger.valueOf(i.toLong()))
            }
            txtResult!!.text ="$number! = $factorialResult"
            imgBtnFactorialShare!!.visibility = View.VISIBLE
            factorialResult = BigInteger.ONE

        }
    }

    fun shareFuctorialButtonClicked(){
        var shareIntent = Intent()
        shareIntent.setAction(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT,"${txtResult!!.text}")
        shareIntent.setType("text/plain")
        shareIntent = Intent.createChooser(shareIntent,"share by")
        startActivity(shareIntent)
    }


}