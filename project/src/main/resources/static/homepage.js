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