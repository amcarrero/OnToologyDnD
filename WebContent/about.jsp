
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OnToology Drag&Drop</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
    rel="stylesheet">
    <link rel="stylesheet" href="static/main.css">
    <link href="https://fonts.googleapis.com/css2?family=Spartan:wght@300;400&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon">
    
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
        <h2 class="pretty2">What does OnToology D&D generate</h2>
        <ul>
            <li class = "pretty">
                Generate HTML documentation and .htaccess using Widoco.
            </li>
            <li class = "pretty">
                Generate class and taxonomy diagrams using AR2DTool.
            </li>
            <li class = "pretty">
                Generate evaluation report using Oops! showing pitfalls in a nice organized HTML.
            </li>
            <li class = "pretty">
                Run tests using Themis.
            </li>
            <li class = "pretty">
                Generate SHACL shapes using Astrea.
            </li>
        </ul>

       <h2 class="pretty2">
        Permission and Security
       </h2>
       <ul>
            <li class="pretty">
                OnToology drag and drop only uses your ontologies to generate the requested files; there is a file deletion routine to remove old ontologies, so we will only have your files the time needed to serve them.
            </li>
        </ul>

       <h2 class="pretty2">
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
    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
      
        ga('create', 'UA-56720864-1', 'auto');
        ga('send', 'pageview');
      
      </script>
      
</body>
</html>