
//Variables
let idClient = localStorage.getItem('id');
let nOnt = localStorage.getItem('nOnt');

let widocoR = document.querySelector('span#widoco');
let astreaR = document.querySelector('span#astrea');
let themisR = document.querySelector('span#themis');
let oopsR = document.querySelector('span#oops');
let radios = [widocoR, astreaR, themisR, oopsR];

let h3 = document.querySelector('h3');

let console = document.querySelector("#console");

let downloadButton = document.getElementById("download");

let configuration = JSON.parse(localStorage.getItem('configuration'));

if(configuration==null || configuration.services.length == 0){
    window.location.href = "/";
}


let index = 0;
let n = configuration.services.length;

//Algorithm to call the backend sequentially depending on the services requested by the client
function generate() {

        radios.forEach(x => {   x.textContent = "radio_button_unchecked";
        x.classList.remove("red");
        x.classList.remove("green");
    });

        const element = configuration.services[index];

        let originalName = element["originalName"];
        let nOntology = element["nOntology"];

        h3.textContent = "Generating elements for " + originalName;

        index++;

        if (element["widoco"]) {
            widoco(originalName, nOntology, element);
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

    if (element["oops"]) {
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
    let t = f.getHours() +":" + f.getMinutes() +":"+ f.getSeconds() + "  The generation of " + service + " elements of " + originalName + " has started";
    console.textContent = console.textContent + t + "\n";
    console.scrollTop = console.scrollHeight;

    var xhr = new XMLHttpRequest();
    xhr.timeout = 120000;
    if(service == "widoco")
        xhr.timeout = 180000;

    let url = "/" + service + '/' + idClient + "/" + nOntology + "/" + originalName;

    xhr.open('GET', url, true);

    xhr.addEventListener('readystatechange', function (e) {
        if (e.target.readyState == 4 && e.target.status == 200) {
            f = new Date();
            let t = f.getHours() +":" + f.getMinutes() +":"+ f.getSeconds() + "  The generation of " + service + " elements of " + originalName + " has finished";
            console.textContent = console.textContent + t + "\n";
            console.scrollTop = console.scrollHeight

            radio.textContent = "radio_button_checked";
            radio.classList.add("green");
            
            next(originalName, nOntology, element);
        } else if (e.target.readyState == 4 && e.target.status != 200) {
            // Error. Inform the user
            f = new Date();
            let t = f.getHours() +":" + f.getMinutes() +":"+ f.getSeconds() + "  The generation of " + service + " elements of " + originalName + " has encountered a problem, the " + service + " files will not be avaliable";
            console.textContent = console.textContent + t + "\n";
            console.scrollTop = console.scrollHeight;
            radio.classList.add("red");

            next(originalName, nOntology, element);
        }
    });

    xhr.ontimeout = function (e) {
        f = new Date();
        let t = f.getHours() +":" + f.getMinutes() +":"+ f.getSeconds() + " PROBLEM: The server endpoint of " + service + " has taken too long to respond, it seems that we are a little saturated, try another time";
        console.textContent = console.textContent + t + "\n";
        console.scrollTop = console.scrollHeight
  };

    xhr.send();
}

//Request to backend to Zip users files
function zip() {
    let f = new Date();
    let t = f.getHours() +":" + f.getMinutes() +":"+ f.getSeconds() + "  The generation of the zip has started";
    console.textContent = console.textContent + t + "\n";
    console.scrollTop = console.scrollHeight


    var xhr = new XMLHttpRequest();
    let url = "/" + 'zip/' + idClient;
    xhr.timeout = 120000;
    xhr.open('GET', url, true);

    xhr.addEventListener('readystatechange', function (e) {
        if (xhr.readyState == 4 && xhr.status == 200) {
            document.querySelector('main div#flexContainer div').removeChild(document.querySelector('main div#flexContainer div img'));

            h3.textContent = "Your download is ready";
            f = new Date();
            let t = f.getHours() +":" + f.getMinutes() +":"+ f.getSeconds() + "  Zip ready, your download will start automatically, if it does not start, you can click on the download button below!";
            console.textContent = console.textContent + t + "\n";
            console.scrollTop = console.scrollHeight
        
            document.querySelector('button').removeAttribute("disabled");
            document.querySelector('button').classList.add("acti");

            downloadButton.addEventListener("click", () => window.location.replace("/download/" + idClient));
            window.location.replace("/download/" + idClient);
        }
        else if (xhr.readyState == 4 && xhr.status != 200) {
            f = new Date();
            let t = f.getHours() +":" + f.getMinutes() +":"+ f.getSeconds() + "  The generation of the zip has encountered a problem, the download will not be avaliable";
            console.textContent = console.textContent + t + "\n";
            console.scrollTop = console.scrollHeight;

            document.querySelector('main div#flexContainer div').removeChild(document.querySelector('main div#flexContainer div img'));
            h3.textContent = "Please try again later";

        
            cntn = false;
        }
    })

    xhr.ontimeout = function (e) {
        f = new Date();
        let t = f.getHours() +":" + f.getMinutes() +":"+ f.getSeconds() + " The server has taken too long to respond, it seems that we are a little saturated, try another time";
        console.textContent = console.textContent + t + "\n";
        console.scrollTop = console.scrollHeight
  };

    xhr.send();
}

console.textContent = console.textContent + "*************** OnToology Drag and Drop **************" + "\n";
console.textContent = console.textContent + "****************** Selected services *****************" + "\n" + "\n";
                                            
for (let index = 0; index < configuration.services.length; index++) {
    const element = configuration.services[index];
    console.textContent = console.textContent + element.nOntology + " - " + element.originalName + ": ";
    let ser = ["widoco", "oops", "themis", "astrea"];
    ser.forEach((e) => {
        if(element[e])
            console.textContent = console.textContent + e + " ";
    });
    console.textContent = console.textContent + "\n";
    console.scrollTop = console.scrollHeight;
}
console.textContent = console.textContent + "\n" + "******************************************************" + "\n";
console.scrollTop = console.scrollHeight;

generate();

