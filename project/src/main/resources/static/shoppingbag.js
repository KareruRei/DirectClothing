window.onload = function() {
    var cards = document.getElementsByClassName("card");

    for (let card of cards) {
        let cardID = card.id;

        var qtyNum = document.getElementById('quantity_id' + cardID);
        qtyNum.addEventListener("input", function() {inputQty(cardID, qtyNum.value);});

        document.getElementById("subButton_id"+cardID).addEventListener("click", function() {changeQty(cardID, -1);});
        document.getElementById("addButton_id"+cardID).addEventListener("click", function() {changeQty(cardID, 1);});
        document.getElementById("removeButton_id"+cardID).addEventListener("click", function() {toggleItemRemoval(cardID);});
    }
};

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('payment-window').style.display = "none";

    const checkoutButton = document.querySelector("#order-summary button");
    if (checkoutButton) {
        checkoutButton.addEventListener("click", function() {
            document.getElementById('payment-window').style.display = "block";
        });
    }

    const closeButton = document.querySelector("#payment-window .close");
    if (closeButton) {
        closeButton.addEventListener("click", function() {
            document.getElementById('payment-window').style.display = "none";
        });
    }

    // Restore Tab Switching for Payment Options
    document.querySelectorAll(".tab").forEach(tab => {
        tab.addEventListener("click", function() {
            let selectedTab = this.getAttribute("onclick").replace("showTab('", "").replace("')", "");

            document.querySelectorAll('.payment-form').forEach(form => form.classList.remove('active'));
            document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));

            document.getElementById(selectedTab + "-form").classList.add('active');
            this.classList.add('active');
        });
    });
});

function changeQty(itemID, change) {
    var qtyNum = document.getElementById('quantity_id' + itemID);
    var orderLineQty = document.getElementById('orderLineQty_id' + itemID);
    var orderLinePrice = document.getElementById('orderLinePrice_id' + itemID);
    var total = document.getElementById('totalAmt');

    var newQty = Number(qtyNum.value) - change;
    if (newQty < 1) newQty = 1;
    
    fetch("/change-qty", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({ itemID: itemID, newQty: newQty })
    })
    .then(response => {
        if (response.status === 500) {
            throw new Error("Failed to change quantity. Not enough stock!");
        } else {
            return response.json();
        }
    })
    .then(cartUpdate => {
        qtyNum.value = cartUpdate.newQty;

        orderLineQty.textContent = "Qty. " + cartUpdate.newQty;
        orderLinePrice.textContent = "PHP " + cartUpdate.discountedPrice;

        total.textContent = "PHP " + cartUpdate.totalPrice;
    })
    .catch(error => {
        alert(error);
    });
}
