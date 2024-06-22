package com.royakash.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var txtViewSelectedDate : TextView? = null
    private var txtViewAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnSelectDate : Button = findViewById(R.id.btn_selectDate)
        txtViewSelectedDate = findViewById(R.id.txtView_selectedDate)
        txtViewAgeInMinutes = findViewById(R.id.txtView_ageInMinutes)

        btnSelectDate.setOnClickListener{
            onClickSelectDate()
        }
    }

    private fun onClickSelectDate() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val date = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "$selectedDayOfMonth-${selectedMonth + 1}-$selectedYear", Toast.LENGTH_LONG).show()

                val enteredDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

                txtViewSelectedDate?.text = enteredDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val selectedDate = sdf.parse(enteredDate)

                selectedDate?.let {
                    val selectedDateInMinutes = selectedDate.time / (60 * 1000) // converting milli-seconds to minutes
                    val selectedDateInHours = selectedDate.time / (60 * 60 * 1000) // converting milli-seconds to hours

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / (60 * 1000)
                        val currentDateInHours = currentDate.time / (60 * 60 * 1000)

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        val differenceInHours = currentDateInHours - selectedDateInHours

                        txtViewAgeInMinutes?.text = differenceInMinutes.toString()
//                        txtViewAgeInMinutes?.text = differenceInHours.toString()
                    }
                }
            },
            year,
            month,
            date
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - (24 * 3600000)
        dpd.show()
    }
}