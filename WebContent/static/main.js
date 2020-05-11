
//Services global configuration
let globalConfig = {
  astrea: true,
  oops: true,
  themis: true,
  ar2dtool: true,
  widoco: true,
}

let astreaButton = document.getElementById("astrea");
addEventToService("astrea", astreaButton);
let oopsButton = document.getElementById("oops");
addEventToService("oops", oopsButton);
let themisButton = document.getElementById("themis");
addEventToService("themis", themisButton);
let ar2dtoolButton = document.getElementById("ar2dtool");
addEventToService("ar2dtool", ar2dtoolButton);
let widocoButton = document.getElementById("widoco");
addEventToService("widoco", widocoButton);

function addEventToService(service, button){
  button.addEventListener("click", () => {
    if (globalConfig[service]) {
      globalConfig[service] = false;
      button.classList.add("dark");
    } else {
      globalConfig[service] = true;
      button.classList.remove("dark");
    }
  
  });
}

//Client id

localStorage.setItem('id', uuid.v4());
let idClient = localStorage.getItem('id');

//Drop area configuration, https://www.smashingmagazine.com/2018/01/drag-drop-file-uploader-vanilla-js/

let dropArea = document.getElementById('dragndrop');
let dragArea = document.getElementById('drop');
let imagednd = document.querySelector("#dragndrop img");
let textdnd = document.querySelector("#dragndrop p");


let events = ['dragenter', 'dragover', 'dragleave', 'drop'];

events.forEach(eventName => {
  dropArea.addEventListener(eventName, preventDefaults, false)
})

function preventDefaults(e) {
  e.preventDefault()
  e.stopPropagation()
}
function addEvents(events, element){
  events.forEach(eventName => element.addEventListener(eventName, () => {
    dropArea.classList.add('is-dragover');
    let textdnd = document.querySelector("#dragndrop p");
    textdnd.classList.remove("error");
    textdnd.textContent = "DROP IT!"
    let img = document.getElementById("dndImg");
    img.src = "static/images/logo-color.png"
  }));
}

addEvents( ['dragover', 'dragenter'], dragArea);
addEvents(events, imagednd);
addEvents(events, textdnd);

['dragleave', 'dragend', 'drop'].forEach(eventName => dropArea.addEventListener(eventName, () => {
  dropArea.classList.remove('is-dragover');
  let img = document.getElementById("dndImg");
  img.src = "static/images/logo-gris.png"
  let textdnd = document.querySelector("#dragndrop p");
  textdnd.textContent = "DROP YOUR ONTOLOGY HERE!"
}));

dropArea.addEventListener('drop', handleDrop, false)

function handleDrop(e) {
  let dt = e.dataTransfer
  let files = dt.files

  handleFiles(files)
}

function handleFiles(files) {
  arrayFiles = [...files];

  arrayFiles.forEach(uploadFile)
}

//Upload ontology

function uploadFile(file) {
  let img = document.getElementById('dndImg');
  img.src = "static/images/loading.gif"
  let url = '/sendOnToology'
  var xhr = new XMLHttpRequest()
  var formData = new FormData()
  xhr.open('POST', url, true)

  xhr.addEventListener('readystatechange', function (e) {
    if (xhr.readyState == 4 && xhr.status == 200) {
      // Done. Inform the user
      img.src = "static/images/logo-gris.png"
      previewFile(file);

      let textdnd = document.querySelector("#dragndrop p");
      textdnd.textContent = "YOU CAN DROP MORE FILES!"

      let sendButton = document.getElementById("send");
      sendButton.removeAttribute("disabled");
      sendButton.classList.add("acti");


    }
    else if (xhr.readyState == 4 && xhr.status != 200) {
      // Error. Inform the user
      let textdnd = document.querySelector("#dragndrop p");
      textdnd.textContent = "PLEASE, UPLOAD A VALID XML/RDF OR TTL ONTOLOGY";
      img.src = "static/images/logo-gris.png";
      textdnd.classList.add("error");

    }
  })

  formData.append('ontology', file);
  formData.append('id', idClient);
  xhr.send(formData)

}

//Preview ontology and configuration

let nOntology = 0;
let configuration = { "clientId": idClient };
let services = [];
configuration["services"] = services;

function previewFile(file) {
  let service = {
    "originalName": file.name,
    "nOntology": nOntology,
    "widoco": globalConfig.widoco,
    "ar2dtool": globalConfig.ar2dtool,
    "oops": globalConfig.oops,
    "themis": globalConfig.themis,
    "astrea": globalConfig.astrea,
  }

  services[services.length] = service;

  let title = file.name;
  let div = document.createElement('div');
  let p = document.createElement('p');
  p.innerText = title;
  let p1 = document.createElement('span');
  p1.innerText = "close";
  p1.className = "material-icons";
  p.style = "display:flex; justify-content:space-between;";
  p1.style = "    cursor: pointer;    ";
  p.appendChild(p1);
  div.appendChild(p);

  function createCheckbox(service, text){
    let label = document.createElement('label');
    let checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.name = nOntology;
    checkbox.id = service + nOntology;
    checkbox.checked = globalConfig[service];
    checkbox.addEventListener("click", () => services[checkbox.name][service] = checkbox.checked);
    label.setAttribute('for', service + nOntology);
    label.innerText = text;
    div.appendChild(checkbox);
    div.appendChild(label);  
  }

  createCheckbox("widoco", "Widoco");
  createCheckbox("ar2dtool", "AR2DTool");
  createCheckbox("oops", "Oops!");

  let labelThemis = document.createElement('label');
  let checkboxThemis = document.createElement('input');
  checkboxThemis.type = 'checkbox';
  checkboxThemis.name = nOntology;
  checkboxThemis.id = "themis" + nOntology;
  checkboxThemis.checked = globalConfig.themis;
  checkboxThemis.addEventListener("click", () => {
    services[checkboxThemis.name].themis = checkboxThemis.checked;
    if (services[checkboxThemis.name].themis) {
      dragndroptest.className="";
      div.classList.add("themis");
    }else{
      dragndroptest.className="hidden";
      div.classList.remove("themis");
    }
  });
  labelThemis.setAttribute('for', "themis" + nOntology);
  labelThemis.innerText = 'Themis';
  div.appendChild(checkboxThemis);
  div.appendChild(labelThemis);

  createCheckbox("astrea", "Astrea");

  //Drag n drop test Themis

  let dragndroptest = document.createElement("div");
  dragndroptest.id = "dragndroptest";
  dragndroptest.className = "dragndroptest";
  let ptest = document.createElement("p");
  ptest.innerText = "Drop your test here!";
  dragndroptest.appendChild(ptest);
  div.classList.add("themis");
  if(!globalConfig.themis){
    dragndroptest.className="hidden";
    div.classList.remove("themis");
  }

  events.forEach(eventName => {
    dragndroptest.addEventListener(eventName, preventDefaults, false)
  })

  dragndroptest.addEventListener('drop', handleDropTest, false)

function handleDropTest(e) {
  let dt = e.dataTransfer
  let files = dt.files
  
  handleFilesTest(files)
}

function handleFilesTest(files) {
  arrayFiles = [...files];

  for (let index = 0; index < arrayFiles.length; index++) {
    const file = arrayFiles[index];
    uploadFileTest(file, checkboxThemis.name, ptest);  
  }
  
}

['dragover', 'dragenter'].forEach(eventName => dragndroptest.addEventListener(eventName, () => {
  dragndroptest.classList.add('is-dragover');
  ptest.classList.remove("error");
}));

['dragleave', 'dragend', 'drop'].forEach(eventName => dragndroptest.addEventListener(eventName, () => {
  dragndroptest.classList.remove('is-dragover');
}));
  div.appendChild(dragndroptest);
  

  let added = document.getElementById('ontologies').appendChild(div);

  //remove ontology
  p1.addEventListener("click", () => {
    services = services.filter( (s) => checkboxThemis.name != s.nOntology);
    configuration.services = services;
    document.getElementById('ontologies').removeChild(added);
    if (services.length == 0) {
      let sendButton = document.getElementById("send");
      sendButton.setAttribute("disabled", true);
      sendButton.classList.remove("acti");
    }
  }
  );


  nOntology++;

}


//Upload Test
function uploadFileTest(file, nOnt, p) {
  let url =  '/sendTest'
  var xhr = new XMLHttpRequest()
  var formData = new FormData()
  xhr.open('POST', url, true)

  xhr.addEventListener('readystatechange', function (e) {
    if (xhr.readyState == 4 && xhr.status == 200) {
      // Done. Inform the user
      p.innerText = file.name + "   has been uploaded, drop another test to replace it";

    }
    else if (xhr.readyState == 4 && xhr.status != 200) {
      // Error. Inform the user
      p.innerText = "Please, upload a valid TTL test"
      p.classList.add("error");
    }
  })

  formData.append('test', file);
  formData.append('id', idClient);
  formData.append('nOnt', nOnt);
  xhr.send(formData)

}

//Send Configuration

function sendConfiguration() {
  window.location.replace("/status");
  localStorage.setItem('configuration', JSON.stringify(configuration));
  localStorage.setItem('nOnt', configuration["services"].length);
}

let sendButton = document.getElementById("send");
sendButton.addEventListener("click", () => sendConfiguration());

