const dbSwitchButton = document.getElementById("db-switch");

dbSwitchButton.addEventListener('click',  e => {

    let data = {}

    fetch("/api/dbswitch", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json)
        .then(response => {
            console.log("posted");
        })


})

