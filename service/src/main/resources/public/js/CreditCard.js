$(document).ready(function () {
   listOfCreditCardList();

});

function saveCreditCardList() {
        var fullName = $("#fullName").val();
        var cardNumber = $("#cardNumber").val();
        var limit = $("#limit").val();
        var obj =
            {
                fullName: fullName,
                cardNumber: cardNumber,
                limit: limit
            };

        $.ajax({
            url: '/api/v1/cardProcessing',
            data: JSON.stringify(obj),
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            success: function () {
                listOfCreditCardList();
            },
            error: function(xhr, status, error){
                  var errorMessage = formatErrorMessage(xhr)
                     alert(errorMessage);
                 }
        });
      }

function listOfCreditCardList() {
    $.ajax({
        type: "GET",
        url: "/api/v1/cardProcessing",
        contentType: "aplication/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
        $("#tableListCard tbody tr").remove();
            $.each(result.creditCardList, function (i, card) {
                var cardRow = '<tr>' +
                    '<td>' + card.fullName + '</td>' +
                    '<td>' + card.cardNumber + '</td>' +
                    '<td><span>&#163;</span>' + card.balance + '</td>' +
                    '<td><span>&#163;</span>' + card.limit + '</td>' +
                    '</tr>';

                $('#cardListBody').append(cardRow);

            });
        },
        error: function (error) {
            alert(error);
        }
    });
}

function formatErrorMessage(xhr) {
var data = xhr.responseText;
var responseStatus = xhr.status;
var responseDescription = JSON.parse(data);

    if (xhr.status === 0) {
        return ('Not connected.\nPlease verify your network connection.');
    } else {
        return ("Status Code:"+responseStatus+"   "+"Message:"+responseDescription["description"]);
    }
}



