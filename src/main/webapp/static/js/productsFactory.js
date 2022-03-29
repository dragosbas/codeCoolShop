

export const productCard = (product) => {
    return `<div class="col col-sm-12 col-md-6 col-lg-4">
            <div class="card">
                <img class="" src='/static/img/product_${product.id}.jpg' alt="">
                <div class="card-header">
                    <h4 class="card-title">${product.name}</h4>
                    <p class="card-text">${product.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${product.price}</p>
                    </div>
                    <div class="card-text">
                        <a class="btn btn-success add">Add to cart</a>
                    </div>
                </div>
            </div>
        </div>`
}