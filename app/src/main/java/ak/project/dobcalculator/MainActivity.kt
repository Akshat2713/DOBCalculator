package ak.project.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var txtSelectedDate: TextView?=null
    private var txtTimeInSecond: TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnSelectButton:Button= findViewById(R.id.btnSelectDate)
        txtSelectedDate=findViewById(R.id.tvSelectedDate)
        txtTimeInSecond=findViewById(R.id.tvTimeInSeconds)





        btnSelectButton.setOnClickListener{
            clickDatePicker()
        }
    }
    fun clickDatePicker(){

        val myCalender= Calendar.getInstance()
        val year= myCalender.get(Calendar.YEAR)
        val month= myCalender.get(Calendar.MONTH)
        val day= myCalender.get(Calendar.DAY_OF_YEAR)
        val dpd= DatePickerDialog(this,
            {view, selectedYear,selectedMonth,selectedDayOfMonth ->
                Toast.makeText(this,"You choose $selectedDayOfMonth/${selectedMonth+1}/$selectedYear",Toast.LENGTH_SHORT).show()
            val selectedDate= "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                txtSelectedDate?.text = selectedDate

                val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate= sdf.parse(selectedDate)
                theDate?.let { val selectedDateInMinutes= theDate.time / 60000
                    val currentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let { val currentDateInMinutes= currentDate.time / 60000
                        val differenceInMinutes=currentDateInMinutes-selectedDateInMinutes
                        txtTimeInSecond?.text= differenceInMinutes.toString() } }



            },
            year, month, day
            )
        dpd.datePicker.maxDate= System.currentTimeMillis()-86400000
        dpd.show()

    }
}