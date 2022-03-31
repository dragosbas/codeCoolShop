

export const productCard = (product) => {
    return `<div class="col col-sm-12 col-md-6 col-lg-4">
            <div class="card">
            <picture class="product-img-holder">
                <img class="product-img img-fluid my-auto" src='${product.img}' alt="">
            </picture>
                <div class="card-header">
                    <h4 class="card-title">${product.name}</h4>
                    <p class="card-text card-description">${product.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text card-price">
                        <p class="lead">${product.price}</p>
                    </div>
                    <div class="card-btn-add" data-id="${product.id}">
                        <a class="btn btn-success add">Add to cart</a>
                    </div>
                </div>
            </div>
        </div>`
}