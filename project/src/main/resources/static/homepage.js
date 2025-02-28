function openPopup(image, title, price) {
    document.getElementById('popup-img').src = image;
    document.getElementById('popup-title').innerText = title;
    document.getElementById('popup-price').innerText = price;
    document.getElementById('popup').style.display = "flex";
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

function updateCart() {
    let cartContainer = document.getElementById('cart-items');
    cartContainer.innerHTML = "";

    if (cart.length === 0) {
        cartContainer.innerHTML = "<p>Cart is empty</p>";
    } else {

        fetch("/get-customercart", {method: 'POST'})
        .then(response => response.json())
        .then(jsonString => {
            const jsObj = JSON.parse(jsonString);
            const itemList = jsObj.items;
            
            Object.keys(itemList).forEach(key => {

                var itemImage = itemList[key].theProduct.imageLink;
                var itemTitle = itemList[key].theProduct.description;
                var itemPrice = itemList[key].discountedPrice;

                cartContainer.innerHTML += `
                    <div class="cart-item">
                        <img src="${itemImage}" alt="${itemTitle}">
                        <div>
                            <p>${itemTitle}</p>
                            <p>${itemPrice}</p>
                            <button onclick="removeFromCart()">Remove</button>
                        </div>
                    </div>
                `;
            })
        })
    }
}

function removeFromCart(itemID) {
            
    fetch("/item-removed", {
        method: 'POST',
        headers: {'Content-Type': 'text/plain',},
        body: itemID
    })
        .then(response => {
            if (response.status === 500) alert("Item does not exist! Failed to remove item from cart.");
            return response.json();
        })
        .then(cartSize => {
            if (response.ok) {
                document.getElementById('cart-count').innerText = cartSize;
            }
        })
        
    updateCart();
}

function closeCart() {
    document.getElementById('cart-ui').style.display = "none";
}

function openCart() {
    document.getElementById('cart-ui').style.display = "flex";
}