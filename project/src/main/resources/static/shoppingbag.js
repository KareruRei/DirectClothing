
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

const miniCart = document.getElementById("cart-ui");
const miniCartButton = document.getElementById("mini-cart-button");

document.addEventListener('click', function(event) {
    if (window.getComputedStyle(miniCart).display === 'flex' && !miniCartButton.contains(event.target) && !miniCart.contains(event.target)) {
        closeCart();
    }
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

function inputQty(itemID, newQty) {
    var qtyNum = document.getElementById('quantity_id' + itemID);
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

        orderLineQty.textContent = "Qty. " + cartUpdate.newQty;
        orderLinePrice.textContent = "PHP " + cartUpdate.discountedPrice;

        total.textContent = "PHP " + cartUpdate.totalPrice;
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
    enableCardEventListeners();
}

function openCart() {
    var cartContainer = document.getElementById('cart-items');
    if (cartContainer.children.length === 0) {
        cartContainer.innerHTML = "<p>Cart is empty</p>";
    }

    document.getElementById('cart-ui').style.display = "flex";
    disableCardEventListeners();
}

function enableCardEventListeners() {
    const cards = document.querySelectorAll(".product");

    cards.forEach(card => {card.style.pointerEvents = 'auto';});
}

function disableCardEventListeners() {
    const cards = document.querySelectorAll(".product");

    cards.forEach(card => {card.style.pointerEvents = 'none';});
}


function openPaymentInput() {
    document.getElementById('payment-window').style.display = "flex";
}

function closePaymentInput() {
    document.getElementById('payment-window').style.display = "none";
}