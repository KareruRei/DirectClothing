let cart = [];
let cartCount = 0;


function openPopup(itemID, image, title, price) {
    document.getElementById('popup-img').src = image;
    document.getElementById('popup-title').innerText = title;
    document.getElementById('popup-price').innerText = price;
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

function openCart() {
    let cartContainer = document.getElementById('cart-items');
    cartContainer.innerHTML = "";

    if (cart.length === 0) {
        cartContainer.innerHTML = "<p>Cart is empty</p>";
    } else {
        cart.forEach((item, index) => {
            cartContainer.innerHTML += `
                <div class="cart-item">
                    <img src="${item.image}" alt="${item.title}">
                    <div>
                        <p>${item.title}</p>
                        <p>${item.price}</p>
                        <button onclick="removeFromCart(${index})">Remove</button>
                    </div>
                </div>
            `;
        });
    }

    document.getElementById('cart-ui').style.display = "flex";
}

function closeCart() {
    document.getElementById('cart-ui').style.display = "none";
}

function removeFromCart(index) {
    cart.splice(index, 1);
    cartCount--;
    document.getElementById('cart-count').innerText = cartCount;

    if (cartCount === 0) {
        document.getElementById('cart-count').style.display = "none";
    }

    openCart();
}