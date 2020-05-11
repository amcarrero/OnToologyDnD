
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
        <h2>What does OnToology D&D generate</h2>
        <ul>
            <li class = "pretty">
                Generate documentation into HTML using Widoco.
            </li>
            <li class = "pretty">
                Generate class and taxonomy diagrams using AR2DTool.
            </li>
            <li class = "pretty">
                Generate evaluation report using OOPS! which is an evaluation of ontology showing pitfalls in a nice organized HTML.
            </li>
            <li class = "pretty">
                Run tests using Themis.
            </li>
            <li class = "pretty">
                Generate SHACL shapes using Astrea.
            </li>
        </ul>

       <h2>
        Permission and Security
       </h2> 
       <p class = "pretty">
        OnToology drag and drop only uses your ontologies to generate the requested files; there is a file deletion routine to remove old ontologies, so we will only have your files the time needed to serve them.
       </p>

       <h2>
        Limitations
       </h2>
       <ul>
           <li class = "pretty">OnToology only works with RDF/XML and TTL ontologies.</li>
           <li class = "pretty">Your ontologies must have a size less than 10MB.</li>
            <li class = "pretty">We are dependent on external services, so we cannot guarantee that all the options offered are always available.</li>
       </ul>


    </main>


    <footer>
        Ontology Engineering Group | Powered by Widoco, AR2DTOOL, Astrea, Themis and OOPS!
    </footer>

    <script src="static/menu.js"></script>
</body>
</html>