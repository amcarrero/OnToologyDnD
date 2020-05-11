
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OnToology Drag&Drop</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
    rel="stylesheet">
    <link rel="stylesheet" href="static/main.css">
    <link href="https://fonts.googleapis.com/css2?family=Spartan:wght@300;400&display=swap" rel="stylesheet">
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/node-uuid/1.4.7/uuid.min.js">
    </script>

</head>
<body>
    <header>
       <a href="/app" ><img src="static/images/logoprop.png" alt="" width="250" height="50"></a> 
       <span class="material-icons">
        menu
        </span>
        <nav>
            <span class="material-icons">
                menu_open
                </span>        
            <ul>
                <li> <a href="/app">App</a></li>
                <li> <a href="/stepbystep"> Step by Step</a></li>
                <li> <a href="/about">About</a> </li>
                <li> <a href="/faq">FAQ</a></li>
            </ul>
        </nav>
    </header>

    <main>        
    <div id="desktop">
        <h2>1) Select the services that you want to use</h2>
        <div id="services">
            <img id="widoco" src="static/images/logo2.png" title="Widoco generate documentation about your ontologies">
            <img id="ar2dtool" src="static/images/ar2dtool.png" title="AR2DTOOL generate class and taxonomy diagrams">
            <img id="oops" src="static/images/logoWhite65.png" title="Oops! reports the pitfalls of the ontologies">
            <img id="themis" src="static/images/themis.png" title="Themis execute tests to validate your ontology">
            <img id="astrea" src="static/images/astrea-500px.png" title="Astrea generate SHACL shapes">
        </div>

        <h2>2) Upload your ontologies</h2>

        <div id="dragndrop">
            <form class="my-form">
              <p>DROP YOUR ONTOLOGY HERE</p>
              <img id="dndImg" src="static/images/logo-gris.png">
              <input type="file" id="fileElem" name="ontology" multiple accept="*.owl" onchange="handleFiles(this.files)">
             <!--<label class="button" for="fileElem">Select some files</label> --> 
            </form>
          </div>

          <div id="ontologies"></div>

          <button id="send" disabled>START!</button>
    </div>
    <div id="mobile">
        <h2>OnToology D&D</h2>
        <p>In order to use OnToology Drag and Drop, please use a computer, or, if you have your ontology in a GitHub repository, go to <a href="http://ontoology.linkeddata.es/">OnToology</a></p>
    </div>


    </main>


    <footer>
        Ontology Engineering Group | Powered by Widoco, AR2DTOOL, Astrea, Themis and OOPS!
    </footer>

    <script src="static/main.js"></script>
    <script src="static/menu.js"></script>

</body>
</html>