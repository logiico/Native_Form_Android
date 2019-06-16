/*
    Default Functionality
    */
$(document).ready(function () {
    /*
     Default
     */
    window.pd = $(".inlineDatepicker").persianDatepicker({
        timePicker: {
            enabled: true
        },
        format: 'YYYY/MM/DD HH:mm',
        altField: '.inlineDatepickerAlt',
        altFormat: "YYYY MM DD HH:mm:ss",
        //            minDate:1258675200000,
        //            maxDate:1358675200000,
        //توابع زیر برای ایجاد محدودیت میباشد مثلا در زیر آورده شده روز 20 ام هر ماه دیسیبل باشد
        //checkDate: function (unix) {
        //    var output = true;
        //    var d = new persianDate(unix);
        //    if (d.date() == 20) {
        //        output = false;
        //    }
        //    return output;
        //},
        //checkMonth: function (month) {
        //    var output = true;
        //    if (month == 1) {
        //        output = false;
        //    }
        //    return output;

        //}, checkYear: function (year) {
        //    var output = true;
        //    if (year == 1396) {
        //        output = false;
        //    }
        //    return output;
        //}

    }).data('datepicker');

    //$("#inlineDatepicker").pDatepicker("setDate", [1391, 12, 1, 11, 14]);

    //pd.setDate([1333,12,28,11,20,30]);

    /**
     * Default
     * */
    $('#default').persianDatepicker({
        altField: '#defaultAlt'

    });


    /*
     observer
     */
    $("#observer").persianDatepicker({
        altField: '#observerAlt',
        altFormat: "YYYY MM DD HH:mm:ss",
        observer: true,
        format: 'YYYY/MM/DD'

    });

    /*
     timepicker
     */
    $("#timepicker").persianDatepicker({
        altField: '#timepickerAltField',
        altFormat: "YYYY MM DD HH:mm:ss",
        format: "HH:mm:ss a",
        onlyTimePicker: true

    });
    /*
     month
     */
    $("#monthpicker").persianDatepicker({
        format: " MMMM YYYY",
        altField: '#monthpickerAlt',
        altFormat: "YYYY MM DD HH:mm:ss",
        yearPicker: {
            enabled: false
        },
        monthPicker: {
            enabled: true
        },
        dayPicker: {
            enabled: false
        }
    });

    /*
     year
     */
    $("#yearpicker").persianDatepicker({
        format: "YYYY",
        altField: '#yearpickerAlt',
        altFormat: "YYYY MM DD HH:mm:ss",
        dayPicker: {
            enabled: false
        },
        monthPicker: {
            enabled: false
        },
        yearPicker: {
            enabled: true
        }
    });
    /*
     year and month
     */
    $("#yearAndMonthpicker").persianDatepicker({
        format: "YYYY MM",
        altFormat: "YYYY MM DD HH:mm:ss",
        altField: '#yearAndMonthpickerAlt',
        dayPicker: {
            enabled: false
        },
        monthPicker: {
            enabled: true
        },
        yearPicker: {
            enabled: true
        }
    });
    /**
     inline with minDate and maxDate
     */
    $("#inlineDatepickerWithMinMax").persianDatepicker({
        altField: '#inlineDatepickerWithMinMaxAlt',
        altFormat: "YYYY MM DD HH:mm:ss",
        minDate: 1416983467029,
        maxDate: 1419983467029
    });
    /**
     Custom Disable Date
     */
    $("#customDisabled").persianDatepicker({
        timePicker: {
            enabled: true
        },
        altField: '#customDisabledAlt',
        checkDate: function (unix) {
            var output = true;
            var d = new persianDate(unix);
            if (d.date() == 20 | d.date() == 21 | d.date() == 22) {
                output = false;
            }
            return output;
        },
        checkMonth: function (month) {
            var output = true;
            if (month == 1) {
                output = false;
            }
            return output;

        }, checkYear: function (year) {
            var output = true;
            if (year == 1396) {
                output = false;
            }
            return output;
        }

    });

    /**
     persianDate
     */
    $("#persianDigit").persianDatepicker({
        altField: '#persianDigitAlt',
        altFormat: "YYYY MM DD HH:mm:ss",
        persianDigit: false
    });
});