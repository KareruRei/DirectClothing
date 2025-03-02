function showTab(tabName) {
    let tabContents = document.getElementsByClassName('tab-content');
    for (let i = 0; i < tabContents.length; i++) {
        tabContents[i].classList.remove('active');
    }
    let tabButtons = document.getElementsByClassName('tab-button');
    for (let i = 0; i < tabButtons.length; i++) {
        tabButtons[i].classList.remove('active');
    }
    document.getElementById(tabName).classList.add('active');
    document.getElementById(tabName + "-button").classList.add('active');
}


function openPopup(itemID, image, title, price) {
    document.getElementById('popup-img').src = image;
    document.getElementById('popup-title').innerText = title;
    document.getElementById('popup-price').innerText = "PHP " + price;
    document.getElementById('popup').style.display = "flex";

    document.getElementById('add-to-cart').onclick = function() {addToCart(itemID);}
}

function closePopup() {
    document.getElementById('popup').style.display = "none";
}

// let lastKnownState = history.state;
// window.addEventListener('popstate', function(event) {
//     let currentState = history.state;
//     if (currentState < lastKnownState) {
//         this.fetch("/");
//     } else if (currentState > lastKnownState) {
//         this.fetch("/shopping-bag");
//     }
// });

window.onclick = function(event) {
    let popup = document.getElementById('popup');
    let cartUI = document.getElementById('cart-ui');
    if (event.target === popup) {
        closePopup();
    }
    if (event.target === cartUI) {
        closeCart();
    }
}

function removeFromCart(itemID) {
            
    fetch("/item-removed", {
        method: 'POST',
        headers: {'Content-Type': 'text/plain', 'Accept': 'application/json'},
        body: itemID
    })
        .then(response => {
            if (response.status === 500) throw new Error("Item does not exist! Failed to remove item from cart.");
            return response.json();
        })
        .then(cartUpdate => {

            var cartContainer = document.getElementById('cart-items');

            document.getElementById('cart-count').innerText = cartUpdate.cartSize;

            if (cartUpdate.cartSize === 0) {
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
        .catch(error => {alert(error);});
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