
window.onload = function() {
    let items = document.getElementsByClassName("card");

    for (item of items) {
        
    }
};

// let productNum = 1;
// let isHolding = false;
// let intervalId;

// function addItem(desc, discount, price) {
//     var card = document.createElement("div");
//     card.className = "card";
//     card.id = "item"+productNum;

//     var productDiv = document.createElement("div");
//     var qtyDiv = document.createElement("div");
//     var priceDiv = document.createElement("div");

//     /* Product Cell */
//     var productImg = document.createElement("div");
//     var image = document.createElement("img");
//     image.src = "https://skoop.com.ph/cdn/shop/files/skoop-basiks-product_shots-crew_neck-storm-front.jpg?v=1702031067";
//     productImg.appendChild(image);

//     var productTxt = document.createElement("div");
//     var txt = document.createElement("p");
//     txt.textContent = "Blazing Chic Collection 2025";
//     productTxt.appendChild(txt);
//     var catalog = document.createElement("p");
//     catalog.textContent = desc;
//     productTxt.appendChild(catalog);

//     var removeButton = document.createElement("button");
//     removeButton.textContent = "Remove";
//     removeButton.id = "removeItem"+productNum;
//     productTxt.appendChild(removeButton);

//     productDiv.appendChild(productImg);
//     productDiv.appendChild(productTxt);

//     /* Quantity Cell */
//     var subButton = document.createElement("button");
//     var addButton = document.createElement("button");
//     var quantity = document.createElement("input");

//     subButton.textContent = "-";
//     subButton.id = "subButton"+productNum;
//     addButton.textContent = "+";
//     addButton.id = "addButton"+productNum;

//     quantity.type = "number";
//     quantity.inputMode = "numeric";
//     quantity.id = "itemQuantity"+productNum;
//     quantity.value = "1";

//     qtyDiv.appendChild(subButton);
//     qtyDiv.appendChild(quantity);
//     qtyDiv.appendChild(addButton);
    
//     /* Subtotal Cell */
//     var priceSpan = document.createElement("p");
//     priceSpan.textContent = "Php. " + price;
//     priceSpan.id = "productPrice"+productNum;
//     priceDiv.appendChild(priceSpan);
    
//     if (discount != 0) {
//         priceDiv.className = "strike-through";

//         var discSpan = document.createElement("p");
//         discSpan.textContent = "Php. " + price * ((100.0 - discount)/100.0);
//         priceDiv.appendChild(discSpan);
//     }


//     card.appendChild(productDiv);
//     card.appendChild(qtyDiv);
//     card.appendChild(priceDiv);
//     document.getElementById("cart-container").appendChild(card);

//     document.getElementById(subButton.id).addEventListener("click", function() {subQty(quantity.id);});
//     document.getElementById(addButton.id).addEventListener("click", function() {addQty(quantity.id);});
//     document.getElementById(removeButton.id).addEventListener("click", function() {toggleItemRemoval(card.id);});

//     productNum++;
// }

// function toggleItemRemoval(cardId) {
//     var item = document.getElementById(cardId);

//     item.classList.toggle("shrink");

//     item.addEventListener("transitionend", function() {document.getElementById(cardId).remove();});
// }
// function createGridTransition(prevGridRow) {

// }

// function addQty(numId) {
//     var qtyNum = document.getElementById(numId);
//     qtyNum.value = Number(qtyNum.value) + 1;
// }
// function subQty(numId) {
//     var qtyNum = document.getElementById(numId);
//     var value = Number(qtyNum.value);

//     if (value > 1) {
//         qtyNum.value = value - 1;
//     }


// }