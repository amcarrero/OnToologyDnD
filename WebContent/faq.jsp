<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OnToology Drag&Drop</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="/static/main.css">
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

        <ul>
            <li>
                <h2>What is OnToology?</h2>
                <p class = "pretty2">
                    A system to automate part of the collaborative ontology development process. Given an ontology
                    file, OnToology will produce diagrams, a complete documentation and validation based on
                    common pitfalls.
                </p>
            </li>
            <li>
                <h2>How to use it?</h2>
                <p class = "pretty2">
                   Please, see the <a href="/stepbystep">Step by Step</a> page for more info.
                </p>
            </li>
            <li>
                <h2>What is the supported files names?</h2>
                <p class = "pretty2">
                    English alphabets (caps and small), underscores, dashes, and dots. Other alphabets and characters are not
                    supported.
                </p>
            </li>
            <li>
                <h2>OnToology did not use Github as a way to collect the ontologies and publish the result?</h2>
                <p class = "pretty2">
                    Yes, and you can still use it at <a href="http://ontoology.linkeddata.es/">OnToology</a>.
                </p>
            </li>
        </ul>

    </main>


    <footer>
        Ontology Engineering Group | Powered by Widoco, AR2DTOOL, Astrea, Themis and OOPS!
    </footer>

    <script src="/static/menu.js"></script>
</body>

</html>