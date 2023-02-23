
$(function () {
    $("#myform").on('submit', function (event) {
        event.preventDefault();
        var f = $(this).serialize();
        console.log(f);
        $(".loader").show();
        $("form").hide();
        $.ajax({
            type: "post",
            url: "register",
            data: f,
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                console.log('success');
                $(".loader").hide();
                $("form").show();
                $("#msg").css({ "color": "green" });
                $("#msg").text("Successfully Registered 🙂");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(data);
                console.log('error');
                $(".loader").hide();
                $("form").show();
                $("#msg").css({ "color": "red" });
                $("#msg").text("Error While Registration 😢");
            }
        });
    });
})
