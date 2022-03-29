import {productCard} from "./productsFactory.js";


const suppliersOption = document.querySelector("#suppliers");
const categoryOption = document.querySelector("#categories");
const productsContainer = document.querySelector("#products");
const cartItems = document.querySelector("#cartItems");

const addToCartButton = (e) => {
    if (e.target.tagName === "A" && e.target.classList.contains("add")) {
        console.log(e.target);
        changeCartNumber()
    }
}

productsContainer.addEventListener("click", addToCartButton);





const requestData = async (url) => {

    const request = await fetch(url);

    if (request.ok) {
        return await request.json();
    }
}


const getProductsSupplier = (supplierId) => {
    const productContainer = document.querySelector("#products");
    productContainer.innerHTML = '';
    requestData(`/api/products/supplier?id=${supplierId}`).then(r => {
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

