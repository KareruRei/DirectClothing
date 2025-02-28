
window.onload = function() {
    var cart = document.getElementById("cart-container");
    var cards = cart.children;

    for (let card of cards) {
        let cardID = card.id;
        let cardChildren = card.children;

        cardChildren[0].children[1].children[2].id = "removeButton_id"+cardID;

        cardChildren[1].children[0].id = "subButton_id"+cardID;
        cardChildren[1].children[1].id = "quantity_id"+cardID;
        cardChildren[1].children[2].id = "addButton_id"+cardID;

        document.getElementById("subButton_id"+cardID).addEventListener("click", function() {changeQty(cardChildren[1].children[1].id, card.id, -1);});
        document.getElementById("addButton_id"+cardID).addEventListener("click", function() {changeQty(cardChildren[1].children[1].id, card.id, 1);});
        document.getElementById("removeButton_id"+cardID).addEventListener("click", function() {toggleItemRemoval(card.id);});
    }
};
function payment() {
    alert("Paying na!");
    closePopup();
}

function changeQty(numId, itemID, change) {
    var qtyNum = document.getElementById(numId);
    qtyNum.value = Number(qtyNum.value) + change;
    if (qtyNum.value < 1) qtyNum.value = 1;
    
    fetch("/change-qty?itemID=" + itemID + "&newQty=" + qtyNum.value, {method: 'POST'})
}

function toggleItemRemoval(itemID) {
    var item = document.getElementById(itemID);

    
    fetch("/item-removed", {
        method: 'POST',
        headers: {'Content-Type': 'text/plain',},
        body: String(itemID)
    })
    .then(response => {
        if (response.status === 500) alert("Item does not exist! Failed to remove item from cart.");
        return response.text();
    })
    .then(cartSize => {
        item.classList.toggle("shrink");
        item.addEventListener("transitionend", function() {document.getElementById(itemID).remove();});
    })
}