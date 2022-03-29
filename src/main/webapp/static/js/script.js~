import {productCard} from "./productsFactory.js";


const suppliersOption = document.querySelector("#suppliers");
const categoryOption = document.querySelector("#categories");
const productsContainer = document.querySelectorAll(".card-btn-add");
const cartItems = document.querySelector("#cartItems");

const addToCartButton = (e) => {
    changeCartNumber()
    sendProductId(e.target.closest(".card-btn-add").dataset.id);
}

console.log(productsContainer)

productsContainer.forEach(product => {
    product.addEventListener("click", addToCartButton)
});


const sendGetRequest = async (url) => {

    const request = await fetch(url);

    if (request.ok) {
        return await request.json();
    }
}

const sendPostRequest = async (url, data) => {
    const request = await fetch(url, {
        method : "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
}


const getProductsSupplier = (supplierId) => {
    const productContainer = document.querySelector("#products");
    productContainer.innerHTML = '';
    sendGetRequest(`/api/products/supplier?id=${supplierId}`).then(r => {
        r.forEach(item => productContainer.innerHTML += productCard(item));
        console.log(r);
    })
}



suppliersOption.addEventListener("change", () => getProductsSupplier(suppliersOption.value));

const changeCartNumber = () => {
    let productsNumber = localStorage.getItem("cartNumber");

    if (productsNumber) {
        localStorage.setItem("cartNumber", parseInt(productsNumber) + 1);
    } else {
        localStorage.setItem("cartNumber", 1);
    }
    cartItems.innerHTML = localStorage.getItem("cartNumber");
}


const onLoadCartItems = () => {
    cartItems.innerHTML = localStorage.getItem("cartNumber");
}

onLoadCartItems();

const sendProductId = (id) => {
    console.log(id);
    const itemId = {
        itemId:  id
    }
    sendPostRequest("/api/cart", itemId);
    // console.log(aLink.closest("span[data-id]"));
}

