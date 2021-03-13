$('.register-form').on('submit', function() {
    if(confirm('Are you sure to register?')) {
        return true;
    }
    return false;
});
$('.update-form').on('submit', function() {
    if(confirm('Are you sure to update?')) {
        return true;
    }
    return false;
});
$('.apply-form').on('submit', function() {
    if(confirm('Are you sure to apply?')) {
        return true;
    }
    return false;
});

$('.date-time-range').daterangepicker({

	autoUpdateInput: false,
    timePicker: true,
    timePickerIncrement: 30,
    locale: {
    cancelLabel: 'Clear',
    format: 'MM/DD/YYYY HH:mm '
    }
})
$('.date-range').daterangepicker({
        autoUpdateInput: false,
        locale: {
              cancelLabel: 'Clear'
          }
    });
$('.datepicker').datepicker();

