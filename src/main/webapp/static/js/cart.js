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
    addProduct(id)
}

function removeFromCart(event) {
    let id = event.target.closest(".card-btn-remove").dataset.id
    let textValue = parseInt(event.target.closest(".card-btn-remove").nextElementSibling.innerText)
    event.target.closest(".card-btn-remove").nextElementSibling.innerText = textValue - 1;
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