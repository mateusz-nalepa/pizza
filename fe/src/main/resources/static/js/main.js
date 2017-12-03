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

    function acceptOrder(orderId) {
        $.ajax
        ({
            type: "GET",
            url: '/user/order/accept/' + orderId,
            contentType:"application/json; charset=utf-8",
            // data: JSON.stringify({"pizzaId": pizzaId, "pizzaType": pizzaType}),
            success: function () {
                alert("Order was accepted!");
            }
        });
    }

    function orderInTransport(orderId) {
        $.ajax
        ({
            type: "GET",
            url: '/user/order/orderInTransport/' + orderId,
            contentType:"application/json; charset=utf-8",
            // data: JSON.stringify({"pizzaId": pizzaId, "pizzaType": pizzaType}),
            success: function () {
                alert("Order was accepted!");
            }
        });
    }

    function orderFinished(orderId) {
        $.ajax
        ({
            type: "POST",
            url: '/user/order/orderFinished/' + orderId,
            contentType:"application/json; charset=utf-8",
            // data: JSON.stringify({"pizzaId": pizzaId, "pizzaType": pizzaType}),
            success: function () {
                alert("Order was accepted!");
            }
        });
    }

    $(".orderWaitForAccept").click(function () {
        var orderId = $(this).attr("order-id");
        acceptOrder(orderId);
        // location.reload();
        $(this).removeClass("orderWaitForAccept").addClass("orderIsInProgress").change();
        $(this).children(":first").text("IN_PROGRESS");
    });

    $(".orderIsInProgress").click(function () {
        var orderId = $(this).attr("order-id");
        orderInTransport(orderId);
        // location.reload();
        $(this).removeClass("orderIsInProgress").addClass("orderIsDuringDelivery").change();
        $(this).children(":first").text("DURING_DELIVERY");
    });

    $(".orderIsDuringDelivery").click(function () {
        var orderId = $(this).attr("order-id");
        orderFinished(orderId);
        // location.reload();
        $(this).removeClass("orderIsDuringDelivery").addClass("orderDone").change();
        $(this).children(":first").text("DONE");
    });

    console.log( "end" );
});