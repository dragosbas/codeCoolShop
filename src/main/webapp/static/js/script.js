const suppliersOptions = document.querySelectorAll("option[data-supplier]");
const categoryOptions = document.querySelectorAll("option[data-category]");
console.log(suppliersOptions);
console.log(categoryOptions);



const requestData = async (url) => {
    // buttons.innerHTML =
    //     "<button id='previous' type=\"button\">Previous</button>" +
    //     "<button id='next' type=\"button\">Next</button>";
    const request = await fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
        // body: JSON.stringify(page),
    });

    if (request.ok) {

        const response =  await request.json();
        // console.log(response.length);
        // lastPage = !response.length;
        return response;
    }
}


const getProducts = () => {
    // currentApiRequest = 'topNews';
    requestData("/api/products/supplier?id=1").then(r => {
        // if (!lastPage){
        //     container.innerHTML = '';
        // }
        // r.map(card => {
        //     container.innerHTML +=
        //         `<div class="card">
        //                 <a href="${card.url}" target="_blank">${card.title}</a>
        //                 <p>${card.time_ago}</p>
        //                 <p>${card.author}</p>
        //             </div>`
        // })
        console.log(r);
    })
}
getProducts();