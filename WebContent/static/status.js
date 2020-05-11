
//Variables
let idClient = localStorage.getItem('id');
let nOnt = localStorage.getItem('nOnt');

let widocoR = document.querySelector('span#widoco');
let ar2dtoolR = document.querySelector('span#ar2dtool');
let astreaR = document.querySelector('span#astrea');
let themisR = document.querySelector('span#themis');
let oopsR = document.querySelector('span#oops');
let radios = [widocoR, ar2dtoolR, astreaR, themisR, oopsR];

let h3 = document.querySelector('h3');

let downloadButton = document.getElementById("download");

let configuration = JSON.parse(localStorage.getItem('configuration'));


let index = 0;
let n = configuration.services.length;

//Algorithm to call the backend sequentially depending on the services requested by the client
function generate() {

        radios.forEach(x => {   x.textContent = "radio_button_unchecked";
    });

        const element = configuration.services[index];

        let originalName = element["originalName"];
        let nOntology = element["nOntology"];

        h3.textContent = "Generating elements for " + originalName;

        index++;

        if (element["widoco"]) {
            widoco(originalName, nOntology, element);
        }

        else if (element["ar2dtool"]) {
            ar2dtool(originalName, nOntology, element);

        }

        else if (element["oops"]) {
            oops(originalName, nOntology, element);

        }

        else if (element["themis"]) {
            themis(originalName, nOntology, element);

        }

        else if (element["astrea"]) {
            astrea(originalName, nOntology, element);

        }

}

function widoco(originalName, nOntology, element) {
    let next = function () { };

    if(index<n)
        next = generate;
    else
        next = zip;

    if (element["ar2dtool"]) {
        next = ar2dtool;
    }
    else if (element["oops"]) {
        next = oops;
    }
    else if (element["themis"]) {
        next = themis;
    }
    else if (element["astrea"]) {
        next = astrea;
    }

    backendRequest('widoco', widocoR, originalName, nOntology, next, element);

}

function ar2dtool(originalName, nOntology, element) {
    let next = function () { };

    if(index<n)
        next = generate;
    else
        next = zip;
    
    if (element["oops"]) {
        next = oops;
    }
    else if (element["themis"]) {
        next = themis;
    }
    else if (element["astrea"]) {
        next = astrea;
    }

    backendRequest('ar2dtool', ar2dtoolR, originalName, nOntology, next, element);
}

function oops(originalName, nOntology, element) {
    let next = function () { };

    if(index<n)
        next = generate;
    else
        next = zip;
    
     if (element["themis"]) {
            next = themis;
        }
    else if (element["astrea"]) {
        next = astrea;
    }

    backendRequest('oops', oopsR, originalName, nOntology, next, element);

}

function themis(originalName, nOntology, element) {
    let next = function () { };

    if(index<n)
        next = generate;
    else
        next = zip;

    if (element["astrea"]) {
        next = astrea;
    }

    backendRequest('themis', themisR, originalName, nOntology, next, element);

}

function astrea(originalName, nOntology, element) {
    let next = function () { };

    if(index<n)
        next = generate;
    else
        next = zip;

    backendRequest('astrea', astreaR, originalName, nOntology, next, element);
}

//Request to OnToology backend
function backendRequest(service, radio, originalName, nOntology, next, element) {
    let f = new Date();
    console.log(f.getDate() + "/" + (f.getMonth() + 1) + "/" + f.getFullYear() + " " + f.getHours() +":" + f.getMinutes() + "  Se acaba de comenzar a generar: " + service + " " + originalName);

    var xhr = new XMLHttpRequest();
    let url = "/" + service + '/' + idClient + "/" + nOntology + "/" + originalName;

    xhr.open('GET', url, true);

    xhr.addEventListener('readystatechange', function (e) {
        if (e.target.readyState == 4 && e.target.status == 200) {

            radio.textContent = "radio_button_checked";
            

            next(originalName, nOntology, element);
        } else if (e.target.readyState == 4 && e.target.status != 200) {
            // Error. Inform the user
            console.log("se lió en " + service);
            next(originalName, nOntology, element);
        }
    });

    xhr.send();
}

//Request to backend to Zip users files
function zip() {
    let f = new Date();

    console.log(f.getDate() + "/" + (f.getMonth() + 1) + "/" + f.getFullYear() + " " + f.getHours() +":" + f.getMinutes() + "  Se acaba de comenzar a generar: zip");

    var xhr = new XMLHttpRequest();
    let url = "/" + 'zip/' + idClient;

    xhr.open('GET', url, true);

    xhr.addEventListener('readystatechange', function (e) {
        if (xhr.readyState == 4 && xhr.status == 200) {
            document.querySelector('main').removeChild(document.querySelector('main img'));

            h3.textContent = "Your download is ready";

            document.querySelector('button').removeAttribute("disabled");
            document.querySelector('button').classList.add("acti");

            downloadButton.addEventListener("click", () => window.location.replace("/download/" + idClient));
        }
        else if (xhr.readyState == 4 && xhr.status != 200) {
            console.log("se lió");
            cntn = false;
        }
    })

    xhr.send();
}

generate();