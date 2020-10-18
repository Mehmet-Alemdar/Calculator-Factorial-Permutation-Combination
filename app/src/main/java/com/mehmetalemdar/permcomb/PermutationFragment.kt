package com.mehmetalemdar.permcomb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import java.math.BigInteger

class PermutationFragment : Fragment() {



    var nNumber:Int? = null
    var rNumber:Int? = null
    var subtractionNumber:Int? = null

    var nNumberFacResult = BigInteger.ONE
    var subtractionNumberFacResult= BigInteger.ONE

    var permutationResult = BigInteger.ONE



    var edtN:EditText? = null
    var edtR:EditText? = null
    var btnPermutation: Button? = null
    var txtResult:TextView? = null
    var imgBtnPermutationShare:ImageButton? = null


    var permutationContext:Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        permutationContext = context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_permutation, container, false)

        edtN=view.findViewById(R.id.edtN)
        edtR=view.findViewById(R.id.edtR)
        btnPermutation=view.findViewById(R.id.btnPermutation)
        txtResult=view.findViewById(R.id.txtPermutationResult)
        imgBtnPermutationShare = view.findViewById(R.id.imgBtnSharePermutation)



        btnPermutation!!.setOnClickListener {
            if(edtN!!.text == null || edtR!!.text == null || edtN!!.text.toString().trim().equals("")||edtR!!.text.toString().trim().equals("")){
                Toast.makeText(permutationContext,"Please enter a number",Toast.LENGTH_SHORT).show()
            }else if(edtN!!.text.toString().toIntOrNull() == null ||edtR!!.text.toString().toIntOrNull() == null ){
                Toast.makeText(permutationContext,"Please enter a number / The highest number could be 220",Toast.LENGTH_LONG).show()
            } else if(edtN!!.text.toString().toInt()>220 || edtR!!.text.toString().toInt()>220){
                Toast.makeText(permutationContext,"The highest number could be 220",Toast.LENGTH_SHORT).show()
            }
            else{
                permutation()
                it.hideKeyboard()
            }


        }

        imgBtnPermutationShare!!.setOnClickListener {
            sharePermutationButtonClicked()
        }

        return view

    }
    

    fun View.hideKeyboard() {
        val inputManager = permutationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
    fun permutation(){
        nNumber = edtN!!.text.toString().toIntOrNull()
        rNumber = edtR!!.text.toString().toIntOrNull()

        if(nNumber == null || rNumber == null){
            Toast.makeText(permutationContext,"Please enter a number / The highest number could be 220",Toast.LENGTH_LONG).show()
        }else{
            subtractionNumber = (nNumber!! - rNumber!!)
            if(subtractionNumber!!<0){
                Toast.makeText(permutationContext, "r cannot be greater than n ",Toast.LENGTH_LONG).show()
            }else{
                // n!
                for (i in 1..nNumber!!){
                    nNumberFacResult = nNumberFacResult.multiply(BigInteger.valueOf(i.toLong()))
                }
                // subtraction!
                for (i in 1..subtractionNumber!!){
                    subtractionNumberFacResult = subtractionNumberFacResult.multiply(BigInteger.valueOf(i.toLong()))
                }

                permutationResult = (nNumberFacResult / subtractionNumberFacResult)
                txtResult!!.text = "P($nNumber,$rNumber) = $permutationResult"
                imgBtnPermutationShare!!.visibility = View.VISIBLE
                permutationResult = BigInteger.ONE
                nNumberFacResult = BigInteger.ONE
                subtractionNumberFacResult = BigInteger.ONE
            }

        }
    }
    fun sharePermutationButtonClicked(){
        var shareIntent = Intent()
        shareIntent.setAction(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT,"${txtResult!!.text}")
        shareIntent.setType("text/plain")
        shareIntent = Intent.createChooser(shareIntent,"share by")
        startActivity(shareIntent)
    }

}