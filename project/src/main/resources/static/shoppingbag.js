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
    var paymentWindowTotal = document.getElementById("payment-window-total");

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
        paymentWindowTotal.textContent = "PHP " + cartUpdate.totalPrice;
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
    var paymentWindowTotal.textContent = document.getElementById("payment-window-total");
    
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
        paymentWindowTotal.textContent = "PHP " + cartUpdate.totalPrice;
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
            let paymentWindowTotal = document.getElementById("payment-window-total");
            paymentWindowTotal.textContent = "PHP " + cartUpdate.totalPrice;
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
    document.getElementById('payment-window').style.display = "block";
}

function closePaymentInput() {
    document.getElementById('payment-window').style.display = "none";
}
function showTab(tab) {
    document.querySelectorAll('.payment-form').forEach(form => form.classList.remove('active'));
    document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
    document.getElementById(tab + '-form').classList.add('active');
    document.querySelector(".tab[onclick=\"showTab('" + tab + "')\"]").classList.add('active');
}

document.addEventListener("DOMContentLoaded", function() {
    showTab('check'); // Ensure check tab is visible on load
});


const cForm = document.getElementById("c-form");
const ccForm = document.getElementById("cc-form");

cForm.addEventListener('submit', function(event) {
    event.preventDefault();

    const bank = document.getElementById("bank").value;
    const drawer = document.getElementById("drawer").value;
    const accountNum = document.getElementById("accountNum").value;
    const payee = document.getElementById("payee").value;
    const routingNum = document.getElementById("routingNum").value;
    const checkNum = document.getElementById("checkNum").value;

    if (!drawer || !accountNum || !payee || !routingNum || !checkNum) {
        alert("Please fill in all required fields!");
        return;
    }

    fetch("/place-order?payMethod=cp", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accepts': 'text/plain'
        },
        body: JSON.stringify({ bank: bank, drawer: drawer, accountNum: accountNum, payee: payee, routingNum: routingNum, checkNum: checkNum })
    })
    .then(response => {
        if (response.ok) return response.text();
    })
    .then(message => {
        alert(message);
    })
    .catch(error => {
        alert(error);
    })
});

ccForm.addEventListener('submit', function(event) {
    event.preventDefault();

    const creditCard = document.getElementById("creditCard").value;
    const creditCardNumber = document.getElementById("creditCardNumber").value;
    const cvv = document.getElementById("cvv").value;
    const cardHolderName = document.getElementById("cardHolderName").value;


    if (!creditCardNumber || !cvv || !cardHolderName || !creditCard) {
        alert("Please fill in all required fields!");
        return;
    }

    fetch("/place-order?payMethod=ccp", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accepts': 'text/plain'
        },
        body: JSON.stringify({ creditCard: creditCard, creditCardNumber: creditCardNumber, cvv: cvv, cardHolderName: cardHolderName })
    })
    .then(response => {
        if (response.ok) return response.text();
    })
    .then(message => {
        alert(message);
    })
    .catch(error => {
        alert(error);
    })
});