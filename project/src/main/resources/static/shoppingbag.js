
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

    newQty = Number(qtyNum.value) + change;
    if (newQty < 1) newQty = 1;
    
    fetch("/change-qty?itemID=" + itemID + "&newQty=" + newQty, {method: 'POST'})
    .then(response => {
        if (response.status === 500) {
            throw new Error("Failed to change quantity. Not enough stock!");
        } else {
            return response.text();
        }
    })
    .then(newQty => {
        qtyNum.value = newQty;
    })
    .catch(error => {
        alert(error);
    });
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
window.onclick = function(event) {
    let cartUI = document.getElementById('cart-ui');
    if (event.target === cartUI) {
        closeCart();
    }
}

function removeFromCart(itemID) {
            
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
            var cartContainer = document.getElementById('cart-items');

            document.getElementById('cart-count').innerText = cartSize;
            if (cartSize === "0") {
                document.getElementById('cart-count').style.display = "none";
                cartContainer.innerHTML = "<p>Cart is empty</p>";
            }
            
            for (let cartItem of cartContainer.children) {
                if (cartItem.id === itemID) {
                    cartItem.remove();
                    break;
                }
            }
        })
}

function closeCart() {
    document.getElementById('cart-ui').style.display = "none";
}

function openCart() {
    var cartContainer = document.getElementById('cart-items');
    if (cartContainer.children.length === 0) {
        cartContainer.innerHTML = "<p>Cart is empty</p>";
    }

    document.getElementById('cart-ui').style.display = "flex";
}