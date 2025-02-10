function showSidebar() {
    var x = document.getElementById('sidebar')
    if(x.className == "sidebar"){
       x.className += " show";
    } else{
       x.className = "sidebar";
    }
 }


const products = [
    { id: 1, name: 'Product 1', price: 29.99 },
    { id: 2, name: 'Product 2', price: 39.99 },
    { id: 3, name: 'Product 3', price: 49.99 },
    { id: 4, name: 'Product 4', price: 59.99 }
];
