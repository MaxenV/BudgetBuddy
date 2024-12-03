package com.example.budget_buddy_android.dashboard

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun DateTimePicker(viewModel: DetailViewModel) {
    val context = LocalContext.current

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            viewModel.calendar.set(year, month, dayOfMonth)
            val timePickerDialog = TimePickerDialog(
                context,
                { _: TimePicker, hourOfDay: Int, minute: Int ->
                    viewModel.calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    viewModel.calendar.set(Calendar.MINUTE, minute)
                    viewModel.updateExpense(dateTime = viewModel.calendar.time)
                },
                viewModel.calendar.get(Calendar.HOUR_OF_DAY),
                viewModel.calendar.get(Calendar.MINUTE),
                true,
            )
            timePickerDialog.show()
        },
        viewModel.calendar.get(Calendar.YEAR),
        viewModel.calendar.get(Calendar.MONTH),
        viewModel.calendar.get(Calendar.DAY_OF_MONTH)
    )

    Text(
        text = viewModel.expenseDateTime,
        modifier = Modifier.clickable { datePickerDialog.show() }
    )
}