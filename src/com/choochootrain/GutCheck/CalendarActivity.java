package com.choochootrain.GutCheck;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class CalendarActivity extends Activity {
    
    private GridView calendarGrid;
    private TextView currentMonth;
    private Button prevMonth;
    private Button nextMonth;
    private Calendar calendar;
    private int current_month;
    private int current_year;
    private int first_month;
    private int first_year;
    private int month;
    private int year;
    private DateFormat dateFormat = new DateFormat();
    private String format = "MMMM yyyy";
    private Locale locale;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calendar);

        locale = Locale.getDefault();
        calendar = Calendar.getInstance(locale);
        setMonthAndYear();
        setCurrentMonthAndYear();
        setFirstMonthAndYear();

        calendarGrid = (GridView)findViewById(R.id.calendar);

        currentMonth = (TextView)findViewById(R.id.current_month);
        setMonthLabel();

        prevMonth = (Button)findViewById(R.id.prev_month);
        prevMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMonth(-1);
            }
        });

        nextMonth = (Button)findViewById(R.id.next_month);
        nextMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMonth(1);
            }
        });
        nextMonth.setEnabled(false);
    }

    protected void changeMonth(int amount) {
        calendar.add(Calendar.MONTH, amount);
        setMonthAndYear();
        setMonthLabel();
        setMonthButtons();
    }

    private void setMonthAndYear() {
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
    }

    private void setMonthLabel() {
        currentMonth.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) +
            " " + calendar.get(Calendar.YEAR));
    }

    private void setMonthButtons() {
        if(current_month == month && current_year == year)
            nextMonth.setEnabled(false);
        else
            nextMonth.setEnabled(true);

        if(first_month == month && first_year == year)
            prevMonth.setEnabled(false);
        else
            prevMonth.setEnabled(true);
    }
    
    private void setCurrentMonthAndYear() {
        current_month = month;
        current_year = year;
    }

    private void setFirstMonthAndYear() {
        first_month = 7;
        first_year = 2011;
    }
}