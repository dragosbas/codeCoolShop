import {productCard} from "./productsFactory.js";

// const suppliersOptions = document.querySelectorAll("option[data-supplier]");
// const categoryOptions = document.querySelectorAll("option[data-category]");
// console.log(suppliersOptions);
// console.log(categoryOptions);
const suppliersOption = document.querySelector("#suppliers");
const categoryOption = document.querySelector("#categories");


let supplierId = 1;


const requestData = async (url) => {

    const request = await fetch(url);

    if (request.ok) {
        return await request.json();
    }
}


const getProductsSupplier = () => {
    const productContainer = document.querySelector("#products");
    productContainer.innerHTML = '';
    requestData(`/api/products/supplier?id=${supplierId}`).then(r => {

        r.forEach(item => productContainer.innerHTML += productCard(item));
        console.log(r);
    })
}

// supplierId = supplierOption.dataset.supplier)
// suppliersOptions.forEach(supplierOption => supplierOption.addEventListener('click', console.log(supplierOption)));
// suppliersOptions.forEach(supplierOption => supplierOption.addEventListener('click', getProductsSupplier));

suppliersOption.addEventListener("change", () => supplierId =  suppliersOption.value);
suppliersOption.addEventListener("change", getProductsSupplier);

