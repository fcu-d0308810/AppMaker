package com.fcu.jslab.demo;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

/**
 * Created by Administrator on 2016/9/18.
 */

public class calendar extends Fragment {

    CalendarView calendarview;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View view = inflater.inflate(R.layout.calendar, container, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        calendarview = (CalendarView) view.findViewById(R.id.Calendar_ClendarView);

        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarview, int year, int month, int dayofmonth) {
                builder.setTitle("當日事件");
                builder.setMessage("XXXXXXXXXXXX");
                builder.setPositiveButton("新增事件",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //按下按鈕要執行的動作
                            }
                        }
                );
                builder.show();
            }
        });
        return view;
    }
}
