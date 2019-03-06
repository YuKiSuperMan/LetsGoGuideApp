package com.njz.letsgoappguides.customview.widget.calendarDecorators;

import com.njz.letsgoappguides.util.DateUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 作者：徐敏敏 on 2016/12/20 0020 14:27
 * 邮箱：15067596185@163.com
 */
public class LunarDecorator implements DayViewDecorator {
    private String year;
    private String month;
    List<PriceModel> pms;
    LunarSpan lunarSpan;

    Date startTime;
    Date endTime;

    public LunarDecorator(String year, String month, List<PriceModel> priceModels) {
        this.year = year;
        this.month = month;
        lunarSpan = new LunarSpan(year, month);


        Calendar instance1 = Calendar.getInstance();

        Calendar instance2 = Calendar.getInstance();
        instance2.add(Calendar.YEAR, 1);


        this.startTime = instance1.getTime();
        this.endTime = instance2.getTime();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {

        if(Integer.valueOf(DateUtil.dateToStr(day.getDate(),"yyyyMMdd")) < Integer.valueOf(DateUtil.dateToStr(startTime,"yyyyMMdd"))){
            return false;
        }
        if(Integer.valueOf(DateUtil.dateToStr(day.getDate(),"yyyyMMdd")) > Integer.valueOf(DateUtil.dateToStr(endTime,"yyyyMMdd"))){
            return false;
        }
//        if (day.getDate().getTime() < startTime.getTime())
//            return false;
//        if (day.getDate().getTime() > endTime.getTime())
//            return false;
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(lunarSpan);
    }

    public void setTime(String year, String month, List<PriceModel> priceModels) {
        this.year = year;
        this.month = month;
        lunarSpan.setTime(year, month, priceModels);
    }

    public void setTime(String year, String month) {
        this.year = year;
        this.month = month;
        lunarSpan.setTime(year, month);
    }


}
