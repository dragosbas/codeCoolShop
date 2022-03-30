const addProductsButtons = document.querySelectorAll(".card-btn-add");
const removeProductsButtons = document.querySelectorAll(".card-btn-remove");


addProductsButtons.forEach(btn => {
    btn.addEventListener('click', addToCart);
})

removeProductsButtons.forEach(btn => {
    btn.addEventListener('click', removeFromCart);
})


function addToCart(event) {
    let id = event.target.closest(".card-btn-add").dataset.id
    let textValue = parseInt(event.target.closest(".card-btn-add").previousElementSibling.innerText)
    event.target.closest(".card-btn-add").previousElementSibling.innerText = textValue + 1;
    changeCartNumberPlus()
    addProduct(id)
}

function removeFromCart(event) {
    let id = event.target.closest(".card-btn-remove").dataset.id
    let textValue = parseInt(event.target.closest(".card-btn-remove").nextElementSibling.innerText) - 1
    event.target.closest(".card-btn-remove").nextElementSibling.innerText = textValue;
    if(textValue === 0){
        event.target.closest(".card").remove();
    }
    changeCartNumberMinus()
    removeProduct(id)
}

const addProduct = (id) => {
    const itemId = {
        itemId:  id
    }
    sendAddRequest("/api/cart", itemId);
}

function removeProduct(id){
    const itemId = {
        itemId:  id
    }
    sendDeleteRequest("/api/cart", itemId);
}

const sendAddRequest = async (url, data) => {
    const request = await fetch(url, {
        method : "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
}

const sendDeleteRequest = async (url, data) => {
    const response = await fetch(url, {
        method:'DELETE',
            headers: {
            'Content-Type':'application/json',
        },
        body: JSON.stringify(data)
    })
}

const changeCartNumberPlus = () => {
    let productsNumber = localStorage.getItem("cartNumber");

    if (productsNumber) {
        localStorage.setItem("cartNumber", parseInt(productsNumber) + 1);
    } else {
        localStorage.setItem("cartNumber", 1);
    }
    cartItems.innerText = localStorage.getItem("cartNumber");
}

const changeCartNumberMinus = () => {
    let productsNumber = localStorage.getItem("cartNumber");

    if (productsNumber - 1 > 0) {
        localStorage.setItem("cartNumber", parseInt(productsNumber) - 1);
    } else {
        localStorage.removeItem("cartNumber");
    }
    cartItems.innerText = localStorage.getItem("cartNumber");
}

const onLoadCartItems = () => {
    cartItems.innerText = localStorage.getItem("cartNumber");
}

onLoadCartItems();