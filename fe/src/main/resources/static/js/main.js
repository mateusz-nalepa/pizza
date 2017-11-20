$( document ).ready(function() {
    console.log( "begin" );

    function addPizza(pizzaId, pizzaType) {
        $.ajax
        ({
            type: "POST",
            url: '/client/order/addPizza',
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify({"pizzaId": pizzaId, "pizzaType": pizzaType}),
            success: function () {
                alert("Pizza was added!");
            }
        });
    }

    $(".dodajPizze").click(function () {
        var pizzaId = $(this).attr("pizza-id");
        var pizzaType = $(this).attr("pizza-type");
        addPizza(pizzaId, pizzaType);

    });

    function addItem(itemId) {
        $.ajax
        ({
            type: "POST",
            url: '/client/order/addItem',
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify({"itemId": itemId}),
            success: function () {
                alert("Item was added!");
            }
        });
    }

    $(".addItem").click(function () {
        var itemId = $(this).attr("item-id");
        addItem(itemId);
    });

    console.log( "end" );
});