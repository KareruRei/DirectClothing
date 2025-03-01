
window.onload = function() {
    var cart = document.getElementById("cart-container");
    var cards = cart.children;

    for (let card of cards) {
        let cardID = card.id;
        let cardChildren = card.children;

        cardChildren[0].children[1].children[2].id = "removeButton_id"+cardID;

        // cardChildren[1].children[0].id = "subButton_id"+cardID;
        // cardChildren[1].children[1].id = "quantity_id"+cardID;
        // cardChildren[1].children[2].id = "addButton_id"+cardID;

        var qtyNum = document.getElementById('quantity_id' + cardID);
        qtyNum.addEventListener("input", function() {inputQty(card.id, qtyNum.value);});

        document.getElementById("subButton_id"+cardID).addEventListener("click", function() {changeQty(card.id, -1);});
        document.getElementById("addButton_id"+cardID).addEventListener("click", function() {changeQty(card.id, 1);});
        document.getElementById("removeButton_id"+cardID).addEventListener("click", function() {toggleItemRemoval(card.id);});
    }
};
function payment() {
    alert("Paying na!");
    closePopup();
}

function changeQty(itemID, change) {
    var qtyNum = document.getElementById('quantity_id' + itemID);
    var rawPrice = document.getElementById('rawPrice_id' + itemID);
    var discountedPrice = document.getElementById('discountedPrice_id' + itemID);
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
        discountedPrice.textContent = "PHP " + cartUpdate.discountedPrice;

        orderLineQty.textContent = "Qty. " + cartUpdate.newQty;
        orderLinePrice.textContent = "PHP " + cartUpdate.discountedPrice;

        total.textContent = "PHP " + cartUpdate.totalPrice;

        if (rawPrice !== null) {
            rawPrice.textContent = "PHP " + cartUpdate.rawPrice;
        }
    })
    .catch(error => {
        alert(error);
    });
}

function inputQty(itemID, newQty) {
    var qtyNum = document.getElementById('quantity_id' + itemID);
    var rawPrice = document.getElementById('rawPrice_id' + itemID);
    var discountedPrice = document.getElementById('discountedPrice_id' + itemID);
    var orderLineQty = document.getElementById('orderLineQty_id' + itemID);
    var orderLinePrice = document.getElementById('orderLinePrice_id' + itemID);
    var total = document.getElementById('totalAmt');
    
    if (newQty < 1 || newQty === "") newQty = 1;

    
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
        discountedPrice.textContent = "PHP " + cartUpdate.discountedPrice;

        orderLineQty.textContent = "Qty. " + cartUpdate.newQty;
        orderLinePrice.textContent = "PHP " + cartUpdate.discountedPrice;

        total.textContent = "PHP " + cartUpdate.totalPrice;

        if (rawPrice !== null) {
            rawPrice.textContent = "PHP " + cartUpdate.rawPrice;
        }
    })
    .catch(error => {
        alert(error);
    });
}

function toggleItemRemoval(itemID) {
    var item = document.getElementById(itemID);
    
    fetch("/item-removed", {
        method: 'POST',
        headers: {'Content-Type': 'text/plain', 'Accept': 'application/json'},
        body: itemID
    })
    .then(response => {
        if (response.status === 500) alert("Item does not exist! Failed to remove item from cart.");
        return response.json();
    })
    .then(cartUpdate => {
        item.classList.toggle("shrink");
        item.addEventListener("transitionend", function() {
            item.remove();

            let orderLine = document.getElementById("orderLine_id" + itemID);
            orderLine.remove();

            let total = document.getElementById('totalAmt');
            total.textContent = "PHP " + cartUpdate.totalPrice;
        });
    })
}