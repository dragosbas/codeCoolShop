const addProductsButtons = document.querySelectorAll(".btn-plus");
const removeProductsButtons = document.querySelectorAll(".btn-minus");


addProductsButtons.forEach(btn => {
    btn.addEventListener('click', addToCart);
})

removeProductsButtons.forEach(btn => {
    btn.addEventListener('click', removeFromCart);
})


function updatePrice(id, number) {
    const totalPrice = document.getElementById("total-price");
    const prodPrice = document.getElementById(`price-${id}`);

    let newTotal = parseFloat(totalPrice.innerText);
    newTotal += parseFloat(prodPrice.innerText) * number;

    totalPrice.innerText = newTotal.toFixed(2);
}

function addToCart(event) {
    let id = event.target.dataset.id
    console.log(id)
    let textValue = parseInt(event.target.closest(".btn-plus").previousElementSibling.innerText) + 1
    event.target.closest(".btn-plus").previousElementSibling.innerText = textValue;
    document.getElementById(`qty-${id}`).innerText = textValue;
    updatePrice(id, 1)
    changeCartNumberPlus()
    addProduct(id)
}

function removeFromCart(event) {
    let id = event.target.dataset.id
    let textValue = parseInt(event.target.closest(".btn-minus").nextElementSibling.innerText) - 1
    event.target.closest(".btn-minus").nextElementSibling.innerText = textValue;
    document.getElementById(`qty-${id}`).innerText = textValue;
    updatePrice(id, -1)

    if(textValue === 0){
        event.target.closest(".card").remove();
        document.querySelector(`li[data-id="${id}"]`).remove();
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