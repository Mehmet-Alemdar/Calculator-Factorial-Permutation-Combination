package com.mehmetalemdar.permcomb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import java.math.BigInteger


class CombinationFragment : Fragment() {
    var nNumber:Int? = null
    var rNumber:Int? = null
    var subtractionNumber:Int? = null

    var nNumberFacResult = BigInteger.ONE
    var rNumberFacResult = BigInteger.ONE
    var subtractionNumberFacResult= BigInteger.ONE

    var combinationResult  = BigInteger.ONE



    var edtN: EditText? = null
    var edtR: EditText? = null
    var btnCombination: Button? = null
    var txtResult: TextView? = null
    var imgBtnCombinationShare:ImageButton? = null


    var combinationContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        combinationContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_combination, container, false)

        edtN=view.findViewById(R.id.edtN)
        edtR=view.findViewById(R.id.edtR)
        btnCombination=view.findViewById(R.id.btnCombination)
        txtResult=view.findViewById(R.id.txtCombinationResult)
        imgBtnCombinationShare=view.findViewById(R.id.imgBtnShareCombination)

        btnCombination!!.setOnClickListener {

            if(edtN!!.text == null || edtR!!.text == null || edtN!!.text.toString().trim().equals("")||edtR!!.text.toString().trim().equals("")){
                Toast.makeText(combinationContext,"Please enter a number", Toast.LENGTH_SHORT).show()
            }else if(edtN!!.text.toString().toIntOrNull() == null ||edtR!!.text.toString().toIntOrNull() == null){
                Toast.makeText(combinationContext,"Please enter a number / The highest number could be 220",Toast.LENGTH_LONG).show()
            }else if(edtN!!.text.toString().toInt()>220 || edtR!!.text.toString().toInt()>220){
                Toast.makeText(combinationContext,"The highest number could be 220",Toast.LENGTH_SHORT).show()
            }else{
                combination()
                it.hideKeyboard()
            }

            imgBtnCombinationShare!!.setOnClickListener{
                shareCombinationButtonClicked()
            }

        }
        return view
    }

    fun View.hideKeyboard() {
        val inputManager = combinationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    fun combination(){
        nNumber = edtN!!.text.toString().toIntOrNull()
        rNumber = edtR!!.text.toString().toIntOrNull()

        if(nNumber == null || rNumber == null){
            Toast.makeText(combinationContext,"Please enter a number / The highest number could be 220",Toast.LENGTH_SHORT).show()
        }else{
            subtractionNumber = nNumber!! - rNumber!!
            if(subtractionNumber!!<0){
                Toast.makeText(combinationContext, "r cannot be greater than n ",Toast.LENGTH_LONG).show()
            }else{

                // n!
                for (i in 1..nNumber!!){
                    nNumberFacResult = nNumberFacResult.multiply(BigInteger.valueOf(i.toLong()))
                }
                // subtraction!
                for(i in 1..subtractionNumber!!){
                    subtractionNumberFacResult = subtractionNumberFacResult.multiply(BigInteger.valueOf(i.toLong()))
                }
                // r!
                for(i in 1..rNumber!!){
                    rNumberFacResult = rNumberFacResult.multiply(BigInteger.valueOf(i.toLong()))
                }

                combinationResult = nNumberFacResult / (subtractionNumberFacResult * rNumberFacResult)

                txtResult!!.text = "C($nNumber,$rNumber) = $combinationResult"
                imgBtnCombinationShare!!.visibility = View.VISIBLE
                combinationResult = BigInteger.ONE
                subtractionNumberFacResult = BigInteger.ONE
                nNumberFacResult = BigInteger.ONE
                rNumberFacResult = BigInteger.ONE
            }

        }
    }
    fun shareCombinationButtonClicked(){
        var shareIntent = Intent()
        shareIntent.setAction(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT,"${txtResult!!.text}")
        shareIntent.setType("text/plain")
        shareIntent = Intent.createChooser(shareIntent,"share by")
        startActivity(shareIntent)
    }

}


